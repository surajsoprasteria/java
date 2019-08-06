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

package org.wipo.connect.ws_factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccRuntimeException;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.vo.ServiceClientInfoVO;
import org.wipo.connect.serviceclient.utils.ServiceClientUtils;
import org.wipo.connect.work.request.IRWorkElaborationRequest;
import org.wipo.connect.work.request.IdentifierType;
import org.wipo.connect.work.request.InternationallyRegisterRequest;
import org.wipo.connect.work.request.RightOwnerType;
import org.wipo.connect.work.request.RightOwnerVO;
import org.wipo.connect.work.request.WorkDetailRequest;
import org.wipo.connect.work.request.WorkListRequest;
import org.wipo.connect.work.response.IRWorkElaborationResponse;
import org.wipo.connect.work.response.InternationallyRegisterResponse;
import org.wipo.connect.work.response.WorkDetailResponse;
import org.wipo.connect.work.response.WorkListResponse;
import org.wipo.connect.work.workservice.WorkService;
import org.wipo.connect.work.workservice.WorkServicePort;

/**
 * The Class WorkServiceFactoryImpl.
 */
@Service
public class WorkServiceFactoryImpl implements WorkServiceFactory {

    private static Logger LOGGER = WipoLoggerFactory.getLogger(WorkServiceFactoryImpl.class);

    @Value("#{configProperties['workServicePort']}")
    private String wsPortName;

    @Value("#{configProperties['wsBasicAuthUser']}")
    private String username;

    @Value("#{configProperties['wsBasicAuthPass']}")
    private String password;

    private WorkService service;

    private WorkServicePort servicePort;

    @Autowired
    private CryptoHelper cryptoHelper;

    /*
     * (non-Javadoc)
     *
     * @see org.wipo.connect.ws_factory.WorkServiceFactory#callGetWorkList(java.util. Map, org.wipo.connect.common.vo.ServiceClientInfoVO)
     */
    @SuppressWarnings("unchecked")
    @Override
    public WorkListResponse callGetWorkList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO) {

	// extracts values from the search map
	String identifier = (String) searchParam.get("identifier");
	Boolean onlyMainId = BooleanUtils.toBooleanObject(searchParam.get("onlyMainId").toString());
	String title = (String) searchParam.get("title");
	String statusCode = (String) searchParam.get("statusCode");
	boolean originalTitles = BooleanUtils.isTrue((Boolean) searchParam.get("originalTitles"));
	String sourceType = (String) searchParam.get("sourceType");
	String workType = (String) searchParam.get("workType");
	Date registrationDate = (Date) searchParam.get("registrationDate");
	String performer = (String) searchParam.get("performer");
	String fullText = (String) searchParam.get("fullText");
	List<String> creationClassList = searchParam.get("creationClassCodeList") != null ? (List<String>) searchParam.get("creationClassCodeList") : new ArrayList<>();
	String categoryCode = searchParam.get("categoryCode") != null ? searchParam.get("categoryCode").toString() : null;
	String countryOfProductionCode = searchParam.get("countryOfProductionCode") != null ? searchParam.get("countryOfProductionCode").toString() : null;
	String label = searchParam.get("label") != null ? searchParam.get("label").toString() : null;
	String catalogueNumber = searchParam.get("catalogueNumber") != null ? searchParam.get("catalogueNumber").toString() : null;
	String dateTypeCode = (String) searchParam.get("dateTypeCode");
	Date dateValue = (Date) searchParam.get("dateValue");
	String wipoLocalId = (String) searchParam.get("wipoLocalId");
	Boolean loadAllFieldFromShared = BooleanUtils.toBooleanObject(searchParam.get("loadAllFieldFromShared").toString());

	PageInfo pageInfo = (PageInfo) searchParam.get("pageInfo");
	String orderByExpression = (String) searchParam.get("orderByExpression");

	List<Map<String, Object>> rightOwnerList = (List<Map<String, Object>>) searchParam.get("rightOwnerList");
	List<RightOwnerType> rightOwnerTypeList = new ArrayList<>();
	for (Map<String, Object> roMap : rightOwnerList) {
	    RightOwnerType ro = new RightOwnerType();
	    String ipin = (String) roMap.get("ipin");
	    String roleCode = (String) roMap.get("roleCode");

	    ro.setIpin(ipin);
	    ro.setRoleCode(roleCode);

	    rightOwnerTypeList.add(ro);
	}

	List<Map<String, Object>> rightOwnerListSearch = (List<Map<String, Object>>) searchParam.get("rightOwnerListSearch");
	List<RightOwnerVO> rightOwnerVOListSearch = new ArrayList<>();
	for (Map<String, Object> roMap : rightOwnerListSearch) {
	    RightOwnerVO ro = new RightOwnerVO();
	    String rightOwnerValue = (String) roMap.get("rightOwnerValue");
	    String nameMainId = (String) roMap.get("nameMainId");

	    ro.setRightOwnerValue(rightOwnerValue);
	    ro.setNameMainId(nameMainId);

	    rightOwnerVOListSearch.add(ro);
	}

	List<Map<String, Object>> identifierVOList = (List<Map<String, Object>>) searchParam.get("identifierSearchVOList");
	List<IdentifierType> identifierTypeList = new ArrayList<>();
	for (Map<String, Object> roMap : identifierVOList) {
	    IdentifierType obj = new IdentifierType();
	    String type = (String) roMap.get("type");
	    String value = (String) roMap.get("value");

	    obj.setType(type);
	    obj.setValue(value);

	    identifierTypeList.add(obj);
	}

	String originalTitleTrimmed = (String) searchParam.get("originalTitleTrimmed");

	List<Long> idToExcludeList = (List<Long>) searchParam.get("idToExcludeList");

	Boolean isSimpleSearch = (Boolean) searchParam.get("isSimpleSearch");

	// init request objects
	WorkListRequest workListRequest = new WorkListRequest();
	WorkListRequest.WorkRequest workRequest = new WorkListRequest.WorkRequest();
	workListRequest.setWorkRequest(workRequest);

	// sets search parameters into request
	workRequest.setIdentifier(identifier);
	workRequest.setOnlyMainId(onlyMainId);
	workRequest.setTitle(title);
	workRequest.setStatusCode(statusCode);
	workRequest.setOriginalTitles(originalTitles);
	workRequest.setSourceType(sourceType);
	workRequest.setWorkType(workType);
	workRequest.setRegistrationDate(registrationDate);
	workRequest.setPerformer(performer);
	workRequest.getRightOwnerList().addAll(rightOwnerTypeList);
	workRequest.getRightOwnerListSearch().addAll(rightOwnerVOListSearch);
	workRequest.setFullText(fullText);
	workRequest.setDateTypeCode(dateTypeCode);
	workRequest.setDateValue(dateValue);
	workRequest.setCatalogueNumber(catalogueNumber);
	workRequest.getCreationClassCodeList().addAll(creationClassList);
	workRequest.setCategoryCode(categoryCode);
	workRequest.setCountryOfProductionCode(countryOfProductionCode);
	workRequest.setLabel(label);
	workRequest.setWipoLocalId(wipoLocalId);
	workRequest.getIdentifierSearchVOList().addAll(identifierTypeList);
	workRequest.setOriginalTitleTrimmed(originalTitleTrimmed);
	workRequest.setLoadAllFieldFromShared(loadAllFieldFromShared);

	workRequest.setPaginationStart(pageInfo.getStart());
	workRequest.setPaginationLength(pageInfo.getLength());
	workRequest.setPaginationDraw(pageInfo.getDraw());
	workRequest.setOrderByExpression(orderByExpression);

	workRequest.setIdToExcludeList(idToExcludeList);

	workRequest.setIsSimpleSearch(isSimpleSearch);

	workListRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	// execute requests
	WorkListResponse workListResponse = this.servicePort.getWorkList(workListRequest);

	return workListResponse;
    }

    @Override
    public WorkDetailResponse callGetWorkDetails(Long idWork, ServiceClientInfoVO clientInfoVO) {

	WorkDetailRequest workDetailRequest = new WorkDetailRequest();
	WorkDetailRequest.WorkRequest workRequest = new WorkDetailRequest.WorkRequest();
	workDetailRequest.setWorkRequest(workRequest);
	workRequest.setIdWork(idWork);
	workDetailRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	WorkDetailResponse workDetailResponse = this.servicePort.getWorkDetail(workDetailRequest);

	return workDetailResponse;
    }

    @Override
    public WorkDetailResponse callGetWorkDetails(String mainId, ServiceClientInfoVO clientInfoVO) {

	WorkDetailRequest workDetailRequest = new WorkDetailRequest();
	WorkDetailRequest.WorkRequest workRequest = new WorkDetailRequest.WorkRequest();
	workDetailRequest.setWorkRequest(workRequest);
	workRequest.setMainId(mainId);
	workDetailRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	WorkDetailResponse workDetailResponse = this.servicePort.getWorkDetail(workDetailRequest);

	return workDetailResponse;
    }

    @Override
    public InternationallyRegisterResponse callRegisterInternationally(String taskCode, List<org.wipo.connect.work.request.InternationallyRegisterRequest.TaskItemRequest> taskItemList,
	    ServiceClientInfoVO clientInfoVO) {

	InternationallyRegisterRequest internationallyRegisterRequest = new InternationallyRegisterRequest();
	internationallyRegisterRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	internationallyRegisterRequest.setTaskCode(taskCode);
	internationallyRegisterRequest.getTaskItemRequest().addAll(taskItemList);

	InternationallyRegisterResponse internationallyRegisterResponse = this.servicePort.registerInternationally(internationallyRegisterRequest);

	if (null != internationallyRegisterResponse.getError()) {
	    LOGGER.error("Work registration error: {}", internationallyRegisterResponse.getError());
	    throw new WccRuntimeException("Work registration error: " + internationallyRegisterResponse.getError());
	}

	return internationallyRegisterResponse;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.wipo.connect.ws_factory.WorkServiceFactory#callGetElaborationResult( java.util.List, org.wipo.connect.common.vo.ServiceClientInfoVO)
     */
    @Override
    public IRWorkElaborationResponse callGetElaborationResult(List<String> itemCodes, ServiceClientInfoVO clientInfoVO) {
	IRWorkElaborationRequest irElaborationRequest = new IRWorkElaborationRequest();
	irElaborationRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	List<String> itemCodeList = irElaborationRequest.getItemCodeList();
	itemCodeList.addAll(itemCodes);

	IRWorkElaborationResponse elaborationResponse = this.servicePort.getElaborationResult(irElaborationRequest);
	if (null != elaborationResponse.getError()) {
	    LOGGER.error("Work registration error: {}", elaborationResponse.getError());
	    throw new WccRuntimeException("Work registration error: " + elaborationResponse.getError());
	}
	return elaborationResponse;
    }

    @PostConstruct
    private void init() {
	// service instantiation
	this.service = new WorkService();
	if (this.service == null) {
	    throw new WccRuntimeException("service is null");
	}
	this.servicePort = this.service.getWorkServicePortBinding();

	if (this.servicePort == null) {
	    throw new WccRuntimeException("servicePort is null");
	}
	// getting BindingProvider
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;

	try {
	    // set property for WS-I
	    Map<String, Object> reqContext = bindingProvider.getRequestContext();
	    reqContext.put(BindingProvider.USERNAME_PROPERTY, this.username);
	    reqContext.put(BindingProvider.PASSWORD_PROPERTY, cryptoHelper.decrypt(this.password));
	} catch (Exception e) {
	    LOGGER.error("Error configuring WS Security: " + e.getMessage());
	    LOGGER.debug("Error configuring WS Security", e);
	}

	// // Client side handler association
	// Binding binding = ((BindingProvider) servicePort).getBinding();
	// List<Handler> handlerList = binding.getHandlerChain();
	// handlerList.add(null);
	// binding.setHandlerChain(handlerList);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.wipo.connect.ws_factory.WorkServiceFactory#changeBaseUrl(java.lang. String)
     */
    @Override
    public void changeBaseUrl(String baseUrl) {
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;

	String endPoint = StringUtils.removeEnd(baseUrl, "/");
	endPoint += "/" + this.wsPortName;
	// set endPoint
	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
    }

}
