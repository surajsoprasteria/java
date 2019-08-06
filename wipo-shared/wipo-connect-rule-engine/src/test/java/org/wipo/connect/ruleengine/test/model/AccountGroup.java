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
package org.wipo.connect.ruleengine.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class AccountGroup implements Serializable {

	private static final long serialVersionUID = 5414042936036733296L;

	private String groupCode;
	private Collection<Account> accounts;

	public AccountGroup(String groupCode, Collection<Account> accounts) {
		super();
		this.groupCode = groupCode;
		this.accounts = accounts;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Collection<Account> getAccounts() {
		if (accounts == null) {
			accounts = new ArrayList<>();
		}
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

}
