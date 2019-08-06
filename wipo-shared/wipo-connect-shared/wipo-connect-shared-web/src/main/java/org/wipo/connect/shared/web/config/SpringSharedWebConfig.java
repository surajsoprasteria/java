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
package org.wipo.connect.shared.web.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.web.viewresolver.CustomJsonView;

/**
 * The Class SpringSharedWebConfig.
 */
@Configuration

public class SpringSharedWebConfig extends WebMvcConfigurationSupport {

    protected static final Logger LOGGER = WipoLoggerFactory.getLogger(SpringSharedWebConfig.class);

    @Autowired
    private CustomJsonView customJsonView;

    @Autowired
    protected Properties configProperties;

    @Bean
    @Qualifier("externalCss")
    public String externalCss() {
	String externalCss = configProperties.getProperty("path.css-file");
	String externalCssFile = "";

	try (InputStream cssIs = new FileInputStream(externalCss)) {
	    externalCssFile = IOUtils.toString(cssIs, ConstantUtils.CHARSET_UTF8);
	} catch (Exception e) {
	    LOGGER.error("Error in " + WccUtils.getMethodName() + ": " + e.getMessage());
	}

	return externalCssFile;
    }

    @Bean
    public void checkPropertiesConfiguration() {
	String[] properties = {};

	for (String property : properties) {
	    if (StringUtils.isBlank(configProperties.getProperty(property))) {
		LOGGER.warn("The configuration of property \"{}\" might be wrong", property);
	    }
	}
    }

    @Bean
    public ViewResolver xmlViewResolver() {
	XmlViewResolver xmlViewResolver = new XmlViewResolver();
	xmlViewResolver.setOrder(0);
	xmlViewResolver.setLocation(new ClassPathResource("spring/views.xml"));
	return xmlViewResolver;
    }

    @Bean
    public ViewResolver jspViewResolver() {
	InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();

	jspViewResolver.setOrder(2);
	jspViewResolver.setPrefix("/WEB-INF/view/");
	jspViewResolver.setSuffix(".jsp");
	jspViewResolver.setViewClass(JstlView.class);
	jspViewResolver.setExposedContextBeanNames("msg", "securityUtils", "applicationProperties", "configProperties");

	return jspViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/terms/**").addResourceLocations("file:///" + configProperties.getProperty("path.terms-dir"));
	registry.addResourceHandler("/style/**").addResourceLocations("file:///" + configProperties.getProperty("path.style-dir"));
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	configurer.ignoreAcceptHeader(true).mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
	registry.enableContentNegotiation(customJsonView);
	registry.viewResolver(xmlViewResolver());
	registry.viewResolver(jspViewResolver());
    }
}
