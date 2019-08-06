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
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.persistence.dao.RoleDAO;
import org.wipo.connect.shared.persistence.mapping.IpiRoleFlatMapper;
import org.wipo.connect.shared.persistence.mapping.RoleFlatMapper;



/**
 * The Class CreationClassDAOImpl.
 *
 */
@Service
@Qualifier("roleDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoleDAOImpl implements RoleDAO {

	/** The role flat mapper. */
	@Autowired
	private RoleFlatMapper roleFlatMapper;
	
	
	/** The ipi role flat mapper. */
	@Autowired
	private IpiRoleFlatMapper ipiRoleFlatMapper;

	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<RoleFlat> findAllRole() {
		return this.roleFlatMapper.findAll();
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public RoleFlat findRoleByCode(String code) {
		return this.roleFlatMapper.findByCode(code);
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public RoleFlat findRoleById(Long id) {
		return this.roleFlatMapper.selectByPrimaryKey(id);
	}
	
	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public IpiRoleFlat findIpiRoleById(Long id) {
		return this.ipiRoleFlatMapper.selectByPrimaryKey(id);
	}
	
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertRole(RoleFlat role) {
		return roleFlatMapper.insert(role);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateRole(RoleFlat role) {
		roleFlatMapper.updateByPrimaryKey(role);
	}
	
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertIpiRole(IpiRoleFlat ipi) {
		return ipiRoleFlatMapper.insert(ipi);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateIpiRole(IpiRoleFlat ipi) {
		ipiRoleFlatMapper.updateByPrimaryKey(ipi);
	}
	
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistWorkCode(String code, Long idToExclude){
		return roleFlatMapper.exsistWorkCode(code, idToExclude);
	}
	
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistIpiCode(String code, Long idToExclude){
		return ipiRoleFlatMapper.exsistIpiCode(code, idToExclude);
	}
	
	
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertIpiCreationClass(Long fkIpiRole, Long fkCreationClass) {
		return ipiRoleFlatMapper.insertIpiCreationClass(fkIpiRole, fkCreationClass);
	}
		


}