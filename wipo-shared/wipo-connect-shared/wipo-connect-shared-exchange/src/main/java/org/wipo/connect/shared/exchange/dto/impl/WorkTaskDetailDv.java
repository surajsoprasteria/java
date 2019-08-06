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
 * The Class WorkTaskDetailDv.
 */
public class WorkTaskDetailDv implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6520620763628019773L;

    /** The id work task detail dv. */
    private Long idWorkTaskDetailDv;
    
    /** The fk work task detail. */
    private Long fkWorkTaskDetail;
    
    /** The territory formula. */
    private String territoryFormula;
    
    /** The derived view name list. */
    private List<WorkTaskDetailDvName> derivedViewNameList;



    @Override
    public Long getId() {
        return getIdWorkTaskDetailDv();
    }
    

    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailDv(id);
    }

    /**
     * Gets the id work task detail dv.
     *
     * @return the id work task detail dv
     */
    public Long getIdWorkTaskDetailDv() {
        return idWorkTaskDetailDv;
    }
    
    /**
     * Sets the id work task detail dv.
     *
     * @param idWorkTaskDetailDv the new id work task detail dv
     */
    public void setIdWorkTaskDetailDv(Long idWorkTaskDetailDv) {
        this.idWorkTaskDetailDv = idWorkTaskDetailDv;
    }
    
    /**
     * Gets the fk work task detail.
     *
     * @return the fk work task detail
     */
    public Long getFkWorkTaskDetail() {
        return fkWorkTaskDetail;
    }
    
    /**
     * Sets the fk work task detail.
     *
     * @param fkWorkTaskDetail the new fk work task detail
     */
    public void setFkWorkTaskDetail(Long fkWorkTaskDetail) {
        this.fkWorkTaskDetail = fkWorkTaskDetail;
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
	 * @param territoryFormula the new territory formula
	 */
	public void setTerritoryFormula(String territoryFormula) {
		this.territoryFormula = territoryFormula;
	}
	
	/**
	 * Gets the derived view name list.
	 *
	 * @return the derived view name list
	 */
	public List<WorkTaskDetailDvName> getDerivedViewNameList() {
        if(derivedViewNameList == null){
            derivedViewNameList = new ArrayList<>();
        }
        return derivedViewNameList;
    }
    
    /**
     * Sets the derived view name list.
     *
     * @param derivedViewNameList the new derived view name list
     */
    public void setDerivedViewNameList(List<WorkTaskDetailDvName> derivedViewNameList) {
        this.derivedViewNameList = derivedViewNameList;
    }

}
