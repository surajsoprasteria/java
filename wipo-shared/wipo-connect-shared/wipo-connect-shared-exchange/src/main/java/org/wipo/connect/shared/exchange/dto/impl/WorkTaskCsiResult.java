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
 * The Class WorkTaskCsiResult.
 *
 * @author fumagalli
 */
public class WorkTaskCsiResult implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6313254608538284081L;


    /** The id work task csi result. */
    private Long idWorkTaskCsiResult;

    /** The fk work task item. */
    private Long fkWorkTaskItem;

    /** The status code. */
    private String statusCode;

    /** The item code. */
    private String itemCode;


    @Override
    public Long getId() {
        return getIdWorkTaskCsiResult();
    }


    @Override
    public void setId(Long id) {
        setIdWorkTaskCsiResult(id);
    }

    /**
     * Gets the id work task csi result.
     *
     * @return the id work task csi result
     */
    public Long getIdWorkTaskCsiResult() {
        return idWorkTaskCsiResult;
    }

    /**
     * Sets the id work task csi result.
     *
     * @param idWorkTaskCsiResult the new id work task csi result
     */
    public void setIdWorkTaskCsiResult(Long idWorkTaskCsiResult) {
        this.idWorkTaskCsiResult = idWorkTaskCsiResult;
    }

    /**
     * Gets the fk work task item.
     *
     * @return the fk work task item
     */
    public Long getFkWorkTaskItem() {
        return fkWorkTaskItem;
    }

    /**
     * Sets the fk work task item.
     *
     * @param fkWorkTaskItem the new fk work task item
     */
    public void setFkWorkTaskItem(Long fkWorkTaskItem) {
        this.fkWorkTaskItem = fkWorkTaskItem;
    }

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode the new status code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
