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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.enumerator.IdentifierManagerRefTableEnum;
import org.wipo.connect.shared.exchange.dto.impl.IdentifierManager;
import org.wipo.connect.shared.persistence.dao.IdentifierManagerDAO;
import org.wipo.connect.shared.persistence.mapping.IdentifierManagerMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IdentifierManagerDAOImpl implements IdentifierManagerDAO {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(IdentifierManagerDAOImpl.class);

    @Value("#{configProperties['cmo.code']}")
    private String CMO_CODE;

    @Autowired
    private IdentifierManagerMapper identifierManagerMapper;

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = Throwable.class)
    public String getNewMainIdentifier(IdentifierManagerRefTableEnum code) {
	Long newVal;

	IdentifierManager idCount = identifierManagerMapper.selectAndLock(code.getRefTable());
	if (idCount == null) {
	    LOGGER.warn("Identifier counter '" + code + "' not found");
	    identifierManagerMapper.createNewCounter(code.getRefTable());
	    LOGGER.warn("Identifier counter '" + code + "' created");
	    idCount = identifierManagerMapper.selectAndLock(code.getRefTable());
	}

	newVal = idCount.getLastVal();
	boolean isUnique;
	String mainId;
	do {
	    newVal++;
	    StringBuilder idSb = new StringBuilder();
	    idSb.append(CMO_CODE);
	    idSb.append("-");
	    idSb.append(newVal);
	    mainId = idSb.toString();
	    isUnique = identifierManagerMapper.checkIdUniqueness(code.getRefTable(), mainId, null);
	} while (!isUnique);

	identifierManagerMapper.update(code.getRefTable(), newVal);

	return mainId;
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = Throwable.class)
    public boolean checkIdUniqueness(IdentifierManagerRefTableEnum code, String mainId) {
	return identifierManagerMapper.checkIdUniqueness(code.getRefTable(), mainId, null);
    }

    @Override
    @ExecutionTimeTrackable
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = Throwable.class)
    public boolean checkIdUniqueness(IdentifierManagerRefTableEnum code, String mainId, List<Long> idsToExclude) {
	return identifierManagerMapper.checkIdUniqueness(code.getRefTable(), mainId, idsToExclude);
    }

}