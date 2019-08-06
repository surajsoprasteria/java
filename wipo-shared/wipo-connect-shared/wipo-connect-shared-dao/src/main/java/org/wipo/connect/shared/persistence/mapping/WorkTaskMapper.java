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
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDuplicate;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;

/**
 * The Interface WorkTaskMapper.
 *
 * @author minervini
 */
public interface WorkTaskMapper {

    // TaskDetail tables
    /**
     * Find work task detail by status.
     *
     * @param statusCode
     *            the status code
     * @return the list
     */
    List<WorkTaskItem> findWorkTaskItemByStatus(@Param("statusCode") String statusCode);

    /**
     * Find work task elaboration result by item code.
     *
     * @param itemCodeList
     *            the item code list
     * @return the list
     */
    List<WorkTaskElaborationResult> findWorkTaskElaborationResultByItemCode(@Param("itemCodeList") List<String> itemCodeList);

    // Task & taskItem tables
    /**
     * Insert work task.
     *
     * @param workTask
     *            the work task
     * @return the int
     */
    int insertWorkTask(WorkTask workTask);

    /**
     * Insert work task detail.
     *
     * @param workTaskDetail
     *            the work task detail
     * @return the int
     */
    int insertWorkTaskDetail(WorkTaskDetail workTaskDetail);

    /**
     * Insert work task detail instrument.
     *
     * @param instrumentCode
     *            the instrument code
     * @param fkWorkTaskDetail
     *            the fk work task detail
     * @return the int
     */
    int insertWorkTaskDetailInstrument(@Param(value = "instrumentCode") String instrumentCode, @Param(value = "fkWorkTaskDetail") Long fkWorkTaskDetail);

    /**
     * Insert work task detail title.
     *
     * @param workTaskDetailTitle
     *            the work task detail title
     * @return the int
     */
    int insertWorkTaskDetailTitle(WorkTaskDetailTitle workTaskDetailTitle);

    /**
     * Insert work task duplicate.
     *
     * @param workTaskDuplicate
     *            the work task duplicate
     * @return the int
     */
    int insertWorkTaskDuplicate(WorkTaskDuplicate workTaskDuplicate);

    /**
     * Insert work task item.
     *
     * @param workTaskItem
     *            the work task item
     * @return the int
     */
    int insertWorkTaskItem(WorkTaskItem workTaskItem);

    /**
     * Update task item status.
     *
     * @param idWorkTaskItem
     *            the id work task item
     * @param statusCode
     *            the status code
     * @return the int
     */
    int updateTaskItemStatus(@Param(value = "id") Long idWorkTaskItem, @Param("statusCode") SyncTaskStatusEnum statusCode);

    /**
     * Update task item work.
     *
     * @param idWorkTaskItem
     *            the id work task item
     * @param fkWork
     *            the fk work
     * @return the int
     */
    int updateTaskItemWork(@Param(value = "id") Long idWorkTaskItem, @Param("fkWork") Long fkWork);

    /**
     * Update task item response status.
     *
     * @param idWorkTaskItem
     *            the id work task item
     * @param responseStatus
     *            the response status
     * @return the int
     */
    int updateTaskItemResponseStatus(@Param(value = "id") Long idWorkTaskItem, @Param("responseStatus") String responseStatus);

    // /**
    // * Insert work task cis result.
    // *
    // * @param workTaskCsiResult
    // * the work task cis result
    // * @return the int
    // */
    // int insertWorkTaskCsiResult(WorkTaskCsiResult workTaskCsiResult);

    /**
     * Insert work task detail perf.
     *
     * @param dto
     *            the dto
     * @return the int
     */
    int insertWorkTaskDetailPerf(WorkTaskDetailPerf dto);

    /**
     * Insert work task detail ident.
     *
     * @param dto
     *            the dto
     * @return the int
     */
    int insertWorkTaskDetailIdent(WorkTaskDetailIdent dto);

    /**
     * Insert work task detail dv.
     *
     * @param dto
     *            the dto
     * @return the int
     */
    int insertWorkTaskDetailDv(WorkTaskDetailDv dto);

    /**
     * Insert work task detail dv ter.
     *
     * @param fkWorkTaskDetailDv
     *            the fk work task detail dv
     * @param territoryCode
     *            the territory code
     * @return the int
     */
    int insertWorkTaskDetailDvTer(@Param("fkWorkTaskDetailDv") Long fkWorkTaskDetailDv, @Param("territoryCode") String territoryCode);

    /**
     * Insert work task detail dv name.
     *
     * @param dto
     *            the dto
     * @return the int
     */
    int insertWorkTaskDetailDvName(WorkTaskDetailDvName dto);

    /**
     * Insert work task detail dv share.
     *
     * @param dto
     *            the dto
     * @return the int
     */
    int insertWorkTaskDetailDvShare(WorkTaskDetailDvShare dto);

    int insertWorkTaskDetailDate(WorkTaskDetailDate dto);

    int insertWorkTaskDetailDerivedWork(WorkTaskDetailDerivedWork dto);

}
