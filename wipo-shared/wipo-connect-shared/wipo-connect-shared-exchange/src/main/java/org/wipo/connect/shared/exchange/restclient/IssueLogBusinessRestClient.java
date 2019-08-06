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
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.restclient.BasicAuthRestTemplate;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;
import org.wipo.connect.shared.exchange.restvo.issueLog.IssueLogRestVO;

/**
 * The Class AccountBusinessRestClient.
 */
@Service
@Qualifier("issueLogBusinessRestClient")
public class IssueLogBusinessRestClient implements IssueLogBusiness {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IssueLogBusinessRestClient.class);

    private static final String PROP_URL = "backendUrl";
    private static final String PROP_PATH = "restPath";
    private static final String CONTROLLER_PATH = "issueLog";
    private String baseUrl;

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
    public void saveLog(IssueLog dto) {
	String endpoint = baseUrl + "saveLog";
	IssueLogRestVO reqVO = new IssueLogRestVO();
	reqVO.setDto(dto);
	restTemplate.postForObject(endpoint, reqVO, Boolean.class);
    }

    @Override
    public List<IssueLog> findAll() throws WccException {
	String endpoint = baseUrl + "findAll";
	try {
	    IssueLog[] results = restTemplate.getForObject(endpoint, IssueLog[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void deleteAll() throws WccException {
	String endpoint = baseUrl + "deleteAll";
	try {
	    restTemplate.getForObject(endpoint, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public IssueLog find(Long idIssueLog) throws WccException {
	String endpoint = baseUrl + "find";
	try {
	    IssueLogRestVO reqVO = new IssueLogRestVO();
	    reqVO.setIdIssueLog(idIssueLog);
	    IssueLog result = restTemplate.postForObject(endpoint, reqVO, IssueLog.class);
	    return result;
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}
    }

    @Override
    public void removeOldIssues() throws WccException {
	String endpoint = baseUrl + "removeOldIssues";
	try {
	    restTemplate.getForObject(endpoint, Boolean.class);
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

}
