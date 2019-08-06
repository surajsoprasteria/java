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

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class IpTaskDetailAffiliationDomain.
 */
public class IpTaskDetailAffiliationDomain implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6520620763628019773L;

    private Long idIpTaskDetailAffiliationDomain;
    
    private Long fkIpTaskItemDetail;
    
    private Long fkIpTaskDetailAffiliation;
    
    private String ipiRightTypeCode;
    private String creationClassCode;
    private String ipiRoleCode;
    
    private Boolean isExcluded;

	@Override
	public Long getId() {
		return getIdIpTaskDetailAffiliationDomain();
	}

	@Override
	public void setId(Long id) {
		setIdIpTaskDetailAffiliationDomain(id);
	}

	public Long getIdIpTaskDetailAffiliationDomain() {
		return idIpTaskDetailAffiliationDomain;
	}

	public void setIdIpTaskDetailAffiliationDomain(Long idIpTaskDetailAffiliationDomain) {
		this.idIpTaskDetailAffiliationDomain = idIpTaskDetailAffiliationDomain;
	}

	public Long getFkIpTaskItemDetail() {
		return fkIpTaskItemDetail;
	}

	public void setFkIpTaskItemDetail(Long fkIpTaskItemDetail) {
		this.fkIpTaskItemDetail = fkIpTaskItemDetail;
	}

	public Long getFkIpTaskDetailAffiliation() {
		return fkIpTaskDetailAffiliation;
	}

	public void setFkIpTaskDetailAffiliation(Long fkIpTaskDetailAffiliation) {
		this.fkIpTaskDetailAffiliation = fkIpTaskDetailAffiliation;
	}

	public String getIpiRightTypeCode() {
		return ipiRightTypeCode;
	}

	public void setIpiRightTypeCode(String ipiRightTypeCode) {
		this.ipiRightTypeCode = ipiRightTypeCode;
	}

	public String getCreationClassCode() {
		return creationClassCode;
	}

	public void setCreationClassCode(String creationClassCode) {
		this.creationClassCode = creationClassCode;
	}

	public String getIpiRoleCode() {
		return ipiRoleCode;
	}

	public void setIpiRoleCode(String ipiRoleCode) {
		this.ipiRoleCode = ipiRoleCode;
	}

	public Boolean getIsExcluded() {
		return isExcluded;
	}

	public void setIsExcluded(Boolean isExcluded) {
		this.isExcluded = isExcluded;
	}



}
