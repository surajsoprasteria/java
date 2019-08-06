/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class IpTaskDetailAffiliation.
 */
public class IpTaskDetailAffiliation implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6520620763628019773L;

    private Long idIpTaskDetailAffiliation;

    private Long fkIpTaskItemDetail;

    private String cmoCode;

    private Date startDate;

    private Date endDate;

    private BigDecimal shareValue;

    private Date signatureDate;

    private Date amendmentTimestamp;

    private String territoryFormula;

    private List<IpTaskDetailAffiliationDomain> affiliationDomainList;

	public Long getIdIpTaskDetailAffiliation() {
		return idIpTaskDetailAffiliation;
	}

	public void setIdIpTaskDetailAffiliation(Long idIpTaskDetailAffiliation) {
		this.idIpTaskDetailAffiliation = idIpTaskDetailAffiliation;
	}

	public Long getFkIpTaskItemDetail() {
		return fkIpTaskItemDetail;
	}

	public void setFkIpTaskItemDetail(Long fkIpTaskItemDetail) {
		this.fkIpTaskItemDetail = fkIpTaskItemDetail;
	}

	public String getCmoCode() {
		return cmoCode;
	}

	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getShareValue() {
		return shareValue;
	}

	public void setShareValue(BigDecimal shareValue) {
		this.shareValue = shareValue;
	}

	public Date getSignatureDate() {
		return signatureDate;
	}

	public void setSignatureDate(Date signatureDate) {
		this.signatureDate = signatureDate;
	}

	public Date getAmendmentTimestamp() {
		return amendmentTimestamp;
	}

	public void setAmendmentTimestamp(Date amendmentTimestamp) {
		this.amendmentTimestamp = amendmentTimestamp;
	}

	public String getTerritoryFormula() {
		return territoryFormula;
	}

	public void setTerritoryFormula(String territoryFormula) {
		this.territoryFormula = territoryFormula;
	}

	public List<IpTaskDetailAffiliationDomain> getAffiliationDomainList() {
		if(affiliationDomainList==null){
			affiliationDomainList=new ArrayList<>();
		}
		return affiliationDomainList;
	}

	public void setAffiliationDomainList(List<IpTaskDetailAffiliationDomain> affiliationDomainList) {
		this.affiliationDomainList = affiliationDomainList;
	}

	@Override
	public Long getId() {
		return getIdIpTaskDetailAffiliation();
	}

	@Override
	public void setId(Long id) {
		setIdIpTaskDetailAffiliation(id);
	}
}
