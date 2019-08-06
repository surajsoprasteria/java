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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.DerivedWork;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.DerivedWorkDAO;
import org.wipo.connect.shared.persistence.mapping.DerivedWorkMapper;


/**
 * The Class WorkPerformerDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DerivedWorkDAOImpl extends BaseDAO<DerivedWork> implements DerivedWorkDAO {

	/** The work performer mapper. */
	@Autowired
	private DerivedWorkMapper derivedWorkMapper;


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int delete(Long id) {
		return derivedWorkMapper.deleteByPrimaryKey(id);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public DerivedWork find(Long id) {
		return derivedWorkMapper.selectByPrimaryKey(id);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<DerivedWork> findByParent(Long idWork) {
		return derivedWorkMapper.findByParent(idWork);
	}

	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<DerivedWork> findByChild(Long idWork) {
		return derivedWorkMapper.findByChild(idWork);
	}


	@Override
	public int insert(DerivedWork dto) {
		return derivedWorkMapper.insert(dto);
	}


	@Override
	public int update(DerivedWork dto) {
		return derivedWorkMapper.updateByPrimaryKey(dto);
	}


	@Override
	public List<DerivedWork> findAllChildrenChain(Long parentId) {
		return findAllChildrenChain(parentId, new HashSet<>());
	}

	public List<DerivedWork> findAllChildrenChain(Long parentId, Set<Long> nodes) {
		List<DerivedWork> listOut= new ArrayList<>();

		if(parentId == null || nodes.contains(parentId)){
			return listOut;
		}

		nodes.add(parentId);
		List<DerivedWork> auxList = findByParent(parentId);
		if(auxList != null && !auxList.isEmpty()){
			for(DerivedWork c : auxList){
				listOut.add(c);
				listOut.addAll(findAllChildrenChain(c.getFkWork(), nodes));
			}
		}

		return listOut;
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<DerivedWork> findParentWorkByIdChildWork(Long idWork) {
		return derivedWorkMapper.findParentWorkByIdChildWork(idWork);
	}

	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int deleteByParent(Long fkParentWork) {
		return derivedWorkMapper.deleteByParent(fkParentWork);
	}


	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public boolean checkWorkIsComponentByMainId(String mainId) {
	    return derivedWorkMapper.checkWorkIsComponentByMainId(mainId);
	}

	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public boolean checkWorkIsComponentByIdWork(Long idWork) {
	    return derivedWorkMapper.checkWorkIsComponentByIdWork(idWork);
	}

}