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

package org.wipo.connect.shared.exchange.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.wipo.connect.common.crypto.CryptoHelper;
import org.wipo.connect.common.crypto.CryptoUtils;
import org.wipo.connect.common.logging.WipoLogger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.properties.ResolvableProperties;
import org.wipo.connect.common.utils.ConstantUtils;

/**
 * The Class SpringSharedExchangeConfig.
 */
@Configuration
public class SpringSharedExchangeConfig {

    private static final WipoLogger LOGGER = WipoLoggerFactory.getWipoLogger(SpringSharedExchangeConfig.class);

    @Autowired
    private ApplicationContext appContext;

    @Value("#{systemProperties['wcc.config.dir']}")
    private String externalConfigDir;

    @Value("#{systemProperties['wcc.config.debug']}")
    private Boolean debug;

    @Bean
    public Properties configProperties() throws IOException {
	String externalPropertiesFile = "file:///" + externalConfigDir + "configuration.properties";
	checkExternalPropertiesConfiguration(externalPropertiesFile);

	PropertiesFactoryBean pfb = new PropertiesFactoryBean();
	pfb.setFileEncoding(ConstantUtils.CHARSET_UTF8);
	pfb.setSingleton(true);
	pfb.setIgnoreResourceNotFound(true);

	pfb.setLocations(appContext.getResource("classpath:config/configuration.properties"), appContext.getResource(externalPropertiesFile));

	pfb.afterPropertiesSet();

	ResolvableProperties spelProperties = new ResolvableProperties();
	spelProperties.putAll(pfb.getObject());
	return spelProperties;
    }

    @Bean
    public TextEncryptor textEncryptor() throws IOException, URISyntaxException {
	String cryptoKey = configProperties().getProperty("cryptoKey");
	String cryptoSalt = configProperties().getProperty("cryptoSalt");
	String cryptoSaltHex = Hex.encodeHexString(cryptoSalt.getBytes(ConstantUtils.CHARSET_UTF8));

	CryptoUtils.removeCryptographyRestrictions();

	TextEncryptor textEncryptor = Encryptors.text(cryptoKey, cryptoSaltHex);

	return textEncryptor;
    }

    @Bean
    public CryptoHelper cryptoHelper() throws IOException, URISyntaxException {
	CryptoHelper ch = new CryptoHelper(textEncryptor());
	return ch;
    }

    private void checkExternalPropertiesConfiguration(String externalPropertiesFile) {

	boolean frontend = false;
	boolean backend = false;

	if (StringUtils.contains(appContext.getId(), "wipo-connect-shared-web")) {
	    frontend = true;
	} else if (StringUtils.contains(appContext.getId(), "wipo-connect-shared-backend")) {
	    backend = true;
	} else {
	    return;
	}

	boolean missingProperty = false;
	Properties properties = new Properties();

	try {
	    InputStream inputStream = appContext.getResource(externalPropertiesFile).getInputStream();

	    if (inputStream == null) {
		throw new FileNotFoundException();
	    }

	    properties.load(inputStream);

	    Set<String> propertiesToCheck = new HashSet<>();

	    propertiesToCheck.add("cmo.code");

	    if (frontend) {
		propertiesToCheck.add("path.terms-dir");
		propertiesToCheck.add("path.style-dir");
		propertiesToCheck.add("path.css-file");
		propertiesToCheck.add("path.i18n-dir");
	    }

	    if (backend) {
		propertiesToCheck.add("path.terms-dir");
		propertiesToCheck.add("path.style-dir");
		propertiesToCheck.add("path.css-file");
		propertiesToCheck.add("path.i18n-dir");
	    }

	    for (String property : propertiesToCheck) {
		if (StringUtils.isBlank(properties.getProperty(property))) {
		    LOGGER.errorNoSave("The configuration of property \"{}\" is missing from external file.", property);
		    missingProperty = true;
		}
	    }

	} catch (IOException e) {
	    LOGGER.warn("External configuration file was not found in \"{}\", check \"wcc.config.dir\" system property", externalConfigDir);
	    return;
	}

	if (missingProperty && BooleanUtils.isNotTrue(debug)) {
	    ((ConfigurableApplicationContext) appContext).close();
	}
    }

}
