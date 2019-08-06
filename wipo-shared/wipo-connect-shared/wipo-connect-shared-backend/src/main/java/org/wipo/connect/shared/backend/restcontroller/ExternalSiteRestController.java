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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.sftp.SftpConnectionResultEnum;
import org.wipo.connect.shared.exchange.business.ExternalSiteBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.restvo.externalSite.FindRestVO;
import org.wipo.connect.shared.exchange.restvo.externalSite.UpdateRestVO;
import org.wipo.connect.shared.exchange.vo.AdminExternalSiteVO;

@RestController
@RequestMapping(value = "/externalSite")
public class ExternalSiteRestController extends BaseRestController {

    @Autowired
    @Qualifier("externalSiteBusinessImpl")
    private ExternalSiteBusiness externalSiteBusiness;

    @ResponseBody
    @RequestMapping("testSftpConnection")
    public SftpConnectionResultEnum testSftpConnection(@RequestBody UpdateRestVO vo) throws WccException {
	return externalSiteBusiness.testSftpConnection(vo.getDto());
    }

    @ResponseBody
    @RequestMapping("updateExternalSiteFTP")
    public boolean updateExternalSiteFTP(@RequestBody UpdateRestVO reqVO) throws WccException {
	externalSiteBusiness.updateExternalSiteFTP(reqVO.getDto());
	return true;
    }

    @ResponseBody
    @RequestMapping("selectExternalSiteByCode")
    public ExternalSite selectExternalSiteByCode(@RequestBody FindRestVO vo) throws WccException {
	return externalSiteBusiness.selectExternalSiteByCode(vo.getCode());
    }

    @ResponseBody
    @RequestMapping("getAllExternalSite")
    public AdminExternalSiteVO getAllExternalSite() throws WccException {
	return externalSiteBusiness.getAllExternalSite();
    }
}
