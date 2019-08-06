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
package org.wipo.connect.common.querypagination;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = -510422350361601841L;

    private Integer column;
    private String dir;

    public Integer getColumn() {
	return column;
    }

    public void setColumn(Integer column) {
	this.column = column;
    }

    public String getDir() {
	return dir;
    }

    public void setDir(String dir) {
	this.dir = dir;
    }

}
