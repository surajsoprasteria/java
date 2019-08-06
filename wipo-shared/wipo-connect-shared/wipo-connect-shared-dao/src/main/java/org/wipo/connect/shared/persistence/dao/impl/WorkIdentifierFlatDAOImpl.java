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
import org.wipo.connect.shared.exchange.utils.Generator;
import org.wipo.connect.shared.exchange.utils.IdentifierGenerator.WccIdType;
import org.wipo.connect.shared.persistence.dao.WorkIdentifierFlatDAO;
import org.wipo.connect.shared.persistence.mapping.WorkIdentifierFlatMapper;

/**
 * The Class WorkIdentifierFlatDAOImpl.
 */
@Service
@Qualifier("workIdentifierFlatDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkIdentifierFlatDAOImpl implements WorkIdentifierFlatDAO {

    /** The work identifier flat mapper. */
    @Autowired
    private WorkIdentifierFlatMapper workIdentifierFlatMapper;

    /** The identifier generator. */
    private Generator identifierGenerator;

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertWorkId(Long idWork, String type) {
	String wccWorkId = this.identifierGenerator.generateWccIdentifier(WccIdType.WORK, idWork);
	return this.workIdentifierFlatMapper.insert(idWork, type, wccWorkId);
    }

    @Override
    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    public int insertWorkId(Long idWork, String type, String wccWorkId) {
	return this.workIdentifierFlatMapper.insert(idWork, type, wccWorkId);
    }

    /**
     * Gets the identifier generator.
     *
     * @return the identifier generator
     */
    public Generator getIdentifierGenerator() {
	return identifierGenerator;
    }

    public void setIdentifierGenerator(Generator identifierGenerator) {
	this.identifierGenerator = identifierGenerator;
    }

    @Override
    public boolean isExistsISWIdentifierValue(String value) {
	return workIdentifierFlatMapper.isExistsISWIdentifierValue(value);
    }

    @Override
    public int delete(Long id) {
	return workIdentifierFlatMapper.delete(id);
    }

    @Override
    public boolean identifierValueAlreadyPresent(String code, String value, Long workId) {
	return workIdentifierFlatMapper.identifierValueAlreadyPresent(code, value, workId);
    }

}