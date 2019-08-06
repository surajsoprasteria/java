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

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * The Class SpringAsyncExecutorConfig.
 */
@Configuration
@EnableAsync
public class SpringAsyncExecutorConfig {

	/**
	 * Async executor.
	 *
	 * @return the executor
	 */
	@Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(300);
        executor.setQueueCapacity(2000);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }

}
