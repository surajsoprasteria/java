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

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class Account.
 *
 * @author Fincons
 */
public class Permission extends BaseDTO implements Identifiable {

	private static final long serialVersionUID = -1552884878935535714L;
	private Long idPermission;
	private String code;
	private String descriptionCode;
	private Long fkDescription;
	private String descriptionPerm;

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

	@Override
	public Long getId() {
		return getIdPermission();
	}

	@Override
	public void setId(Long id) {
		setIdPermission(id);
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
	 * Gets the fk description.
	 *
	 * @return the fk description
	 */
	public Long getFkDescription() {
		return fkDescription;
	}

	/**
	 * Sets the fk description.
	 *
	 * @param fkDescription the new fk description
	 */
	public void setFkDescription(Long fkDescription) {
		this.fkDescription = fkDescription;
	}

	/**
	 * Gets the description perm.
	 *
	 * @return the description perm
	 */
	public String getDescriptionPerm() {
		return descriptionPerm;
	}

	/**
	 * Sets the description perm.
	 *
	 * @param descriptionPerm the new description perm
	 */
	public void setDescriptionPerm(String descriptionPerm) {
		this.descriptionPerm = descriptionPerm;
	}

	/**
	 * Gets the description code.
	 *
	 * @return the description code
	 */
	public String getDescriptionCode() {
		return descriptionCode;
	}

	/**
	 * Sets the description code.
	 *
	 * @param descriptionCode the new description code
	 */
	public void setDescriptionCode(String descriptionCode) {
		this.descriptionCode = descriptionCode;
	}

}
