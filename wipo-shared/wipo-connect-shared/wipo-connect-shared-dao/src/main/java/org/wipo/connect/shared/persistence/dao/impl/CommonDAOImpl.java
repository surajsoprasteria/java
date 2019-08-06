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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.exchange.dto.impl.ImportStatusFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;
import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;
import org.wipo.connect.shared.exchange.dto.impl.Territory;
import org.wipo.connect.shared.exchange.dto.impl.Translation;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.exchange.enumerator.ReferenceTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;
import org.wipo.connect.shared.exchange.utils.DTOUtils;
import org.wipo.connect.shared.exchange.vo.TerritoryHierarchyVO;
import org.wipo.connect.shared.persistence.dao.CommonDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierMapper;
import org.wipo.connect.shared.persistence.mapping.ImportStatusFlatMapper;
import org.wipo.connect.shared.persistence.mapping.IpiRightTypeFlatMapper;
import org.wipo.connect.shared.persistence.mapping.IpiRoleFlatMapper;
import org.wipo.connect.shared.persistence.mapping.ReferenceFlatMapper;
import org.wipo.connect.shared.persistence.mapping.RightTypeFlatMapper;
import org.wipo.connect.shared.persistence.mapping.RoleFlatMapper;
import org.wipo.connect.shared.persistence.mapping.TerritoryMapper;
import org.wipo.connect.shared.persistence.mapping.TranslationFlatMapper;

/**
 * The Class CommonDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CommonDAOImpl implements CommonDAO {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CommonDAOImpl.class);

    /** The territory mapper. */
    @Autowired
    private TerritoryMapper territoryMapper;

    /** The ipi role flat mapper. */
    @Autowired
    private IpiRoleFlatMapper ipiRoleFlatMapper;

    @Autowired
    private IpiRightTypeFlatMapper ipiRightTypeFlatMapper;

    @Autowired
    private RightTypeFlatMapper rightTypeFlatMapper;

    @Autowired
    private RoleFlatMapper roleFlatMapper;

    /** The import status flat mapper. */
    @Autowired
    private ImportStatusFlatMapper importStatusFlatMapper;

    /** The translation Flat Mapper. */
    @Autowired
    private TranslationFlatMapper translationFlatMapper;
    @Autowired
    private IdentifierMapper identifierMapper;

    @Autowired
    private ReferenceFlatMapper referenceFlatMapper;

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findAllTerritory() {
	return this.territoryMapper.findAll();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findAllTerritory(TerritoryOrderTypeEnum territoryOrderType) {
	return this.territoryMapper.findAll(territoryOrderType);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findTerritoryNamesById(Long idTerritory) {
	return territoryMapper.findTerritoryNamesById(idTerritory);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findAllCountriesFromTerritory() {
	return this.territoryMapper.findCountriesFromTerritory();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findAllTerritoryWithIdValue() {
	return this.territoryMapper.findAllTerritoryWithIdValue();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRoleFlat findIpiRoleByCode(String code) {
	return this.ipiRoleFlatMapper.findByCode(code);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public IpiRoleFlat findIpiRoleById(Long id) {
	return this.ipiRoleFlatMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IpiRoleFlat> findAllIpiRole() {
	return this.ipiRoleFlatMapper.findAll();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<IpiRightTypeFlat> findAllIpiRightTypeByCc(Long idCreationClass) {
	return this.ipiRightTypeFlatMapper.findAllByCc(idCreationClass);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Territory findTerritoryById(Long id) {
	return this.territoryMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findTerritoryByParent(Long idParent) {
	return territoryMapper.findTerritoryByParent(idParent);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> findTerritoryByParentTisa(String parentCode) {
	return territoryMapper.findTerritoryByParentTisa(parentCode);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Territory findTerritoryByTisa(String parentCode) {
	return territoryMapper.findTerritoryByTisa(parentCode);
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public TerritoryHierarchyVO getTerritoryHierarchy(String tisaCode) {
	TerritoryHierarchyVO vo = null;
	Territory territory = territoryMapper.findTerritoryByTisa(tisaCode);

	if (territory != null) {
	    vo = new TerritoryHierarchyVO();
	    vo.setTerritory(territory);

	    List<Territory> territoryChildList = territoryMapper.findTerritoryByParentTisa(territory.getTisa());
	    for (Territory territoryChild : territoryChildList) {
		TerritoryHierarchyVO voChild = getTerritoryHierarchy(territoryChild.getTisa());
		vo.getChildList().add(voChild);
	    }
	}

	return vo;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public Set<Long> resolveTerritoryFormula(String territoryFormula) throws WccException {
	Set<Long> territoryCountryToAdd = new HashSet<>();
	Set<Long> territoryCountryToSubtract = new HashSet<>();
	Set<Long> result;

	List<Territory> territoryList = findAllTerritory(TerritoryOrderTypeEnum.CODE_TISA);
	Map<Object, Territory> territoryCodeMap = DTOUtils.listToMapByField(territoryList, "tisa");

	// TODO: maybe necessary to validate formula

	String[] territorySplitted = StringUtils.defaultString(territoryFormula).split("(?=[+])|(?=[-])");
	List<String> territorySplittedList = new ArrayList<>(Arrays.asList(territorySplitted));

	List<String> territoriesToAdd = getTerritoryList(new ArrayList<>(territorySplittedList), "+");
	List<String> territoriesToSubtract = getTerritoryList(new ArrayList<>(territorySplittedList), "-");

	territoryCountryToAdd = loadCountries(territoriesToAdd, territoryCodeMap);
	territoryCountryToSubtract = loadCountries(territoriesToSubtract, territoryCodeMap);

	result = new HashSet<>();
	result.addAll(CollectionUtils.subtract(territoryCountryToAdd, territoryCountryToSubtract));

	return result;
    }

    private Set<Long> loadCountries(List<String> territories, Map<Object, Territory> territoryCodeMap) throws WccException {
	Set<Long> countries = new HashSet<>();
	for (String terr : territories) {
	    Long idTerritory = territoryCodeMap.get(terr).getId();
	    countries.addAll(getChildrenCountries(idTerritory));
	}
	return countries;
    }

    private List<String> getTerritoryList(List<String> territorySplitted, final String splitChar) {
	List<String> resList = new ArrayList<>();

	CollectionUtils.filter(territorySplitted, new Predicate() {
	    @Override
	    public boolean evaluate(Object input) {
		String regex = "([" + splitChar + "]){1}([a-zA-Z0-9]{2,3})";
		return Pattern.matches(regex, (String) input);
	    }
	});

	for (String ter : territorySplitted) {
	    resList.add(StringUtils.remove(ter, splitChar));
	}

	return resList;
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private Set<Long> getChildrenCountries(Collection<Long> idTerritoryList) {
	Set<Long> childrenSet = new HashSet<>();

	for (Long idTerritory : idTerritoryList) {
	    childrenSet.addAll(getChildrenCountries(idTerritory));
	}

	return childrenSet;

    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    private Set<Long> getChildrenCountries(Long idTerritory) {
	Set<Long> childrenSet = new HashSet<>();

	List<Long> subTerritories = territoryMapper.selectTerritoryChild(idTerritory);

	if (subTerritories.isEmpty()) {
	    childrenSet.add(idTerritory);
	} else {
	    for (Long idSubTert : subTerritories) {
		childrenSet.addAll(getChildrenCountries(idSubTert));
	    }
	}

	return childrenSet;

    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertTranslationLabel(Translation label) {
	return translationFlatMapper.insert(label);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void updateTranslationLabel(Translation label) {
	translationFlatMapper.updateByPrimaryKey(label);
    }

    @CacheEvict(value = "mainCache", allEntries = true)
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int deleteTranslationLabel(Long idTranslation) {
	return translationFlatMapper.deleteByPrimaryKey(idTranslation);
    }

    @Override
    public List<Identifier> selectAllIdentifier() {
	return identifierMapper.selectAllIdentifier();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ReferenceFlat> findReferenceByCode(ReferenceTypeEnum code) {
	return this.referenceFlatMapper.findByCode(code.name());
    }

    @Override
    public List<RightTypeFlat> findRightTypeListByCC(List<String> ccCodeList) {
	return this.rightTypeFlatMapper.findRightTypeListByCC(ccCodeList);
    }

    @Override
    public List<RoleFlat> findWorkRoleListByCC(String cc) {
	return roleFlatMapper.findWorkRoleListByCC(cc);
    }

    @Override
    @CacheEvict(value = "mainCache", allEntries = true)
    public void evictMainCache() {
	LOGGER.debug("mainCache evicted");
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<ImportStatusFlat> findAllImportStatus() {
	return importStatusFlatMapper.findAll();
    }

    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public ImportStatusFlat findImportStatusByCode(ImportStatusEnum code) {
	return importStatusFlatMapper.findByCode(code);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<Territory> getTerritoryByParentTisa(String tisaCode) {
	return territoryMapper.findTerritoryByParentTisa(tisaCode);
    }
}