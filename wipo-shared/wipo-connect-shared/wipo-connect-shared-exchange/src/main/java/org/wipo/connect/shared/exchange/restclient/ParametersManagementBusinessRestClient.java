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
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.restvo.parametersManagement.UpdateNumberFormatRestVO;

/**
 * The Class ParametersManagementBusinessRestClient.
 */
@Service
@Qualifier("parametersManagementBusinessRestClient")
public class ParametersManagementBusinessRestClient implements ParametersManagementBusiness {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ParametersManagementBusinessRestClient.class);

    /** The Constant PROP_URL. */
    private static final String PROP_URL = "backendUrl";

    /** The Constant PROP_PATH. */
    private static final String PROP_PATH = "restPath";

    /** The Constant CONTROLLER_PATH. */
    private static final String CONTROLLER_PATH = "parametersManagement";

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
	baseUrl = configProperties.getProperty(PROP_URL) + "/" +
		configProperties.getProperty(PROP_PATH) + "/" +
		CONTROLLER_PATH + "/";

	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public NumberFormatParam updateNumberFormat(NumberFormatParam dto) throws WccException {
	String endpoint = baseUrl + "updateNumberFormat";

	try {
	    UpdateNumberFormatRestVO reqVO = new UpdateNumberFormatRestVO();
	    reqVO.setNumberFormatParam(dto);
	    return restTemplate.postForObject(endpoint, reqVO, NumberFormatParam.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public NumberFormatParam findNumberFormat() throws WccException {
	String endpoint = baseUrl + "findNumberFormat";

	try {
	    return restTemplate.getForObject(endpoint, NumberFormatParam.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public List<PerformerConfiguration> findPerformerConfiguration() throws WccException {
	String endpoint = baseUrl + "findPerformerConfiguration";

	try {
	    PerformerConfiguration[] results = restTemplate.getForObject(endpoint, PerformerConfiguration[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void savePerformerConfiguration(PerformerConfiguration performerConfiguration) throws WccException {
	String endpoint = baseUrl + "savePerformerConfiguration";

	try {
	    restTemplate.postForEntity(endpoint, performerConfiguration, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public Boolean getPerformerConfigurationByFkCreationClass(Long fkCreationClass) throws WccException {
	String endpoint = baseUrl + "getPerformerConfigurationByFkCreationClass";

	try {
	    return restTemplate.postForObject(endpoint, fkCreationClass, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

}
