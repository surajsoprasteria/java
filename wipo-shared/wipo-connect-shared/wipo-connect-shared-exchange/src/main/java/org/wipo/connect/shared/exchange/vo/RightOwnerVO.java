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
package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class RightOwnerVO implements Serializable{
    private static final long serialVersionUID = 249700844527669948L;

    private String rightOwnerValue;

    private String nameMainId;

    /** The exec delete. */
    private Boolean execDelete = false;


    public String getRightOwnerValue() {
        return rightOwnerValue;
    }

    public void setRightOwnerValue(String rightOwnerValue) {
        this.rightOwnerValue = rightOwnerValue;
    }

    public String getNameMainId() {
        return nameMainId;
    }

    public void setNameMainId(String nameMainId) {
        this.nameMainId = nameMainId;
    }

    public Boolean getExecDelete() {
        return execDelete;
    }

    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }

    public String getRightOwnerValueLike(){
	 return "%" + StringUtils.defaultString(rightOwnerValue) + "%";
	    }
}
