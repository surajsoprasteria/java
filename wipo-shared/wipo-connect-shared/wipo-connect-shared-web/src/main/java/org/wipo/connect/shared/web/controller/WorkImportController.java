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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.WorkBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.utils.DTOUtils;

@Controller
@RequestMapping("workImport")
public class WorkImportController extends BaseController {

    @Autowired
    protected WorkBusiness workBusiness;

    @Autowired
    protected CommonBusiness commonBusiness;

    @RequestMapping("search")
    public ModelAndView search() throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in" + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("findWorkImportList.json")
    public ModelAndView findWorkImportList() throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();

	    List<WorkImport> data = workBusiness.findWorkImport();
	    List<ImportStatusFlat> statusList = commonBusiness.getImportStatusList();

	    mv.addObject("data", data);
	    mv.addObject("statusMap", DTOUtils.listToMapById(statusList));

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in" + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

}