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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.dto.impl.GroupDTO;
import org.wipo.connect.shared.exchange.dto.impl.GroupDetailDTO;
import org.wipo.connect.shared.exchange.vo.NameGroupResultVO;
import org.wipo.connect.shared.exchange.vo.NameSearchVO;

@Controller
@RequestMapping("groupManagement")
public class GroupManagementController extends BaseController {

    @Autowired
    private InterestedPartyBusiness interestedPartyBusiness;

    @Value("#{configProperties['workGroup.nameType'].split(',')}")
    private List<String> groupNameTypeList;

    @RequestMapping("search")
    public ModelAndView search(@RequestParam(defaultValue = "false") boolean autoSearchMode, NameSearchVO searchVO)
	    throws WccControllerException {
	ModelAndView mv;
	try {
	    mv = new ModelAndView();
	    mv.addObject("searchVO", searchVO);
	    mv.addObject("autoSearchMode", autoSearchMode);
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

	return mv;
    }

    @RequestMapping("findGroup.json")
    public ModelAndView findGroup(NameSearchVO searchVO, HttpServletRequest req, BindingResult results) throws WccControllerException {
	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }
	    ModelAndView mv = new ModelAndView();

	    PageInfo pageInfo = super.extractPageInfo(req);
	    searchVO.setPageInfo(pageInfo);

	    NameGroupResultVO searchResult = interestedPartyBusiness.findGroups(searchVO);

	    mv.addAllObjects(searchResult.asMap());

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    /**
     * Open group detail or new group page
     * 
     * @param insertMode
     * @param vo
     * @param results
     * @return
     * @throws WccControllerException
     */
    @ResponseBody
    @RequestMapping("detailGroup")
    public ModelAndView detailGroup(@RequestParam(required = false) Long idGroup) throws WccControllerException {
	try {

	    ModelAndView mv = new ModelAndView();
	    GroupDTO vo = new GroupDTO();

	    vo = interestedPartyBusiness.findGroupById(idGroup);
	    mv.addObject("vo", vo);
	    mv.addObject("groupNameTypeList", groupNameTypeList);
	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    /**
     * Load detail
     * 
     * @param insertMode
     * @param vo
     * @param results
     * @return
     * @throws WccControllerException
     */
    @ResponseBody
    @RequestMapping("findDetailGroup")
    public ModelAndView findDetailGroup(@RequestParam(required = false) Long idGroup) throws WccControllerException {
	try {
	    ModelAndView mv = new ModelAndView();
	    List<GroupDetailDTO> data = interestedPartyBusiness.findGroupDetailsByIdGroup(idGroup);

	    mv.addObject("data", data);

	    return mv;

	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

}