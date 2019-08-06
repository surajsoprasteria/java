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



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.Address;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.AddressDAO;



/**
 * The Class AddressDAOImpl.
 *
 * @author Minervini
 */
@Service
@Qualifier("addressDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AddressDAOImpl extends BaseDAO<Address> implements AddressDAO {

}