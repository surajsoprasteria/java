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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.DerivedView;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewName;
import org.wipo.connect.shared.exchange.dto.impl.DerivedViewNameShare;
import org.wipo.connect.shared.persistence.dao.DerivedViewDAO;
import org.wipo.connect.shared.persistence.mapping.DerivedViewMapper;


/**
 * The Class DerivedViewDAOImpl.
 */
@Service
@Qualifier("derivedViewDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DerivedViewDAOImpl implements DerivedViewDAO {

	/** The derived view mapper. */
	@Autowired
	private DerivedViewMapper derivedViewMapper;


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void delete(Long idDerivedView) {
		deleteDerivedViewNameByDerivedView(idDerivedView);
		this.derivedViewMapper.deleteByPrimaryKey(idDerivedView);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void deleteDerivedViewNameByDerivedView(Long idDerivedView) {
		List<DerivedViewName> toDeleteList = findDerivedViewNameByDerivedView(idDerivedView);
		for (DerivedViewName toDelete : toDeleteList) {
			deleteDerivedViewNameShareByDerivedViewName(toDelete.getId());
		}
		this.derivedViewMapper.deleteDerivedViewNameByDerivedView(idDerivedView);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void deleteDerivedViewNameById(Long idDerivedViewName) {
		deleteDerivedViewNameShareByDerivedViewName(idDerivedViewName);
		this.derivedViewMapper.deleteDerivedViewNameById(idDerivedViewName);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void deleteDerivedViewNameShareByDerivedViewName(Long idDerivedViewName) {
		this.derivedViewMapper.deleteDerivedViewNameShareByDerivedViewName(idDerivedViewName);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<DerivedView> findByWork(Long idWork) {
		return this.derivedViewMapper.findByWork(idWork);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<DerivedViewName> findDerivedViewNameByDerivedView(Long idDerivedView) {
		return this.derivedViewMapper.findDerivedViewNameByDerivedView(idDerivedView);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insert(DerivedView dto) {
		return this.derivedViewMapper.insert(dto);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertDerivedViewName(DerivedViewName dto) {
		return this.derivedViewMapper.insertDerivedViewName(dto);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertDerivedViewNameShare(DerivedViewNameShare dto) {
		return this.derivedViewMapper.insertDerivedViewNameShare(dto);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertDerivedViewTerritory(Long fkDerivedView, Long fkTerritory) {
		return this.derivedViewMapper.insertDerivedViewTerritory(fkDerivedView, fkTerritory);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void update(DerivedView dto) {
		this.derivedViewMapper.updateByPrimaryKey(dto);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateDerivedViewName(DerivedViewName dto) {
		this.derivedViewMapper.updateDerivedViewName(dto);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateDerivedViewNameShare(DerivedViewNameShare dto) {
		this.derivedViewMapper.updateDerivedViewNameShare(dto);
	}


	@Override
	public boolean isExistsByNameId(Long nameId) {
		return this.derivedViewMapper.isExistsByNameId(nameId);
	}


	@Override
	public List<Long> findIdNameInWorkByNameMainId(String ipin) {
		return this.derivedViewMapper.findIdNameInWorkByNameMainId(ipin);
	}


	@Override
	public int updateNameRefDerivedViewName(Long newIdName, Long oldIdName) {
		return this.derivedViewMapper.updateNameRefDerivedViewName(newIdName, oldIdName);
	}

}