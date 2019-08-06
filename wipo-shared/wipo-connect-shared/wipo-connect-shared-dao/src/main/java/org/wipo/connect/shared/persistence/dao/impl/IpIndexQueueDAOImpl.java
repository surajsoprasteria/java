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
import org.wipo.connect.shared.exchange.dto.impl.IpIndexQueue;
import org.wipo.connect.shared.exchange.enumerator.IndexQueueActionEnum;
import org.wipo.connect.shared.persistence.dao.IpIndexQueueDAO;
import org.wipo.connect.shared.persistence.mapping.IpIndexQueueMapper;




/**
 * The Class IpIndexQueueDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IpIndexQueueDAOImpl implements IpIndexQueueDAO {


    /** The ip index queue mapper. */
    @Autowired
    private IpIndexQueueMapper ipIndexQueueMapper;


    @Override
    public List<Long> findIpByAction(IndexQueueActionEnum action) {
        return ipIndexQueueMapper.findIpByAction(action);
    }


    @Override
    public int deleteByIp(List<Long> idList) {
        return ipIndexQueueMapper.deleteByIp(idList);
    }


    @Override
    public int insert(IpIndexQueue dto) {
        if(dto.getFkInterestedParty() != null){
            ipIndexQueueMapper.deleteByIp(Arrays.asList(dto.getFkInterestedParty()));
        }
        return ipIndexQueueMapper.insert(dto);
    }


    @Override
    public IpIndexQueue find(Long id) {
        return ipIndexQueueMapper.selectByPrimaryKey(id);
    }


}