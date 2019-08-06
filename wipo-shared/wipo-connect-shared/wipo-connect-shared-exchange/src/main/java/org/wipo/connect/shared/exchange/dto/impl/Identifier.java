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
 * The Class Identifier.
 */
public class Identifier implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    /** The id identifier. */
    private Long idIdentifier;

    /** The fk label. */
    private Long fkLabel;

    /** The acronym. */
    private String acronym;

    /** The code. */
    private String code;

    private Boolean hideFromUi;


    /**
     * Gets the acronym.
     *
     * @return the acronym
     */
    public String getAcronym() {
        return this.acronym;
    }



    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }



    /**
     * Gets the fk label.
     *
     * @return the fk label
     */
    public Long getFkLabel() {
        return this.fkLabel;
    }




    @Override
    public Long getId() {

        return getIdIdentifier();
    }



    /**
     * Gets the id identifier.
     *
     * @return the id identifier
     */
    public Long getIdIdentifier() {
        return this.idIdentifier;
    }



    /**
     * Sets the acronym.
     *
     * @param acronym
     *            the new acronym
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
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
     * Sets the fk label.
     *
     * @param fkLabel
     *            the new fk label
     */
    public void setFkLabel(Long fkLabel) {
        this.fkLabel = fkLabel;
    }




    @Override
    public void setId(Long id) {
        setIdIdentifier(id);
    }



    /**
     * Sets the id identifier.
     *
     * @param idIdentifier
     *            the new id identifier
     */
    public void setIdIdentifier(Long idIdentifier) {
        this.idIdentifier = idIdentifier;
    }



    public Boolean getHideFromUi() {
        return hideFromUi;
    }



    public void setHideFromUi(Boolean hideFromUi) {
        this.hideFromUi = hideFromUi;
    }

}
