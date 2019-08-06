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
import org.wipo.connect.shared.exchange.dto.impl.WorkDate;
import org.wipo.connect.shared.persistence.dao.WorkDateDAO;
import org.wipo.connect.shared.persistence.mapping.WorkDateMapper;


/**
 * The Class WorkDateDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkDateDAOImpl implements WorkDateDAO {

	/** The work date mapper. */
	@Autowired
	private WorkDateMapper workDateMapper;


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int delete(Long id) {
		return this.workDateMapper.deleteByPrimaryKey(id);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public WorkDate find(Long id) {
		return this.workDateMapper.selectByPrimaryKey(id);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<WorkDate> findByWork(Long idWork) {
		return this.workDateMapper.findByWork(idWork);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insert(WorkDate dto) {
		return this.workDateMapper.insert(dto);
	}


	@Override
	public int update(WorkDate wp) {
		return this.workDateMapper.update(wp);
	}


	@Override
	public List<WorkDate> findAll() {
		return null;
	}


	@Override
	public WorkDate merge(WorkDate obj) {
		return null;
	}

}