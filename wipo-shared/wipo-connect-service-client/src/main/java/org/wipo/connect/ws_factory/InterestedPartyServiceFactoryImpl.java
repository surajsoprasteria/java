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

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccRuntimeException;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.vo.ServiceClientInfoVO;
import org.wipo.connect.interestedparty.interestedpartyservice.InterestedPartyService;
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
import org.wipo.connect.interestedparty.response.IpLookupResponse;
import org.wipo.connect.interestedparty.response.NameLookupResponse;
import org.wipo.connect.serviceclient.utils.ServiceClientUtils;

@Service
public class InterestedPartyServiceFactoryImpl implements InterestedPartyServiceFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(InterestedPartyServiceFactoryImpl.class);
    @Value("#{configProperties['interestedPartyServicePort']}")
    private String wsPortName;

    @Value("#{configProperties['wsBasicAuthUser']}")
    private String username;

    @Value("#{configProperties['wsBasicAuthPass']}")
    private String password;

    private InterestedPartyService service;

    private InterestedPartyServicePort servicePort;

    @Autowired
    private CryptoHelper cryptoHelper;

    @SuppressWarnings("unchecked")
    @Override
    public IpLookupResponse callGetInterestedPartyList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO) {

	IpLookupRequest request = new IpLookupRequest();
	request.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	String identifier = searchParam.get("identifier") != null ? searchParam.get("identifier").toString() : null;
	Boolean onlyMainId = BooleanUtils.toBooleanObject(searchParam.get("onlyMainId").toString());
	String statusCode = searchParam.get("statusCode") != null ? searchParam.get("statusCode").toString() : null;
	Date birthFoundationDate = (Date) searchParam.get("dateBirth");
	String gender = searchParam.get("sex") != null ? searchParam.get("sex").toString() : null;
	String type = searchParam.get("type") != null ? searchParam.get("type").toString() : null;
	String birthPlace = searchParam.get("birthPlace") != null ? searchParam.get("birthPlace").toString() : null;
	String birthCountryCode = searchParam.get("countryOfBirth") != null ? searchParam.get("countryOfBirth").toString() : null;

	String citizenship = searchParam.get("countryCitizenship") != null ? searchParam.get("countryCitizenship").toString() : null;
	String lastCompanyName = searchParam.get("lastName") != null ? searchParam.get("lastName").toString() : null;
	String firstName = searchParam.get("firstName") != null ? searchParam.get("firstName").toString() : null;
	String nameType = searchParam.get("nameType") != null ? searchParam.get("nameType").toString() : null;
	String cmoAcronym = searchParam.get("cmoOfAffiliation") != null ? searchParam.get("cmoOfAffiliation").toString() : null;
	Date dateFrom = (Date) searchParam.get("dateFrom");
	Date dateTo = (Date) searchParam.get("dateTo");
	String fullText = searchParam.get("fullText") != null ? searchParam.get("fullText").toString() : null;
	List<String> creationClassList = (List<String>) searchParam.get("creationClassCodeList");
	String wipoLocalId = searchParam.get("wipoLocalId") != null ? searchParam.get("wipoLocalId").toString() : null;
	List<String> nameMainIdList = (List<String>) searchParam.get("nameMainIdList");
	List<String> nameTypeExcludeList = (List<String>) searchParam.get("nameTypeExcludeList");

	PageInfo pageInfo = (PageInfo) searchParam.get("pageInfo");
	String orderByExpression = (String) searchParam.get("orderByExpression");

	List<Long> idToExcludeList = (List<Long>) searchParam.get("idToExcludeList");

	Boolean isSimpleSearch = (Boolean) searchParam.get("isSimpleSearch");

	InterestedPartyRequest interestedPartyRequest = new InterestedPartyRequest();
	interestedPartyRequest.setIdentifier(identifier);
	interestedPartyRequest.setOnlyMainId(onlyMainId);
	interestedPartyRequest.setStatusCode(statusCode);
	if (birthFoundationDate != null) {
	    interestedPartyRequest.setBirthFoundationDate(birthFoundationDate);
	}
	interestedPartyRequest.setGender(gender);
	interestedPartyRequest.setType(type);
	interestedPartyRequest.setBirthPlace(birthPlace);
	interestedPartyRequest.setBirthCountryCode(birthCountryCode);
	interestedPartyRequest.setCitizenship(citizenship);
	interestedPartyRequest.setLastCompanyName(lastCompanyName);
	interestedPartyRequest.setFirstName(firstName);
	interestedPartyRequest.setNameType(nameType);
	interestedPartyRequest.setCmoAcronym(cmoAcronym);
	interestedPartyRequest.setCreationClassCodeList(creationClassList);

	if (dateFrom != null) {
	    // interestedPartyRequest.setDateFrom(DateUtils.toXMLGregorianCalendar(dateFrom));
	    interestedPartyRequest.setDateFrom(dateFrom);
	}
	if (dateTo != null) {
	    // interestedPartyRequest.setDateTo(DateUtils.toXMLGregorianCalendar(dateTo));
	    interestedPartyRequest.setDateTo(dateTo);
	}
	interestedPartyRequest.setFullText(fullText);
	interestedPartyRequest.setWipoLocalId(wipoLocalId);
	interestedPartyRequest.setNameMainIdList(nameMainIdList);
	interestedPartyRequest.setNameTypeExcludeList(nameTypeExcludeList);

	interestedPartyRequest.setPaginationStart(pageInfo.getStart());
	interestedPartyRequest.setPaginationLength(pageInfo.getLength());
	interestedPartyRequest.setPaginationDraw(pageInfo.getDraw());
	interestedPartyRequest.setOrderByExpression(orderByExpression);

	interestedPartyRequest.setIdToExcludeList(idToExcludeList);

	interestedPartyRequest.setIsSimpleSearch(isSimpleSearch);

	request.setInterestedPartyRequest(interestedPartyRequest);
	IpLookupResponse response = this.servicePort.getInterestedPartyList(request);

	return response;
    }

    @Override
    public IpDetailsResponse callGetInterestedPartyDetails(Long idInterestedParty, ServiceClientInfoVO clientInfoVO) {

	IpDetailsRequest ipDetailsRequest = new IpDetailsRequest();
	ipDetailsRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	InterestedPartyDetailRequest interestedPartyDetailRequest = new InterestedPartyDetailRequest();
	interestedPartyDetailRequest.setIdInterestedParty(idInterestedParty);
	ipDetailsRequest.setInterestedPartyDetailRequest(interestedPartyDetailRequest);

	IpDetailsResponse ipDetailsResponse = this.servicePort.getInterestedPartyDetails(ipDetailsRequest);
	if (null != ipDetailsResponse.getError()) {
	    // LOGGER.error("Work registration error: {}", ipDetailsResponse.getError());
	    throw new WccRuntimeException("Get Interested Party Details error: " + ipDetailsResponse.getError());
	}
	return ipDetailsResponse;
    }

    @Override
    public IpDetailsResponse callGetInterestedPartyDetails(String mainId, ServiceClientInfoVO clientInfoVO) {

	IpDetailsRequest ipDetailsRequest = new IpDetailsRequest();
	ipDetailsRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	InterestedPartyDetailRequest interestedPartyDetailRequest = new InterestedPartyDetailRequest();
	interestedPartyDetailRequest.setMainId(mainId);
	ipDetailsRequest.setInterestedPartyDetailRequest(interestedPartyDetailRequest);

	IpDetailsResponse ipDetailsResponse = this.servicePort.getInterestedPartyDetails(ipDetailsRequest);
	if (null != ipDetailsResponse.getError()) {
	    // LOGGER.error("Work registration error: {}", ipDetailsResponse.getError());
	    throw new WccRuntimeException("Get Interested Party Details error: " + ipDetailsResponse.getError());
	}
	return ipDetailsResponse;
    }

    @Override
    public InternationallyRegisterResponse callRegisterInternationally(String taskCode, List<InternationallyRegisterRequest.TaskItemRequest> taskItemRequest, ServiceClientInfoVO clientInfoVO) {

	InternationallyRegisterRequest internationallyRegisterRequest = new InternationallyRegisterRequest();
	internationallyRegisterRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	internationallyRegisterRequest.setTaskCode(taskCode);
	internationallyRegisterRequest.getTaskItemRequest().addAll(taskItemRequest);

	InternationallyRegisterResponse internationallyRegisterResponse = this.servicePort.registerInternationally(internationallyRegisterRequest);

	return internationallyRegisterResponse;
    }

    @Override
    public IRElaborationResponse callGetElaborationResult(List<String> itemCodes, ServiceClientInfoVO clientInfoVO) {

	IRElaborationRequest irElaborationRequest = new IRElaborationRequest();
	irElaborationRequest.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));
	irElaborationRequest.setItemCodeList(itemCodes);
	// List<String> itemCodeList = irElaborationRequest.getItemCodeList();
	// itemCodeList.addAll(itemCodes);

	IRElaborationResponse elaborationResponse = this.servicePort.getElaborationResult(irElaborationRequest);

	return elaborationResponse;
    }

    @SuppressWarnings("unchecked")
    @Override
    public NameLookupResponse callGetNameList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO) {

	NameLookupRequest request = new NameLookupRequest();
	request.setContext(ServiceClientUtils.generateServiceContext(clientInfoVO));

	String valueForm = searchParam.get("valueForm") != null ? searchParam.get("valueForm").toString() : null;
	List<String> creationClassList = (List<String>) searchParam.get("creationClassCodeList");
	boolean onlyRegistered = BooleanUtils.isTrue((Boolean) searchParam.get("onlyRegistered"));
	PageInfo pageInfo = (PageInfo) searchParam.get("pageInfo");

	NameRequest nameRequest = new NameRequest();
	nameRequest.setValueForm(valueForm);
	nameRequest.setOnlyRegistered(onlyRegistered);
	nameRequest.setCreationClassCodeList(creationClassList);
	nameRequest.setPageInfo(pageInfo);
	request.setNameRequest(nameRequest);

	NameLookupResponse response = this.servicePort.getNameList(request);

	return response;
    }

    @PostConstruct
    private void init() {
	// service instantiation
	this.service = new InterestedPartyService();
	if (this.service == null) {
	    throw new WccRuntimeException("service is null");
	}
	this.servicePort = this.service.getInterestedPartyServicePortBinding();

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

    }

    @Override
    public void changeBaseUrl(String baseUrl) {
	BindingProvider bindingProvider = (BindingProvider) this.servicePort;

	String endPoint = StringUtils.removeEnd(baseUrl, "/");
	endPoint += "/" + this.wsPortName;
	// set endPoint
	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
    }

}
