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
package org.wipo.connect.shared.business.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.shared.exchange.business.IssueLogBusiness;
import org.wipo.connect.shared.persistence.dao.IssueLogDAO;

import net.bull.javamelody.MonitoredWithSpring;

@Service
@MonitoredWithSpring
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IssueLogBusinessImpl implements IssueLogBusiness {

    @Autowired
    private IssueLogDAO issueLogDAO;

    @Value("#{configProperties['issueLog.remove-after']}")
    private Integer REMOVE_AFTER_DAYS;

    @Override
    public void saveLog(IssueLog dto) {
	issueLogDAO.insert(dto);
    }

    @Override
    public List<IssueLog> findAll() {
	return issueLogDAO.findAll();
    }

    @Override
    public void deleteAll() {
	issueLogDAO.deleteAll();
    }

    @Override
    public IssueLog find(Long idIssueLog) throws WccException {
	return issueLogDAO.findAll(idIssueLog);
    }

    @Override
    public void removeOldIssues() throws WccException {
	Calendar minDate = Calendar.getInstance();
	minDate.add(Calendar.DAY_OF_YEAR, -1 * ObjectUtils.defaultIfNull(REMOVE_AFTER_DAYS, 1));
	issueLogDAO.deleteOld(minDate.getTime());
    }

}
