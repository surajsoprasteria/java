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



/**
 * The Class FindByIdSecGroupRestVO.
 */
public class FindByIdsPermissionRestVO implements Serializable {

    private static final long serialVersionUID = 2881303384280965565L;
   
    private List<Long> idsPermission;

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

}
