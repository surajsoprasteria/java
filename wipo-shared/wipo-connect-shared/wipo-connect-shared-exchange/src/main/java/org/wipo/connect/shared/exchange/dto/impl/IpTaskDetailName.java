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
import java.util.Date;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class IpTaskDetailName.
 */
public class IpTaskDetailName implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6520620763628019773L;

    private Long idIpTaskDetailName;
    
    private Long fkIpTaskItemDetail;
    
    private String name;
    private String firstName;
    private String nameType;
    private Date creationTimestamp;
    private Date amendmentTimestamp;
    private String mainId;
    
	@Override
	public Long getId() {
		return getIdIpTaskDetailName();
	}
	@Override
	public void setId(Long id) {
		setIdIpTaskDetailName(id);
	}
	public Long getIdIpTaskDetailName() {
		return idIpTaskDetailName;
	}
	public void setIdIpTaskDetailName(Long idIpTaskDetailName) {
		this.idIpTaskDetailName = idIpTaskDetailName;
	}
	public Long getFkIpTaskItemDetail() {
		return fkIpTaskItemDetail;
	}
	public void setFkIpTaskItemDetail(Long fkIpTaskItemDetail) {
		this.fkIpTaskItemDetail = fkIpTaskItemDetail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public Date getAmendmentTimestamp() {
		return amendmentTimestamp;
	}
	public void setAmendmentTimestamp(Date amendmentTimestamp) {
		this.amendmentTimestamp = amendmentTimestamp;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

}
