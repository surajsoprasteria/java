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
package org.wipo.connect.shared.backend.logging;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.common.logging.IssueLogManager;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
public class IssueLogManagerImpl implements IssueLogManager {

    @Autowired
    private IssueLogBusiness issueLogBusinessImpl;

    @Value("#{configProperties['issueLog.enabled']}")
    private Boolean isLogEnabled;

    @Async
    @Override
    public void saveLog(IssueLog dto) {
	if (BooleanUtils.isTrue(isLogEnabled)) {
	    issueLogBusinessImpl.saveLog(dto);
	}
    }

}
