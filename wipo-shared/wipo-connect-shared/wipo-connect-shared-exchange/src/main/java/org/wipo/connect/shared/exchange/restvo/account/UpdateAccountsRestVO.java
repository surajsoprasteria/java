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
 * The Class UpdateAccountsRestVO.
 */
public class UpdateAccountsRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private List<Long> idsAccount;
    private SecGroup secGroup;
    
	
	/**
	 * Gets the ids account.
	 *
	 * @return the ids account
	 */
	public List<Long> getIdsAccount() {
		if(idsAccount==null){
			idsAccount=new ArrayList<Long>();
		}
		return idsAccount;
	}
	
	/**
	 * Sets the ids account.
	 *
	 * @param idsAccount the new ids account
	 */
	public void setIdsAccount(List<Long> idsAccount) {
		this.idsAccount = idsAccount;
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
