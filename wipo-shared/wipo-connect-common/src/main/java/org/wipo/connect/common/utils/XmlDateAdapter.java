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
package org.wipo.connect.common.utils;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class XmlDateAdapter.
 */
public class XmlDateAdapter extends XmlAdapter<XMLGregorianCalendar, Date> {

	@Override
	public XMLGregorianCalendar marshal(Date date) throws Exception {
		return DateUtils.toXMLGregorianCalendar(date);
	}

	@Override
	public Date unmarshal(XMLGregorianCalendar v) throws Exception {
		return DateUtils.toDate(v);
	}
}