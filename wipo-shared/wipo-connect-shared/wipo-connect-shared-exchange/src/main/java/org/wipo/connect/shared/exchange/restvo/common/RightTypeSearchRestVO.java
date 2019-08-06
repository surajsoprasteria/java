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
import java.util.List;

/**
 * The Class DeleteByIdRestVO.
 */
public class RightTypeSearchRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2293486698540426860L;

	/** The id. */
	private List<String> ccCodeList;

	public List<String> getCcCodeList() {
		return ccCodeList;
	}

	public void setCcCodeList(List<String> ccCodeList) {
		this.ccCodeList = ccCodeList;
	}

}
