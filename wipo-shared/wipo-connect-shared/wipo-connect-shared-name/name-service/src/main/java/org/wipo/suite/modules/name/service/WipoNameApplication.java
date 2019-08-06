package org.wipo.suite.modules.name.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.wipo.suite.modules.name.service.configuration.SpringNameConfig;

@Configuration
@SpringBootApplication
@EnableCaching
@ImportResource("classpath:config/applicationContext.xml")
@EnableJpaRepositories(basePackages = "org.wipo.suite.modules.name.dal.repositories")
@EnableAutoConfiguration(exclude = {  SecurityAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class })
public class WipoNameApplication extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WipoNameApplication.class);

	public static void main(String[] args) throws Exception {
		SpringNameConfig.loadExternalSpringBootProperties();
		SpringNameConfig.setehCachePersistanceDir();
		SpringApplication.run(WipoNameApplication.class, args);
	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WipoNameApplication.class);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			LOGGER.info("Database url: {}", ctx.getEnvironment().getProperty("microservice.datasource.url"));
			LOGGER.info("Beans registred: {}", ctx.getBeanDefinitionNames().length);
		};
	}

}
