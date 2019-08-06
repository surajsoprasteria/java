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
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.SecGroup;



/**
 * The Class UpdatePermissionsRestVO.
 */
public class UpdatePermissionsRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private List<Long> idsPermission;
    private SecGroup secGroup;
    
	/**
	 * Gets the ids permission.
	 *
	 * @return the ids permission
	 */
	public List<Long> getIdsPermission() {
		if(idsPermission==null){
			idsPermission=new ArrayList<Long>();
		}
		return idsPermission;
	}
	
	/**
	 * Sets the ids permission.
	 *
	 * @param idsPermission the new ids permission
	 */
	public void setIdsPermission(List<Long> idsPermission) {
		this.idsPermission = idsPermission;
	}
	
	/**
	 * Gets the sec group.
	 *
	 * @return the sec group
	 */
	public SecGroup getSecGroup() {
		return secGroup;
	}
	
	/**
	 * Sets the sec group.
	 *
	 * @param secGroup the new sec group
	 */
	public void setSecGroup(SecGroup secGroup) {
		this.secGroup = secGroup;
	}

	
}
