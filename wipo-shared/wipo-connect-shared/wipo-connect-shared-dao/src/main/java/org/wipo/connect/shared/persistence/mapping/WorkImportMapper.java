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

package org.wipo.connect.shared.persistence.mapping;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.WorkImport;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportFile;
import org.wipo.connect.shared.exchange.enumerator.ImportStatusEnum;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface WorkImportMapper extends Mapper<WorkImport> {

    int insertWorkImport(WorkImport workImport);

    int insertWorkImportFile(WorkImportFile workImportFile);

    List<WorkImport> findWorkImportFromStatus(@Param("statusCodeList") ImportStatusEnum[] statusCodeList);

    @Override
    List<WorkImport> findAll();

    int updateImportStatus(@Param("idWorkImport") Long idWorkImport, @Param("idStatus") Long idStatus);

    int updateImportStatusCode(@Param("idWorkImport") Long idWorkImport, @Param("statusCode") ImportStatusEnum status);

    int updateImportStartDate(@Param("idWorkImport") Long idWorkImport, @Param("startDate") Date startDate);

    int updateImportEndDate(@Param("idWorkImport") Long idWorkImport, @Param("endDate") Date endDate);

    WorkImportFile findWorkImportFileById(@Param("id") Long id);

    Long countWorkImportFileByName(@Param("fileName") String fileName);

    String findImportCodeByWorkId(Long workId);

    int updateRowResult(WorkImport workImport);

    int markAllPendingAsError();
}
