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



package org.wipo.connect.shared.exchange.restvo.account;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.vo.AccountSearchVO;



/**
 * The Class FindAccountRestVO.
 */
public class FindAccountRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private AccountSearchVO searchVO;

	/**
	 * Gets the search VO.
	 *
	 * @return the search VO
	 */
	public AccountSearchVO getSearchVO() {
		return searchVO;
	}

	/**
	 * Sets the search VO.
	 *
	 * @param searchVO the new search VO
	 */
	public void setSearchVO(AccountSearchVO searchVO) {
		this.searchVO = searchVO;
	}
}
