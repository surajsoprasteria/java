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

import org.wipo.connect.shared.exchange.vo.NameSearchVO;



/**
 * The Class FindNameRestVO.
 */
public class FindNameRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8284906314618314105L;

    /** The search vo. */
    private NameSearchVO searchVO;

	/**
	 * Gets the search vo.
	 *
	 * @return the search vo
	 */
	public NameSearchVO getSearchVO() {
		return searchVO;
	}

	/**
	 * Sets the search vo.
	 *
	 * @param searchVO the new search vo
	 */
	public void setSearchVO(NameSearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
