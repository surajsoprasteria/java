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
import org.wipo.connect.shared.exchange.dto.impl.IpTask;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;
import org.wipo.connect.shared.persistence.Dao;




/**
 * The IpTaskDAO interface provides methods that manipulate the data from the
 * database.
 *
 * @author minervini
 *
 */
public interface IpTaskDAO extends Dao<IpTask> {
    /**
     * Update ip-task-status.
     *
     * @return the int
     */
    int checkTaskStatusAndUpdate();



    /**
     * Insert ip-task-item-detail.
     *
     * @param ipTaskItemDetail
     *            the id ip-task-item-detail
     * @return the long
     */
    Long insertIpTaskItemDetail(IpTaskItemDetail ipTaskItemDetail);



    /**
     * Insert is-task.
     *
     * @param taskCode
     *            the task code
     * @param taskStatus
     *            the task status
     * @return the long
     */
    Long insertTask(String taskCode, String cmoCode);



    /**
     * Insert ip-task-item.
     *
     * @param idIpTask
     *            the id ip-task
     * @param fkInterestedParty
     *            the id interested party
     * @param itemStatus
     *            the item status
     * @param progr
     *            the progr
     * @return the long
     */
    Long insertTaskItem(Long idIpTask, Long fkInterestedParty,
            String itemStatus, Long progr, String itemCode);


    /**
     * Find task item using task code and status parameters.
     *
     * @param taskCode
     *            the task status
     * @param status
     *            the status
     * @return the int
     */
    int selectTaskByCodeAndStatus(String taskCode,
            SyncTaskStatusEnum status);




	/**
	 * Find ip task item by status.
	 *
	 * @param statusCode the status code
	 * @return the list
	 */
	List<IpTaskItem> findIpTaskItemByStatus(String statusCode);



	void updateTaskItemStatus(Long id, SyncTaskStatusEnum statusCode);



	void updateTaskItemResponseStatus(Long idIpTaskItem, SyncTaskStatusEnum responseStatus);


	void updateTaskItemIp(Long idIpTaskItem, Long sharedId);



	List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(List<String> itemCodeList);

}
