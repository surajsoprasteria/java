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



package org.wipo.connect.shared.backend.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.restvo.parametersManagement.UpdateNumberFormatRestVO;

/**
 * The Class ParametersManagementRestController.
 */
@RestController
@RequestMapping(value = "/parametersManagement")
public class ParametersManagementRestController extends BaseRestController {

    @Autowired
    @Qualifier("parametersManagementBusinessImpl")
    private ParametersManagementBusiness parametersManagementBusiness;

	@ResponseBody
   	@RequestMapping("findNumberFormat")
	public NumberFormatParam findNumberFormat() throws WccException {
		return parametersManagementBusiness.findNumberFormat();
	}

	@ResponseBody
   	@RequestMapping("updateNumberFormat")
	public NumberFormatParam updateNumberFormat(@RequestBody UpdateNumberFormatRestVO reqVO) throws WccException {
		return parametersManagementBusiness.updateNumberFormat(reqVO.getNumberFormatParam());
	}
	
	@ResponseBody
	@RequestMapping("findPerformerConfiguration")
	public List<PerformerConfiguration> findPerformerConfiguration() throws WccException {
		return parametersManagementBusiness.findPerformerConfiguration();
	}
	
	@ResponseBody
	@RequestMapping("savePerformerConfiguration")
	public boolean savePerformerConfiguration(@RequestBody PerformerConfiguration performerConfiguration) throws WccException {
		parametersManagementBusiness.savePerformerConfiguration(performerConfiguration);
		return true;
	}
	
	@ResponseBody
   	@RequestMapping("getPerformerConfigurationByFkCreationClass")
	public Boolean getPerformerConfigurationByFkCreationClass(@RequestBody Long fkCreationClass) throws WccException {
		return parametersManagementBusiness.getPerformerConfigurationByFkCreationClass(fkCreationClass);
	}


}
