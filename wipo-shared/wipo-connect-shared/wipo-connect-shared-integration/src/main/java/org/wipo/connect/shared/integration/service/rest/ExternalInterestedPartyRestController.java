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
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;
import org.wipo.connect.shared.exchange.dto.impl.Name;
import org.wipo.connect.shared.exchange.restvo.external.InterestedPartyRestVOResponse;
import org.wipo.connect.shared.exchange.restvo.external.NameRestVOResponse;

@RestController
@RequestMapping(value = "/rightOwner")
public class ExternalInterestedPartyRestController extends BaseRestController {

    @Autowired
    InterestedPartyBusiness interestedPartyBusiness;

    @Autowired
    private DozerUtility dozerUtility;

    @ResponseBody
    @RequestMapping(path = "getRightOwnerByMainId", method = RequestMethod.GET)
    public InterestedPartyRestVOResponse getRightOwnerByMainId(@RequestParam(required = true, name = "mainId") String ipMainId) throws WccException {
	InterestedParty ip = interestedPartyBusiness.findByMainId(ipMainId);
	if (null == ip) {
	    ip = new InterestedParty();
	}
	InterestedPartyRestVOResponse ipResponse = dozerUtility.map(ip, new InterestedPartyRestVOResponse());
	return ipResponse;

    }

    @ResponseBody
    @RequestMapping(path = "getNameByMainId", method = RequestMethod.GET)
    public NameRestVOResponse getNameByMainId(@RequestParam(required = true, name = "mainId") String nameMainId) throws WccException {
	Name name = interestedPartyBusiness.getNameByMainId(nameMainId);
	if (null == name) {
	    name = new Name();
	}
	NameRestVOResponse nameResponse = dozerUtility.map(name, new NameRestVOResponse());
	return nameResponse;

    }

}
