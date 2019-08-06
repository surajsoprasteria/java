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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.BIBusiness;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BISearch;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.vo.BIResultSetVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("businessIntelligence")
public class BusinessIntelligenceController extends BaseController {

    @Autowired
    protected BIBusiness bIBusiness;

    @Autowired
    protected CommonBusiness commonBusiness;

    @Autowired
    protected MessageSource messageSource;

    @RequestMapping("search")
    public ModelAndView search(@RequestParam(defaultValue = "false") boolean autoSearchMode, @RequestParam(defaultValue = "false") boolean favoriteSearch, BISearch vo, BindingResult results)
	    throws WccControllerException {
	ModelAndView mv;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    mv = new ModelAndView();

	    List<String> queryCodeList = bIBusiness.getQueryCodeList();
	    mv.addObject("queryCodeList", queryCodeList);

	    if (StringUtils.isNotEmpty(vo.getQueryCode())) {
		Map<String, BIParameterFlat> queryParameterObject = bIBusiness.getQueryParameters(vo.getQueryCode());
		Map<String, BIParameterFlat> auxValues = vo.getQueryParameterObject();

		for (String key : auxValues.keySet()) {
		    if (queryParameterObject.containsKey(key)) {
			BIParameterFlat aux = auxValues.get(key);
			BIParameterFlat param = queryParameterObject.get(key);
			param.setFormValue(aux.getFormValue());
		    }
		}
		vo.setQueryParameterObject(queryParameterObject);
	    }

	    mv.addObject("vo", vo);
	    mv.addObject("autoSearchMode", autoSearchMode);
	    mv.addObject("favoriteSearch", favoriteSearch);

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @RequestMapping("findQueryParam.json")
    public ModelAndView findQueryParam(@RequestParam(defaultValue = "false") String selectedQueryCode) throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();
	    Map<String, BIParameterFlat> queryParameterObject = bIBusiness.getQueryParameters(selectedQueryCode);

	    List<String> queryCodeList = bIBusiness.getQueryCodeList();
	    mv.addObject("queryCodeList", queryCodeList);

	    BISearch searchParameter = new BISearch();
	    searchParameter.setQueryCode(selectedQueryCode);
	    searchParameter.setQueryParameterObject(queryParameterObject);
	    mv.addObject("vo", searchParameter);

	    return mv;
	} catch (WccValidationException e) {
	    ModelAndView mv = new ModelAndView();
	    String message = this.messageSource.getMessage("bi.query-error", null, getCurrentLocale());
	    mv.addAllObjects(getAsMap(new RequestResultVO(RequestResultTypeEnum.WARN, message)));
	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in" + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("executeQuery.json")
    public ModelAndView executeQuery(BISearch vo, BindingResult results, HttpServletRequest req) throws WccControllerException {
	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Error in " + WccUtils.getMethodName() + " - " + results);
	    }

	    BIResultSetVO pageResult;

	    if (StringUtils.isEmpty(vo.getQueryCode())) {
		pageResult = new BIResultSetVO(true);
	    } else {
		vo.setPageInfo(super.extractPageInfo(req));
		pageResult = bIBusiness.executeQuery(vo);
	    }

	    ModelAndView mv = new ModelAndView();
	    mv.addAllObjects(pageResult.asMap());

	    return mv;
	} catch (WccValidationException e) {
	    ModelAndView mv = new ModelAndView();
	    String message = this.messageSource.getMessage("bi.query-error", null, getCurrentLocale());
	    mv.addAllObjects(getAsMap(new RequestResultVO(RequestResultTypeEnum.WARN, message)));
	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("exportResults")
    public ModelAndView exportResults(BISearch vo, BindingResult results) throws WccControllerException {
	if (results.hasErrors()) {
	    throw new IllegalArgumentException("Error in " + WccUtils.getMethodName() + " - " + results);
	}

	try {
	    ModelAndView mv = new ModelAndView("download-view");
	    mv.addObject("download", bIBusiness.exportResults(vo));
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

}