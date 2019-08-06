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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.DataAccessBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("administration/dataAccess")
public class AdministrationDataAccessController extends BaseController {

    @Autowired
    private DataAccessBusiness dataAccessBusiness;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("start")
    public ModelAndView start(ClientInfo vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    mv.addObject("vo", vo);
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("find.json")
    public ModelAndView find(ClientInfo vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    List<ClientInfo> resList = dataAccessBusiness.findAll();
	    mv.addObject("data", resList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("detail")
    public ModelAndView detail(ClientInfo vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }
	    ModelAndView mv = new ModelAndView();

	    if (vo.getId() != null) {
		vo = dataAccessBusiness.findById(vo.getId());
	    }

	    mv.addObject("vo", vo);
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "save.json")
    public RequestResultVO save(ClientInfo vo, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    vo = dataAccessBusiness.insertOrUpdate(vo);

	    res = new RequestResultVO();
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}

	return res;
    }

    @ResponseBody
    @RequestMapping(value = "delete.json")
    public RequestResultVO delete(ClientInfo vo, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    dataAccessBusiness.delete(vo.getId());

	    res = new RequestResultVO();
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}

	return res;
    }

    @ResponseBody
    @RequestMapping("checkCodeUniqueness.json")
    public String checkCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (dataAccessBusiness.checkCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("dataAccess.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }
}