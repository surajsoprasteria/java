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

package org.wipo.connect.shared.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.IssueLog;
import org.wipo.connect.shared.persistence.dao.IssueLogDAO;
import org.wipo.connect.shared.persistence.mapping.IssueLogMapper;

@Service
@Qualifier("issueLogDAOImpl")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
public class IssueLogDAOImpl implements IssueLogDAO {

    @Autowired
    private IssueLogMapper issueLogMapper;

    @Override
    public int insert(IssueLog dto) {
	return issueLogMapper.insert(dto);
    }

    @Override
    public List<IssueLog> findAll() {
	return issueLogMapper.findAll();
    }

    @Override
    public int deleteAll() {
	return issueLogMapper.deleteAll();
    }

    @Override
    public IssueLog findAll(Long idIssueLog) {
	return issueLogMapper.find(idIssueLog);
    }

    @Override
    public int deleteOld(Date minDate) {
	return issueLogMapper.deleteOld(minDate);
    }

}
