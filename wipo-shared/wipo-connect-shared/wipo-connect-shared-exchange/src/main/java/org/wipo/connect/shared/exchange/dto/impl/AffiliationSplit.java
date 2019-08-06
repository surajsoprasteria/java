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

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class AffiliationSplit.
 *
 * @author fumagalli
 */
public class AffiliationSplit implements Serializable {

	private static final long serialVersionUID = -2795577316100010892L;

	private Long idName;
	private Long idInterestedParty;
	private Long idRole;
	private String roleCode;
	private Long idRightType;
	private String rightTypeCode;
	private Long idCmo;
	private String cmoAcronym;
	private BigDecimal shareValue;
	private String cmoCode;

	/**
	 * Gets the id name.
	 *
	 * @return the id name
	 */
	public Long getIdName() {
		return idName;
	}

	/**
	 * Sets the id name.
	 *
	 * @param idName the new id name
	 */
	public void setIdName(Long idName) {
		this.idName = idName;
	}

	/**
	 * Gets the id interested party.
	 *
	 * @return the id interested party
	 */
	public Long getIdInterestedParty() {
		return idInterestedParty;
	}

	/**
	 * Sets the id interested party.
	 *
	 * @param idInterestedParty the new id interested party
	 */
	public void setIdInterestedParty(Long idInterestedParty) {
		this.idInterestedParty = idInterestedParty;
	}

	/**
	 * Gets the id role.
	 *
	 * @return the id role
	 */
	public Long getIdRole() {
		return idRole;
	}

	/**
	 * Sets the id role.
	 *
	 * @param idRole the new id role
	 */
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	/**
	 * Gets the id right type.
	 *
	 * @return the id right type
	 */
	public Long getIdRightType() {
		return idRightType;
	}

	/**
	 * Sets the id right type.
	 *
	 * @param idRightType the new id right type
	 */
	public void setIdRightType(Long idRightType) {
		this.idRightType = idRightType;
	}

	/**
	 * Gets the id cmo.
	 *
	 * @return the id cmo
	 */
	public Long getIdCmo() {
		return idCmo;
	}

	/**
	 * Sets the id cmo.
	 *
	 * @param idCmo the new id cmo
	 */
	public void setIdCmo(Long idCmo) {
		this.idCmo = idCmo;
	}

	/**
	 * Gets the cmo acronym.
	 *
	 * @return the cmo acronym
	 */
	public String getCmoAcronym() {
		return cmoAcronym;
	}

	/**
	 * Sets the cmo acronym.
	 *
	 * @param cmoAcronym the new cmo acronym
	 */
	public void setCmoAcronym(String cmoAcronym) {
		this.cmoAcronym = cmoAcronym;
	}

	/**
	 * Gets the share value.
	 *
	 * @return the share value
	 */
	public BigDecimal getShareValue() {
		return shareValue;
	}

	/**
	 * Sets the share value.
	 *
	 * @param shareValue the new share value
	 */
	public void setShareValue(BigDecimal shareValue) {
		this.shareValue = shareValue;
	}

	/**
	 * Gets the role code.
	 *
	 * @return the role code
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * Sets the role code.
	 *
	 * @param roleCode the new role code
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * Gets the right type code.
	 *
	 * @return the right type code
	 */
	public String getRightTypeCode() {
		return rightTypeCode;
	}

	/**
	 * Sets the right type code.
	 *
	 * @param rightTypeCode the new right type code
	 */
	public void setRightTypeCode(String rightTypeCode) {
		this.rightTypeCode = rightTypeCode;
	}

	/**
	 * Gets the item key.
	 *
	 * @return the item key
	 */
	public String getItemKey(){
		StringBuilder sb = new StringBuilder();

		sb.append(idName);
		sb.append("\u0000\u0000\u0000");
		sb.append(idRole);
		sb.append("\u0000\u0000\u0000");
		sb.append(rightTypeCode);

		return sb.toString();
	}

	/**
	 * Gets the cmo code.
	 *
	 * @return the cmo code
	 */
	public String getCmoCode() {
		return cmoCode;
	}

	/**
	 * Sets the cmo code.
	 *
	 * @param cmoCode the new cmo code
	 */
	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}

}