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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.shared.exchange.dto.impl.WorkImportDetail;
import org.wipo.connect.shared.exchange.enumerator.ImportDetailStatusEnum;
import org.wipo.connect.shared.persistence.dao.WorkImportDetailDAO;
import org.wipo.connect.shared.persistence.mapping.WorkImportDetailMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkImportDetailDAOImpl implements WorkImportDetailDAO {

    @Autowired
    private WorkImportDetailMapper workImportDetailMapper;

    @Override
    public Long insertWorkImportDetail(WorkImportDetail workImportDetail) {
	Long idWorkImportDetail = 0l;
	if (workImportDetail.getFkWork() != null) {
	    if (!workImportDetailMapper.existFkWork(workImportDetail.getFkWork())) {
		int rowNum = workImportDetailMapper.insertWorkImportDetail(workImportDetail);
		if (0 < rowNum) {
		    idWorkImportDetail = workImportDetail.getIdWorkImportDetail();
		}
	    } else {
		workImportDetailMapper.updateImportDetailByFkWork(workImportDetail.getFkWorkImport(), workImportDetail.getFkWork());
	    }
	}
	return idWorkImportDetail;
    }

    @Override
    public List<WorkImportDetail> findWorkImportDetailFromStatus(ImportDetailStatusEnum status) {
	List<WorkImportDetail> workImportDetailList = workImportDetailMapper.findWorkImportDetailFromStatus(status);
	return workImportDetailList;
    }

    @Override
    public int delete(Long id) {
	return 0;
    }

    @Override
    public WorkImportDetail find(Long id) {
	return null;
    }

    @Override
    public List<WorkImportDetail> findAll() {
	return null;
    }

    @Override
    public WorkImportDetail merge(WorkImportDetail obj) {
	return null;
    }

}
