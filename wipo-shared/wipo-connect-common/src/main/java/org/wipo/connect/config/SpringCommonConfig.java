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

package org.wipo.connect.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wipo.connect.common.utils.ConstantUtils;

@Configuration
public class SpringCommonConfig {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public Properties applicationProperties() throws IOException {
	PropertiesFactoryBean pfb = new PropertiesFactoryBean();
	pfb.setFileEncoding(ConstantUtils.CHARSET_UTF8);
	pfb.setSingleton(true);
	pfb.setIgnoreResourceNotFound(false);

	pfb.setLocations(appContext.getResource("classpath:" + "application.properties"));

	pfb.afterPropertiesSet();

	return pfb.getObject();
    }

}