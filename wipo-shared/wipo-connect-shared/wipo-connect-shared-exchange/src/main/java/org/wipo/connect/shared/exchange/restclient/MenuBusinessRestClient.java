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
import org.wipo.connect.shared.exchange.business.MenuBusiness;
import org.wipo.connect.shared.exchange.menu.Menu;
import org.wipo.connect.shared.exchange.restvo.menu.GetMenuRestVO;

@Service
@Qualifier("menuBusinessRestClient")
public class MenuBusinessRestClient implements MenuBusiness {

    private static final String PROP_URL = "backendUrl";
    private static final String PROP_PATH = "restPath";
    private static final String CONTROLLER_PATH = "menu";
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

    @PostConstruct
    public void init() {
	baseUrl = configProperties.getProperty(PROP_URL) + "/" + configProperties.getProperty(PROP_PATH) + "/" + CONTROLLER_PATH + "/";
	restWsPass = cryptoHelper.decrypt(restWsPass);
	restTemplate = new BasicAuthRestTemplate(restWsUser, restWsPass);
    }

    @Override
    public List<Menu> getHomeMenuList(List<String> permissionList, String ctx) throws WccException {
	String endpoint = baseUrl + "getHomeMenuList";

	try {
	    GetMenuRestVO vo = new GetMenuRestVO();
	    vo.setPermissionList(permissionList);
	    vo.setCtx(ctx);

	    Menu[] results = restTemplate.postForObject(endpoint, vo, Menu[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

    @Override
    public List<Menu> getMainMenuList(List<String> permissionList, String ctx) throws WccException {
	String endpoint = baseUrl + "getMainMenuList";

	try {
	    GetMenuRestVO vo = new GetMenuRestVO();
	    vo.setPermissionList(permissionList);
	    vo.setCtx(ctx);

	    Menu[] results = restTemplate.postForObject(endpoint, vo, Menu[].class);
	    return new ArrayList<>(Arrays.asList(results));
	} catch (HttpServerErrorException e) {
	    throw WccExceptionFactory.getWccException(e.getResponseBodyAsString(), e);
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
	}

    }

}
