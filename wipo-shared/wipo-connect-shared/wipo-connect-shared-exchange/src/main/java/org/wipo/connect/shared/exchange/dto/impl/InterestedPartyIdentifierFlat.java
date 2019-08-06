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
 * The Class InterestedPartyIdentifierFlat.
 */
public class InterestedPartyIdentifierFlat implements Serializable,
        Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 175497802726503360L;

    /** The id interested party identifier. */
    private Long idInterestedPartyIdentifier;

    /** The fk interested party. */
    private Long fkInterestedParty;

    /** The fk identifier. */
    private Long fkIdentifier;

    /** The code. */
    private String code;

    /** The value. */
    private String value;



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
     * Gets the fk interested party.
     *
     * @return the fk interested party
     */
    public Long getFkInterestedParty() {
        return this.fkInterestedParty;
    }




    @Override
    public Long getId() {
        return getIdInterestedPartyIdentifier();
    }



    /**
     * Gets the id interested party identifier.
     *
     * @return the id interested party identifier
     */
    public Long getIdInterestedPartyIdentifier() {
        return this.idInterestedPartyIdentifier;
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
     * Sets the fk interested party.
     *
     * @param fkInterestedParty
     *            the new fk interested party
     */
    public void setFkInterestedParty(Long fkInterestedParty) {
        this.fkInterestedParty = fkInterestedParty;
    }




    @Override
    public void setId(Long id) {
        setIdInterestedPartyIdentifier(id);
    }



    /**
     * Sets the id interested party identifier.
     *
     * @param idInterestedPartyIdentifier
     *            the new id interested party identifier
     */
    public void setIdInterestedPartyIdentifier(Long idInterestedPartyIdentifier) {
        this.idInterestedPartyIdentifier = idInterestedPartyIdentifier;
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




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
