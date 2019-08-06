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
 * The Class WorkTaskDetailPerf.
 */
public class WorkTaskDetailPerf implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5360531738283042270L;

    /** The id work task detail perf. */
    private Long idWorkTaskDetailPerf;
    
    /** The fk work task detail. */
    private Long fkWorkTaskDetail;
    
    /** The performer name. */
    private String performerName;




    @Override
    public Long getId() {
        return getIdWorkTaskDetailPerf();
    }
    

    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailPerf(id);
    }
    
    /**
     * Gets the id work task detail perf.
     *
     * @return the id work task detail perf
     */
    public Long getIdWorkTaskDetailPerf() {
        return idWorkTaskDetailPerf;
    }
    
    /**
     * Sets the id work task detail perf.
     *
     * @param idWorkTaskDetailPerf the new id work task detail perf
     */
    public void setIdWorkTaskDetailPerf(Long idWorkTaskDetailPerf) {
        this.idWorkTaskDetailPerf = idWorkTaskDetailPerf;
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
     * Gets the performer name.
     *
     * @return the performer name
     */
    public String getPerformerName() {
        return performerName;
    }
    
    /**
     * Sets the performer name.
     *
     * @param performerName the new performer name
     */
    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }
}
