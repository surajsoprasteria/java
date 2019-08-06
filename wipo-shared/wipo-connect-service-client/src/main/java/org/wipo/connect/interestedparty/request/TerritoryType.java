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

package org.wipo.connect.interestedparty.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The Class TerritoryType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TerritoryType", propOrder = {
    "code"
})
public class TerritoryType {

    @XmlElement(required = true)
    protected String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}
