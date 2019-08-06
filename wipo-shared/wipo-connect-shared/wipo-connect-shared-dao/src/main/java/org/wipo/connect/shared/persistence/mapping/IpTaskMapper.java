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
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.exchange.dto.impl.IpTask;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailAffiliation;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailAffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailIdent;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailName;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;




/**
 * The Interface IpTaskMapper.
 *
 * @author minervini
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface IpTaskMapper extends Mapper<IpTask> {

    /**
     * Insert ip task.
     *
     * @param ipTask
     *            the ip task
     * @return the int
     */
    int insertIpTask(IpTask ipTask);



    /**
     * Insert ip task item.
     *
     * @param ipTask
     *            the ip task
     * @return the int
     */
    int insertIpTaskItem(IpTaskItem ipTask);



    /**
     * Insert ip task item detail.
     *
     * @param ipTaskItemDetail
     *            the ip task item detail
     * @return the int
     */
    int insertIpTaskItemDetail(IpTaskItemDetail ipTaskItemDetail);



    /**
     * Select task by code and status.
     *
     * @param taskCode
     *            the task code
     * @param status
     *            the status
     * @return the int
     */
    int selectTaskByCodeAndStatus(@Param("taskCode") String taskCode,
            @Param("status") SyncTaskStatusEnum status);




    /**
     * Update task status.
     *
     * @return the int
     */
    int updateTaskStatus();

    int insertIpTaskDetailAff(IpTaskDetailAffiliation ipTaskDetailAffiliation);
    int insertIpTaskDetailAffDomain(IpTaskDetailAffiliationDomain ipTaskDetailAffiliationDomain);
    int insertIpTaskDetailName(IpTaskDetailName ipTaskDetailName);
    int insertIpTaskDetailTerritory(@Param(value = "fkIpTaskItemDetail") Long fkIpTaskItemDetail, @Param("code") String code);
    int insertIpTaskDetailIdent(IpTaskDetailIdent ipTaskDetailIdent);

    /**
     * Find ip task detail by status.
     *
     * @param statusCode the status code
     * @return the list
     */
    List<IpTaskItem> findIpTaskItemByStatus(@Param("statusCode") String statusCode);

    int updateTaskItemStatus(@Param(value = "id") Long idIpTaskItem, @Param("statusCode") SyncTaskStatusEnum statusCode);

    int updateTaskItemResponseStatus(@Param(value = "id") Long idIpTaskItem, @Param("responseStatus") String responseStatus);

    int updateTaskItemIp(@Param(value = "id") Long idIpTaskItem, @Param("fkInterestedParty") Long fkInterestedParty);

    List<IpTaskElaborationResult> findIpTaskElaborationResultByItemCode(@Param("itemCodeList") List<String> itemCodeList);

}
