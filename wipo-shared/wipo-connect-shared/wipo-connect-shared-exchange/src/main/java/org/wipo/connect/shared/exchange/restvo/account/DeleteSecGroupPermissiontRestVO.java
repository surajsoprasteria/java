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



/**
 * The Class DeleteSecGroupPermissiontRestVO.
 */
public class DeleteSecGroupPermissiontRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private Long idSecGroup;
    
    private Long idPermission;

	/**
	 * Gets the id sec group.
	 *
	 * @return the id sec group
	 */
	public Long getIdSecGroup() {
		return idSecGroup;
	}

	/**
	 * Sets the id sec group.
	 *
	 * @param idSecGroup the new id sec group
	 */
	public void setIdSecGroup(Long idSecGroup) {
		this.idSecGroup = idSecGroup;
	}

	/**
	 * Gets the id permission.
	 *
	 * @return the id permission
	 */
	public Long getIdPermission() {
		return idPermission;
	}

	/**
	 * Sets the id permission.
	 *
	 * @param idPermission the new id permission
	 */
	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}


}
