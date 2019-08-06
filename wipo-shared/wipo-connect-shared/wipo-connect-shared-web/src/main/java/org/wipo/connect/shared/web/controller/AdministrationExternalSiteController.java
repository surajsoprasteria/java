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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.sftp.SftpConnectionResultEnum;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.ExternalSiteBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.exchange.enumerator.RequestResultTypeEnum;
import org.wipo.connect.shared.exchange.vo.AdminExternalSiteVO;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;

@Controller
@RequestMapping("administration/externalSite")
public class AdministrationExternalSiteController extends BaseController {

    @Autowired
    private ExternalSiteBusiness externalSiteBusiness;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("detail")
    public ModelAndView detail(AdminExternalSiteVO vo, BindingResult results) throws WccControllerException {

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }

	    ModelAndView mv = new ModelAndView();
	    vo = this.externalSiteBusiness.getAllExternalSite();

	    setCodeIfNull(vo);
	    mv.addObject("vo", vo);

	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}

    }

    private void setCodeIfNull(AdminExternalSiteVO vo) {
	vo.getIpMassiveExportFTP().setCode(ExternalSiteEnum.IP_MASSIVE_EXPORT_SFTP);
	vo.getIpsFTP().setCode(ExternalSiteEnum.IP_IMPORT_SFTP);
	vo.getIpSubmissionsFTP().setCode(ExternalSiteEnum.IP_SUBMISSION_SFTP);
	vo.getWorkMassiveExportFTP().setCode(ExternalSiteEnum.WORK_MASSIVE_EXPORT_SFTP);
	vo.getWorkSubmissionsFTP().setCode(ExternalSiteEnum.WORK_SUBMISSION_SFTP);
	vo.getWorksFTP().setCode(ExternalSiteEnum.WORK_IMPORT_SFTP);
	vo.getQueryImportFTP().setCode(ExternalSiteEnum.QUERY_IMPORT_SFTP);
    }

    @ResponseBody
    @RequestMapping(value = "updateExternalSiteFTP.json")
    public RequestResultVO updateExternalSiteFTP(ExternalSite externalSite, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }
	    this.externalSiteBusiness.updateExternalSiteFTP(externalSite);

	    res = new RequestResultVO();
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}

	return res;
    }

    @ResponseBody
    @RequestMapping(value = "testExtenalSite.json")
    public RequestResultVO testExtenalSite(ExternalSite vo, BindingResult results) {

	RequestResultVO res;

	try {
	    if (results.hasErrors()) {
		throw new IllegalArgumentException("Binding error - " + results);
	    }
	    SftpConnectionResultEnum testRes = externalSiteBusiness.testSftpConnection(vo);

	    res = new RequestResultVO();

	    switch (testRes) {
		case SUCCESS:
		    res.setType(RequestResultTypeEnum.INFO.name());
		    break;
		case CONNECTION_ERROR:
		    res.setType(RequestResultTypeEnum.WARN.name());
		    res.setUseGenericError(false);
		    res.setMessage(messageSource.getMessage("externalSite.test-error-connection", null, getCurrentLocale()));
		    break;
		case READ_ERROR:
		    res.setType(RequestResultTypeEnum.WARN.name());
		    res.setUseGenericError(false);
		    res.setMessage(messageSource.getMessage("externalSite.test-error-read", null, getCurrentLocale()));
		    break;
		case WRITE_ERROR:
		    res.setType(RequestResultTypeEnum.WARN.name());
		    res.setUseGenericError(false);
		    res.setMessage(messageSource.getMessage("externalSite.test-error-write", null, getCurrentLocale()));
		    break;
		default:
		    throw new IllegalStateException("Invalid result: " + testRes);
	    }

	    res.getData().put("testResult", testRes);
	} catch (Exception e) {
	    LOGGER.error("Error in save", e);
	    res = new RequestResultVO(e);
	}

	return res;
    }

}