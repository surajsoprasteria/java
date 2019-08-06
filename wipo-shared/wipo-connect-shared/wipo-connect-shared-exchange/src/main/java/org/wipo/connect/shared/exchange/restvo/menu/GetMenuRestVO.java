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

package org.wipo.connect.shared.exchange.restvo.menu;

import java.io.Serializable;
import java.util.List;

public class GetMenuRestVO implements Serializable {

    private static final long serialVersionUID = -3737790283140553921L;

    private List<String> permissionList;

    private String ctx;

    public List<String> getPermissionList() {
	return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
	this.permissionList = permissionList;
    }

    public String getCtx() {
	return ctx;
    }

    public void setCtx(String ctx) {
	this.ctx = ctx;
    }

}
