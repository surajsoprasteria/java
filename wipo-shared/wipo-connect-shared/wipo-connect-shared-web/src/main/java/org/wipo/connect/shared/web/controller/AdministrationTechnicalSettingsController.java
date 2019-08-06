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

package org.wipo.connect.shared.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("administration/technicalSettings")
public class AdministrationTechnicalSettingsController extends BaseController {

    @Autowired
    private InterestedPartyBusiness interestedPartyBusiness;

    @Autowired
    private WorkBusiness workBusiness;

    @Autowired
    private CommonBusiness commonBusiness;

    @ResponseBody
    @RequestMapping("rebuildWorkIndex.json")
    public RequestResultVO rebuildWorkIndex() {

	try {
	    Integer items = workBusiness.rebuildSolrIndex();
	    RequestResultVO vo = new RequestResultVO();

	    vo.getData().put("items", items);
	    vo.setMessage("Solr Rebuild Index");
	    return vo;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    return new RequestResultVO(e);
	}

    }

    @ResponseBody
    @RequestMapping("rebuildIPIndex.json")
    public RequestResultVO rebuildIPIndex() {

	try {
	    Integer items = interestedPartyBusiness.rebuildSolrIndex();
	    RequestResultVO vo = new RequestResultVO();

	    vo.setMessage("Solr Rebuild Index");
	    vo.getData().put("items", items);
	    return vo;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    return new RequestResultVO(e);

	}
    }

    @RequestMapping("debug")
    public ModelAndView debug() throws WccException {

	ModelAndView mv = new ModelAndView();
	mv.addObject("dbInfo", commonBusiness.getDBConnectionInfo());

	return mv;
    }

}