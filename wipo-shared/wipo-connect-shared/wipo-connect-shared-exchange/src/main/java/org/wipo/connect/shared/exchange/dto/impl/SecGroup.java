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

package org.wipo.connect.shared.exchange.dto.impl;

import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Account.
 *
 * @author Fincons
 */
public class SecGroup extends BaseDTO implements Identifiable, Creatable, Updatable{

	private static final long serialVersionUID = -425262565866346929L;

	private Long idSecGroup;

	private String code;

	private String description;

	private String note;

	private List<Permission> permissionList;

	private List<Account> accountList;


	@Override
	public Long getId() {
		return getIdSecGroup();
	}

	@Override
	public void setId(Long id) {
		setIdSecGroup(id);
	}

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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the permission list.
	 *
	 * @return the permission list
	 */
	public List<Permission> getPermissionList() {
		if (permissionList == null) {
			permissionList = new ArrayList<Permission>();
		}
		return permissionList;
	}

	/**
	 * Sets the permission list.
	 *
	 * @param permissionList the new permission list
	 */
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the account list.
	 *
	 * @return the account list
	 */
	public List<Account> getAccountList() {
		if(accountList==null){
			accountList=new ArrayList<Account>();
		}
		return accountList;

	}

	/**
	 * Sets the account list.
	 *
	 * @param accountList the new account list
	 */
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

}
