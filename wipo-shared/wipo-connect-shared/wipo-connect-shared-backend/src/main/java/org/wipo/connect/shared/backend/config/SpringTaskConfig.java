/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.backend.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.scope.thread.ThreadScopeRunnable;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.business.import_ip.ImportIpService;
import org.wipo.connect.shared.business.import_work.ImportWorkService;
import org.wipo.connect.shared.business.task.IpScheduledTask;
import org.wipo.connect.shared.business.task.WorkScheduledTask;
import org.wipo.connect.shared.business.task.bi.BusinessIntelligenceTask;
import org.wipo.connect.shared.exchange.business.InterestedPartyBusiness;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;
import org.wipo.connect.shared.exchange.business.WorkBusiness;

/**
 * The Class SpringTaskConfig.
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig implements SchedulingConfigurer {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(SchedulingConfigurer.class);

    private static final String OFF_VALUE = "OFF";

    @Autowired
    private WorkScheduledTask workScheduledTaskImpl;

    @Autowired
    private IpScheduledTask ipScheduledTaskImpl;

    @Autowired
    private WorkBusiness workBusinessImpl;

    @Autowired
    private InterestedPartyBusiness interestedPartyBusinessImpl;

    @Autowired
    private IssueLogBusiness issueLogBusinessImpl;

    @Autowired
    private ImportIpService importIpService;

    @Autowired
    private ImportWorkService importWorkService;

    @Autowired
    private BusinessIntelligenceTask businessIntelligenceTaskImpl;

    @Value("#{configProperties['cron.work.submit']}")
    private String cron_work_submitWorkToCISNET;

    @Value("#{configProperties['cron.work.searchFilesToImport']}")
    private String cron_work_searchFilesToImport;

    @Value("#{configProperties['cron.work.processExternalResult']}")
    private String cron_work_processCSIResults;

    @Value("#{configProperties['cron.work.import']}")
    private String cron_work_import;

    @Value("#{configProperties['cron.rightOwner.import']}")
    private String cron_rightOwner_import;

    @Value("#{configProperties['cron.rightOwner.searchFilesToImport']}")
    private String cron_rightOwner_searchFilesToImport;

    @Value("#{configProperties['cron.work.rebuildSolrIndex']}")
    private String cron_work_rebuildSolrIndex;

    @Value("#{configProperties['cron.rightOwner.rebuildSolrIndex']}")
    private String cron_rightOwner_rebuildSolrIndex;

    @Value("#{configProperties['cron.rightOwner.submit']}")
    private String cron_rightOwner_submit;

    @Value("#{configProperties['cron.rightOwner.processExternalResult']}")
    private String cron_rightOwner_processExternalResult;

    @Value("#{configProperties['cron.issueLog.clean']}")
    private String cron_issueLog_clean;

    @Value("#{configProperties['cron.biQuery.import']}")
    private String cron_biQuery_import;

    @Bean
    public Executor scheduledExecutor() {
	return Executors.newScheduledThreadPool(50);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	taskRegistrar.setScheduler(scheduledExecutor());

	if (StringUtils.isNotEmpty(cron_rightOwner_import) && !StringUtils.equalsIgnoreCase(OFF_VALUE, cron_rightOwner_import)) {
	    try {
		importIpService.markAllPendingAsError();
	    } catch (Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    }
	}

	if (StringUtils.isNotEmpty(cron_work_import) && !StringUtils.equalsIgnoreCase(OFF_VALUE, cron_work_import)) {
	    try {
		importWorkService.markAllPendingAsError();
	    } catch (Exception e) {
		LOGGER.error("Error in " + WccUtils.getMethodName(), e);
	    }
	}

	// if active, register the task - work_submitWorkToCISNET_cron
	addCronTask(taskRegistrar, cron_work_submitWorkToCISNET, new Runnable() {
	    @Override
	    public void run() {
		try {
		    workScheduledTaskImpl.submitWorkToCISNET();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - work_searchFilesToImport_cron
	addCronTask(taskRegistrar, cron_work_searchFilesToImport, new Runnable() {
	    @Override
	    public void run() {
		try {
		    workScheduledTaskImpl.searchFilesToImport();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - work_processCSIResults_cron
	addCronTask(taskRegistrar, cron_work_processCSIResults, new Runnable() {
	    @Override
	    public void run() {
		try {
		    workScheduledTaskImpl.processCSIResults();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - ip_searchFilesToImport_cron
	addCronTask(taskRegistrar, cron_rightOwner_searchFilesToImport, new Runnable() {
	    @Override
	    public void run() {
		try {
		    ipScheduledTaskImpl.searchFilesToImport();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - work_rebuildSolrIndex_cron
	addCronTask(taskRegistrar, cron_work_rebuildSolrIndex, new Runnable() {
	    @Override
	    public void run() {
		try {
		    workBusinessImpl.rebuildSolrIndex();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - ip_rebuildSolrIndex_cron
	addCronTask(taskRegistrar, cron_rightOwner_rebuildSolrIndex, new Runnable() {
	    @Override
	    public void run() {
		try {
		    interestedPartyBusinessImpl.rebuildSolrIndex();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	// if active, register the task - work_searchFilesToImport_cron
	addCronTask(taskRegistrar, cron_rightOwner_import, new Runnable() {
	    @Override
	    public void run() {
		try {
		    ipScheduledTaskImpl.processImport();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});
	addCronTask(taskRegistrar, cron_work_import, new Runnable() {
	    @Override
	    public void run() {
		try {
		    workScheduledTaskImpl.processImport();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	addCronTask(taskRegistrar, cron_rightOwner_submit, new Runnable() {
	    @Override
	    public void run() {
		try {
		    ipScheduledTaskImpl.submitIp();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	addCronTask(taskRegistrar, cron_rightOwner_processExternalResult, new Runnable() {
	    @Override
	    public void run() {
		try {
		    ipScheduledTaskImpl.processExternalResults();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	addCronTask(taskRegistrar, cron_issueLog_clean, new Runnable() {
	    @Override
	    public void run() {
		try {
		    issueLogBusinessImpl.removeOldIssues();
		} catch (Exception e) {
		    LOGGER.error("Error running scheduled task", e);
		}
	    }
	});

	addCronTask(taskRegistrar, cron_biQuery_import, new Runnable() {
	    @Override
	    public void run() {
		try {
		    businessIntelligenceTaskImpl.importQueries();
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
	    taskRegistrar.addCronTask(new ThreadScopeRunnable(task), cronExpression);
	    scheduled = true;
	}

	return scheduled;
    }

}
