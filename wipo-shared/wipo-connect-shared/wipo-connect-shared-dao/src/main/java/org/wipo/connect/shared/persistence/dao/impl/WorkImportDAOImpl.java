/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.common.utils.WccUtils;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.persistence.dao.WorkImportDAO;
import org.wipo.connect.shared.persistence.mapping.WorkImportMapper;

/**
 * The Class WorkImportDAOImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkImportDAOImpl implements WorkImportDAO {

    /** The Constant IMPORT_PREFIX. */
    private static final String IMPORT_PREFIX = "WO-";

    /** The work import mapper. */
    @Autowired
    private WorkImportMapper workImportMapper;

    @Loggable(level = "DEBUG")
    @Override
    public WorkImport insertWorkImport(WorkImport workImport) {
	if (StringUtils.isEmpty(workImport.getImportCode())) {
	    SimpleDateFormat sdf = new SimpleDateFormat(ConversionUtils.DATE_TIME_STAMP_MILLI);
	    String code = IMPORT_PREFIX + sdf.format(new Date());
	    workImport.setImportCode(code);
	}

	workImportMapper.insertWorkImport(workImport);

	Long idWorkImport = workImport.getIdWorkImport();
	for (WorkImportFile workImportFile : workImport.getWorkImportFileList()) {
	    workImportFile.setFkWorkImport(idWorkImport);
	    workImportMapper.insertWorkImportFile(workImportFile);
	}

	return workImport;
    }

    @Override
    public int updateStatus(Long idWorkImport, Long idStatus) {
	return workImportMapper.updateImportStatus(idWorkImport, idStatus);
    }

    @Override
    public int updateStatus(Long idWorkImport, ImportStatusEnum statusCode) {
	return workImportMapper.updateImportStatusCode(idWorkImport, statusCode);
    }

    public List<WorkImport> findWorkImportFromStatus(ImportStatusEnum... statusCode) {
	if (statusCode == null || statusCode.length == 0) {
	    throw new IllegalArgumentException("At least one statusCode is required");
	}
	return workImportMapper.findWorkImportFromStatus(statusCode);
    }

    @Override
    public List<WorkImport> findAll() {
	return workImportMapper.findAll();
    }

    @Override
    public int updateImportStartDate(Long idWorkImport, Date startDate) {
	return workImportMapper.updateImportStartDate(idWorkImport, startDate);
    }

    @Override
    public int updateImportEndDate(Long idWorkImport, Date endDate) {
	return workImportMapper.updateImportEndDate(idWorkImport, endDate);
    }

    @Override
    public WorkImportFile findWorkImportFileById(Long id) {
	return workImportMapper.findWorkImportFileById(id);
    }

    @Override
    public int delete(Long id) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public WorkImport find(Long id) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public WorkImport merge(WorkImport obj) {
	throw new NotImplementedException("not implemented: " + WccUtils.getMethodName());
    }

    @Override
    public int insertWorkImportFile(WorkImportFile workImportFile) {
	return workImportMapper.insertWorkImportFile(workImportFile);
    }

    @Override
    public WorkImport updateRowResult(WorkImport workImport) {
	workImportMapper.updateRowResult(workImport);
	return workImport;
    }

    @Override
    public Long countWorkImportFileByName(String fileName) {
	return workImportMapper.countWorkImportFileByName(fileName);
    }

    @Override
    public String findImportCodeByWorkId(Long workId) {
	return workImportMapper.findImportCodeByWorkId(workId);
    }

    @Override
    public int markAllPendingAsError() {
	return workImportMapper.markAllPendingAsError();
    }

}
