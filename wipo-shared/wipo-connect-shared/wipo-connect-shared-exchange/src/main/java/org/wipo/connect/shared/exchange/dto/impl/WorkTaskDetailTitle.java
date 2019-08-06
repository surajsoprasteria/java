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

import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class WorkTaskDetailTitle.
 *
 * @author minervini
 */
public class WorkTaskDetailTitle implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2125007506537927606L;

    /** The id work task title. */
    private Long idWorkTaskDetailTitle;

    /** The fk work task detail. */
    private Long fkWorkTaskDetail;

    /** The description. */
    private String description;

    /** The type code. */
    private String typeCode;



    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }



    /**
     * Gets the type code.
     *
     * @return the type code
     */
    public String getTypeCode() {
        return this.typeCode;
    }



    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }



    /**
     * Sets the type code.
     *
     * @param typeCode
     *            the new type code
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }



    /**
     * Gets the id work task detail title.
     *
     * @return the id work task detail title
     */
    public Long getIdWorkTaskDetailTitle() {
        return idWorkTaskDetailTitle;
    }



    /**
     * Sets the id work task detail title.
     *
     * @param idWorkTaskDetailTitle the new id work task detail title
     */
    public void setIdWorkTaskDetailTitle(Long idWorkTaskDetailTitle) {
        this.idWorkTaskDetailTitle = idWorkTaskDetailTitle;
    }




    @Override
    public Long getId() {
        return getIdWorkTaskDetailTitle();
    }




    @Override
    public void setId(Long id) {
        setIdWorkTaskDetailTitle(id);
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

}
