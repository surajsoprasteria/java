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
import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;
import org.wipo.connect.shared.persistence.dao.IdentifierFlatDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierFlatMapper;


/**
 * The Class IdentifierFlatDAOImpl.
 */
@Service
@Qualifier("identifierDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IdentifierFlatDAOImpl implements IdentifierFlatDAO {

	@Autowired
	private IdentifierFlatMapper identifierFlatMapper;


	//@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public IdentifierFlat find(Long id) {
		return this.identifierFlatMapper.selectByPrimaryKey(id);
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public IdentifierFlat findByCode(IdentifierTypeEnum code) {
		return this.identifierFlatMapper.findByCode(code);
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<IdentifierFlat> findAll() {
		return this.identifierFlatMapper.findAll();
	}


	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertIdentifier(IdentifierFlat Identifier) {
		return identifierFlatMapper.insert(Identifier);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateIdentifier(IdentifierFlat Identifier) {
		identifierFlatMapper.updateByPrimaryKey(Identifier);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int deleteIdentifier(Long idIdentifier){
		return identifierFlatMapper.deleteIdentifier(idIdentifier);
	}

	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistIdentifierCode(String code, Long idToExclude){
		return identifierFlatMapper.exsistIdentifierCode(code, idToExclude);
	}

	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean isIdentifierUsedInAnyEntity(Long fkIdentifier){
		return identifierFlatMapper.isIdentifierUsedInAnyEntity(fkIdentifier);
	}
	
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertIdentifierCreationClass(Long fkIdentifier, Long fkCreationClass) {
		return identifierFlatMapper.insertIdentifierCreationClass(fkIdentifier, fkCreationClass);
	}
}
