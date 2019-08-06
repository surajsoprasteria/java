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



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.Title;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.TitleDAO;
import org.wipo.connect.shared.persistence.mapping.TitleMapper;




/**
 * The Class TitleDAOImpl.
 */
@Service
@Qualifier("titleDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TitleDAOImpl extends BaseDAO<Title> implements TitleDAO {

    /** The title mapper. */
    @Autowired
    private TitleMapper titleMapper;




    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insert(Title dto) {
        return this.titleMapper.insert(dto);
    }




    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public void update(Title dto) {
        this.titleMapper.updateByPrimaryKey(dto);
    }

}