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



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.WorkIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;
import org.wipo.connect.shared.persistence.dao.WorkIndexQueueDAO;
import org.wipo.connect.shared.persistence.mapping.WorkIndexQueueMapper;





/**
 * The Class WorkIndexQueueDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkIndexQueueDAOImpl implements WorkIndexQueueDAO {


    /** The work index queue mapper. */
    @Autowired
    private WorkIndexQueueMapper workIndexQueueMapper;


    @Override
    public List<Long> findWorkByAction(IndexQueueActionEnum action) {
        return workIndexQueueMapper.findWorkByAction(action);
    }


    @Override
    public int deleteByWork(List<Long> idList) {
        return workIndexQueueMapper.deleteByWork(idList);
    }


    @Override
    public int insert(WorkIndexQueue dto) {
        if(dto.getFkWork() != null){
            workIndexQueueMapper.deleteByWork(Arrays.asList(dto.getFkWork()));
        }
        return workIndexQueueMapper.insert(dto);
    }


    @Override
    public WorkIndexQueue find(Long id) {
        return workIndexQueueMapper.selectByPrimaryKey(id);
    }


}