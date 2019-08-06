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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
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
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
import org.wipo.connect.shared.exchange.business.DataAccessBusiness;
import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;
import org.wipo.connect.shared.exchange.restvo.common.CheckUniquenessCodeRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.DeleteRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.FindByIdRestVO;
import org.wipo.connect.shared.exchange.restvo.dataAccess.InsertOrUpdateRestVO;

/**
 * The Class ExternalSiteBusinessRestClient.
 */
@Service
@Qualifier("dataAccessBusinessRestClient")
public class DataAccessBusinessRestClient implements DataAccessBusiness {

    /** The Constant LOGGER. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DataAccessBusinessRestClient.class);

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "dataAccess";

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
	baseUrl = configProperties.getProperty(PROP_URL) + "/" + configProperties.getProperty(PROP_PATH)
		+ "/" + CONTROLLER_PATH + "/";

	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public List<ClientInfo> findAll() throws WccException {
	String endpoint = baseUrl + "findAll";

	try {
	    ClientInfo[] result = restTemplate.getForObject(endpoint, ClientInfo[].class);
	    return new ArrayList<>(Arrays.asList(result));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ClientInfo findById(Long idClientInfo) throws WccException {
	String endpoint = baseUrl + "findById";

	try {
	    FindByIdRestVO reqVO = new FindByIdRestVO();
	    reqVO.setIdClientInfo(idClientInfo);

	    ClientInfo result = restTemplate.postForObject(endpoint, reqVO, ClientInfo.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public ClientInfo insertOrUpdate(ClientInfo clientInfo) throws WccException {
	String endpoint = baseUrl + "insertOrUpdate";

	try {
	    InsertOrUpdateRestVO reqVO = new InsertOrUpdateRestVO();
	    reqVO.setClientInfo(clientInfo);

	    ClientInfo result = restTemplate.postForObject(endpoint, reqVO, ClientInfo.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void delete(Long idClientInfo) throws WccException {
	String endpoint = baseUrl + "delete";

	try {
	    DeleteRestVO reqVO = new DeleteRestVO();
	    reqVO.setIdClientInfo(idClientInfo);

	    restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean checkCodeUniqueness(String code, Long idClientInfo) throws WccException {
	String endpoint = baseUrl + "checkCodeUniqueness";
	try {
	    CheckUniquenessCodeRestVO reqVO = new CheckUniquenessCodeRestVO();
	    reqVO.setCode(code);
	    reqVO.setId(idClientInfo);
	    return restTemplate.postForObject(endpoint, reqVO, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

}
