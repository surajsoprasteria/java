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
package org.wipo.connect.common.crypt;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.crypto.CryptoUtils;


public class CryptoHelperTest {

	private static final Logger logger = WipoLoggerFactory.getLogger(CryptoHelperTest.class);

	private static final String PASSWORD = "th15-i5.the+pa$$word";
	private static final String SALT = KeyGenerators.string().generateKey();


	@BeforeClass
	public static void init(){
		CryptoUtils.removeCryptographyRestrictions();
	}

	@Test
	public void baseTest(){
		TextEncryptor encryptor = Encryptors.text(PASSWORD, SALT);
		CryptoHelper cryptoHelper = new CryptoHelper(encryptor);

		CryptedDto dto = new CryptedDto();

		dto.setField1(10);
		dto.setField2("AAA");
		dto.setField3(Arrays.asList("AAA","BBB"));
		dto.setField4(BigDecimal.ONE);
		dto.setField5("AAA");
		dto.setField6(new CryptedInnerDto());
		dto.getField6().setFieldA("AAA");
		dto.getField6().setFieldB("BBB");
		dto.setField7(new CryptedInnerDto());
		dto.getField7().setFieldA("AAA");
		dto.getField7().setFieldB("BBB");

		logger.debug(dto.toString());

		CryptedDto dto2 = cryptoHelper.encrypt(dto);
		logger.debug(dto2.toString());

		Assert.assertEquals("Field1",  dto.getField1(), dto2.getField1());
		Assert.assertNotEquals("Field2",  dto.getField2(), dto2.getField2());
		Assert.assertEquals("Field6.FieldA",  dto.getField6().getFieldA(), dto2.getField6().getFieldA());
		Assert.assertNotEquals("Field6.FieldB",  dto.getField6().getFieldB(), dto2.getField6().getFieldB());
		Assert.assertEquals("Field7.FieldA",  dto.getField7().getFieldA(), dto2.getField7().getFieldA());
		Assert.assertEquals("Field7.FieldB",  dto.getField7().getFieldB(), dto2.getField7().getFieldB());


		CryptedDto dto3 = cryptoHelper.decrypt(dto2);
		logger.debug(dto3.toString());

		Assert.assertEquals("Field1",  dto.getField1(), dto3.getField1());
		Assert.assertEquals("Field2",  dto.getField2(), dto3.getField2());
		Assert.assertEquals("Field6.FieldA",  dto.getField6().getFieldA(), dto3.getField6().getFieldA());
		Assert.assertEquals("Field6.FieldB",  dto.getField6().getFieldB(), dto3.getField6().getFieldB());
		Assert.assertEquals("Field7.FieldA",  dto.getField7().getFieldA(), dto3.getField7().getFieldA());
		Assert.assertEquals("Field7.FieldB",  dto.getField7().getFieldB(), dto3.getField7().getFieldB());


		String s0 = "admin!wcc";
		String s1 = cryptoHelper.encrypt(s0);
		String s2 = cryptoHelper.decrypt(s1);

		logger.debug(s0);
		logger.debug(s1);
		logger.debug(s2);

		Assert.assertNotEquals(s0, s1);
		Assert.assertEquals(s0, s2);



	}
}
