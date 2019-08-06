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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.shared.exchange.business.MenuBusiness;
import org.wipo.connect.shared.exchange.menu.Menu;
import org.wipo.connect.shared.exchange.restvo.menu.GetMenuRestVO;

@RestController
@RequestMapping(value = "/menu")
public class MenuRestController extends BaseRestController {

    @Autowired
    @Qualifier("menuBusinessImpl")
    private MenuBusiness menuBusiness;

    @ResponseBody
    @RequestMapping("getHomeMenuList")
    public List<Menu> getHomeMenuList(@RequestBody GetMenuRestVO vo) throws WccException {
	return menuBusiness.getHomeMenuList(vo.getPermissionList(), vo.getCtx());
    }

    @ResponseBody
    @RequestMapping("getMainMenuList")
    public List<Menu> getSecondMenuList(@RequestBody GetMenuRestVO vo) throws WccException {
	return menuBusiness.getMainMenuList(vo.getPermissionList(), vo.getCtx());
    }
}
