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



import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;



/**
 * The Class Contact.
 *
 * @author minervini
 */
public class Contact extends BaseDTO implements Identifiable, Creatable,
        Updatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6437309071100758525L;

    /** The id contact. */
    private Long idContact;

    /** The value. */
    private String value;

    /** The contact type. */
    private String contactType;

    /** The exec delete. */
    private Boolean execDelete = false;



    /**
     * Gets the contact type.
     *
     * @return the contact type
     */
    public String getContactType() {
        return this.contactType;
    }




    @Override
    public Boolean getExecDelete() {
        return this.execDelete;
    }




    @Override
    public Long getId() {
        return getIdContact();
    }



    /**
     * Gets the id contact.
     *
     * @return the id contact
     */
    public Long getIdContact() {
        return this.idContact;
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
     * Sets the contact type.
     *
     * @param contactType
     *            the new contact type
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }




    @Override
    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }




    @Override
    public void setId(Long id) {
        setIdContact(id);
    }



    /**
     * Sets the id contact.
     *
     * @param idContact
     *            the new id contact
     */
    public void setIdContact(Long idContact) {
        this.idContact = idContact;
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
