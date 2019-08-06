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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Affiliation.
 *
 * @author fumagalli
 */
public class Affiliation extends BaseDTO implements Identifiable, Creatable, Updatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 214395494851542902L;

    /** The id affiliation. */
    private Long idAffiliation;

    /** The fk interested party. */
    private Long fkInterestedParty;

    /** The cmo. */
    private Cmo cmo;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The share value. */
    private BigDecimal shareValue;

    /** The signature date. */
    private Date signatureDate;

    /** The amendment timestamp. */
    private Date amendmentTimestamp;

    /** The affiliation domain list. */
    private List<AffiliationDomain> affiliationDomainList;

    /** The fk territory list. */
    private List<Long> fkTerritoryList;

    /** The territory code list. */
    private List<String> territoryCodeList;

    /** The territory formula. */
    private String territoryFormula;

    private boolean execUpdate;

    private boolean execDelete;

    /**
     * Gets the affiliation domain list.
     *
     * @return the affiliation domain list
     */
    public List<AffiliationDomain> getAffiliationDomainList() {
	if (this.affiliationDomainList == null) {
	    this.affiliationDomainList = new ArrayList<>();
	}
	return this.affiliationDomainList;
    }

    /**
     * Gets the amendment timestamp.
     *
     * @return the amendment timestamp
     */
    public Date getAmendmentTimestamp() {
	return this.amendmentTimestamp;
    }

    /**
     * Gets the cmo.
     *
     * @return the cmo
     */
    public Cmo getCmo() {
	return this.cmo;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
	return this.endDate;
    }

    /**
     * Gets the fk territory list.
     *
     * @return the fk territory list
     */
    public List<Long> getFkTerritoryList() {
	if (this.fkTerritoryList == null) {
	    this.fkTerritoryList = new ArrayList<>();
	}
	return this.fkTerritoryList;
    }

    @Override
    public Long getId() {
	return getIdAffiliation();
    }

    /**
     * Gets the id affiliation.
     *
     * @return the id affiliation
     */
    public Long getIdAffiliation() {
	return this.idAffiliation;
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
     * Gets the signature date.
     *
     * @return the signature date
     */
    public Date getSignatureDate() {
	return this.signatureDate;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
	return this.startDate;
    }

    /**
     * Sets the affiliation domain list.
     *
     * @param affiliationDomainList
     *            the new affiliation domain list
     */
    public void setAffiliationDomainList(
	    List<AffiliationDomain> affiliationDomainList) {
	this.affiliationDomainList = affiliationDomainList;
    }

    /**
     * Sets the amendment timestamp.
     *
     * @param amendmentTimestamp
     *            the new amendment timestamp
     */
    public void setAmendmentTimestamp(Date amendmentTimestamp) {
	this.amendmentTimestamp = amendmentTimestamp;
    }

    /**
     * Sets the cmo.
     *
     * @param cmo
     *            the new cmo
     */
    public void setCmo(Cmo cmo) {
	this.cmo = cmo;
    }

    /**
     * Sets the end date.
     *
     * @param endDate
     *            the new end date
     */
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    /**
     * Sets the fk territory list.
     *
     * @param fkTerritoryList
     *            the new fk territory list
     */
    public void setFkTerritoryList(List<Long> fkTerritoryList) {
	this.fkTerritoryList = fkTerritoryList;
    }

    @Override
    public void setId(Long id) {
	setIdAffiliation(id);
    }

    /**
     * Sets the id affiliation.
     *
     * @param idAffiliation
     *            the new id affiliation
     */
    public void setIdAffiliation(Long idAffiliation) {
	this.idAffiliation = idAffiliation;
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

    /**
     * Sets the signature date.
     *
     * @param signatureDate
     *            the new signature date
     */
    public void setSignatureDate(Date signatureDate) {
	this.signatureDate = signatureDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate
     *            the new start date
     */
    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    /**
     * Gets the fk interested party.
     *
     * @return the fk interested party
     */
    public Long getFkInterestedParty() {
	return fkInterestedParty;
    }

    /**
     * Sets the fk interested party.
     *
     * @param fkInterestedParty
     *            the new fk interested party
     */
    public void setFkInterestedParty(Long fkInterestedParty) {
	this.fkInterestedParty = fkInterestedParty;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Gets the territory code list.
     *
     * @return the territory code list
     */
    public List<String> getTerritoryCodeList() {
	if (territoryCodeList == null) {
	    territoryCodeList = new ArrayList<String>();
	}
	return territoryCodeList;
    }

    /**
     * Gets the territory formula.
     *
     * @return the territory formula
     */
    public String getTerritoryFormula() {
	return territoryFormula;
    }

    /**
     * Sets the territory formula.
     *
     * @param territoryFormula
     *            the new territory formula
     */
    public void setTerritoryFormula(String territoryFormula) {
	this.territoryFormula = territoryFormula;
    }

    /**
     * Sets the territory code list.
     *
     * @param territoryCodeList
     *            the new territory code list
     */
    public void setTerritoryCodeList(List<String> territoryCodeList) {
	this.territoryCodeList = territoryCodeList;
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
