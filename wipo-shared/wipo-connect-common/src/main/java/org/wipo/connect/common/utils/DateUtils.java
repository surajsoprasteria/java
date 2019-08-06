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
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;



/**
 * The Class DateUtils.
 */
public class DateUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DateUtils.class);

    /**
     * validates a yyyy-mm-dd, yyyy mm dd, or yyyy/mm/dd date - makes sure day is within valid range for the month - does NOT validate Feb. 29 on a leap year, only that Feb. CAN have 29 days
     */
    public static final String DATE_STAMP_REGEXP = "^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$";

    private DateUtils() {
        super();
    }



    /**
     * Current xml gregorian calendar.
     *
     * @return the XML gregorian calendar
     */
    public static XMLGregorianCalendar currentXMLGregorianCalendar() {
        return toXMLGregorianCalendar(new Date());
    }

    /**
     * Converts {@link java.util.Date} to
     * {@link javax.xml.datatype.XMLGregorianCalendar}
     *
     * @param date the date
     * @return {@link javax.xml.datatype.XMLGregorianCalendar} value
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            if (xmlCalendar != null) {
            	xmlCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
    		}
        } catch (DatatypeConfigurationException e) {
            LOGGER.error("Error", e);
        }
        return xmlCalendar;
    }



    /**
     * Converts {@link javax.xml.datatype.XMLGregorianCalendar} to
     * {@link java.util.Date}
     *
     * @param calendar the calendar
     * @return {@link java.util.Date} value
     */
    public static Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }




}
