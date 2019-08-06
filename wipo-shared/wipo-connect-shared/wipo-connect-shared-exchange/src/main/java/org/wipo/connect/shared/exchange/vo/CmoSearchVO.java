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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CmoSearchVO.
 */
public class CmoSearchVO implements Serializable {

	private static final long serialVersionUID = 3253266179476796618L;

	private Boolean onlyDataSources = false;
	private List<Long> exclusionList;
	private Boolean filterClientInfo = false;
	private Long currentClientInfo;


	/**
	 * Gets the only data sources.
	 *
	 * @return the only data sources
	 */
	public Boolean getOnlyDataSources() {
		return onlyDataSources;
	}

	/**
	 * Sets the only data sources.
	 *
	 * @param onlyDataSources the new only data sources
	 */
	public void setOnlyDataSources(Boolean onlyDataSources) {
		this.onlyDataSources = onlyDataSources;
	}

	/**
	 * Gets the exclusion list.
	 *
	 * @return the exclusion list
	 */
	public List<Long> getExclusionList() {
		if (exclusionList == null) {
			exclusionList = new ArrayList<>();
		}
		return exclusionList;
	}

	/**
	 * Sets the exclusion list.
	 *
	 * @param exclusionList the new exclusion list
	 */
	public void setExclusionList(List<Long> exclusionList) {
		this.exclusionList = exclusionList;
	}

	/**
	 * Gets the filter client info.
	 *
	 * @return the filter client info
	 */
	public Boolean getFilterClientInfo() {
		return filterClientInfo;
	}

	/**
	 * Sets the filter client info.
	 *
	 * @param filterClientInfo the new filter client info
	 */
	public void setFilterClientInfo(Boolean filterClientInfo) {
		this.filterClientInfo = filterClientInfo;
	}

	/**
	 * Gets the current client info.
	 *
	 * @return the current client info
	 */
	public Long getCurrentClientInfo() {
		return currentClientInfo;
	}

	/**
	 * Sets the current client info.
	 *
	 * @param currentClientInfo the new current client info
	 */
	public void setCurrentClientInfo(Long currentClientInfo) {
		this.currentClientInfo = currentClientInfo;
	}



}
