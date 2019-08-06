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
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.enumerator.IpiRightTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.RightTypeEnum;
import org.wipo.connect.shared.persistence.dao.RightTypeDAO;
import org.wipo.connect.shared.persistence.mapping.IpiRightTypeFlatMapper;
import org.wipo.connect.shared.persistence.mapping.RightTypeFlatMapper;

/**
 * The Class CreationClassDAOImpl.
 *
 */
@Service
@Qualifier("rightTypeDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RightTypeDAOImpl implements RightTypeDAO {

    /** The Right Type Flat Mapper. */
    @Autowired
    private RightTypeFlatMapper rightTypeFlatMapper;

    /** The ipi Right Type Flat Mapper. */
    @Autowired
    private IpiRightTypeFlatMapper ipiRightTypeFlatMapper;

    // @Autowired
    // private RightSplitMapper rightSplitMapper;

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertRightType(RightTypeFlat rightType) {
	return rightTypeFlatMapper.insert(rightType);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void updateRightType(RightTypeFlat rightType) {
	rightTypeFlatMapper.updateByPrimaryKey(rightType);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Boolean exsistRightTypeCode(String code, Long idToExclude) {
	return rightTypeFlatMapper.exsistRightTypeCode(code, idToExclude);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertIpiRightType(IpiRightTypeFlat irt) {
	return ipiRightTypeFlatMapper.insert(irt);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void updateIpiRightType(IpiRightTypeFlat irt) {
	ipiRightTypeFlatMapper.updateByPrimaryKey(irt);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertIpiRightTypeCreationClass(Long fkIpiRightType, Long fkCreationClass) {
	return ipiRightTypeFlatMapper.insertIpiRightTypeCreationClass(fkIpiRightType, fkCreationClass);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertRightTypeCreationClass(Long fkRightType, Long fkCreationClass) {
	return rightTypeFlatMapper.insertRightTypeCreationClass(fkRightType, fkCreationClass);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Boolean exsistIpiRightTypeCode(String code, Long idToExclude) {
	return ipiRightTypeFlatMapper.exsistIpiRightTypeCode(code, idToExclude);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRightTypeFlat findIpiRightTypeById(Long id) {
	return this.ipiRightTypeFlatMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IpiRightTypeFlat> findAllIpiRightType() {
	return this.ipiRightTypeFlatMapper.findAll();
    }

    // @Override
    // @Loggable(level = "TRACE")
    // @ExecutionTimeTrackable
    // public List<IpiRightTypeFlat> findIpiRightTypeByRightType(Long fkRightType){
    // return this.rightSplitMapper.findIpiRightTypeByRightType(fkRightType);
    // }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRightTypeFlat findIpiRightTypeByCode(IpiRightTypeEnum code) {
	return this.findIpiRightTypeByCode(code.name());
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRightTypeFlat findIpiRightTypeByCode(String code) {
	return this.ipiRightTypeFlatMapper.findByCode(code);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RightTypeFlat findRightTypeByCode(RightTypeEnum code) {
	return this.rightTypeFlatMapper.findByCode(code.name());
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RightTypeFlat findRightTypeByCode(String code) {
	return this.rightTypeFlatMapper.findByCode(code);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public RightTypeFlat findRightTypeById(Long id) {
	return this.rightTypeFlatMapper.selectByPrimaryKey(id);

    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<RightTypeFlat> findAllRightType() {
	return this.rightTypeFlatMapper.findAll();
    }

}