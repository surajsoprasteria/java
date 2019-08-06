/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.integration.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.vo.WorkSearchResultVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.shared.integration.utils.DozerHelper;
import org.wipo.connect.shared.integration.utils.WSHelper;
import org.wipo.connect.work.request.IRWorkElaborationRequest;
import org.wipo.connect.work.request.InternationallyRegisterRequest;
import org.wipo.connect.work.request.WorkDetailRequest;
import org.wipo.connect.work.request.WorkListRequest;
import org.wipo.connect.work.request.WorkListRequest.WorkRequest;
import org.wipo.connect.work.response.IRWorkElaborationResponse;
import org.wipo.connect.work.response.InternationallyRegisterResponse;
import org.wipo.connect.work.response.WorkDetailResponse;
import org.wipo.connect.work.response.WorkListResponse;
import org.wipo.connect.work.workservice.WorkServicePort;

/**
 * The Class WorkServicePortImpl.
 *
 * @author minervini
 */
@WebService(serviceName = "WorkService", endpointInterface = "org.wipo.connect.work.workservice.WorkServicePort", targetNamespace = "http://workService.work.connect.wipo.org")
public class WorkServicePortImpl implements WorkServicePort {

    /** The logger. */
    private static Logger LOGGER = WipoLoggerFactory.getLogger(WorkServicePortImpl.class);

    /** The work business. */
    @Autowired
    private WorkBusiness workBusiness;

    @Autowired
    private CommonBusiness commonBusiness;

    /** The dozer helper. */
    @Autowired
    private DozerHelper dozerHelper;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public WorkListResponse getWorkList(WorkListRequest mainRequest) {
	WorkListResponse response = new WorkListResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    String code = reqContext.getClientId();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    WorkRequest request = mainRequest.getWorkRequest();
	    WorkSearchVO searchVO = new WorkSearchVO();

	    searchVO = dozerHelper.workRequestToSearchVO(request);
	    searchVO.setCode(code);
	    searchVO.setForceValidStatus(true);
	    WorkSearchResultVO resultVO = this.workBusiness.findWorkForWs(searchVO);

	    // if (BooleanUtils.isTrue(resultVO.getHasTooManyResults())) {
	    // response.setError(WSHelper.generateTooManyResultsErrorResponse());
	    // } else
	    if (BooleanUtils.isTrue(request.getLoadAllFieldFromShared())) {
		this.dozerHelper.toWorkDetailResponse(resultVO.getData(), response.getWorkDetailResponse());
	    } else {
		this.dozerHelper.toWorkResponse(resultVO.getData(), response.getWorkResponse());
	    }

	    response.setPaginationDraw(resultVO.getDraw());
	    response.setPaginationRecordsTotal(resultVO.getRecordsTotal());

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}

	return response;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public WorkDetailResponse getWorkDetail(WorkDetailRequest mainRequest) {
	WorkDetailResponse response = new WorkDetailResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    String code = reqContext.getClientId();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", code, reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    WorkDetailRequest.WorkRequest workDetailRequest = mainRequest.getWorkRequest();
	    WorkDetailResponse.WorkResponse workResponse = new WorkDetailResponse.WorkResponse();
	    response.setWorkResponse(workResponse);

	    Work work = null;
	    if (StringUtils.isNotEmpty(workDetailRequest.getMainId())) {
		work = this.workBusiness.findByMainId(workDetailRequest.getMainId());
		work = this.workBusiness.findById(work.getId(), code);
	    } else {
		work = this.workBusiness.findById(workDetailRequest.getIdWork(), code);
	    }

	    this.dozerHelper.toWorkResponse(work, workResponse);

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}

	return response;
    }

    @ExecutionTimeTrackable
    @Loggable(level = "TRACE")
    @Override
    public InternationallyRegisterResponse registerInternationally(InternationallyRegisterRequest mainRequest) {
	InternationallyRegisterResponse response = new InternationallyRegisterResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    String taskCode = mainRequest.getTaskCode();
	    List<InternationallyRegisterRequest.TaskItemRequest> taskItemRequestList = mainRequest.getTaskItemRequest();
	    List<WorkTaskItem> workTaskItemList = new ArrayList<>();
	    InternationallyRegisterResponse.WorkResponse workResponse = new InternationallyRegisterResponse.WorkResponse();
	    response.setWorkResponse(workResponse);

	    workTaskItemList = dozerHelper.toWorkTaskItem(taskItemRequestList);
	    workBusiness.storeTaskItemDetails(workTaskItemList, taskCode, reqContext.getClientId());
	    workResponse.setRegistrationAck("OK");

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	    response.getWorkResponse().setRegistrationAck("KO");
	}

	return response;
    }

    @Override
    public IRWorkElaborationResponse getElaborationResult(IRWorkElaborationRequest mainRequest) {
	IRWorkElaborationResponse response = new IRWorkElaborationResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    List<String> taskCodeList = mainRequest.getItemCodeList();
	    List<WorkTaskElaborationResult> er = workBusiness.getWorkTaskElaborationResultByItemCode(taskCodeList);
	    this.dozerHelper.toWorkElaborationResponse(er, response.getElaborationResponse());
	    // response.getElaborationResponse().
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}

	return response;
    }

}