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
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;
import org.wipo.connect.shared.exchange.restvo.issueLog.IssueLogRestVO;

@RestController
@RequestMapping(value = "/issueLog")
public class IssueLogRestController extends BaseRestController {

    @Autowired
    @Qualifier("issueLogBusinessImpl")
    private IssueLogBusiness issueLogBusiness;

    @ResponseBody
    @RequestMapping("saveLog")
    public Boolean saveLog(@RequestBody IssueLogRestVO reqVO) throws WccException {
	try {
	    issueLogBusiness.saveLog(reqVO.getDto());
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return true;
    }

    @ResponseBody
    @RequestMapping("deleteAll")
    public Boolean deleteAll() throws WccException {
	try {
	    issueLogBusiness.deleteAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return true;
    }

    @ResponseBody
    @RequestMapping("findAll")
    public List<IssueLog> findAll() throws WccException {
	try {
	    return issueLogBusiness.findAll();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @ResponseBody
    @RequestMapping("find")
    public IssueLog find(@RequestBody IssueLogRestVO reqVO) throws WccException {
	try {
	    return issueLogBusiness.find(reqVO.getIdIssueLog());
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @ResponseBody
    @RequestMapping("removeOldIssues")
    public Boolean removeOldIssues() throws WccException {
	try {
	    issueLogBusiness.removeOldIssues();
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
	return true;
    }

}
