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

import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;



/**
 * The Class CreationClassVO.
 */
public class CreationClassVO implements Serializable {

	private static final long serialVersionUID = -4317704340286641301L;
	
	private CreationClassFlat creationClass;

	public CreationClassFlat getCreationClass() {
		return creationClass;
	}

	public void setCreationClass(CreationClassFlat creationClass) {
		this.creationClass = creationClass;
	}


}
