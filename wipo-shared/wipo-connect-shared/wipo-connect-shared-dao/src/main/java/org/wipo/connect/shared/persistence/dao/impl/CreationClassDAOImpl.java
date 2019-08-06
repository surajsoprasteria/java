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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.CreationClassDAO;
import org.wipo.connect.shared.persistence.mapping.CreationClassFlatMapper;



/**
 * The Class CreationClassDAOImpl.
 *
 */
@Service
@Qualifier("creationClassDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CreationClassDAOImpl extends BaseDAO<CreationClassFlat> implements CreationClassDAO {

	@Autowired
	private CreationClassFlatMapper creationClassFlatMapper;


	
	@Override
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	public CreationClassFlat findCreationClassById(Long id) {
		return creationClassFlatMapper.selectByPrimaryKey(id);
	}
	
	
	@Override
	@Loggable(level = "TRACE")
	@CacheEvict(value = "mainCache", allEntries=true)
	@ExecutionTimeTrackable
	public int insertCreationClass(CreationClassFlat creationClass) {
		return creationClassFlatMapper.insert(creationClass);
	}
	
	@Override
	@Loggable(level = "TRACE")
	@CacheEvict(value = "mainCache", allEntries=true)
	@ExecutionTimeTrackable
	public void updateCreationClass(CreationClassFlat creationClass) {
		creationClassFlatMapper.updateByPrimaryKey(creationClass);
	}
	
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistCode(String code, Long idToExclude){
		return creationClassFlatMapper.exsistCode(code, idToExclude);
	}
	
	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<CreationClassFlat> findAllCreationClass() {
		return this.creationClassFlatMapper.findAll();
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public CreationClassFlat findCreationClassByCode(String code) {
		return this.creationClassFlatMapper.findByCode(code);
	}
		


}