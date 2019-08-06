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



package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The Class AffiliationIpVO.
 */
public class AffiliationIpVO implements Serializable {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4847089829238169876L;

    
    /** The id affiliation. */
    private Long idAffiliation;
    
    /** The id affiliation domain. */
    private Long idAffiliationDomain;
    
    
    private Long fkInterestedParty;
    
    /** The cmo. */
	private Long fkCmo;
	
	private String CmoAcronym;
	
	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;
	
	/** The share value. */
	private BigDecimal shareValue;
	
	/** The territory formula. */
	private String territoryFormula;
	
    /** The fk role. */
    private Long fkIpiRole;

    /** The fk creation class. */
    private Long fkCreationClass;

    /** The fk right type. */
    private Long fkIpiRightType;
    
    

	public Long getIdAffiliation() {
		return idAffiliation;
	}

	public void setIdAffiliation(Long idAffiliation) {
		this.idAffiliation = idAffiliation;
	}

	public Long getIdAffiliationDomain() {
		return idAffiliationDomain;
	}

	public void setIdAffiliationDomain(Long idAffiliationDomain) {
		this.idAffiliationDomain = idAffiliationDomain;
	}

	public Long getFkInterestedParty() {
		return fkInterestedParty;
	}

	public void setFkInterestedParty(Long fkInterestedParty) {
		this.fkInterestedParty = fkInterestedParty;
	}

	public Long getFkCmo() {
		return fkCmo;
	}

	public void setFkCmo(Long fkCmo) {
		this.fkCmo = fkCmo;
	}

	public String getCmoAcronym() {
		return CmoAcronym;
	}

	public void setCmoAcronym(String cmoAcronym) {
		CmoAcronym = cmoAcronym;
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

	public String getTerritoryFormula() {
		return territoryFormula;
	}

	public void setTerritoryFormula(String territoryFormula) {
		this.territoryFormula = territoryFormula;
	}

	public Long getFkIpiRole() {
		return fkIpiRole;
	}

	public void setFkIpiRole(Long fkIpiRole) {
		this.fkIpiRole = fkIpiRole;
	}

	public Long getFkCreationClass() {
		return fkCreationClass;
	}

	public void setFkCreationClass(Long fkCreationClass) {
		this.fkCreationClass = fkCreationClass;
	}

	public Long getFkIpiRightType() {
		return fkIpiRightType;
	}

	public void setFkIpiRightType(Long fkIpiRightType) {
		this.fkIpiRightType = fkIpiRightType;
	}

}
	
	

 