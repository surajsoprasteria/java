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
 * The Class WorkTaskDetailIdent.
 */
public class WorkTaskDetailIdent implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 132344793461194339L;

    /** The id work task detail ident. */
    private Long idWorkTaskDetailIdent;
    
    /** The fk work task detail. */
    private Long fkWorkTaskDetail;
    
    /** The code. */
    private String code;
    
    /** The value. */
    private String value;


    @Override
    public Long getId() {
        return getIdWorkTaskDetailIdent();
    }
    

    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailIdent(id);
    }

    /**
     * Gets the id work task detail ident.
     *
     * @return the id work task detail ident
     */
    public Long getIdWorkTaskDetailIdent() {
        return idWorkTaskDetailIdent;
    }
    
    /**
     * Sets the id work task detail ident.
     *
     * @param idWorkTaskDetailIdent the new id work task detail ident
     */
    public void setIdWorkTaskDetailIdent(Long idWorkTaskDetailIdent) {
        this.idWorkTaskDetailIdent = idWorkTaskDetailIdent;
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
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
