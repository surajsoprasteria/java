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



import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;



/**
 * The Class WorkPerformer.
 */
public class WorkPerformer extends BaseDTO implements Identifiable, Updatable, Creatable, Deletable {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6117757565931789409L;

    /** The id work performer. */
    private Long idWorkPerformer;
    
    /** The fk work. */
    private Long fkWork;
    
    /** The performer name. */
    private String performerName;
    
    /** The exec delete. */
    private Boolean execDelete;



    /**
     * Gets the id work performer.
     *
     * @return the id work performer
     */
    public Long getIdWorkPerformer() {
        return idWorkPerformer;
    }



    /**
     * Sets the id work performer.
     *
     * @param idWorkPerformer the new id work performer
     */
    public void setIdWorkPerformer(Long idWorkPerformer) {
        this.idWorkPerformer = idWorkPerformer;
    }



    /**
     * Gets the fk work.
     *
     * @return the fk work
     */
    public Long getFkWork() {
        return fkWork;
    }



    /**
     * Sets the fk work.
     *
     * @param fkWork the new fk work
     */
    public void setFkWork(Long fkWork) {
        this.fkWork = fkWork;
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




    @Override
    public Boolean getExecDelete() {
        return this.execDelete;
    }




    @Override
    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }




    @Override
    public Long getId() {
        return this.idWorkPerformer;
    }




    @Override
    public void setId(Long id) {
        this.idWorkPerformer = id;
    }

}
