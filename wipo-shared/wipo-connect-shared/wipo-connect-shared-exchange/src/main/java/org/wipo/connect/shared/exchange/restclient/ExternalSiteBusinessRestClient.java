/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.exchange.restclient;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.exception.WccExceptionFactory;
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
import org.wipo.connect.common.sftp.SftpConnectionResultEnum;
import org.wipo.connect.shared.exchange.business.ExternalSiteBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;
import org.wipo.connect.shared.exchange.enumerator.ExternalSiteEnum;
import org.wipo.connect.shared.exchange.restvo.externalSite.FindRestVO;
import org.wipo.connect.shared.exchange.restvo.externalSite.UpdateRestVO;
import org.wipo.connect.shared.exchange.vo.AdminExternalSiteVO;

/**
 * The Class ExternalSiteBusinessRestClient.
 */
@Service
@Qualifier("externalSiteBusinessRestClient")
public class ExternalSiteBusinessRestClient implements ExternalSiteBusiness {

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ExternalSiteBusinessRestClient.class);

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "externalSite";

    /** The base url. */
    private String baseUrl;

    /** The shared remoting properties. */
    @Autowired
    private Properties configProperties;

    private RestTemplate restTemplate;

    @Value("#{configProperties.restWsUser}")
    private String restWsUser;

    @Value("#{configProperties.restWsPass}")
    private String restWsPass;

    @Autowired
    private CryptoHelper cryptoHelper;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
	baseUrl = configProperties.getProperty(PROP_URL) + "/" + configProperties.getProperty(PROP_PATH) + "/" + CONTROLLER_PATH + "/";

	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public SftpConnectionResultEnum testSftpConnection(ExternalSite vo) throws WccException {
	String endpoint = baseUrl + "testSftpConnection";

	try {
	    UpdateRestVO reqVO = new UpdateRestVO();
	    reqVO.setDto(vo);
	    return restTemplate.postForObject(endpoint, reqVO, SftpConnectionResultEnum.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ExternalSite selectExternalSiteByCode(ExternalSiteEnum code) throws WccException {
	String endpoint = baseUrl + "selectExternalSiteByCode";

	try {
	    FindRestVO reqVO = new FindRestVO();
	    reqVO.setCode(code);
	    return restTemplate.postForObject(endpoint, reqVO, ExternalSite.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public AdminExternalSiteVO getAllExternalSite() throws WccException {
	String endpoint = baseUrl + "getAllExternalSite";

	try {
	    return restTemplate.getForObject(endpoint, AdminExternalSiteVO.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void updateExternalSiteFTP(ExternalSite vo) throws WccException {
	String endpoint = baseUrl + "updateExternalSiteFTP";

	try {
	    UpdateRestVO reqVO = new UpdateRestVO();
	    reqVO.setDto(vo);
	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

}
