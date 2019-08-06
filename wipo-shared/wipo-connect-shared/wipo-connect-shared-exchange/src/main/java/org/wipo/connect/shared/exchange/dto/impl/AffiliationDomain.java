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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class AffiliationDomain.
 *
 * @author fumagalli
 */
public class AffiliationDomain implements Identifiable, Deletable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4796050923183836876L;

    /** The id affiliation domain. */
    private Long idAffiliationDomain;

    /** The fk affiliation. */
    private Long fkAffiliation;

    /** The fk ipi role. */
    private Long fkIpiRole;

    /** The fk creation class. */
    private Long fkCreationClass;

    /** The fk ipi right type. */
    private Long fkIpiRightType;

    /** The is excluded. */
    private Boolean isExcluded;

    /** The ipi right type code. */
    private String ipiRightTypeCode;

    /** The creation class code. */
    private String creationClassCode;

    /** The ipi role code. */
    private String ipiRoleCode;

    private boolean execUpdate;

    private boolean execDelete;

    /**
     * Gets the fk affiliation.
     *
     * @return the fk affiliation
     */
    public Long getFkAffiliation() {
	return this.fkAffiliation;
    }

    /**
     * Gets the fk creation class.
     *
     * @return the fk creation class
     */
    public Long getFkCreationClass() {
	return this.fkCreationClass;
    }

    /**
     * Gets the fk ipi right type.
     *
     * @return the fk ipi right type
     */
    public Long getFkIpiRightType() {
	return this.fkIpiRightType;
    }

    /**
     * Gets the fk ipi role.
     *
     * @return the fk ipi role
     */
    public Long getFkIpiRole() {
	return this.fkIpiRole;
    }

    @Override
    public Long getId() {
	return getIdAffiliationDomain();
    }

    /**
     * Gets the id affiliation domain.
     *
     * @return the id affiliation domain
     */
    public Long getIdAffiliationDomain() {
	return this.idAffiliationDomain;
    }

    /**
     * Sets the fk affiliation.
     *
     * @param fkAffiliation
     *            the new fk affiliation
     */
    public void setFkAffiliation(Long fkAffiliation) {
	this.fkAffiliation = fkAffiliation;
    }

    /**
     * Sets the fk creation class.
     *
     * @param fkCreationClass
     *            the new fk creation class
     */
    public void setFkCreationClass(Long fkCreationClass) {
	this.fkCreationClass = fkCreationClass;
    }

    /**
     * Sets the fk ipi right type.
     *
     * @param fkIpiRightType
     *            the new fk ipi right type
     */
    public void setFkIpiRightType(Long fkIpiRightType) {
	this.fkIpiRightType = fkIpiRightType;
    }

    /**
     * Sets the fk ipi role.
     *
     * @param fkIpiRole
     *            the new fk ipi role
     */
    public void setFkIpiRole(Long fkIpiRole) {
	this.fkIpiRole = fkIpiRole;
    }

    @Override
    public void setId(Long id) {
	setIdAffiliationDomain(id);
    }

    /**
     * Sets the id affiliation domain.
     *
     * @param idAffiliationDomain
     *            the new id affiliation domain
     */
    public void setIdAffiliationDomain(Long idAffiliationDomain) {
	this.idAffiliationDomain = idAffiliationDomain;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Gets the checks if is excluded.
     *
     * @return the checks if is excluded
     */
    public Boolean getIsExcluded() {
	return isExcluded;
    }

    /**
     * Sets the checks if is excluded.
     *
     * @param isExcluded
     *            the new checks if is excluded
     */
    public void setIsExcluded(Boolean isExcluded) {
	this.isExcluded = isExcluded;
    }

    /**
     * Gets the ipi right type code.
     *
     * @return the ipi right type code
     */
    public String getIpiRightTypeCode() {
	return ipiRightTypeCode;
    }

    /**
     * Sets the ipi right type code.
     *
     * @param ipiRightTypeCode
     *            the new ipi right type code
     */
    public void setIpiRightTypeCode(String ipiRightTypeCode) {
	this.ipiRightTypeCode = ipiRightTypeCode;
    }

    /**
     * Gets the creation class code.
     *
     * @return the creation class code
     */
    public String getCreationClassCode() {
	return creationClassCode;
    }

    /**
     * Sets the creation class code.
     *
     * @param creationClassCode
     *            the new creation class code
     */
    public void setCreationClassCode(String creationClassCode) {
	this.creationClassCode = creationClassCode;
    }

    /**
     * Gets the ipi role code.
     *
     * @return the ipi role code
     */
    public String getIpiRoleCode() {
	return ipiRoleCode;
    }

    /**
     * Sets the ipi role code.
     *
     * @param ipiRoleCode
     *            the new ipi role code
     */
    public void setIpiRoleCode(String ipiRoleCode) {
	this.ipiRoleCode = ipiRoleCode;
    }

    public boolean isExecUpdate() {
	return execUpdate;
    }

    public void setExecUpdate(boolean execUpdate) {
	this.execUpdate = execUpdate;
    }

    @Override
    public Boolean getExecDelete() {
	return execDelete;
    }

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

}
