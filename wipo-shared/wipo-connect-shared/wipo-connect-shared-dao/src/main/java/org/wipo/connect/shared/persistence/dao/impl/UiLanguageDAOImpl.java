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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.UiLanguageDAO;
import org.wipo.connect.shared.persistence.mapping.UiLanguageMapper;







/**
 * The Class UiLanguageDAOImpl.
 *
 * @author fincons
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UiLanguageDAOImpl extends BaseDAO<UiLanguage>  implements UiLanguageDAO {


    @Autowired
    private UiLanguageMapper uiLanguageMapper;



    @Override
    public UiLanguage findByAccount(Long idAccount) {
        return uiLanguageMapper.findByAccount(idAccount);
    }


}