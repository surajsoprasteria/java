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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.enumerator.CurrencyPositionEnum;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.utils.NumericConversionUtils;
import org.wipo.connect.shared.exchange.vo.AdminParametersManagementVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

/**
 * The Class AdministrationParametersManagementController.
 */
@Controller
@RequestMapping("administration/parametersManagement")
public class AdministrationParametersManagementController extends BaseController {

	@Autowired
	private NumericConversionUtils numericConversionUtils;

	/** The parameters management business. */
	@Autowired
	private ParametersManagementBusiness parametersManagementBusiness;
	
	/** The common business. */
	@Autowired
	private CommonBusiness commonBusiness;

	/**
	 * Detail.
	 *
	 * @param vo the vo
	 * @param results the results
	 * @return the model and view
	 * @throws WccControllerException the wcc controller exception
	 */
	@RequestMapping("detail")
	public ModelAndView detail(AdminParametersManagementVO vo,
			BindingResult results) throws WccControllerException {

		try {
			if (results.hasErrors()) {
				throw new IllegalArgumentException("Binding error - " + results);
			}

			ModelAndView mv = new ModelAndView();

			NumberFormatParam numberFormatVO = parametersManagementBusiness.findNumberFormat();
			mv.addObject("numberFormatVO", numberFormatVO);
			mv.addObject("currencyPositionList", CurrencyPositionEnum.values());

			return mv;

		} catch (Exception e) {
			LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			throw new WccControllerException(e);
		}
	}


	//------------ number format ----------------------------------------


	@ResponseBody
	@RequestMapping(value = "saveNumberFormat.json")
	public RequestResultVO saveNumberFormat(NumberFormatParam vo, BindingResult results) {

		RequestResultVO res;

		try {
			if (results.hasErrors()) {
				throw new IllegalArgumentException("Binding error - " + results);
			}

			parametersManagementBusiness.updateNumberFormat(vo);
			numericConversionUtils.init();

			res = new RequestResultVO();
		} catch (Exception e) {
			LOGGER.error("Error in save", e);
			res = new RequestResultVO(e);
		}

		return res;
	}
	
	//---------------------------------------------------------------------
	
	@RequestMapping("findPerformersConfig.json")
	public ModelAndView findPerformersConfig() throws WccControllerException {

		try {
			ModelAndView mv = new ModelAndView();

			Map<Long, CreationClassFlat> creationClassMap = DTOUtils.listToMapById(this.commonBusiness.getCreationClassFlatList());
			mv.addObject("creationClassMap", creationClassMap);

			List<PerformerConfiguration> performerConfigurationList = parametersManagementBusiness.findPerformerConfiguration();

			mv.addObject("data", performerConfigurationList);
			return mv;

		} catch (Exception e) {
			LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			throw new WccControllerException(e);
		}

	}

	@RequestMapping("detailPerformersConfig")
	public ModelAndView detailPerformersConfig(PerformerConfiguration vo, BindingResult results) throws WccControllerException {

		try {
			if (results.hasErrors()) {
				throw new IllegalArgumentException("Binding error - " + results);
			}
			ModelAndView mv = new ModelAndView();

			List<CreationClassFlat> creationClassList = this.commonBusiness.getCreationClassFlatList();
			mv.addObject("creationClassList", creationClassList);
			mv.addObject("vo", vo);

			return mv;

		} catch (Exception e) {
			LOGGER.error("Error in " + WccUtils.getMethodName(), e);
			throw new WccControllerException(e);
		}

	}

	@ResponseBody
	@RequestMapping(value = "savePerformersConfig.json")
	public RequestResultVO savePerformersConfig(PerformerConfiguration vo, BindingResult results) {

		RequestResultVO res;

		try {
			if (results.hasErrors()) {
				throw new IllegalArgumentException("Binding error - " + results);
			}

			parametersManagementBusiness.savePerformerConfiguration(vo);

			res = new RequestResultVO();
		} catch (Exception e) {
			LOGGER.error("Error in save", e);
			res = new RequestResultVO(e);
		}
		return res;
	}

}