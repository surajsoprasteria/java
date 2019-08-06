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
package org.wipo.connect.common.customvalidation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConversionUtils;

public class DateFormatTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DateFormatTest.class);

    @Test
    public void dateFormat() throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_STAMP_SEP);
    	sdf.setLenient(false);
    	String dateStart;
    	String dateEnd;
    	Date aux;

    	dateStart = "1986-09-02";

    	aux = sdf.parse(dateStart);
    	dateEnd = sdf.format(aux);
    	LOGGER.info("date: {} --> {}",dateStart,dateEnd);
    	Assert.assertEquals(dateStart, dateEnd);


    	aux = DateUtils.parseDateStrictly(dateStart, ConversionUtils.DATE_STAMP_SEP);
    	dateEnd = sdf.format(aux);
    	LOGGER.info("date: {} --> {}",dateStart,dateEnd);
    	Assert.assertEquals(dateStart, dateEnd);

    }


}
