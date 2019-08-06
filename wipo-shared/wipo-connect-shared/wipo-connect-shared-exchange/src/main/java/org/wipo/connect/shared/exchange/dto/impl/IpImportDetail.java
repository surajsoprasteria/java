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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class IpImportDetail {

    private Long idIpImportDetail;

    private Long fkIpImport;

    private Long fkInterestedParty;

    public Long getIdIpImportDetail() {
	return idIpImportDetail;
    }

    public void setIdIpImportDetail(Long idIpImportDetail) {
	this.idIpImportDetail = idIpImportDetail;
    }

    public Long getFkIpImport() {
	return fkIpImport;
    }

    public void setFkIpImport(Long fkIpImport) {
	this.fkIpImport = fkIpImport;
    }

    public Long getFkInterestedParty() {
	return fkInterestedParty;
    }

    public void setFkInterestedParty(Long fkInterestedParty) {
	this.fkInterestedParty = fkInterestedParty;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

}
