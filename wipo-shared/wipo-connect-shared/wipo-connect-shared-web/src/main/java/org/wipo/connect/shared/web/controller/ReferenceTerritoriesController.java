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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.DateOverlapException;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ReferenceBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;
import org.wipo.connect.shared.exchange.vo.TerritoryNameVO;

/**
 * The Class ReferenceTerritoriesController.
 */
@Controller
@RequestMapping("reference/territories")
public class ReferenceTerritoriesController extends BaseController {

    /** The common business. */
    @Autowired
    private CommonBusiness commonBusiness;

    /** The reference business. */
    @Autowired
    private ReferenceBusiness referenceBusiness;

    /** The message source. */
    @Autowired
    private MessageSource messageSource;

    /**
     * Search.
     *
     * @return the model and view
     * @throws WccControllerException
     *             the wcc controller exception
     */
    @RequestMapping("search")
    public ModelAndView search() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

    /**
     * Find all territory.
     *
     * @return the model and view
     * @throws WccControllerException
     *             the wcc controller exception
     */
    @RequestMapping("findAllTerritory")
    public ModelAndView findAllTerritory() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<Territory> territoryList = referenceBusiness.getAllTerritoryList();

	    Map<Long, ReferenceFlat> territoryTypeMap = DTOUtils.listToMapById(this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.TERRITORY_TYPE.name()));

	    mv.addObject("data", territoryList);
	    mv.addObject("territoryTypeMap", territoryTypeMap);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Detail.
     *
     * @param idTerritory
     *            the id territory
     * @return the model and view
     * @throws WccControllerException
     *             the wcc controller exception
     */
    @ResponseBody
    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean insertMode, @RequestParam Long idTerritory, @RequestParam(defaultValue = "false") boolean readOnlyMode)
	    throws WccControllerException {

	try {

	    ModelAndView mv = new ModelAndView();
	    Territory territory;
	    if (idTerritory != null) {
		List<Territory> territoryList = this.commonBusiness.findTerritoryNamesById(idTerritory);
		territory = territoryList.get(0);
	    } else {
		territory = new Territory();
	    }
	    mv.addObject("vo", territory);
	    mv.addObject("territoryTypeList", commonBusiness.getReferenceByCode(ReferenceTypeEnum.TERRITORY_TYPE.name()));
	    mv.addObject("insertMode", insertMode);
	    mv.addObject("readOnlyMode", readOnlyMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @ResponseBody
    @RequestMapping("detailTerritoryName")
    public ModelAndView detailTerrytoryName(@RequestParam(defaultValue = "false") boolean insertMode, TerritoryNameVO vo) throws WccControllerException {

	try {

	    ModelAndView mv = new ModelAndView();

	    mv.addObject("vo", vo.getTerritoryName());
	    mv.addObject("territoryTypeList", commonBusiness.getReferenceByCode(ReferenceTypeEnum.TERRITORY_TYPE.name()));
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("findTerritoryNamesByCode")
    public ModelAndView findTerritoryNamesById(@RequestParam String code) throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    List<TerritoryName> tnList = new ArrayList<TerritoryName>();
	    if (code != null) {
		tnList = referenceBusiness.findTerritoryNamesByCode(code);
	    }

	    mv.addObject("data", tnList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Find territory names by id.
     *
     * @param idTerritory
     *            the id territory
     * @return the model and view
     * @throws WccControllerException
     *             the wcc controller exception
     */
    @RequestMapping("findTerritoryNamesById")
    public ModelAndView findTerritoryNamesById(@RequestParam Long idTerritory) throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<Territory> territoryList = commonBusiness.findTerritoryNamesById(idTerritory);

	    mv.addObject("data", territoryList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Serves the Save request.
     *
     * @param vo
     *            the vo
     * @param results
     *            the results
     * @return the request result vo
     */
    @RequestMapping("save.json")
    @ResponseBody
    public RequestResultVO save(Territory vo, BindingResult results) {
	RequestResultVO result;
	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    vo = this.referenceBusiness.insertOrUpdateTerritory(vo);

	    result = new RequestResultVO();
	} catch (DateOverlapException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.territory-name-date-overlap", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (WccException e) {
	    result = new RequestResultVO(e);
	    if (e.getCode() == WccExceptionCodeEnum.VALIDATION_ERROR) {
		result.setUseGenericError(false);
		result.setType(RequestResultTypeEnum.WARN.name());
		result.setMessage(messageSource.getMessage("territory.no-name", null, getCurrentLocale()));
	    }
	    LOGGER.error("Error in " + WccUtils.getMethodName());
	} catch (Exception e) {
	    result = new RequestResultVO(e);
	    LOGGER.error("Error in save", e);
	}
	return result;
    }

    @ResponseBody
    @RequestMapping("checkTisnCodeUniqueness.json")
    public String checkTisnCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkTisnCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @ResponseBody
    @RequestMapping("checkTisaCodeUniqueness.json")
    public String checkTisaCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkTisaCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("findTerritoryChildrenByTisa")
    public ModelAndView findTerritoryChildrenByTisa(@RequestParam String tisaCode) throws WccControllerException {
	ModelAndView mv;
	try {

	    mv = new ModelAndView();
	    List<Territory> territoryList = new ArrayList<>();
	    if (tisaCode != null) {
		territoryList = commonBusiness.getTerritoryByParentTisa(tisaCode);
	    }
	    Map<Long, ReferenceFlat> territoryTypeMap = DTOUtils.listToMapById(this.commonBusiness.getReferenceByCode(ReferenceTypeEnum.TERRITORY_TYPE.name()));
	    mv.addObject("data", territoryList);
	    mv.addObject("territoryTypeMap", territoryTypeMap);
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

}