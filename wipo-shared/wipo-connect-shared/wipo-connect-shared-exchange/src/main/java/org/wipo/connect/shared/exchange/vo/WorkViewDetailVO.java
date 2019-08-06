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
package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;

public class WorkViewDetailVO implements Serializable, Identifiable, Deletable {

    private static final long serialVersionUID = 4816118431485662605L;

    private Long id;
    private Long idName;
    private String fullName;
    private String nameMainId;
    private Long fkRole;
    private Long creatorRefIndex;
    private String creatorRefMainId;
    private Long refIndex;
    private String interestedPartyType;
    private String sourceType;

    private Map<String, WorkViewShareVO> viewShareVOMap;
    /** The exec delete. */
    private Boolean execDelete = false;

    public Long getIdName() {
	return idName;
    }

    public void setIdName(Long idName) {
	this.idName = idName;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getNameMainId() {
	return nameMainId;
    }

    public void setNameMainId(String nameMainId) {
	this.nameMainId = nameMainId;
    }

    public Long getFkRole() {
	return fkRole;
    }

    public void setFkRole(Long fkRole) {
	this.fkRole = fkRole;
    }

    public Long getCreatorRefIndex() {
	return creatorRefIndex;
    }

    public void setCreatorRefIndex(Long creatorRefIndex) {
	this.creatorRefIndex = creatorRefIndex;
    }

    public String getCreatorRefMainId() {
	return creatorRefMainId;
    }

    public void setCreatorRefMainId(String creatorRefMainId) {
	this.creatorRefMainId = creatorRefMainId;
    }

    public Long getRefIndex() {
	return refIndex;
    }

    public void setRefIndex(Long refIndex) {
	this.refIndex = refIndex;
    }

    public String getInterestedPartyType() {
	return interestedPartyType;
    }

    public void setInterestedPartyType(String interestedPartyType) {
	this.interestedPartyType = interestedPartyType;
    }

    public String getSourceType() {
	return sourceType;
    }

    public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
    }

    public Map<String, WorkViewShareVO> getViewShareVOMap() {
	if (null == viewShareVOMap) {
	    viewShareVOMap = new HashMap<>();
	}
	return viewShareVOMap;
    }

    public void setViewShareVOMap(Map<String, WorkViewShareVO> viewShareVOMap) {
	this.viewShareVOMap = viewShareVOMap;
    }

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public Boolean getExecDelete() {
	return this.execDelete;
    }

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

}
