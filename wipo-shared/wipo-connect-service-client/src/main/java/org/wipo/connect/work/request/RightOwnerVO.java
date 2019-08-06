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

package org.wipo.connect.work.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class RightOwnerType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RightOwnerVO", propOrder = { 
	"rightOwnerValue",
	"nameMainId" 
	})

public class RightOwnerVO {

    @XmlElement(required = true)
    protected String rightOwnerValue;
    
    protected String nameMainId;

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

}
