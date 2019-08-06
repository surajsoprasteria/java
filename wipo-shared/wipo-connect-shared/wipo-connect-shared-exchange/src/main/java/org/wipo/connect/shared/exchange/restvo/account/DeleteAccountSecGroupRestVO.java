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
 * The Class DeleteAccountSecGroupRestVO.
 */
public class DeleteAccountSecGroupRestVO implements Serializable {

    private static final long serialVersionUID = 8284906314618314105L;

    private Long idSecGroup;
    
    private Long idAccount;

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
	 * Gets the id account.
	 *
	 * @return the id account
	 */
	public Long getIdAccount() {
		return idAccount;
	}

	/**
	 * Sets the id account.
	 *
	 * @param idAccount the new id account
	 */
	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}



}
