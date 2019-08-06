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

import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;



/**
 * The Class RoleFlatVO.
 */
public class RoleFlatVO implements Serializable {


	private static final long serialVersionUID = 4227078296555881598L;
	
	private RoleFlat workRole;

	public RoleFlat getWorkRole() {
		return workRole;
	}

	public void setWorkRole(RoleFlat workRole) {
		this.workRole = workRole;
	}


	
}
