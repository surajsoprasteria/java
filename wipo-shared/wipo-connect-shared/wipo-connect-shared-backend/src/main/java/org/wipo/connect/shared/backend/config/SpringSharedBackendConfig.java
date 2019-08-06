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

package org.wipo.connect.shared.backend.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wipo.connect.common.logging.WipoLoggerFactory;

@Configuration
public class SpringSharedBackendConfig {

    protected static final Logger LOGGER = WipoLoggerFactory.getLogger(SpringSharedBackendConfig.class);

    @Autowired
    protected Properties configProperties;

    @Bean
    public void checkPropertiesConfiguration() {
	String[] properties = {};

	for (String property : properties) {
	    if (StringUtils.isBlank(configProperties.getProperty(property))) {
		LOGGER.warn("The configuration of property \"{}\" might be wrong", property);
	    }
	}
    }

}
