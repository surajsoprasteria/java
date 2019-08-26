/*
 * Copyright (C) 2019 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Steria
 *
 */

package org.wipo.connect.shared.identifierprocessor.config;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.identifierprocessor.temp.IdentifierAssigner;
import org.wipo.connect.shared.identifierprocessor.temp.ThreadScopeRunnable;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class CronConfig implements SchedulingConfigurer {

	private static final Logger LOGGER = WipoLoggerFactory.getLogger(CronConfig.class);

	private static final String OFF_VALUE = "OFF";

	@Value("${cron.expression.identifierprocessor}")
	private String cron_expression_IdentifierProcessor;

	@Autowired
	Environment env;

	@Autowired
	IdentifierAssigner identifierAssignerImpl;

	@Bean
	public Executor scheduledExecutor() {
		return Executors.newScheduledThreadPool(5);
	}

	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(scheduledExecutor());

		// if active, register the task - work_submitWorkToCISNET_cron
		addCronTask(taskRegistrar, cron_expression_IdentifierProcessor, new Runnable() {
			public void run() {
				try {
					LOGGER.debug("Start calling identifierAssigner");
					System.out.println("Start calling identifierAssigner");
					identifierAssignerImpl.searchFilesToImport();
					LOGGER.debug("End identifierAssigner call");
					System.out.println("End identifierAssigner call");
					System.out.println("Testing for cron enabled" + new Date());
				} catch (Exception e) {
					LOGGER.error("Error running scheduled task", e);
				}
			}
		});

	}

	private boolean addCronTask(ScheduledTaskRegistrar taskRegistrar, String cronExpression, Runnable task) {
		boolean scheduled = false;

		// if the cron value is present or the value is "OFF" the task will be
		// skeduled
		if (StringUtils.isNotEmpty(cronExpression) && !StringUtils.equalsIgnoreCase(OFF_VALUE, cronExpression)) {
			System.out.println("adding CronTask " + cronExpression);
			taskRegistrar.addCronTask(new ThreadScopeRunnable(task), cronExpression);
			scheduled = true;
		}

		return scheduled;
	}

}
