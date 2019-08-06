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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.interestedparty.interestedpartyservice.InterestedPartyServicePort;
import org.wipo.connect.interestedparty.request.IRElaborationRequest;
import org.wipo.connect.interestedparty.request.InternationallyRegisterRequest;
import org.wipo.connect.interestedparty.request.IpDetailsRequest;
import org.wipo.connect.interestedparty.request.IpDetailsRequest.InterestedPartyDetailRequest;
import org.wipo.connect.interestedparty.request.IpLookupRequest;
import org.wipo.connect.interestedparty.request.IpLookupRequest.InterestedPartyRequest;
import org.wipo.connect.interestedparty.request.NameLookupRequest;
import org.wipo.connect.interestedparty.request.NameLookupRequest.NameRequest;
import org.wipo.connect.interestedparty.response.IRElaborationResponse;
import org.wipo.connect.interestedparty.response.InternationallyRegisterResponse;
import org.wipo.connect.interestedparty.response.IpDetailsResponse;
import org.wipo.connect.interestedparty.response.IpDetailsResponse.IpResponse;
import org.wipo.connect.interestedparty.response.IpLookupResponse;
import org.wipo.connect.interestedparty.response.IpLookupResponse.InterestedPartyListResponse;
import org.wipo.connect.interestedparty.response.NameLookupResponse;
import org.wipo.connect.interestedparty.response.NameLookupResponse.NameListResponse;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Affiliation;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchResultVO;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.integration.utils.DozerHelper;
import org.wipo.connect.shared.integration.utils.WSHelper;

/**
 * The Class InterestedPartyServicePortImpl.
 *
 * @author minervini
 */
@WebService(serviceName = "InterestedPartyService", endpointInterface = "org.wipo.connect.interestedparty.interestedpartyservice.InterestedPartyServicePort", targetNamespace = "http://interestedPartyService.interestedParty.connect.wipo.org")
public class InterestedPartyServicePortImpl implements InterestedPartyServicePort {

    /** The logger. */
    private static Logger LOGGER = WipoLoggerFactory.getLogger(InterestedPartyServicePortImpl.class);

    /** The interested party business. */
    @Autowired
    private InterestedPartyBusiness interestedPartyBusiness;

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
    @ExecutionTimeTrackable
    @Loggable(level = "INFO")
    public IpLookupResponse getInterestedPartyList(IpLookupRequest mainRequest) {

	IpLookupResponse response = new IpLookupResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    String code = reqContext.getClientId();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    InterestedPartyRequest request = mainRequest.getInterestedPartyRequest();
	    List<InterestedPartyListResponse> interestedPartyResponse = response.getInterestedPartyListResponse();
	    InterestedPartySearchVO searchVO = new InterestedPartySearchVO();

	    searchVO = dozerHelper.interestedPartyRequestToSearchVO(request);
	    searchVO.setForceValidStatus(true);
	    searchVO.setCode(code);

	    InterestedPartySearchResultVO resultVO = this.interestedPartyBusiness.findInterestedPartyForWs(searchVO);
	    // List<InterestedParty> interestedPartyList = resultVO.getResults();

	    // if (BooleanUtils.isTrue(resultVO.getHasTooManyResults())) {
	    // response.setError(WSHelper.generateTooManyResultsErrorResponse());
	    // } else {
	    this.dozerHelper.toInterestedPartyResponse(resultVO.getData(), interestedPartyResponse, InterestedPartyListResponse.class);
	    // }
	    response.setPaginationDraw(resultVO.getDraw());
	    response.setPaginationRecordsTotal(resultVO.getRecordsTotal());

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}
	return response;
    }

    @Override
    public IpDetailsResponse getInterestedPartyDetails(IpDetailsRequest mainRequest) {

	IpDetailsResponse response = new IpDetailsResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    String code = reqContext.getClientId();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    InterestedPartyDetailRequest interestedPartyDetailRequest = mainRequest.getInterestedPartyDetailRequest();
	    IpResponse ipResponse = new IpResponse();
	    response.setIpResponse(ipResponse);

	    InterestedParty interestedParty = null;
	    if (StringUtils.isNotEmpty(interestedPartyDetailRequest.getMainId())) {
		interestedParty = this.interestedPartyBusiness.findByMainId(interestedPartyDetailRequest.getMainId());
		interestedParty = this.interestedPartyBusiness.findById(interestedParty.getId(), code, reqContext.getManagedCreationClassCodeList());
	    } else {
		interestedParty = this.interestedPartyBusiness.findById(interestedPartyDetailRequest.getIdInterestedParty(), code, reqContext.getManagedCreationClassCodeList());
	    }

	    List<Affiliation> affiliationList = this.interestedPartyBusiness.explodeIpiRightType(interestedParty.getAffiliationList(), true);
	    interestedParty.setAffiliationList(affiliationList);
	    // explodeIpiRightType
	    this.dozerHelper.toIpResponse(interestedParty, ipResponse);
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}

	return response;
    }

    @Override
    public IRElaborationResponse getElaborationResult(IRElaborationRequest mainRequest) {
	IRElaborationResponse response = new IRElaborationResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    List<String> taskCodeList = mainRequest.getItemCodeList();
	    List<IpTaskElaborationResult> er = interestedPartyBusiness.getIpTaskElaborationResultByItemCode(taskCodeList);
	    this.dozerHelper.toIpElaborationResponse(er, response.getElaborationResponse());
	    // response.getElaborationResponse().
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
	    List<IpTaskItem> iptdList = new ArrayList<>();
	    InternationallyRegisterResponse.IpResponse ipResponse = new InternationallyRegisterResponse.IpResponse();
	    response.setIpResponse(ipResponse);

	    iptdList = this.dozerHelper.toIpTaskItem(taskItemRequestList);
	    this.interestedPartyBusiness.storeTaskItemDetails(iptdList, taskCode, reqContext.getClientId());
	    ipResponse.setRegistrationAck("OK");

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	    response.getIpResponse().setRegistrationAck("KO");
	}

	return response;
    }

    @Override
    @ExecutionTimeTrackable
    @Loggable(level = "INFO")
    public NameLookupResponse getNameList(NameLookupRequest mainRequest) {

	NameLookupResponse response = new NameLookupResponse();

	try {
	    ContextType reqContext = mainRequest.getContext();
	    if (!commonBusiness.checkClientIdentity(reqContext.getClientId(), reqContext.getClientKey())) {
		LOGGER.warn("Invalid client code/key ({}/{})", reqContext.getClientId(), reqContext.getClientKey());
		response.setError(WSHelper.generateWsAuthErrorResponse(reqContext));
		return response;
	    }

	    NameRequest request = mainRequest.getNameRequest();
	    List<NameListResponse> nameListResponse = response.getNameListResponse();
	    NameSearchVO searchVO = dozerHelper.nameRequestToSearchVO(request);

	    NameSearchResultVO resultVO = this.commonBusiness.findNameVO(searchVO);

	    this.dozerHelper.toNameResponse(resultVO.getData(), nameListResponse, NameListResponse.class);

	    response.setPaginationDraw(resultVO.getDraw());
	    response.setPaginationRecordsTotal(resultVO.getRecordsTotal());

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    response.setError(WSHelper.generateWsErrorResponse(e.getMessage()));
	}
	return response;
    }

}