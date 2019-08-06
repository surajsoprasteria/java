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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.wipo.connect.common.dto.IDownloadable;
import org.wipo.connect.common.exception.WccControllerException;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.querypagination.PageResult;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.business.CommonBusiness;
import org.wipo.connect.shared.exchange.business.MenuBusiness;
import org.wipo.connect.shared.exchange.menu.Menu;
import org.wipo.connect.shared.exchange.vo.RequestResultVO;
import org.wipo.connect.shared.web.utils.SharedConfigUtils;

/**
 * The Class CommonController.
 */
@Controller
@RequestMapping("common")
public class CommonController extends BaseController {

    @Value("#{configProperties['path.user-manual-file']}")
    private String userManualFile;

    @Autowired
    private LocaleResolver localeResolver;

    @Value("#{configProperties['webSiteLink']}")
    private String webSiteLink;

    @Value("#{configProperties['helpUrl']}")
    private String helpUrl;

    @Autowired
    private String externalCss;

    @Autowired
    private CommonBusiness commonBusiness;

    @Autowired
    private MenuBusiness menuBusiness;

    @RequestMapping("home")
    public ModelAndView home() {
	ModelAndView mv = new ModelAndView();
	return mv;
    }

    @RequestMapping("index")
    public ModelAndView index() {
	ModelAndView mv = new ModelAndView();
	mv.addObject("webSiteLink", webSiteLink);
	return mv;
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam(defaultValue = "false") Boolean loginError, @RequestParam(defaultValue = "false") Boolean userDisabled, HttpServletRequest request,
	    HttpServletResponse response) throws WccException {

	ModelAndView mv = new ModelAndView();
	Boolean isBackendDown = false;

	mv.addObject("javaVersion", SystemUtils.JAVA_RUNTIME_NAME + " " + SystemUtils.JAVA_VERSION);
	mv.addObject("osVersion", SystemUtils.OS_NAME + " " + SystemUtils.OS_VERSION + " " + SystemUtils.OS_ARCH);

	Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
	if (flashMap != null) {
	    isBackendDown = (Boolean) flashMap.get("isBackendDown");
	}

	if (BooleanUtils.isNotTrue(isBackendDown)) {
	    mv.addObject("dbInfo", commonBusiness.getDBConnectionInfo());
	    mv.addObject("loginError", loginError);
	    mv.addObject("userDisabled", userDisabled);
	} else {
	    mv.addObject("isBackendDown", isBackendDown);
	}
	return mv;
    }

    @RequestMapping("logout")
    public ModelAndView logout() {
	ModelAndView mv = new ModelAndView();
	return mv;
    }

    @ResponseBody
    @RequestMapping("changeLang.json")
    public RequestResultVO changeLang(String language, HttpServletRequest req, HttpServletResponse resp) {

	RequestResultVO vo;

	try {
	    Locale newLocale = LocaleUtils.toLocale(language);
	    SharedConfigUtils.changeLocale(req, resp, newLocale, localeResolver);
	    vo = new RequestResultVO();
	} catch (Exception e) {
	    vo = new RequestResultVO(e);
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	}

	return vo;
    }

    @ResponseBody
    @RequestMapping("external.css")
    public String externalCss() {
	return externalCss;
    }

    @RequestMapping("downloadUserManual")
    public ModelAndView downloadUserManual() throws WccControllerException {
	try {
	    File userManual = new File(userManualFile);
	    IDownloadable downloadManual;

	    try (FileInputStream fis = new FileInputStream(userManual);) {
		BufferedInputStream is = new BufferedInputStream(fis);
		byte[] fileContent = IOUtils.toByteArray(is);
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

		downloadManual = new IDownloadable() {

		    @Override
		    public Long getFileSize() {
			return userManual.length();
		    }

		    @Override
		    public String getFileName() {
			return userManual.getName();
		    }

		    @Override
		    public byte[] getDocument() {
			return fileContent;
		    }

		    @Override
		    public String getContentType() {
			return mimeTypesMap.getContentType(userManual);
		    }
		};
	    }

	    ModelAndView mv = new ModelAndView("download-view");
	    mv.addObject("download", downloadManual);
	    return mv;
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    throw new WccControllerException(e);
	}
    }

    @RequestMapping("goToHelpUrl")
    public ModelAndView goToHelpUrl() {
	return new ModelAndView("redirect:" + helpUrl);
    }

    @ResponseBody
    @RequestMapping("emptyData.json")
    public PageResult<Serializable> emptyData() {
	PageResult<Serializable> empty = new PageResult<>(new ArrayList<>(), 0, 0);
	return empty;
    }

    @ResponseBody
    @RequestMapping("mainMenu.json")
    public ModelAndView mainMenu(HttpServletRequest request) {

	ModelAndView mv = new ModelAndView();

	try {
	    List<Menu> menuList = new ArrayList<>();
	    List<String> permissions = new ArrayList<>();

	    Collection<? extends GrantedAuthority> granted = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

	    if (granted != null) {
		permissions = granted.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	    }

	    menuList = menuBusiness.getMainMenuList(permissions, request.getContextPath());
	    mv.addObject("menuList", menuList);

	} catch (Exception e) {
	    LOGGER.error("Error in home", e);
	}

	return mv;
    }

    @ResponseBody
    @RequestMapping("homeMenu.json")
    public ModelAndView homeMenu(HttpServletRequest request) {

	ModelAndView mv = new ModelAndView();

	try {
	    List<Menu> menuList = new ArrayList<>();
	    List<String> permissions = new ArrayList<>();

	    Collection<? extends GrantedAuthority> granted = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

	    if (granted != null) {
		permissions = granted.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	    }

	    menuList = menuBusiness.getHomeMenuList(permissions, request.getContextPath());
	    setHomeMenuStyle(menuList);
	    mv.addObject("menuList", menuList);

	} catch (Exception e) {
	    LOGGER.error("Error in home", e);
	}

	return mv;
    }

    private void setHomeMenuStyle(List<Menu> menuList) {

	menuList.parallelStream().forEach(menu -> {

	    if (!menu.getMenuItemList().isEmpty()) {

		if (menu.getMenuItemList().size() == 1) {
		    menu.getMenuItemList().forEach(mi -> mi.addCssClass("size120"));
		} else if (menu.getMenuItemList().size() == 2) {
		    menu.getMenuItemList().forEach(mi -> mi.addCssClass("size60"));
		} else if (menu.getMenuItemList().size() == 3) {
		    menu.getMenuItemList().forEach(mi -> mi.addCssClass("size40"));
		} else if (menu.getMenuItemList().size() == 4) {
		    menu.getMenuItemList().forEach(mi -> mi.addCssClass("size30"));
		} else {
		    menu.getMenuItemList().forEach(mi -> mi.addCssClass("size20"));
		}

		if (menu.getMenuItemList().size() == 1) {
		    menu.getMenuItemList().get(0).addCssClass("single");
		} else {
		    menu.getMenuItemList().get(0).addCssClass("first");
		    menu.getMenuItemList().get(menu.getMenuItemList().size() - 1).addCssClass("last");
		}

		// Third level menu not allowed
		menu.getMenuItemList().forEach(mi -> {
		    if (!mi.getMenuItemList().isEmpty()) {
			mi.getMenuItemList().clear();
		    }
		});

	    }

	});

    }

}