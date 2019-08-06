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
import org.wipo.connect.shared.exchange.business.DataAccessBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.exchange.restvo.common.CheckUniquenessCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.DeleteRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.InsertOrUpdateRestVO;

@RestController
@RequestMapping(value = "/dataAccess")
public class DataAccessRestController extends BaseRestController {

    @Autowired
    @Qualifier("dataAccessBusinessImpl")
    private DataAccessBusiness dataAccessBusiness;

    @ResponseBody
    @RequestMapping("findAll")
    public List<ClientInfo> findAll() throws WccException {
	return dataAccessBusiness.findAll();
    }

    @ResponseBody
    @RequestMapping("findById")
    public ClientInfo findById(@RequestBody FindByIdRestVO reqVO) throws WccException {
	return dataAccessBusiness.findById(reqVO.getIdClientInfo());
    }

    @ResponseBody
    @RequestMapping("insertOrUpdate")
    public ClientInfo insertOrUpdate(@RequestBody InsertOrUpdateRestVO reqVO) throws WccException {
	return dataAccessBusiness.insertOrUpdate(reqVO.getClientInfo());
    }

    @ResponseBody
    @RequestMapping("delete")
    public Boolean delete(@RequestBody DeleteRestVO reqVO) throws WccException {
	dataAccessBusiness.delete(reqVO.getIdClientInfo());
	return true;
    }

    @ResponseBody
    @RequestMapping("checkCodeUniqueness")
    public Boolean checkCodeUniqueness(@RequestBody CheckUniquenessCodeRestVO reqVO) throws WccException {
	return dataAccessBusiness.checkCodeUniqueness(reqVO.getCode(), reqVO.getId());
    }

}
