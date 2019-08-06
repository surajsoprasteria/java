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

import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;


/**
 * The Class IpiRoleFlatVO.
 */
public class IpiRoleFlatVO implements Serializable {

	private static final long serialVersionUID = 8093530819393596924L;
	
	private IpiRoleFlat ipiRole;

	public IpiRoleFlat getIpiRole() {
		return ipiRole;
	}

	public void setIpiRole(IpiRoleFlat ipiRole) {
		this.ipiRole = ipiRole;
	}

	


	
}
