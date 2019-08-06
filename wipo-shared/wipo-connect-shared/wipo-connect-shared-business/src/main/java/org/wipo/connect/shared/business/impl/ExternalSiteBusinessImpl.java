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

package org.wipo.connect.shared.business.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.sftp.SftpConnectionChecker;
import org.wipo.connect.common.sftp.SftpConnectionResultEnum;
import org.wipo.connect.shared.exchange.business.ExternalSiteBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.exchange.vo.AdminExternalSiteVO;
import org.wipo.connect.shared.persistence.dao.ExternalSiteDAO;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ExternalSiteBusinessImpl implements ExternalSiteBusiness {

    @Value("#{configProperties['path.rightOwner.sftpImport-dir']}")
    private String BASE_DIR_IP_IMPORT;

    @Value("#{configProperties['path.work.sftpImport-dir']}")
    private String BASE_DIR_WORK_IMPORT;

    @Value("#{configProperties['path.work.sftpSubmit-dir']}")
    private String BASE_DIR_WORK_EXPORT;

    @Value("#{configProperties['path.rightOwner.sftpSubmit-dir']}")
    private String BASE_DIR_IP_EXPORT;

    @Value("#{configProperties['path.work.sftpExport-dir']}")
    private String BASE_DIR_WORK_FULL_EXPORT;

    @Value("#{configProperties['path.rightOwner.sftpExport-dir']}")
    private String BASE_DIR_IP_FULL_EXPORT;

    @Value("#{configProperties['path.bi.sftpImport-dir']}")
    private String BASE_DIR_QUERY_IMPORT;

    @Autowired
    private ExternalSiteDAO externalSiteDAO;

    @Override
    public ExternalSite selectExternalSiteByCode(ExternalSiteEnum code) throws WccException {
	return externalSiteDAO.selectExternalSiteByCode(code);
    }

    @Override
    public AdminExternalSiteVO getAllExternalSite() throws WccException {
	AdminExternalSiteVO externalSiteVO = new AdminExternalSiteVO();
	externalSiteVO.setIpsFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_IMPORT_SFTP));
	externalSiteVO.setWorksFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_IMPORT_SFTP));
	externalSiteVO.setWorkSubmissionsFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_SUBMISSION_SFTP));
	externalSiteVO.setIpSubmissionsFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_SUBMISSION_SFTP));
	externalSiteVO.setWorkMassiveExportFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.WORK_MASSIVE_EXPORT_SFTP));
	externalSiteVO.setIpMassiveExportFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.IP_MASSIVE_EXPORT_SFTP));
	externalSiteVO.setQueryImportFTP(externalSiteDAO.selectExternalSiteByCode(ExternalSiteEnum.QUERY_IMPORT_SFTP));

	return externalSiteVO;
    }

    @Override
    public void updateExternalSiteFTP(ExternalSite vo) throws WccException {
	this.externalSiteDAO.updateExternalSiteByCode(vo);
	this.externalSiteDAO.updateExternalSitePassword(vo);
    }

    @Override
    public SftpConnectionResultEnum testSftpConnection(ExternalSite vo) throws WccException {

	try {
	    String baseDir;
	    String password;

	    switch (vo.getCode()) {
		case WORK_SUBMISSION_SFTP:
		    baseDir = BASE_DIR_WORK_EXPORT;
		    break;
		case WORK_IMPORT_SFTP:
		    baseDir = BASE_DIR_WORK_IMPORT;
		    break;
		case IP_IMPORT_SFTP:
		    baseDir = BASE_DIR_IP_IMPORT;
		    break;
		case IP_SUBMISSION_SFTP:
		    baseDir = BASE_DIR_IP_EXPORT;
		    break;
		case IP_MASSIVE_EXPORT_SFTP:
		    baseDir = BASE_DIR_IP_FULL_EXPORT;
		    break;
		case WORK_MASSIVE_EXPORT_SFTP:
		    baseDir = BASE_DIR_WORK_FULL_EXPORT;
		    break;
		case QUERY_IMPORT_SFTP:
		    baseDir = BASE_DIR_QUERY_IMPORT;
		    break;
		default:
		    throw new IllegalStateException("Invalid Extenal site code: " + vo.getCode());

	    }

	    if (StringUtils.isEmpty(vo.getPassword())) {
		ExternalSite aux = selectExternalSiteByCode(vo.getCode());
		password = aux.getPassword();
	    } else {
		password = vo.getPassword();
	    }
	    return SftpConnectionChecker.check(vo.getHost(), vo.getPort(), vo.getUser(), password, baseDir);

	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}

    }

}