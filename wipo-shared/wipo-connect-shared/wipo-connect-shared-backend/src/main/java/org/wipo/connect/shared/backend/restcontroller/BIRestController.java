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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.BIBusiness;
import org.wipo.connect.shared.exchange.dto.impl.BIParameterFlat;
import org.wipo.connect.shared.exchange.dto.impl.BISearch;
import org.wipo.connect.shared.exchange.utils.importexport.ExportBean;
import org.wipo.connect.shared.exchange.vo.BIResultSetVO;

/**
 * The Class CmoRestController.
 */
@RestController
@RequestMapping(value = "/biBusinessManagement")
public class BIRestController extends BaseRestController {

    @Autowired
    @Qualifier("bIBusinessImpl")
    private BIBusiness biBusiness;

    @ResponseBody
    @RequestMapping("getQueryCodeList")
    public List<String> getQueryCodeList() throws WccException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	return biBusiness.getQueryCodeList();

    }

    @ResponseBody
    @RequestMapping("getQueryParameters")
    public List<BIParameterFlat> getQueryParameters(@RequestBody String selectedQueryCode) throws WccException {
	return new ArrayList<BIParameterFlat>(biBusiness.getQueryParameters(selectedQueryCode).values());
    }

    @ResponseBody
    @RequestMapping("executeQuery")
    public BIResultSetVO executeQuery(@RequestBody BISearch vo) throws WccException {
	return biBusiness.executeQuery(vo);
    }

    @ResponseBody
    @RequestMapping("exportResults")
    public ExportBean exportResults(@RequestBody BISearch vo) throws WccException {
	return biBusiness.exportResults(vo);
    }

}
