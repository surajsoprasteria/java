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
import java.math.BigDecimal;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskDetailDvShare.
 */
public class WorkTaskDetailDvShare implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5327839844275235915L;

    /** The id work task detail dv share. */
    private Long idWorkTaskDetailDvShare;
    
    /** The fk work task detail dv name. */
    private Long fkWorkTaskDetailDvName;
    
    /** The right type code. */
    private String rightTypeCode;
    
    /** The share value. */
    private BigDecimal shareValue;
    
    /** The cmo code. */
    private String cmoCode;


    @Override
    public Long getId() {
        return getIdWorkTaskDetailDvShare();
    }
    

    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailDvShare(id);
    }

    /**
     * Gets the id work task detail dv share.
     *
     * @return the id work task detail dv share
     */
    public Long getIdWorkTaskDetailDvShare() {
        return idWorkTaskDetailDvShare;
    }
    
    /**
     * Sets the id work task detail dv share.
     *
     * @param idWorkTaskDetailDvShare the new id work task detail dv share
     */
    public void setIdWorkTaskDetailDvShare(Long idWorkTaskDetailDvShare) {
        this.idWorkTaskDetailDvShare = idWorkTaskDetailDvShare;
    }
    
    /**
     * Gets the fk work task detail dv name.
     *
     * @return the fk work task detail dv name
     */
    public Long getFkWorkTaskDetailDvName() {
        return fkWorkTaskDetailDvName;
    }
    
    /**
     * Sets the fk work task detail dv name.
     *
     * @param fkWorkTaskDetailDvName the new fk work task detail dv name
     */
    public void setFkWorkTaskDetailDvName(Long fkWorkTaskDetailDvName) {
        this.fkWorkTaskDetailDvName = fkWorkTaskDetailDvName;
    }
    
    /**
     * Gets the right type code.
     *
     * @return the right type code
     */
    public String getRightTypeCode() {
        return rightTypeCode;
    }
    
    /**
     * Sets the right type code.
     *
     * @param rightTypeCode the new right type code
     */
    public void setRightTypeCode(String rightTypeCode) {
        this.rightTypeCode = rightTypeCode;
    }
    
    /**
     * Gets the share value.
     *
     * @return the share value
     */
    public BigDecimal getShareValue() {
        return shareValue;
    }
    
    /**
     * Sets the share value.
     *
     * @param shareValue the new share value
     */
    public void setShareValue(BigDecimal shareValue) {
        this.shareValue = shareValue;
    }
    
    /**
     * Gets the cmo code.
     *
     * @return the cmo code
     */
    public String getCmoCode() {
        return cmoCode;
    }
    
    /**
     * Sets the cmo code.
     *
     * @param cmoCode the new cmo code
     */
    public void setCmoCode(String cmoCode) {
        this.cmoCode = cmoCode;
    }
}
