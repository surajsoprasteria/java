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
package org.wipo.connect.common.caseconverter;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class CaseConverterTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CaseConverterTest.class);

    @Test
    public void baseTest() throws IllegalAccessException {

	Family testDto = new Family(new Person("Jessica", GenreEnum.F, 30, null),
		new Person("Brian", GenreEnum.M, 35, "Brian desc"),
		Arrays.asList(
			new Person("Zack", GenreEnum.M, 5, "desc AAA"),
			new Person("Vilma", GenreEnum.F, 3, "desc BBB")),
		"family OK");
	testDto.setAttach(new byte[] { 1, 2, 3, 4, 5 });

	CaseConverter.convertCase(testDto, CaseConversionEnum.UPPERCASE, new String[] { "org.wipo.connect" });

	LOGGER.info(testDto.getMom().getName());

    }

}
