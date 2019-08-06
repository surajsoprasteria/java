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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.shared.exchange.dto.impl.BankAccount;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.BankAccountDAO;
import org.wipo.connect.shared.persistence.mapping.BankAccountMapper;

@Service
@Qualifier("bankAccountDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BankAccountDAOImpl extends BaseDAO<BankAccount> implements BankAccountDAO {

    /** The logger. */
    @SuppressWarnings("unused")
    private static Logger LOGGER = WipoLoggerFactory.getLogger(BankAccountDAOImpl.class);

    /** The bank account mapper. */
    @Autowired
    private BankAccountMapper bankAccountMapper;

}