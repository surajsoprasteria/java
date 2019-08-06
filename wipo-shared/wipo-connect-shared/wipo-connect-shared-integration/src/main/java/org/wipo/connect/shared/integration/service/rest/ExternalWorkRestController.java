/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.integration.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.mapping.DozerUtility;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.restvo.external.WorkRestVOResponse;

@RestController
@RequestMapping(value = "/work")
public class ExternalWorkRestController extends BaseRestController {

    @Autowired
    WorkBusiness workBusiness;

    @Autowired
    private DozerUtility dozerUtility;

    @ResponseBody
    @RequestMapping(path = "getWorkByMainId", method = RequestMethod.GET)
    public WorkRestVOResponse getWorkByMainId(@RequestParam(required = true, name = "mainId") String workMainId) throws WccException {
	Work work = workBusiness.findByMainId(workMainId);
	if (work == null) {
	    work = new Work();
	}
	WorkRestVOResponse workResponse = dozerUtility.map(work, new WorkRestVOResponse());
	return workResponse;
    }

}
