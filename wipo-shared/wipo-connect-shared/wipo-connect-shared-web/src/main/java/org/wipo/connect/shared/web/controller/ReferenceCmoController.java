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
import org.wipo.connect.common.exception.DuplicatedItemException;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ReferenceBusiness;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.vo.CmoSearchVO;
import org.wipo.connect.shared.exchange.vo.CmoVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("reference/cmo")
public class ReferenceCmoController extends BaseController {

    /** The common business. */
    @Autowired
    private CommonBusiness commonBusiness;

    /** The reference business. */
    @Autowired
    private ReferenceBusiness referenceBusiness;

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean insertMode, CmoVO vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();
	    Cmo cmo = new Cmo();

	    if (vo.getCmo() != null && vo.getCmo().getId() != null) {
		cmo = referenceBusiness.getCmoById(vo.getCmo().getId());
	    }

	    mv.addObject("vo", cmo);
	    mv.addObject("territoryList", commonBusiness.getCountriesFromTerritoryList(true));
	    mv.addObject("typeList", referenceBusiness.getCmoTypeList());
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("search")
    public ModelAndView search(@RequestParam(defaultValue = "false") boolean singleSelectMode, @RequestParam(defaultValue = "false") boolean multipleSelectMode, CmoSearchVO vo, BindingResult results)
	    throws WccControllerException {
	ModelAndView mv;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    mv = new ModelAndView();

	    mv.addObject("searchVO", vo);
	    mv.addObject("singleSelectMode", singleSelectMode);
	    mv.addObject("multipleSelectMode", multipleSelectMode);
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    @RequestMapping("findAllCmo")
    public ModelAndView findAllCmo() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    mv.addObject("data", referenceBusiness.getCmoList());
	    mv.addObject("territoryMap", commonBusiness.getTerritoryMap(TerritoryOrderTypeEnum.TRASL_name));
	    mv.addObject("typeMap", referenceBusiness.getCmoTypeMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("find.json")
    public ModelAndView find(CmoSearchVO vo, BindingResult results) throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    List<Cmo> resList = this.referenceBusiness.findCmo(vo);

	    mv.addObject("territoryMap", commonBusiness.getTerritoryMap(TerritoryOrderTypeEnum.TRASL_name));
	    mv.addObject("typeMap", referenceBusiness.getCmoTypeMap());
	    mv.addObject("data", resList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("saveCmo.json")
    @ResponseBody
    public RequestResultVO saveCmo(Cmo vo, BindingResult results) {
	RequestResultVO result;
	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    vo = this.referenceBusiness.insertOrUpdateCmo(vo);

	    result = new RequestResultVO();
	} catch (DuplicatedItemException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (Exception e) {
	    result = new RequestResultVO(e);
	    LOGGER.error("Error in save", e);
	}
	return result;
    }

    @ResponseBody
    @RequestMapping("checkCodeUniqueness.json")
    public String checkCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkCmoCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

}