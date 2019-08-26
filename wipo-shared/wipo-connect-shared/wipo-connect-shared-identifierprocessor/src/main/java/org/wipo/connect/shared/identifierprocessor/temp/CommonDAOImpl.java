/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 

package org.wipo.connect.shared.identifierprocessor.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;

*//**
 * The Class CommonDAOImpl.
 *//*
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CommonDAOImpl implements CommonDAO {

	*//** The import status flat mapper. *//*
    @Autowired
    private ImportStatusFlatMapper importStatusFlatMapper;
	
    @ExecutionTimeTrackable
    public ImportStatusFlat findImportStatusByCode(ImportStatusEnum code) {
	return importStatusFlatMapper.findByCode(code);
    }
}*/