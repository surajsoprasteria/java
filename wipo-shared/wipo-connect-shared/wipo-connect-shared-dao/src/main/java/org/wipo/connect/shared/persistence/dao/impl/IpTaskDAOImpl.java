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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.enumerator.SyncTaskStatusEnum;
import org.wipo.connect.shared.exchange.dto.impl.IpTask;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailAffiliation;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailAffiliationDomain;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailIdent;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskDetailName;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskElaborationResult;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;
import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;
import org.wipo.connect.shared.persistence.dao.IpTaskDAO;
import org.wipo.connect.shared.persistence.mapping.IpTaskMapper;

/**
 * The Class IpTaskDAOImpl.
 *
 * @author minervini
 */
@Service
@Qualifier("ipTaskDAOImpl")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class IpTaskDAOImpl implements IpTaskDAO {

    private static final int MAX_ID_LIST_SIZE = 250;

    /** The ip task mapper. */
    @Autowired
    private IpTaskMapper ipTaskMapper;

    @Override
    public int checkTaskStatusAndUpdate() {
	int updatedRows = this.ipTaskMapper.updateTaskStatus();
	return updatedRows;
    }

    @Override
    public int delete(Long id) {
	throw new NotImplementedException("merge Not Implemented");
    }

    @Override
    public IpTask find(Long id) {
	throw new NotImplementedException("find Not Implemented");
    }

    @Override
    public List<IpTask> findAll() {
	throw new NotImplementedException("findAll Not Implemented");
    }

    @Override
    public Long insertIpTaskItemDetail(IpTaskItemDetail ipTaskItemDetail) {
	this.ipTaskMapper.insertIpTaskItemDetail(ipTaskItemDetail);
	Long id = ipTaskItemDetail.getIdIpTaskItemDetail();
	for (IpTaskDetailName iptdn : ipTaskItemDetail.getNameList()) {
	    iptdn.setFkIpTaskItemDetail(id);
	    this.ipTaskMapper.insertIpTaskDetailName(iptdn);
	}

	for (String iptdt : ipTaskItemDetail.getCitizenshipCodeList()) {
	    this.ipTaskMapper.insertIpTaskDetailTerritory(ipTaskItemDetail.getId(), iptdt);
	}
	for (IpTaskDetailIdent iptdi : ipTaskItemDetail.getInterestedPartyIdentifierFlatList()) {
	    iptdi.setFkIpTaskItemDetail(id);
	    this.ipTaskMapper.insertIpTaskDetailIdent(iptdi);
	}

	for (IpTaskDetailAffiliation iptdaff : ipTaskItemDetail.getAffiliationList()) {
	    iptdaff.setFkIpTaskItemDetail(id);
	    this.ipTaskMapper.insertIpTaskDetailAff(iptdaff);
	    for (IpTaskDetailAffiliationDomain iptdaffd : iptdaff.getAffiliationDomainList()) {
		iptdaffd.setFkIpTaskItemDetail(id);
		iptdaffd.setFkIpTaskDetailAffiliation(iptdaff.getId());
		this.ipTaskMapper.insertIpTaskDetailAffDomain(iptdaffd);
	    }
	}
	return id;
    }

    @Override
    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    public Long insertTask(String taskCode, String cmoCode) {
	IpTask ipTask = new IpTask();
	ipTask.setTaskCode(taskCode);
	ipTask.setCmoCode(cmoCode);
	this.ipTaskMapper.insertIpTask(ipTask);

	return ipTask.getIdIpTask();
    }

    @Override
    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    public Long insertTaskItem(Long idIpTask, Long fkInterestedParty, String itemStatus, Long progr, String itemCode) {

	IpTaskItem ipTaskItem = new IpTaskItem();
	ipTaskItem.setFkIpTask(idIpTask);
	ipTaskItem.setItemStatus(itemStatus);
	ipTaskItem.setProgr(progr);
	ipTaskItem.setItemCode(itemCode);
	this.ipTaskMapper.insertIpTaskItem(ipTaskItem);

	return ipTaskItem.getId();
    }

    @Override
    public IpTask merge(IpTask obj) {
	throw new NotImplementedException("merge Not Implemented");
    }

    @Override
    public int selectTaskByCodeAndStatus(String taskCode, SyncTaskStatusEnum status) {
	int rows = this.ipTaskMapper.selectTaskByCodeAndStatus(taskCode, status);
	return rows;
    }

    @Loggable(level = "TRACE")
    @ExecutionTimeTrackable
    @Override
    public List<IpTaskItem> findIpTaskItemByStatus(String statusCode) {
	return this.ipTaskMapper.findIpTaskItemByStatus(statusCode);
    }

    @Override
    public void updateTaskItemStatus(Long id, SyncTaskStatusEnum statusCode) {
	this.ipTaskMapper.updateTaskItemStatus(id, statusCode);
    }

    @Override
    public void updateTaskItemResponseStatus(Long idIpTaskItem, SyncTaskStatusEnum responseStatus) {
	ipTaskMapper.updateTaskItemResponseStatus(idIpTaskItem, responseStatus.name());
    }

    @Override
    public void updateTaskItemIp(Long idIpTaskItem, Long sharedId) {
	ipTaskMapper.updateTaskItemIp(idIpTaskItem, sharedId);
    }

    @Override
    public List<IpTaskElaborationResult> getIpTaskElaborationResultByItemCode(List<String> itemCodeList) {
	List<IpTaskElaborationResult> outList = new ArrayList<>();
	for (List<String> subList : ListUtils.partition(itemCodeList, MAX_ID_LIST_SIZE)) {
	    outList.addAll(ipTaskMapper.findIpTaskElaborationResultByItemCode(subList));
	}
	return outList;
    }
}
