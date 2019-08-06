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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.WorkPerformer;
import org.wipo.connect.shared.persistence.dao.WorkPerformerDAO;
import org.wipo.connect.shared.persistence.mapping.WorkPerformerMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkPerformerDAOImpl implements WorkPerformerDAO {

    @Autowired
    private WorkPerformerMapper workPerformerMapper;

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int delete(Long id) {
	return this.workPerformerMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public WorkPerformer find(Long id) {
	return this.workPerformerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<WorkPerformer> findByWork(Long idWork) {
	return this.workPerformerMapper.findByWork(idWork);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insert(WorkPerformer dto) {
	return this.workPerformerMapper.insert(dto);
    }

    @Override
    public List<WorkPerformer> findAll() {
	return null;
    }

    @Override
    public WorkPerformer merge(WorkPerformer obj) {
	return null;
    }

    @Override
    public int update(WorkPerformer wp) {
	return this.workPerformerMapper.update(wp);
    }

}