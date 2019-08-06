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

import java.util.List;

import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;

public interface WorkTaskDAO {

    List<WorkTaskItem> findWorkTaskItemByStatus(String statusCode);

    Long insertWorkTask(String taskCode, String cmoCode);

    Long insertWorkTaskDetail(WorkTaskDetail workTaskDetail);

    Long insertWorkTaskItem(Long idWorkTask, Long fkWork, String itemStatus, Long progr, String itemCode);

    void updateTaskItemStatus(Long idWorkTaskItem, SyncTaskStatusEnum statusCode);

    void updateTaskItemWork(Long idWorkTaskItem, Long fkWork);

    void updateTaskItemResponseStatus(Long idWorkTaskItem, SyncTaskStatusEnum responseStatus);

    List<WorkTaskElaborationResult> getWorkTaskElaborationResultByItemCode(List<String> itemCodeList);

}
