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

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface WorkImportDetailMapper extends Mapper<WorkImportDetail> {

    int insertWorkImportDetail(WorkImportDetail workImportDetail);

    List<WorkImportDetail> findWorkImportDetailFromStatus(ImportDetailStatusEnum status);

    int updateImportDetailStatus(@Param("idWorkImportDetail") Long idWorkImportDetail, @Param("idStatus") Long idStatus);

    int updateImportDetailStatusCode(@Param("idWorkImportDetail") Long idWorkImportDetail, @Param("statusCode") ImportDetailStatusEnum statusCode);

    void updateImportDetailByFkWork(@Param("fkWorkImport") Long fkWorkImport, @Param("fkWork") Long fkWork);

    Boolean existFkWork(Long fkWork);

}
