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
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskDetailDvName.
 */
public class WorkTaskDetailDvName implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2644213949287183063L;

    /** The id work task detail dv name. */
    private Long idWorkTaskDetailDvName;

    /** The fk work task detail dv. */
    private Long fkWorkTaskDetailDv;

    /** The name. */
    private Name name;

    /** The role code. */
    private String roleCode;

    /** The creator ref index. */
    private Long creatorRefIndex;

    /** The ref index. */
    private Long refIndex;

    /** The source type code. */
    private String sourceTypeCode;

    /** The derived view name share list. */
    private List<WorkTaskDetailDvShare> derivedViewNameShareList;



    @Override
    public Long getId() {
        return getIdWorkTaskDetailDvName();
    }


    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailDvName(id);
    }

    /**
     * Gets the id work task detail dv name.
     *
     * @return the id work task detail dv name
     */
    public Long getIdWorkTaskDetailDvName() {
        return idWorkTaskDetailDvName;
    }

    /**
     * Sets the id work task detail dv name.
     *
     * @param idWorkTaskDetailDvName the new id work task detail dv name
     */
    public void setIdWorkTaskDetailDvName(Long idWorkTaskDetailDvName) {
        this.idWorkTaskDetailDvName = idWorkTaskDetailDvName;
    }

    /**
     * Gets the fk work task detail dv.
     *
     * @return the fk work task detail dv
     */
    public Long getFkWorkTaskDetailDv() {
        return fkWorkTaskDetailDv;
    }

    /**
     * Sets the fk work task detail dv.
     *
     * @param fkWorkTaskDetailDv the new fk work task detail dv
     */
    public void setFkWorkTaskDetailDv(Long fkWorkTaskDetailDv) {
        this.fkWorkTaskDetailDv = fkWorkTaskDetailDv;
    }

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param roleCode the new role code
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * Gets the creator ref index.
     *
     * @return the creator ref index
     */
    public Long getCreatorRefIndex() {
        return creatorRefIndex;
    }

    /**
     * Sets the creator ref index.
     *
     * @param creatorRefIndex the new creator ref index
     */
    public void setCreatorRefIndex(Long creatorRefIndex) {
        this.creatorRefIndex = creatorRefIndex;
    }

    /**
     * Gets the ref index.
     *
     * @return the ref index
     */
    public Long getRefIndex() {
        return refIndex;
    }

    /**
     * Sets the ref index.
     *
     * @param refIndex the new ref index
     */
    public void setRefIndex(Long refIndex) {
        this.refIndex = refIndex;
    }

    /**
     * Gets the source type code.
     *
     * @return the source type code
     */
    public String getSourceTypeCode() {
        return sourceTypeCode;
    }

    /**
     * Sets the source type code.
     *
     * @param sourceTypeCode the new source type code
     */
    public void setSourceTypeCode(String sourceTypeCode) {
        this.sourceTypeCode = sourceTypeCode;
    }

    /**
     * Gets the derived view name share list.
     *
     * @return the derived view name share list
     */
    public List<WorkTaskDetailDvShare> getDerivedViewNameShareList() {
        if(derivedViewNameShareList == null){
            derivedViewNameShareList = new ArrayList<>();
        }
        return derivedViewNameShareList;
    }

    /**
     * Sets the derived view name share list.
     *
     * @param derivedViewNameShareList the new derived view name share list
     */
    public void setDerivedViewNameShareList(List<WorkTaskDetailDvShare> derivedViewNameShareList) {
        this.derivedViewNameShareList = derivedViewNameShareList;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(Name name) {
        this.name = name;
    }


}
