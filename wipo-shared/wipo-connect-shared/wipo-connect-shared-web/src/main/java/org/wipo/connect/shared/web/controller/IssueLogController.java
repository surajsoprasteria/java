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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("administration/issueLog")
public class IssueLogController extends BaseController {

    @Autowired
    private IssueLogBusiness issueLogBusiness;

    @RequestMapping("search")
    public ModelAndView search(IssueLog vo, BindingResult results) throws WccControllerException {
	ModelAndView mv;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    mv = new ModelAndView();

	    mv.addObject("searchVO", vo);

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @RequestMapping("detail")
    public ModelAndView detail(IssueLog vo, BindingResult results) throws WccControllerException {
	ModelAndView mv;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    mv = new ModelAndView();

	    vo = issueLogBusiness.find(vo.getId());

	    mv.addObject("vo", vo);

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @RequestMapping("find.json")
    public ModelAndView find(IssueLog vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }
	    ModelAndView mv = new ModelAndView();

	    mv.addObject("data", issueLogBusiness.findAll());

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @ResponseBody
    @RequestMapping(value = "deleteAll.json")
    public RequestResultVO save(IssueLog vo, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    issueLogBusiness.deleteAll();

	    res = new RequestResultVO();

	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}

	return res;
    }

}