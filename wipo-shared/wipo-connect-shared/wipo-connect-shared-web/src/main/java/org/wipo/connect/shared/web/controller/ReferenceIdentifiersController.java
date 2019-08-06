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
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
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
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierLinkedEntityEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

/**
 * The Class ReferenceIdentifiersController.
 */
@Controller
@RequestMapping("reference/identifiers")
public class ReferenceIdentifiersController extends BaseController {

    /** The common business. */
    @Autowired
    private CommonBusiness commonBusiness;

    /** The reference business. */
    @Autowired
    private ReferenceBusiness referenceBusiness;

    @Autowired
    private MessageSource messageSource;

    /**
     * Search.
     *
     * @return the model and view
     * @throws WccControllerException
     *                                    the wcc controller exception
     */
    @RequestMapping("search")
    public ModelAndView search() throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();

	    mv.addObject("linkedEntityEnum", IdentifierLinkedEntityEnum.values());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /**
     * Find all identifier.
     *
     * @return the model and view
     * @throws WccControllerException
     *                                    the wcc controller exception
     */
    @RequestMapping("findAllIdentifier.json")
    public ModelAndView findAllIdentifier() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<IdentifierFlat> identifierList = commonBusiness.getAllIdentifier();
	    identifierList = identifierList.stream().filter(idf -> BooleanUtils.isNotTrue(idf.getHideFromUi())).collect(Collectors.toList());
	    mv.addObject("data", identifierList);
	    mv.addObject("creationClassMap", commonBusiness.getCreationClassMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Detail.
     *
     * @param insertMode
     *                       the insert mode
     * @param vo
     *                       the vo
     * @param results
     *                       the results
     * @return the model and view
     * @throws WccControllerException
     *                                    the wcc controller exception
     */
    @ResponseBody
    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean insertMode, IdentifierFlat vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    IdentifierFlat idFlat = new IdentifierFlat();
	    List<CreationClassFlat> ccList = commonBusiness.getCreationClassFlatList();

	    if (vo != null && vo.getId() != null) {
		idFlat = commonBusiness.getIdentifierById(vo.getId());
	    }

	    mv.addObject("vo", idFlat);
	    mv.addObject("ccList", ccList);
	    mv.addObject("entityList", IdentifierLinkedEntityEnum.values());
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /**
     * Save identifier.
     *
     * @param vo
     *                    the vo
     * @param results
     *                    the results
     * @return the request result VO
     */
    @ResponseBody
    @RequestMapping(value = "saveIdentifier.json")
    public RequestResultVO saveIdentifier(IdentifierFlat vo, BindingResult results, Locale locale) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    this.referenceBusiness.insertOrUpdateIdentifier(vo);
	    res = new RequestResultVO();

	} catch (DuplicatedItemException e) {
	    LOGGER.error("Error in save", e.getMessage());
	    res = new RequestResultVO(e);
	    res.setUseGenericError(false);
	    res.setType(RequestResultTypeEnum.WARN.name());
	    res.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}
	return res;
    }

}