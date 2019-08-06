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
package org.wipo.connect.shared.exchange.restvo.work;

import java.io.Serializable;
import java.util.List;

/**
 * The Class GetWorkTaskElabResultByItemCodeRestVO.
 */
public class GetWorkTaskElabResultByItemCodeRestVO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1158578077248888357L;
	
	/** The item code list. */
	private List<String> itemCodeList;

	/**
	 * Gets the item code list.
	 *
	 * @return the item code list
	 */
	public List<String> getItemCodeList() {
		return itemCodeList;
	}

	/**
	 * Sets the item code list.
	 *
	 * @param itemCodeList the new item code list
	 */
	public void setItemCodeList(List<String> itemCodeList) {
		this.itemCodeList = itemCodeList;
	}
	
}
