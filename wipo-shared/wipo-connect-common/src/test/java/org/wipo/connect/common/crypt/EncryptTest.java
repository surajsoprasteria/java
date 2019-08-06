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

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.wipo.connect.common.crypto.CryptoUtils;


public class EncryptTest {

	private static final Logger logger = WipoLoggerFactory.getLogger(EncryptTest.class);

	private static final String PASSWORD = "th15-i5.the+pa$$word";
	private static final String SALT = KeyGenerators.string().generateKey();

	@BeforeClass
	public static void init(){
		CryptoUtils.removeCryptographyRestrictions();
	}

	@Test
	public void baseTest(){
		final TextEncryptor encryptor = Encryptors.text(PASSWORD, SALT);

		String toCrypt = "Crypt me, i'm a secret";
		String crypted = encryptor.encrypt(toCrypt);
		String cryptedCheck = encryptor.encrypt(toCrypt);
		String decrypted = encryptor.decrypt(crypted);

		logger.debug("toCrypt: {}",toCrypt);
		logger.debug("encrypted: {}", crypted);
		logger.debug("cryptedCheck: {}", cryptedCheck);
		logger.debug("decrypted: {}", decrypted);

		Assert.assertEquals("Decrypted text", toCrypt, decrypted);
		Assert.assertNotEquals("Encrypted text", crypted, cryptedCheck);
	}

	@Test
	public void differentSaltTest(){
		final String salt2 = KeyGenerators.string().generateKey();

		final TextEncryptor encryptor1 = Encryptors.text(PASSWORD, SALT);
		final TextEncryptor encryptor2 = Encryptors.text(PASSWORD, salt2);

		String toCrypt = "Crypt me, i'm a secret";
		String crypted = encryptor1.encrypt(toCrypt);

		boolean decryptError = false;

		try{
			encryptor2.decrypt(crypted);
		}catch(IllegalStateException e){
			decryptError = true;
		}

		Assert.assertTrue("decryptError", decryptError);
	}

	@Test
	public void differentPasswordTest(){
		final String password2 = "different.passWord";

		final TextEncryptor encryptor1 = Encryptors.text(PASSWORD, SALT);
		final TextEncryptor encryptor2 = Encryptors.text(password2, SALT);

		String toCrypt = "Crypt me, i'm a secret";
		String crypted = encryptor1.encrypt(toCrypt);

		boolean decryptError = false;

		try{
			encryptor2.decrypt(crypted);
		}catch(IllegalStateException e){
			decryptError = true;
		}

		Assert.assertTrue("decryptError", decryptError);
	}

}
