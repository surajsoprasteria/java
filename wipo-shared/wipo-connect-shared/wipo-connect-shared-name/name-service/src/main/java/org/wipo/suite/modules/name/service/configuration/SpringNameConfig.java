package org.wipo.suite.modules.name.service.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.wipo.connect.common.conversion.JacksonObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class SpringSharedWebConfig.
 */
@Configuration
public class SpringNameConfig extends WebMvcConfigurationSupport {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SpringNameConfig.class);

	private static final String cacheName = "wcc-name";

	@Autowired
	private JacksonObjectMapper jacksonObjectMapper;

	@PostConstruct
	public void init() {
		jacksonObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter(jacksonObjectMapper));
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).mediaType("json", MediaType.APPLICATION_JSON);
	}

	public static void loadExternalSpringBootProperties() {
		String externalPath = System.getProperty("wcc.config.dir") + File.separator;
		System.setProperty("spring.config.name", "application,microservice");
		System.setProperty("spring.config.additional-location", externalPath);
		try {
			File propFile = new File(externalPath + "microservice.properties");
			// File propFile = new File("D:\\microservice.properties");
			if (!propFile.exists()) {
				LOGGER.warn("External config file not found: {}microservice.properties", externalPath);
			}
		} catch (Exception e) {
			LOGGER.warn("Error reading file {}microservice.properties", externalPath);
		}
	}

	public static void setehCachePersistanceDir() {
		FileSystem fileSystem = FileSystems.getDefault();
		String cachePath = System.getProperty("java.io.tmpdir") + cacheName + fileSystem.getSeparator();
		System.setProperty("java.io.tmpdir", cachePath);
		File directory = new File(cachePath);
		
		  try { FileUtils.forceDelete(directory); } catch (IOException e) { }
		 
		directory.mkdirs();

	}

}
