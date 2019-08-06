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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.DuplicatedItemException;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.ReferenceManagedByEnum;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ReferenceBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.exchange.enumerator.AgreementTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

/**
 * The Class ReferenceOtherController.
 */
@Controller
@RequestMapping("reference/otherReferences")
public class ReferenceOtherController extends BaseController {

    /** The common business. */
    @Autowired
    private CommonBusiness commonBusiness;

    /** The reference business. */
    @Autowired
    private ReferenceBusiness referenceBusiness;

    /** The Message source. */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("search")
    public ModelAndView search() throws WccControllerException {
	try {

	    ModelAndView mv = new ModelAndView();

	    mv.addObject("referenceManagedByEnum", ReferenceManagedByEnum.values());

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("findAllOtherReference")
    public ModelAndView findAllOtherReference() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<ReferenceTypeFlat> referenceTypeList = new ArrayList<ReferenceTypeFlat>();

	    // Show only SHARED and SYSTEM reference
	    for (ReferenceTypeFlat ft : commonBusiness.getAllReferenceType()) {
		if (ft.getManagedBy().equalsIgnoreCase(ReferenceManagedByEnum.SYSTEM.getCode()) && !ft.getCode().equals(ReferenceTypeEnum.AGREEMENT_TYPE.name())) {
		    referenceTypeList.add(ft);
		} else if (ft.getManagedBy().equalsIgnoreCase(ReferenceManagedByEnum.SHARED.getCode()) && !ft.getCode().equals(ReferenceTypeEnum.AGREEMENT_TYPE.name())) {
		    referenceTypeList.add(ft);
		}

	    }

	    mv.addObject("data", referenceTypeList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(defaultValue = "false") boolean insertMode, ReferenceTypeFlat vo) throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    ReferenceTypeFlat refereceType = new ReferenceTypeFlat();
	    // Can be insert only shared reference type
	    refereceType.setManagedBy(ReferenceManagedByEnum.SHARED.name());

	    if (vo != null && vo.getId() != null) {
		refereceType = commonBusiness.getReferenceTypeById(vo.getId());
	    }

	    mv.addObject("vo", refereceType);
	    mv.addObject("managedByTypeList", ReferenceManagedByEnum.values());
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @ResponseBody
    @RequestMapping("detailValueReference")
    public ModelAndView detailValueReference(@RequestParam(defaultValue = "false") boolean insertMode, ReferenceFlat vo) throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    mv.addObject("vo", vo);
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("findAllValuesReference")
    public ModelAndView findAllValuesReference(@RequestParam String code) throws WccControllerException {
	ModelAndView mv;
	try {

	    mv = new ModelAndView();

	    List<ReferenceFlat> valuesReferenceList = new ArrayList<>();
	    if (code != null && code != "") {
		valuesReferenceList = commonBusiness.getReferenceByCode(code);

		if (code.equals(ReferenceTypeEnum.AGREEMENT_TYPE.name())) {
		    ReferenceFlat referenceImpliedAgreement = valuesReferenceList.stream().filter(it -> AgreementTypeEnum.IM.value().equals(it.getName())).collect(Collectors.toList()).get(0);
		    valuesReferenceList.remove(referenceImpliedAgreement);
		}
	    }

	    mv.addObject("data", valuesReferenceList);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "saveReferenceType.json", method = { RequestMethod.POST })
    public RequestResultVO saveReferenceType(ReferenceTypeFlat vo, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    vo = this.referenceBusiness.insertOrUpdateReferenceType(vo);

	    if (vo.getReferenceValueList() != null) {
		for (ReferenceFlat reference : vo.getReferenceValueList()) {
		    reference.setFkRefType(vo.getId());
		    this.referenceBusiness.insertOrUpdateReference(reference);
		}
	    }

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

    @ResponseBody
    @RequestMapping("checkCodeUniqueness.json")
    public String checkCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkReferenceTypeCodeUniqueness(code, id)) {
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