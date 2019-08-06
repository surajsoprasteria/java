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

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TypeEnum;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.NameSearchResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;
import org.wipo.connect.shared.web.utils.StatusColorManager;

/**
 * The Class NameController.
 */
@Controller
@RequestMapping("name")
public class NameController extends BaseController {

    /** The common business. */
    @Autowired
    private CommonBusiness commonBusiness;

    @Autowired
    private InterestedPartyBusiness interestedPartyBusiness;

    @Autowired
    private StatusColorManager statusColorManager;

    @RequestMapping("find.json")
    public ModelAndView find(NameSearchVO vo, HttpServletRequest req, BindingResult results) throws WccControllerException {

	try {

	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();

	    PageInfo pageInfo = super.extractPageInfo(req);
	    vo.setPageInfo(pageInfo);

	    NameSearchResultVO pageResult = this.commonBusiness.findNameVO(vo);
	    mv.addAllObjects(pageResult.asMap());

	    Map<String, CreationClassFlat> ccMap = commonBusiness.getCreationClassFlatList().stream().collect(Collectors.toMap(CreationClassFlat::getCode, Function.identity()));
	    mv.addObject("ccMap", ccMap);
	    mv.addObject("nameTypeMap", NameTypeEnum.getAsMap());
	    mv.addObject("statusMap", DTOUtils.listToMapById(this.interestedPartyBusiness.findAllIPStatus()));

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    @RequestMapping("search")
    public ModelAndView search(NameSearchVO vo, @RequestParam(defaultValue = "false") boolean singleSelectMode, @RequestParam(defaultValue = "false") boolean isOnlyPatronym,
	    @RequestParam(defaultValue = "false") boolean multipleSelectMode, @RequestParam(required = false) Long idToExclude, @RequestParam(defaultValue = "false") boolean isOnlyLegalEntity,
	    BindingResult results) throws WccControllerException {
	ModelAndView mv;
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    mv = new ModelAndView();
	    if (isOnlyPatronym) {
		vo.setIsOnlyPatronym(isOnlyPatronym);
	    }
	    if (null != idToExclude) {
		vo.setExcludeId(idToExclude);
	    }

	    if (isOnlyLegalEntity) {
		vo.setIsOnlyLegalEntity(isOnlyLegalEntity);
	    }

	    mv.addObject("searchVO", vo);
	    mv.addObject("singleSelectMode", singleSelectMode);
	    mv.addObject("multipleSelectMode", multipleSelectMode);
	    mv.addObject("statusColorMap", statusColorManager.getIpStatusColorMap());
	    mv.addObject("interestedPartyTypeList", TypeEnum.values());
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
	return mv;
    }

}