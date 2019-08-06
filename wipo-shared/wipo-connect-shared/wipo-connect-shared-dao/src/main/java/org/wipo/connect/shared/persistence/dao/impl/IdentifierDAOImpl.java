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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.exchange.dto.impl.Identifier;
import org.wipo.connect.shared.persistence.BaseDAO;
import org.wipo.connect.shared.persistence.dao.IdentifierDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierMapper;


/**
 * The Class IdentifierDAOImpl.
 */
@Service
@Qualifier("identifierDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IdentifierDAOImpl extends BaseDAO<Identifier> implements IdentifierDAO {

	/** The identifier mapper. */
	@Autowired
	private IdentifierMapper identifierMapper;


	@CacheEvict(value="mainCache")
	@Override
	@Loggable(level = "TRACE")
	@ExecutionTimeTrackable
	public void insertIdentifier(Identifier identifier) {
		this.identifierMapper.insertIdentifier(identifier);
	}


	@Override
	@Cacheable(value = "mainCache",keyGenerator = "customKeyGenerator")
	public Identifier findByCode(String code) {
		return this.identifierMapper.findByCode(code);
	}

}
