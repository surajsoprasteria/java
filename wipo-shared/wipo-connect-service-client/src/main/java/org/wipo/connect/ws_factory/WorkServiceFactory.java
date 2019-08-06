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
import org.wipo.connect.work.response.IRWorkElaborationResponse;
import org.wipo.connect.work.response.InternationallyRegisterResponse;
import org.wipo.connect.work.response.WorkDetailResponse;
import org.wipo.connect.work.response.WorkListResponse;

public interface WorkServiceFactory {

    WorkListResponse callGetWorkList(Map<String, Object> searchParam, ServiceClientInfoVO clientInfoVO);

    WorkDetailResponse callGetWorkDetails(Long idWork, ServiceClientInfoVO clientInfoVO);

    WorkDetailResponse callGetWorkDetails(String mainId, ServiceClientInfoVO clientInfoVO);

    InternationallyRegisterResponse callRegisterInternationally(String taskCode, List<org.wipo.connect.work.request.InternationallyRegisterRequest.TaskItemRequest> taskItemList,
	    ServiceClientInfoVO clientInfoVO);

    IRWorkElaborationResponse callGetElaborationResult(List<String> itemCodes, ServiceClientInfoVO clientInfoVO);

    void changeBaseUrl(String baseUrl);

}
