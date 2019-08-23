package org.wipo.connect.shared.identifierprocessor.config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
public class CronConfig {
	
	@Scheduled(cron = "${cron.expression}")	
	public void demoServiceMethod()
	{
		System.out.println("Method executed at every time set which we in cron expression. Current time is :: "+ new Date());
	}

}
