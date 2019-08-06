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
package org.wipo.connect.shared.exchange.restvo.common;

import java.io.Serializable;

/**
 * The Class FindRoleByIdRestVO.
 */
public class FindRoleByIdRestVO implements Serializable {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8395915285640281570L;

    /** The id. */
    private Long id;
   

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
