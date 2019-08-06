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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class WorkIdentifierFlat.
 */
public class WorkIdentifierFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 175497802726503360L;

    /** The id work identifier. */
    private Long idWorkIdentifier;

    /** The fk work. */
    private Long fkWork;

    /** The fk identifier. */
    private Long fkIdentifier;

    /** The code. */
    private String code;

    /** The value. */
    private String value;

    /** The exec delete. */
    private Boolean execDelete = false;
   
    /** The acronym. */
	private String acronym;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }



    /**
     * Gets the fk identifier.
     *
     * @return the fk identifier
     */
    public Long getFkIdentifier() {
        return this.fkIdentifier;
    }



    /**
     * Gets the fk work.
     *
     * @return the fk work
     */
    public Long getFkWork() {
        return this.fkWork;
    }




    @Override
    public Long getId() {
        return getIdWorkIdentifier();
    }



    /**
     * Gets the id work identifier.
     *
     * @return the id work identifier
     */
    public Long getIdWorkIdentifier() {
        return this.idWorkIdentifier;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }



    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }



    /**
     * Sets the fk identifier.
     *
     * @param fkIdentifier
     *            the new fk identifier
     */
    public void setFkIdentifier(Long fkIdentifier) {
        this.fkIdentifier = fkIdentifier;
    }



    /**
     * Sets the fk work.
     *
     * @param fkWork
     *            the new fk work
     */
    public void setFkWork(Long fkWork) {
        this.fkWork = fkWork;
    }




    @Override
    public void setId(Long id) {
        setIdWorkIdentifier(id);
    }



    /**
     * Sets the id work identifier.
     *
     * @param idWorkIdentifier
     *            the new id work identifier
     */
    public void setIdWorkIdentifier(Long idWorkIdentifier) {
        this.idWorkIdentifier = idWorkIdentifier;
    }



    /**
     * Sets the value.
     *
     * @param value
     *            the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    

    /**
     * Gets the exec delete.
     *
     * @return the exec delete
     */
    public Boolean getExecDelete() {
		return execDelete;
	}



	/**
	 * Sets the exec delete.
	 *
	 * @param execDelete the new exec delete
	 */
	public void setExecDelete(Boolean execDelete) {
		this.execDelete = execDelete;
	}




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	/**
	 * Gets the acronym.
	 *
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}



	/**
	 * Sets the acronym.
	 *
	 * @param acronym the new acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

}
