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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;
import org.wipo.connect.shared.exchange.dto.impl.PerformerConfiguration;
import org.wipo.connect.shared.persistence.dao.GeneralParametersManagementDAO;
import org.wipo.connect.shared.persistence.mapping.GeneralParametersManagementMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class GeneralParametersManagementDAOImpl implements GeneralParametersManagementDAO {

    @Autowired
    private GeneralParametersManagementMapper generalParametersManagementMapper;

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public NumberFormatParam findNumberFormat() {
	return generalParametersManagementMapper.findNumberFormat();
    }

    @Override
    @CacheEvict(value = "mainCache", allEntries = true)
    public int updateNumberFormat(NumberFormatParam dto) {
	return generalParametersManagementMapper.updateNumberFormat(dto);
    }

    @Override
    @CacheEvict(value = "mainCache", allEntries = true)
    public List<PerformerConfiguration> findPerformerConfiguration() {
	return generalParametersManagementMapper.findPerformerConfiguration();
    }

    @Override
    @CacheEvict(value = "mainCache", allEntries = true)
    public void savePerformerConfiguration(PerformerConfiguration performerConfiguration) {
	generalParametersManagementMapper.savePerformerConfiguration(performerConfiguration);
    }

    @Override
    public Boolean findPerformerConfigurationByFkCreationClass(Long fkCreationClass) {
	return generalParametersManagementMapper.findPerformerConfigurationByFkCreationClass(fkCreationClass);
    }

}