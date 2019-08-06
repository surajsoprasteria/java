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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.exchange.dto.impl.WorkTask;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailDate;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailDerivedWork;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailDv;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailDvName;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailDvShare;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailIdent;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailPerf;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetailTitle;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;
import org.wipo.connect.shared.persistence.dao.WorkTaskDAO;
import org.wipo.connect.shared.persistence.mapping.WorkTaskMapper;

/**
 * The Class WorkTaskDAOImpl.
 *
 * @author minervini
 */
@Service
@Qualifier("workTaskDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WorkTaskDAOImpl implements WorkTaskDAO {

    /** The work task mapper. */
    @Autowired
    private WorkTaskMapper workTaskMapper;

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public List<WorkTaskItem> findWorkTaskItemByStatus(String statusCode) {
	return this.workTaskMapper.findWorkTaskItemByStatus(statusCode);
    }

    @Override
    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    public Long insertWorkTask(String taskCode, String cmoCode) {
	WorkTask workTask = new WorkTask();
	workTask.setTaskCode(taskCode);
	workTask.setCmoCode(cmoCode);
	this.workTaskMapper.insertWorkTask(workTask);

	return workTask.getIdWorkTask();
    }

    @Override
    public Long insertWorkTaskDetail(WorkTaskDetail workTaskDetail) {
	this.workTaskMapper.insertWorkTaskDetail(workTaskDetail);
	Long idTaskDetail = workTaskDetail.getIdWorkTaskDetail();
	for (WorkTaskDetailTitle workTaskDetailTitle : workTaskDetail.getTitleList()) {
	    workTaskDetailTitle.setFkWorkTaskDetail(idTaskDetail);
	    this.workTaskMapper.insertWorkTaskDetailTitle(workTaskDetailTitle);
	}
	for (String instrument : workTaskDetail.getInstrumentCodeList()) {
	    this.workTaskMapper.insertWorkTaskDetailInstrument(instrument, idTaskDetail);
	}
	for (WorkTaskDetailPerf perf : workTaskDetail.getWorkPerformerList()) {
	    perf.setFkWorkTaskDetail(idTaskDetail);
	    workTaskMapper.insertWorkTaskDetailPerf(perf);
	}
	for (WorkTaskDetailIdent ident : workTaskDetail.getWorkIdentifierList()) {
	    ident.setFkWorkTaskDetail(idTaskDetail);
	    workTaskMapper.insertWorkTaskDetailIdent(ident);
	}
	for (WorkTaskDetailDv dv : workTaskDetail.getDerivedViewList()) {
	    dv.setFkWorkTaskDetail(idTaskDetail);
	    workTaskMapper.insertWorkTaskDetailDv(dv);

	    for (WorkTaskDetailDvName dvName : dv.getDerivedViewNameList()) {
		dvName.setFkWorkTaskDetailDv(dv.getId());
		workTaskMapper.insertWorkTaskDetailDvName(dvName);

		for (WorkTaskDetailDvShare dvShare : dvName.getDerivedViewNameShareList()) {
		    dvShare.setFkWorkTaskDetailDvName(dvName.getId());
		    workTaskMapper.insertWorkTaskDetailDvShare(dvShare);
		}
	    }

	}
	for (WorkTaskDetailDate date : workTaskDetail.getWorkDateList()) {
	    date.setFkWorkTaskDetail(idTaskDetail);
	    workTaskMapper.insertWorkTaskDetailDate(date);
	}
	for (WorkTaskDetailDerivedWork derivedWork : workTaskDetail.getDerivedWorkList()) {
	    derivedWork.setFkWorkTaskDetail(idTaskDetail);
	    workTaskMapper.insertWorkTaskDetailDerivedWork(derivedWork);
	}

	return idTaskDetail;
    }

    @Override
    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    public Long insertWorkTaskItem(Long idWorkTask, Long fkWork, String itemStatus, Long progr, String itemCode) {
	WorkTaskItem workTaskItem = new WorkTaskItem();
	workTaskItem.setFkWorkTask(idWorkTask);
	workTaskItem.setItemStatus(itemStatus);
	workTaskItem.setProgr(progr);
	workTaskItem.setItemCode(itemCode);
	this.workTaskMapper.insertWorkTaskItem(workTaskItem);

	return workTaskItem.getIdWorkTaskItem();
    }

    @Override
    public void updateTaskItemStatus(Long idWorkTaskItem, SyncTaskStatusEnum statusCode) {

	workTaskMapper.updateTaskItemStatus(idWorkTaskItem, statusCode);

    }

    // @Override
    // public int insertWorkTaskCsiResult(WorkTaskCsiResult workTaskCsiResult) {
    // return workTaskMapper.insertWorkTaskCsiResult(workTaskCsiResult);
    // }

    @Override
    public void updateTaskItemResponseStatus(Long idWorkTaskItem, SyncTaskStatusEnum responseStatus) {

	workTaskMapper.updateTaskItemResponseStatus(idWorkTaskItem, responseStatus.name());
    }

    @Override
    public List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(List<String> itemCodeList) {
	return workTaskMapper.findWorkTaskElaborationResultByItemCode(itemCodeList);
    }

    @Override
    public void updateTaskItemWork(Long idWorkTaskItem, Long fkWork) {
	workTaskMapper.updateTaskItemWork(idWorkTaskItem, fkWork);
    };

}
