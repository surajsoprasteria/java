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

import java.util.List;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.SecGroup;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.SecGroupDAO;
import org.wipo.connect.shared.persistence.mapping.SecGroupMapper;

@Service
@Qualifier("secGroupDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SecGroupDAOImpl extends BaseDAO<SecGroup> implements SecGroupDAO {

    @SuppressWarnings("unused")
    private static Logger logger = WipoLoggerFactory.getLogger(SecGroupDAOImpl.class);

    @Autowired
    private SecGroupMapper secGroupMapper;

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public SecGroup getSecGroupById(Long id) {
	return super.find(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<SecGroup> getSecGroupList() {
	return this.secGroupMapper.findAll();
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<SecGroup> findSecGroupByIdAccount(Long fkAccount) {
	return this.secGroupMapper.findSecGroupByIdAccount(fkAccount);
    }

}
