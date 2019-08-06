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



package org.wipo.connect.shared.exchange.restvo.externalSite;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;



/**
 * The Class UpdateRestVO.
 */
public class UpdateRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7561154206425489726L;

    /** The dto. */
    private ExternalSite dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public ExternalSite getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(ExternalSite dto) {
		this.dto = dto;
	}

}
