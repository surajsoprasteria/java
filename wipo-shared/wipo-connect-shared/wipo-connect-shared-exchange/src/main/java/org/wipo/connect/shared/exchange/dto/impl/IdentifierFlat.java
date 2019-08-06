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
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class IdentifierFlat.
 */
public class IdentifierFlat implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3274204903831459601L;

    /** The id identifier. */
    private Long idIdentifier;

    /** The label. */
    private String name;

    /** The acronym. */
    private String acronym;

    /** The code. */
    private String code;

    private Long fkName;

    private Boolean isLocalIdentifier;

    private String linkedEntity;

    /** The Creation Class List. */
    private List<Long> fkCcList;

    private Boolean hideFromUi;

    /**
     * Gets the acronym.
     *
     * @return the acronym
     */
    public String getAcronym() {
	return this.acronym;
    }

    @Override
    public Long getId() {
	return getIdIdentifier();
    }

    /**
     * Gets the id identifier.
     *
     * @return the id identifier
     */
    public Long getIdIdentifier() {
	return this.idIdentifier;
    }

    /**
     * Sets the acronym.
     *
     * @param acronym
     *            the new acronym
     */
    public void setAcronym(String acronym) {
	this.acronym = acronym;
    }

    @Override
    public void setId(Long id) {
	setIdIdentifier(id);
    }

    /**
     * Sets the id identifier.
     *
     * @param idIdentifier
     *            the new id identifier
     */
    public void setIdIdentifier(Long idIdentifier) {
	this.idIdentifier = idIdentifier;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
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
     * @param code
     *            the new code
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * Gets the checks if is local identifier.
     *
     * @return the checks if is local identifier
     */
    public Boolean getIsLocalIdentifier() {
	return isLocalIdentifier;
    }

    /**
     * Sets the checks if is local identifier.
     *
     * @param isLocalIdentifier
     *            the new checks if is local identifier
     */
    public void setIsLocalIdentifier(Boolean isLocalIdentifier) {
	this.isLocalIdentifier = isLocalIdentifier;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getFkName() {
	return fkName;
    }

    public void setFkName(Long fkName) {
	this.fkName = fkName;
    }

    public String getLinkedEntity() {
	return linkedEntity;
    }

    public void setLinkedEntity(String linkedEntity) {
	this.linkedEntity = linkedEntity;
    }

    public List<Long> getFkCcList() {
	return fkCcList;
    }

    public void setFkCcList(List<Long> fkCcList) {
	this.fkCcList = fkCcList;
    }

    public Boolean getHideFromUi() {
	return hideFromUi;
    }

    public void setHideFromUi(Boolean hideFromUi) {
	this.hideFromUi = hideFromUi;
    }

}
