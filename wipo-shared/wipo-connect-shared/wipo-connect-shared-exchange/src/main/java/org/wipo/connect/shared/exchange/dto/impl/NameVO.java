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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.wipo.connect.common.dto.Identifiable;

public class NameVO extends Name implements Serializable, Identifiable {

    private static final long serialVersionUID = 1609346931143824065L;

    private String statusCode;

    private String fkStatus;

    private Date birthDate;

    private String type;

    private String ipMainId;

    private Long ipId;

    private List<String> creationClassCodeList;

    private Set<String> affiliatedCmos;

    public String getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public String getIpMainId() {
	return ipMainId;
    }

    public void setIpMainId(String mainId) {
	this.ipMainId = mainId;
    }

    public List<String> getCreationClassCodeList() {
	if (creationClassCodeList == null) {
	    creationClassCodeList = new ArrayList<>();
	}
	return creationClassCodeList;
    }

    public void setCreationClassCodeList(List<String> creationClassCodeList) {
	this.creationClassCodeList = creationClassCodeList;
    }

    public Set<String> getAffiliatedCmos() {
	if (affiliatedCmos == null) {
	    affiliatedCmos = new HashSet<>();
	}
	return affiliatedCmos;
    }

    public void setAffiliatedCmos(Set<String> affiliatedCmos) {
	this.affiliatedCmos = affiliatedCmos;
    }

    public String getFkStatus() {
	return fkStatus;
    }

    public void setFkStatus(String fkStatus) {
	this.fkStatus = fkStatus;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Long getIpId() {
	return ipId;
    }

    public void setIpId(Long ipId) {
	this.ipId = ipId;
    }

}
