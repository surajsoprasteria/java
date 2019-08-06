/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.dao.impl;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.ImportDetailStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.dao.ImportDetailStatusFlatDAO;
import org.wipo.connect.shared.persistence.mapping.ImportDetailStatusFlatMapper;

/**
 * The Class ImportDetailStatusFlatDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ImportDetailStatusFlatDAOImpl implements ImportDetailStatusFlatDAO {

    /** The import detail status flat mapper. */
    @Autowired
    private ImportDetailStatusFlatMapper importDetailStatusFlatMapper;

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<ImportDetailStatusFlat> findAll() {
	List<ImportDetailStatusFlat> importDetailStatusFlatList = importDetailStatusFlatMapper.findAll();
	return importDetailStatusFlatList;
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public ImportDetailStatusFlat findByCode(ImportDetailStatusEnum code) {
	ImportDetailStatusFlat importDetailStatusFlat = importDetailStatusFlatMapper.findByCode(code);
	return importDetailStatusFlat;
    }

    @Override
    public int delete(Long id) {
	throw new NotImplementedException("Not implemented");
    }

    @Override
    public ImportDetailStatusFlat find(Long id) {
	throw new NotImplementedException("Not implemented");
    }

    @Override
    public ImportDetailStatusFlat merge(ImportDetailStatusFlat obj) {
	throw new NotImplementedException("Not implemented");
    }

}
