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

package org.wipo.connect.shared.persistence.dao;

import java.util.Date;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;
import org.wipo.connect.shared.persistence.Dao;

/**
 * The WorkImportDAO interface provides methods that manipulate the data from the database.
 *
 * @author minervini
 *
 */
public interface WorkImportDAO extends Dao<WorkImport> {

    WorkImport insertWorkImport(WorkImport WorkImport);

    int insertWorkImportFile(WorkImportFile WorkImportFile);

    List<WorkImport> findWorkImportFromStatus(ImportStatusEnum... statusCode);

    int updateStatus(Long idWorkImport, Long idStatus);

    int updateStatus(Long idWorkImport, ImportStatusEnum statusCode);

    int updateImportStartDate(Long idWorkImport, Date startDate);

    int updateImportEndDate(Long idWorkImport, Date endDate);

    WorkImportFile findWorkImportFileById(Long id);

    Long countWorkImportFileByName(String fileName);

    String findImportCodeByWorkId(Long workId);

    WorkImport updateRowResult(WorkImport workImport);

    int markAllPendingAsError();

}
