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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.WorkStatusFlat;
import org.wipo.connect.shared.exchange.enumerator.WorkStatusEnum;
import org.wipo.connect.shared.persistence.dao.WorkStatusFlatDAO;
import org.wipo.connect.shared.persistence.mapping.WorkStatusFlatMapper;




/**
 * The Class WorkStatusFlatDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkStatusFlatDAOImpl implements WorkStatusFlatDAO {

    /** The work status flat mapper. */
    @Autowired
    private WorkStatusFlatMapper workStatusFlatMapper;



    @Cacheable(value = "mainCache",keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public WorkStatusFlat find(Long id ) {
        return this.workStatusFlatMapper.selectByPrimaryKey(id);
    }


    @Cacheable(value = "mainCache",keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public List<WorkStatusFlat> findAll() {
        return this.workStatusFlatMapper.findAll();
    }


    @Cacheable(value = "mainCache",keyGenerator = "customKeyGenerator")
    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public WorkStatusFlat findByCode(WorkStatusEnum code) {
        return this.workStatusFlatMapper.findByCode(code);
    }

}