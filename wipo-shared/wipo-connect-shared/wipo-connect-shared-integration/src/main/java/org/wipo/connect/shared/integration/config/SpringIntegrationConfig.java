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
package org.wipo.connect.shared.integration.config;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.wipo.connect.common.logging.WipoLoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class SpringSharedWebConfig.
 */
@Configuration
@EnableWebMvc
public class SpringIntegrationConfig extends WebMvcConfigurationSupport {

    protected static final Logger LOGGER = WipoLoggerFactory.getLogger(SpringIntegrationConfig.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	converters.add(new MappingJackson2HttpMessageConverter(jacksonObjectMapper));
    }

}
