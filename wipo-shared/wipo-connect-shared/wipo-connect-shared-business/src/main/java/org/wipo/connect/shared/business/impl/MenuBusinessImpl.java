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

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.conversion.JacksonObjectMapper;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.business.MenuBusiness;
import org.wipo.connect.shared.exchange.menu.Menu;
import org.wipo.connect.shared.exchange.menu.MenuItem;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.CollectionType;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
@Qualifier("menuBusinessImpl")
public class MenuBusinessImpl implements MenuBusiness {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(MenuBusinessImpl.class);

    private static final String JSON_HOME_MENU = "home-menu.json";

    private static final String JSON_MAIN_MENU = "main-menu.json";

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @Value("#{configProperties['path.home-menu-file']}")
    private String homeMenuFile;

    @Value("#{configProperties['path.main-menu-file']}")
    private String mainMenuFile;

    @Override
    public List<Menu> getHomeMenuList(List<String> permissionList, String ctx) throws WccException {

	try {

	    InputStream jsonFile = getJsonResource(homeMenuFile, JSON_HOME_MENU);

	    CollectionType collectionType = jacksonObjectMapper.getTypeFactory().constructCollectionType(List.class, Menu.class);

	    List<Menu> menuList = jacksonObjectMapper.readValue(jsonFile, collectionType);
	    jsonFile.close();

	    filterMenuByPermission(menuList, permissionList);
	    setMenuContext(menuList, ctx);

	    return menuList;

	} catch (JsonMappingException e) {
	    LOGGER.error("Error mapping JSON to Class: " + e.getMessage());
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	} catch (Exception e) {
	    LOGGER.error("Error: " + e.getMessage());
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	}

    }

    @Override
    public List<Menu> getMainMenuList(List<String> permissionList, String ctx) throws WccException {

	try {

	    InputStream jsonFile = getJsonResource(mainMenuFile, JSON_MAIN_MENU);

	    CollectionType collectionType = jacksonObjectMapper.getTypeFactory().constructCollectionType(List.class, Menu.class);

	    List<Menu> menuList = jacksonObjectMapper.readValue(jsonFile, collectionType);
	    jsonFile.close();

	    filterMenuByPermission(menuList, permissionList);
	    setMenuContext(menuList, ctx);

	    return menuList;

	} catch (JsonMappingException e) {
	    LOGGER.error("Error mapping JSON to Class: " + e.getMessage());
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	} catch (Exception e) {
	    LOGGER.error("Error: " + e.getMessage());
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	}

    }

    private void filterMenuByPermission(List<Menu> menuList, List<String> filterByPermission) {

	if (filterByPermission == null || filterByPermission.isEmpty()) {
	    LOGGER.debug("Permission list is empty");
	    return;
	}

	menuList.removeIf(m -> {
	    m.getMenuItemList().removeIf(mi -> {
		if (StringUtils.trimToNull(mi.getPermission()) == null) {
		    return false;
		} else if (filterByPermission.contains(mi.getPermission())) {
		    return false;
		} else {
		    return true;
		}
	    });

	    if (m.getMenuItemList().isEmpty()) {
		return true;
	    }

	    if (StringUtils.trimToNull(m.getPermission()) == null) {
		return false;
	    } else if (filterByPermission.contains(m.getPermission())) {
		return false;
	    } else {
		return true;
	    }

	});

    }

    private InputStream getJsonResource(String propertyFile, String menuFile) throws Exception {

	Resource configRes = appContext.getResource("file:///" + propertyFile);

	if (!configRes.exists()) {
	    configRes = appContext.getResource("classpath:config/" + menuFile);
	}

	return configRes.getInputStream();

    }

    private void setMenuContext(List<Menu> menuList, String ctx) {
	String ctxMvc = ctx + "/mvc";

	menuList.forEach(m -> {
	    if (m.getMenuItemList().size() > 0) {
		setMenuItemContext(m.getMenuItemList(), ctxMvc);
	    }
	});
    }

    private void setMenuItemContext(List<MenuItem> menuItemList, String ctx) {

	menuItemList.forEach(mi -> {
	    mi.setCtx(ctx);
	    if (mi.getMenuItemList().size() > 0) {
		setMenuItemContext(mi.getMenuItemList(), ctx);
	    }
	});
    }

}
