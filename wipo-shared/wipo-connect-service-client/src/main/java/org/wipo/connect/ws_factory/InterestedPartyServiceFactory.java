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

import java.util.List;
import java.util.Map;

import org.wipo.connect.common.vo.ServiceClientInfoVO;
import org.wipo.connect.interestedparty.request.InternationallyRegisterRequest;
import org.wipo.connect.interestedparty.response.IRElaborationResponse;
import org.wipo.connect.interestedparty.response.InternationallyRegisterResponse;
import org.wipo.connect.interestedparty.response.IpDetailsResponse;
import org.wipo.connect.interestedparty.response.IpLookupResponse;
import org.wipo.connect.interestedparty.response.NameLookupResponse;

/**
 * A factory for creating InterestedPartyService objects.
 */
public interface InterestedPartyServiceFactory {

    IpLookupResponse callGetInterestedPartyList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO);

    IpDetailsResponse callGetInterestedPartyDetails(Long idInterestedParty, ServiceClientInfoVO clientInfoVO);

    IpDetailsResponse callGetInterestedPartyDetails(String mainId, ServiceClientInfoVO clientInfoVO);

    InternationallyRegisterResponse callRegisterInternationally(String taskCode, List<InternationallyRegisterRequest.TaskItemRequest> taskItemRequest, ServiceClientInfoVO clientInfoVO);

    IRElaborationResponse callGetElaborationResult(List<String> itemCodes, ServiceClientInfoVO clientInfoVO);

    void changeBaseUrl(String baseUrl);

    NameLookupResponse callGetNameList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO);
}
