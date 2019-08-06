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

package org.wipo.connect.shared.exchange.restvo.common;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.vo.CmoSearchVO;

/**
 * The Class FindCmoRestVO.
 */
public class FindCmoRestVO implements Serializable {

	private static final long serialVersionUID = 8498813946350550517L;

	private CmoSearchVO searchVO;

	/**
	 * Gets the search VO.
	 *
	 * @return the search VO
	 */
	public CmoSearchVO getSearchVO() {
		return searchVO;
	}

	/**
	 * Sets the search VO.
	 *
	 * @param searchVO the new search VO
	 */
	public void setSearchVO(CmoSearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
