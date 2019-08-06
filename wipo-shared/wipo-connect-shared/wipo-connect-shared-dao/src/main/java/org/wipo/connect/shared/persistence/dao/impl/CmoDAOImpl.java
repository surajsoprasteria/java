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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.Cmo;
import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.CmoDAO;
import org.wipo.connect.shared.persistence.mapping.CmoMapper;




/**
 * The Class CmoDAOImpl.
 *
 * @author Fumagalli
 */
@Service
@Qualifier("cmoDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CmoDAOImpl extends BaseDAO<Cmo> implements CmoDAO {

    /** The cmo mapper. */
    @Autowired
    private CmoMapper cmoMapper;




    @Override
    public List<Cmo> findByAffiliatedName(Long idName) {
        return this.cmoMapper.findByAffiliatedName(idName);
    }




    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public Cmo findByCode(String code) {
        return this.cmoMapper.findByCode(code);
    }




    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public Cmo findByAcronym(String acronym) {
        return this.cmoMapper.findByAcronym(acronym);
    }




    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Cmo> findAll() {
        return cmoMapper.findAll();
    }

    @Override
    @Cacheable(value = "mainCache", keyGenerator = "customKeyGenerator")
    public List<Cmo> findDataSource() {
        return cmoMapper.findDataSource();
    }



	@Override
	public List<Cmo> findCmo(Map<String, Object> searchMap) {
		return cmoMapper.findCmo(searchMap);
	}

	@Override
	public List<ReferenceFlat> findCmoType() {
		return cmoMapper.findCmoType();
	}

	@Override
	@Loggable(level = "TRACE")
	@CacheEvict(value = "mainCache", allEntries=true)
	@ExecutionTimeTrackable
	public int insertCmo(Cmo cmo) {
		return cmoMapper.insert(cmo);
	}
	
	@Override
	@Loggable(level = "TRACE")
	@CacheEvict(value = "mainCache", allEntries=true)
	@ExecutionTimeTrackable
	public void updateCmo(Cmo cmo) {
		cmoMapper.updateByPrimaryKey(cmo);
	}
	
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public Boolean exsistCmoCode(String code, Long idToExclude){
		return cmoMapper.exsistCmoCode(code, idToExclude);
	}
}