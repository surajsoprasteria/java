/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskElaborationResult.
 */
public class WorkTaskElaborationResult implements Serializable, Identifiable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -740078852712252955L;

	/** The id work task item. */
	private Long idWorkTaskItem;

	/** The item code. */
	private String itemCode;

	/** The task status. */
	private String taskStatus;

	/** The csi status. */
	private String csiStatus;

	private String sharedWorkId;

	private String sharedWorkMainId;

	/** The work response status. */
	private String workResponseStatus;

	/**
	 * Gets the id work task item.
	 *
	 * @return the id work task item
	 */
	public Long getIdWorkTaskItem() {
		return idWorkTaskItem;
	}

	/**
	 * Sets the id work task item.
	 *
	 * @param idWorkTaskItem
	 *            the new id work task item
	 */
	public void setIdWorkTaskItem(Long idWorkTaskItem) {
		this.idWorkTaskItem = idWorkTaskItem;
	}

	/**
	 * Gets the item code.
	 *
	 * @return the item code
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Sets the item code.
	 *
	 * @param itemCode
	 *            the new item code
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * Gets the task status.
	 *
	 * @return the task status
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * Sets the task status.
	 *
	 * @param taskStatus
	 *            the new task status
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * Gets the csi status.
	 *
	 * @return the csi status
	 */
	public String getCsiStatus() {
		return csiStatus;
	}

	/**
	 * Sets the csi status.
	 *
	 * @param csiStatus
	 *            the new csi status
	 */
	public void setCsiStatus(String csiStatus) {
		this.csiStatus = csiStatus;
	}

	/**
	 * Gets the shared work id.
	 *
	 * @return the shared work id
	 */
	public String getSharedWorkId() {
		return sharedWorkId;
	}

	/**
	 * Sets the shared work id.
	 *
	 * @param sharedWorkId
	 *            the new shared work id
	 */
	public void setSharedWorkId(String sharedWorkId) {
		this.sharedWorkId = sharedWorkId;
	}

	@Override
	public Long getId() {
		return getIdWorkTaskItem();
	}

	@Override
	public void setId(Long id) {
		setIdWorkTaskItem(id);
	}

	/**
	 * Gets the work response status.
	 *
	 * @return the work response status
	 */
	public String getWorkResponseStatus() {
		return workResponseStatus;
	}

	/**
	 * Sets the work response status.
	 *
	 * @param workResponseStatus
	 *            the new work response status
	 */
	public void setWorkResponseStatus(String workResponseStatus) {
		this.workResponseStatus = workResponseStatus;
	}

	public String getSharedWorkMainId() {
		return sharedWorkMainId;
	}

	public void setSharedWorkMainId(String sharedWorkMainId) {
		this.sharedWorkMainId = sharedWorkMainId;
	}


}
