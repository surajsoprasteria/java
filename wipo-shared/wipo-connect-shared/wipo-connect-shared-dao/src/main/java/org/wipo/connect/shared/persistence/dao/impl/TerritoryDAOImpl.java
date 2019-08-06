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
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;
import org.wipo.connect.shared.exchange.dto.impl.TerritoryNameIdentifierFlat;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.TerritoryDAO;
import org.wipo.connect.shared.persistence.mapping.TerritoryIdentifierFlatMapper;
import org.wipo.connect.shared.persistence.mapping.TerritoryMapper;
import org.wipo.connect.shared.persistence.mapping.TerritoryNameIdentifierFlatMapper;
import org.wipo.connect.shared.persistence.mapping.TerritoryNameMapper;

/**
 * The Class TerritoryDAOImpl.
 *
 * @author Minervini
 */
@Service
@Qualifier("territoryDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TerritoryDAOImpl extends BaseDAO<Territory> implements TerritoryDAO {

    @Autowired
    private TerritoryMapper territoryMapper;

    @Autowired
    private TerritoryNameMapper territoryNameMapper;

    @Autowired
    private TerritoryIdentifierFlatMapper territoryIdentifierFlatMapper;

    @Autowired
    private TerritoryNameIdentifierFlatMapper territoryNameIdentifierFlatMapper;

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Territory> findAllTerritoryWithIdValue() {
	List<Territory> territoryList = territoryMapper.findAllTerritoryWithIdValue();
	return territoryList;
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Territory> findCountriesFromTerritory() {
	return territoryMapper.findCountriesFromTerritory();
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Territory> findCountriesFromTerritoryWithWorld() {
	return territoryMapper.findCountriesFromTerritoryWithWorld();
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Territory> findAll(TerritoryOrderTypeEnum territoryOrderType) {
	List<Territory> territoryList = territoryMapper.findAll(territoryOrderType);
	return territoryList;
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Territory> findAllTerritory() {
	return territoryMapper.findAllTerritory();
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public Territory find(Long id) {
	return territoryMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<TerritoryName> findTerritoryNamesByCode(String code) {
	return territoryNameMapper.findTerritoryNamesByCode(code);
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public TerritoryName findTerritoryNameById(Long id) {
	return territoryNameMapper.selectByPrimaryKey(id);
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public Territory findTerritoryById(Long id) {
	return territoryMapper.selectByPrimaryKey(id);
    }

    @Override
    @Loggable(level = "TRACE")
    @CacheEvict(value = "mainCache", allEntries = true)
    @ExecutionTimeTrackable
    public int insertTerritory(Territory territory) {
	int id = territoryMapper.insert(territory);
	territoryIdentifierFlatMapper.insert(territory.getId(), IdentifierTypeEnum.TISN, territory.getTisn());
	return id;
    }

    @Override
    @Loggable(level = "TRACE")
    @CacheEvict(value = "mainCache", allEntries = true)
    @ExecutionTimeTrackable
    public void updateTerritory(Territory territory) {
	territoryMapper.updateByPrimaryKey(territory);
	// territoryIdentifierFlatMapper.update(territory.getId(), territory.getTisn());
    }

    @Override
    @Loggable(level = "TRACE")
    @CacheEvict(value = "mainCache", allEntries = true)
    @ExecutionTimeTrackable
    public int insertTerritoryName(TerritoryName territoryName) {
	int id = territoryNameMapper.insert(territoryName);
	territoryNameIdentifierFlatMapper.insert(territoryName.getId(), IdentifierTypeEnum.TISA, territoryName.getTisa());
	return id;
    }

    @Override
    @Loggable(level = "TRACE")
    @CacheEvict(value = "mainCache", allEntries = true)
    @ExecutionTimeTrackable
    public void updateTerritoryName(TerritoryName territoryName) {
	territoryNameMapper.updateByPrimaryKey(territoryName);
	territoryNameIdentifierFlatMapper.update(territoryName.getId(), territoryName.getTisa());
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Boolean exsistTisnCode(String code, Long idToExclude) {
	return territoryMapper.exsistTisnCode(code, idToExclude);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Boolean exsistTisaCode(String code, Long idToExclude) {
	return territoryNameMapper.exsistTisaCode(code, idToExclude);
    }

    @Override
    public TerritoryNameIdentifierFlat findIdentifierFromTerritoryCode(String code) {
	return territoryNameIdentifierFlatMapper.findIdentifierFromTerritoryCode(code, IdentifierTypeEnum.TISA);
    }

}