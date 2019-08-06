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

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.utils.XmlDateAdapter;

/**
 * The Class WorkDate.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkDate", propOrder = { "dateTypeCode", "dateValue" })
public class WorkDate {

	@XmlElement(required = true)
	protected String dateTypeCode;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date dateValue;

	public String getDateTypeCode() {
		return dateTypeCode;
	}

	public void setDateTypeCode(String dateTypeCode) {
		this.dateTypeCode = dateTypeCode;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}



}
