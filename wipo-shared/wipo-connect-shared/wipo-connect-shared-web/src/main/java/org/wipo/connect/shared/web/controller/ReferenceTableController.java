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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RoleTypeEnum;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("reference/referenceTable")
public class ReferenceTableController extends BaseController {

    @Autowired
    private CommonBusiness commonBusiness;

    @Autowired
    private ReferenceBusiness referenceBusiness;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("search")
    public ModelAndView search() throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();
	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /********* CREATION CLASS ************/

    @RequestMapping("findAllCreationClass")
    public ModelAndView findAllCreationClass() throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();
	    List<CreationClassFlat> creationClassList = commonBusiness.getCreationClassFlatList();
	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);
	    mv.addObject("data", creationClassList);

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping("detailCreationClass")
    public ModelAndView detailCreationClass(@RequestParam(defaultValue = "false") boolean insertMode, CreationClassFlat vo) throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    CreationClassFlat creationClass = new CreationClassFlat();

	    if (vo != null && vo.getId() != null) {
		creationClass = referenceBusiness.findCreationClassById(vo.getId());
	    }

	    mv.addObject("vo", creationClass);
	    mv.addObject("insertMode", insertMode);

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("saveCreationClass.json")
    @ResponseBody
    public RequestResultVO saveCreationClass(CreationClassFlat vo, BindingResult results) {
	RequestResultVO result;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    vo = this.referenceBusiness.insertOrUpdateCreationClass(vo);

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
    @RequestMapping("checkCcCodeUniqueness.json")
    public String checkCcCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkCcCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /********* RIGHT TYPE ************/

    @RequestMapping("findAllIpiRightType")
    public ModelAndView findAllIpiRightType() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<IpiRightTypeFlat> ipiRightTypeList = commonBusiness.getIpiRightTypeList();

	    ipiRightTypeList.removeIf(irt -> irt.getCode().equalsIgnoreCase(IpiRightTypeEnum.ALL.name()));

	    mv.addObject("data", ipiRightTypeList);
	    mv.addObject("creationClassMap", commonBusiness.getCreationClassMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("detailIpiRightType")
    public ModelAndView detailIpiRightType(@RequestParam(defaultValue = "false") boolean insertMode, IpiRightTypeFlat vo, BindingResult results) throws WccControllerException {
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    IpiRightTypeFlat ipiRightType = new IpiRightTypeFlat();
	    List<CreationClassFlat> ccList = commonBusiness.getCreationClassFlatList();

	    if (vo != null && vo.getId() != null) {
		ipiRightType = commonBusiness.findIpiRightTypeById(vo.getId());

	    }

	    mv.addObject("vo", ipiRightType);
	    mv.addObject("ccList", ccList);
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "saveIpiRightType.json")
    public RequestResultVO saveIpiRightType(IpiRightTypeFlat vo, BindingResult results, Locale locale) {
	RequestResultVO result;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    this.referenceBusiness.insertOrUpdateIpiRightType(vo);
	    result = new RequestResultVO();
	} catch (DuplicatedItemException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    result = new RequestResultVO(e);
	}

	return result;
    }

    @ResponseBody
    @RequestMapping("checkIpiRightTypeCodeUniqueness.json")
    public String checkIpiRightTypeCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkIpiRightTypeCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /********* RIGHT CATEGORY ************/

    @RequestMapping("findAllRightType")
    public ModelAndView findAllRightType() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    List<RightTypeFlat> rightTypeList = commonBusiness.getRightTypeList();

	    mv.addObject("data", rightTypeList);
	    mv.addObject("creationClassMap", commonBusiness.getCreationClassMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("detailRightType")
    public ModelAndView detailRightType(@RequestParam(defaultValue = "false") boolean insertMode, RightTypeFlat vo, BindingResult results) throws WccControllerException {
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    RightTypeFlat rightType = new RightTypeFlat();
	    List<CreationClassFlat> ccList = commonBusiness.getCreationClassFlatList();

	    if (vo != null && vo.getId() != null) {
		rightType = commonBusiness.findRightTypeById(vo.getId());
	    }

	    mv.addObject("vo", rightType);
	    mv.addObject("ccList", ccList);
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "saveRightType.json")
    public RequestResultVO saveRightType(RightTypeFlat vo, BindingResult results, Locale locale) {
	RequestResultVO result;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    this.referenceBusiness.insertOrUpdateRightType(vo);
	    result = new RequestResultVO();
	} catch (DuplicatedItemException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    result = new RequestResultVO(e);
	}

	return result;
    }

    @ResponseBody
    @RequestMapping("checkRightTypeCodeUniqueness.json")
    public String checkRightTypeCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkRightTypeCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /********* IPI ROLE ************/

    @RequestMapping("findCcByIpiRole")
    public ModelAndView findCcByIpiRole(Long id) throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();

	    mv.addObject("data", referenceBusiness.findIpiRoleById(id).getFkCcList());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("findAllIpiRole")
    public ModelAndView findAllIpiRole() throws WccControllerException {

	try {
	    ModelAndView mv = new ModelAndView();

	    mv.addObject("data", commonBusiness.getIpiRoleList());
	    mv.addObject("creationClassMap", commonBusiness.getCreationClassMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("detailIpiRole")
    public ModelAndView detailIpiRole(@RequestParam(defaultValue = "false") boolean insertMode, IpiRoleFlat vo, BindingResult results) throws WccControllerException {
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    IpiRoleFlat ipi = new IpiRoleFlat();

	    List<CreationClassFlat> ccList = commonBusiness.getCreationClassFlatList();

	    if (vo != null && vo.getId() != null) {
		ipi = referenceBusiness.findIpiRoleById(vo.getId());
	    }

	    mv.addObject("vo", ipi);
	    mv.addObject("ccList", ccList);
	    mv.addObject("insertMode", insertMode);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "saveIpiRole.json")
    public RequestResultVO saveIpiRole(IpiRoleFlat vo, BindingResult results, Locale locale) {
	RequestResultVO result;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    this.referenceBusiness.insertOrUpdateIpiRole(vo);
	    result = new RequestResultVO();
	} catch (DuplicatedItemException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    result = new RequestResultVO(e);
	}

	return result;
    }

    @ResponseBody
    @RequestMapping("checkIpiRoleCodeUniqueness.json")
    public String checkIpiRoleCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkIpiRoleCodeUniqueness(code, id)) {
		return Boolean.TRUE.toString();
	    } else {
		return messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale());
	    }
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /********* WORK ROLE ************/

    @RequestMapping("findAllWorkRole")
    public ModelAndView findAllWorkRole() throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();

	    Map<String, String> roleTypeMap = new HashMap<>();
	    Arrays.stream(RoleTypeEnum.values()).forEach(v -> {
		String mex = messageSource.getMessage(v.getMsgCode(), null, getCurrentLocale());
		roleTypeMap.put(v.name(), mex);
	    });

	    mv.addObject("data", commonBusiness.getRoleList());
	    mv.addObject("ipiRoleMap", commonBusiness.getIpiRoleMap());
	    mv.addObject("roleTypeMap", roleTypeMap);
	    mv.addObject("creationClassMap", commonBusiness.getCreationClassMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("detailWorkRole")
    public ModelAndView detailWorkRole(@RequestParam(defaultValue = "false") boolean insertMode, RoleFlat vo, BindingResult results) throws WccControllerException {
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    List<CreationClassFlat> ccList = commonBusiness.getCreationClassFlatList();
	    RoleFlat role = new RoleFlat();

	    if (vo != null && vo.getId() != null) {
		role = commonBusiness.findRoleById(vo.getId());

	    }

	    mv.addObject("vo", role);
	    mv.addObject("ccList", ccList);
	    mv.addObject("insertMode", insertMode);
	    mv.addObject("roleTypeList", RoleTypeEnum.values());
	    mv.addObject("ipiRoleList", commonBusiness.getIpiRoleList());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @ResponseBody
    @RequestMapping(value = "saveWorkRole.json")
    public RequestResultVO saveWorkRole(RoleFlat vo, BindingResult results, Locale locale) {
	RequestResultVO result;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    this.referenceBusiness.insertOrUpdateRole(vo);
	    result = new RequestResultVO();
	} catch (DuplicatedItemException e) {
	    result = new RequestResultVO(e);
	    result.setType(RequestResultTypeEnum.WARN.name());
	    result.setMessage(messageSource.getMessage("reference.duplicate-code", null, getCurrentLocale()));
	    result.setUseGenericError(false);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    result = new RequestResultVO(e);
	}

	return result;
    }

    @ResponseBody
    @RequestMapping("checkWorkRoleCodeUniqueness.json")
    public String checkWorkRoleCodeUniqueness(@RequestParam String code, @RequestParam(required = false) Long id) throws WccControllerException {
	try {
	    if (referenceBusiness.checkRoleCodeUniqueness(code, id)) {
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