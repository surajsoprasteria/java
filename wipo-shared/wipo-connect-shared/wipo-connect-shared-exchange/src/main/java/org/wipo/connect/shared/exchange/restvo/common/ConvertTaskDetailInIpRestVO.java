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

import org.wipo.connect.shared.exchange.dto.impl.IpTaskItemDetail;

/**
 * The Class ConvertTaskDetailInIpRestVO.
 */
public class ConvertTaskDetailInIpRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7561154206425489726L;

	private IpTaskItemDetail ipTaskItemDetail;

	public IpTaskItemDetail getIpTaskItemDetail() {
		return ipTaskItemDetail;
	}

	public void setIpTaskItemDetail(IpTaskItemDetail ipTaskItemDetail) {
		this.ipTaskItemDetail = ipTaskItemDetail;
	}



}
