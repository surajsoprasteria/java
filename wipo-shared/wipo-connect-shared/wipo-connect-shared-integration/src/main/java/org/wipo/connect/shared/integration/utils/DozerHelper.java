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

package org.wipo.connect.shared.integration.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.interestedparty.request.InternationallyRegisterRequest;
import org.wipo.connect.interestedparty.request.IpLookupRequest.InterestedPartyRequest;
import org.wipo.connect.interestedparty.request.NameLookupRequest.NameRequest;
import org.wipo.connect.interestedparty.response.IRElaborationResponse;
import org.wipo.connect.interestedparty.response.IpDetailsResponse.IpResponse;
import org.wipo.connect.interestedparty.response.IpLookupResponse.InterestedPartyListResponse;
import org.wipo.connect.interestedparty.response.NameLookupResponse.NameListResponse;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;
import org.wipo.connect.shared.exchange.dto.impl.NameVO;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.exchange.vo.InterestedPartySearchVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.exchange.vo.WorkSearchVO;
import org.wipo.connect.work.request.InternationallyRegisterRequest.TaskItemRequest;
import org.wipo.connect.work.request.WorkListRequest.WorkRequest;
import org.wipo.connect.work.response.IRWorkElaborationResponse;
import org.wipo.connect.work.response.WorkDetailResponse;
import org.wipo.connect.work.response.WorkListResponse;

/**
 * The Class DozerHelper.
 *
 * @author minervini
 */
@Service
public class DozerHelper {

    /** The dozer utility. */
    @Autowired
    private DozerUtility dozerUtility;

    /**
     * To interested party response.
     *
     * @param interestedPartyList
     *            the interested party list
     * @param interestedPartyResponseList
     *            the interested party response list
     * @param classType
     *            the class type
     * @return the list
     */
    public List<InterestedPartyListResponse> toInterestedPartyResponse(List<InterestedParty> interestedPartyList, List<InterestedPartyListResponse> interestedPartyResponseList,
	    Class<InterestedPartyListResponse> classType) {

	return this.dozerUtility.map(interestedPartyList, interestedPartyResponseList, classType);
    }

    /**
     * Ip lookup request to search vo.
     *
     * @param interestedPartyRequest
     *            the ip lookup request
     * @return the interested party search vo
     */
    public InterestedPartySearchVO interestedPartyRequestToSearchVO(InterestedPartyRequest interestedPartyRequest) {
	InterestedPartySearchVO sVO = new InterestedPartySearchVO();
	dozerUtility.map(interestedPartyRequest, sVO);
	return sVO;
    }

    /**
     * Work request to search vo.
     *
     * @param workRequest
     *            the work request
     * @return the work search vo
     */
    public WorkSearchVO workRequestToSearchVO(WorkRequest workRequest) {
	WorkSearchVO sVO = new WorkSearchVO();
	dozerUtility.map(workRequest, sVO);
	return sVO;
    }

    /**
     * To ip response.
     *
     * @param interestedParty
     *            the interested party
     * @param ipResponse
     *            the ip response
     * @return the ip response
     */
    public IpResponse toIpResponse(InterestedParty interestedParty, IpResponse ipResponse) {

	return this.dozerUtility.map(interestedParty, ipResponse);
    }

    /**
     * To ip response.
     *
     * @param ipItemDetailRequestList
     *            the ip item detail request list
     * @param iptdList
     *            the iptd list
     * @param classType
     *            the class type
     * @return the list
     */
    public List<IpTaskItemDetail> toIpResponse(List<org.wipo.connect.interestedparty.request.InternationallyRegisterRequest.TaskItemRequest> taskItemRequestList, List<IpTaskItemDetail> iptdList,
	    Class<IpTaskItemDetail> classType) {

	return this.dozerUtility.map(taskItemRequestList, iptdList, classType);
    }

    /**
     * To work list response.
     *
     * @param workRequestList
     *            the work request list
     * @return the list
     */
    public List<WorkTaskItem> toWorkTaskItem(List<TaskItemRequest> workRequestList) {
	List<WorkTaskItem> itemList = new ArrayList<WorkTaskItem>();
	itemList = this.dozerUtility.map(workRequestList, itemList, WorkTaskItem.class);
	return itemList;
    }

    public List<IpTaskItem> toIpTaskItem(List<InternationallyRegisterRequest.TaskItemRequest> ipRequestList) {
	List<IpTaskItem> itemList = new ArrayList<IpTaskItem>();
	itemList = this.dozerUtility.map(ipRequestList, itemList, IpTaskItem.class);
	return itemList;
    }

    /**
     * To work response.
     *
     * @param workList
     *            the work list
     * @param responseList
     *            the response list
     * @return the list
     */
    public List<WorkListResponse.WorkResponse> toWorkResponse(List<Work> workList, List<WorkListResponse.WorkResponse> responseList) {
	responseList = this.dozerUtility.map(workList, responseList, WorkListResponse.WorkResponse.class);
	return responseList;
    }

    public List<WorkDetailResponse.WorkResponse> toWorkDetailResponse(List<Work> workList, List<WorkDetailResponse.WorkResponse> responseList) {
	responseList = this.dozerUtility.map(workList, responseList, WorkDetailResponse.WorkResponse.class);
	return responseList;
    }

    /**
     * To work response.
     *
     * @param work
     *            the work
     * @param response
     *            the response
     * @return the work detail response. work response
     */
    public WorkDetailResponse.WorkResponse toWorkResponse(Work work, WorkDetailResponse.WorkResponse response) {
	response = this.dozerUtility.map(work, response);
	return response;
    }

    /**
     * To work elaboration response.
     *
     * @param elaborationResponse
     *            the elaboration response
     * @param response
     *            the response
     * @return the list
     */
    public List<IRWorkElaborationResponse.ElaborationResponse> toWorkElaborationResponse(List<WorkTaskElaborationResult> elaborationResponse,
	    List<IRWorkElaborationResponse.ElaborationResponse> response) {
	response = this.dozerUtility.map(elaborationResponse, response, IRWorkElaborationResponse.ElaborationResponse.class);
	return response;
    }

    /**
     * To ip elaboration response.
     *
     * @param elaborationResponse
     *            the elaboration response
     * @param response
     *            the response
     * @return the list
     */
    public List<IRElaborationResponse.ElaborationResponse> toIpElaborationResponse(List<IpTaskElaborationResult> elaborationResponse, List<IRElaborationResponse.ElaborationResponse> response) {
	response = this.dozerUtility.map(elaborationResponse, response, IRElaborationResponse.ElaborationResponse.class);
	return response;
    }

    public NameSearchVO nameRequestToSearchVO(NameRequest nameRequest) {
	NameSearchVO sVO = new NameSearchVO();
	dozerUtility.map(nameRequest, sVO);
	return sVO;
    }

    public List<NameListResponse> toNameResponse(List<NameVO> nameList, List<NameListResponse> nameResponseList, Class<NameListResponse> classType) {

	return this.dozerUtility.map(nameList, nameResponseList, classType);
    }

}
