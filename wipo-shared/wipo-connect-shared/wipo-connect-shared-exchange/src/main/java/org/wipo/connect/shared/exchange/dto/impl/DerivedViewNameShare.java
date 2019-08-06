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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.customvalidation.CustomValidatedField;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class DerivedViewNameShare.
 */
public class DerivedViewNameShare implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4760741771489407016L;

	/** The id derived view name share. */
	private Long idDerivedViewNameShare;

	/** The fk derived view name. */
	private Long fkDerivedViewName;

	/** The fk right type. */
	private Long fkRightType;

	/** The right type code. */
	private String rightTypeCode;

	/** The share value. */
	@CustomValidatedField(fieldCode = "VIEW_SHARE_VALUE")
	private BigDecimal shareValue;

	private List<Cmo> cmoList;

	/**
	 * Gets the fk derived view name.
	 *
	 * @return the fk derived view name
	 */
	public Long getFkDerivedViewName() {
		return this.fkDerivedViewName;
	}

	/**
	 * Gets the fk right type.
	 *
	 * @return the fk right type
	 */
	public Long getFkRightType() {
		return this.fkRightType;
	}


	@Override
	public Long getId() {
		return getIdDerivedViewNameShare();
	}

	/**
	 * Gets the id derived view name share.
	 *
	 * @return the id derived view name share
	 */
	public Long getIdDerivedViewNameShare() {
		return this.idDerivedViewNameShare;
	}

	/**
	 * Gets the share value.
	 *
	 * @return the share value
	 */
	public BigDecimal getShareValue() {
		return this.shareValue;
	}

	/**
	 * Sets the fk derived view name.
	 *
	 * @param fkDerivedViewName
	 *            the new fk derived view name
	 */
	public void setFkDerivedViewName(Long fkDerivedViewName) {
		this.fkDerivedViewName = fkDerivedViewName;
	}

	/**
	 * Sets the fk right type.
	 *
	 * @param fkRightType
	 *            the new fk right type
	 */
	public void setFkRightType(Long fkRightType) {
		this.fkRightType = fkRightType;
	}


	@Override
	public void setId(Long id) {
		setIdDerivedViewNameShare(id);
	}

	/**
	 * Sets the id derived view name share.
	 *
	 * @param idDerivedViewNameShare
	 *            the new id derived view name share
	 */
	public void setIdDerivedViewNameShare(Long idDerivedViewNameShare) {
		this.idDerivedViewNameShare = idDerivedViewNameShare;
	}

	/**
	 * Sets the share value.
	 *
	 * @param shareValue
	 *            the new share value
	 */
	public void setShareValue(BigDecimal shareValue) {
		this.shareValue = shareValue;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
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
	 * @param rightTypeCode
	 *            the new right type code
	 */
	public void setRightTypeCode(String rightTypeCode) {
		this.rightTypeCode = rightTypeCode;
	}

	/**
	 * Gets the cmo list.
	 *
	 * @return the cmo list
	 */
	public List<Cmo> getCmoList() {
		if(cmoList == null){
			cmoList = new ArrayList<>();
		}
		return cmoList;
	}

	/**
	 * Sets the cmo list.
	 *
	 * @param cmoList the new cmo list
	 */
	public void setCmoList(List<Cmo> cmoList) {
		this.cmoList = cmoList;
	}

}
