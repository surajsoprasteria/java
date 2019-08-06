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
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;
import org.wipo.connect.shared.persistence.dao.ReferenceDAO;
import org.wipo.connect.shared.persistence.mapping.ReferenceFlatMapper;
import org.wipo.connect.shared.persistence.mapping.ReferenceTypeFlatMapper;



/**
 * The Class referenceDAOImpl.
 *
 */
@Service
@Qualifier("referenceDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ReferenceDAOImpl implements ReferenceDAO {
	
	/** The Reference Flat Mapper. */
	@Autowired
	private ReferenceFlatMapper referenceFlatMapper;
	
	/** The Reference Type Flat Mapper. */
	@Autowired
	private ReferenceTypeFlatMapper referenceTypeFlatMapper;
	

	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public ReferenceFlat findReferenceById(Long id) {
		return this.referenceFlatMapper.selectByPrimaryKey(id);
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<ReferenceFlat> findReferenceByCode(String code) {
		return this.referenceFlatMapper.findByCode(code);
	}


	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<ReferenceFlat> findAllReference() {
		return this.referenceFlatMapper.findAll();
	}
	
	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public ReferenceTypeFlat findReferenceTypeByCode(String code) {
		return referenceTypeFlatMapper.findByCode(code);
	}
	
	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public ReferenceTypeFlat findReferenceTypeById(Long id) {
		return referenceTypeFlatMapper.selectByPrimaryKey(id);
	}
	
	
	@Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public List<ReferenceTypeFlat> findAllReferenceType(){
		return this.referenceTypeFlatMapper.findAll();
	}
		

	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertReference(ReferenceFlat reference) {
		return referenceFlatMapper.insert(reference);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateReference(ReferenceFlat reference) {
		referenceFlatMapper.updateByPrimaryKey(reference);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public int insertReferenceType(ReferenceTypeFlat referenceType) {
		return referenceTypeFlatMapper.insert(referenceType);
	}
	
	@CacheEvict(value = "mainCache", allEntries=true)
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void updateReferenceType(ReferenceTypeFlat referenceType) {
		referenceTypeFlatMapper.updateByPrimaryKey(referenceType);
	}
	
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public boolean existReferenceCode(String code, String refCode, Long idToExclude) {
		return referenceFlatMapper.existReferenceCode(code, refCode, idToExclude);
	}
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistReferenceTypeCode(String code, Long idToExclude){
		return referenceTypeFlatMapper.exsistReferenceTypeCode(code, idToExclude);
	}

}