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

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class DateValidationTest {

	private static final Logger LOGGER = WipoLoggerFactory.getLogger(DateValidationTest.class);

	@Test
	public void checkValidDates(){
		final String[] validDateList = {"00000101",
							 "99991231",
							 "20160920",
							 "20010205",
							 "20000229",
							 "20160229",
							 "20160831"};




		for(String dt : validDateList){
			boolean res = WccUtils.isValidDate(dt);
			LOGGER.debug("Valid date " + dt);
			Assert.assertTrue("Valid date " + dt, res);
		}

	}

	@Test
	public void checkInvalidDates(){
		final String[] invalidDateList = {"0011",
									 "aaaammgg",
									 "2016092",
									 "-1000101",
									 "20001301",
									 "20011555",
									 "20161099",
									 "20000230",
									 "20150229",
									 "21000229",
									 "18000931",
									 "19861131"};



		for(String dt : invalidDateList){
			boolean res = WccUtils.isValidDate(dt);
			LOGGER.debug("Invalid date " + dt);
			Assert.assertFalse("Invalid date " + dt, res);
		}

	}
}
