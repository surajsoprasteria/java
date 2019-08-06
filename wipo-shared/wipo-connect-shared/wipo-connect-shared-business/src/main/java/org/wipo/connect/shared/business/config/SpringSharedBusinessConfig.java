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
package org.wipo.connect.shared.business.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wipo.connect.common.i18n.ImportMessagesDecoder;
import org.wipo.connect.common.import_reader.IpReaderFactory;
import org.wipo.connect.common.import_reader.WorkReaderFactory;
import org.wipo.connect.common.import_writer.IpWriterFactory;
import org.wipo.connect.common.utils.ConstantUtils;

@Configuration
public class SpringSharedBusinessConfig {

    @Autowired
    private ApplicationContext appContext;

    @Value("#{configProperties['path.i18n-dir']}/import-messages.properties")
    private String importMessages;

    @Bean
    public Properties importMessages() throws IOException {
	PropertiesFactoryBean pfb = new PropertiesFactoryBean();
	pfb.setFileEncoding(ConstantUtils.CHARSET_UTF8);
	pfb.setSingleton(true);
	pfb.setIgnoreResourceNotFound(true);

	pfb.setLocations(appContext.getResource("classpath:config/" + "import-messages.properties"), appContext.getResource("file:///" + importMessages));

	pfb.afterPropertiesSet();

	return pfb.getObject();
    }

    @Bean
    public ImportMessagesDecoder workImportMessagesDecoder() throws IOException {
	ImportMessagesDecoder decoder = new ImportMessagesDecoder("work-", importMessages());
	return decoder;
    }

    @Bean
    public ImportMessagesDecoder ipImportMessagesDecoder() throws IOException {
	ImportMessagesDecoder decoder = new ImportMessagesDecoder("ro-", importMessages());
	return decoder;
    }

    @Bean
    public IpReaderFactory ipReaderFactory() {
	IpReaderFactory ipReaderFactory = new IpReaderFactory();
	return ipReaderFactory;
    }

    @Bean
    public IpWriterFactory ipWriterFactory() {
	IpWriterFactory ipWriterFactory = new IpWriterFactory();
	return ipWriterFactory;
    }

    @Bean
    public WorkReaderFactory workReaderFactory() {
	WorkReaderFactory workReaderFactory = new WorkReaderFactory();
	return workReaderFactory;
    }

}
