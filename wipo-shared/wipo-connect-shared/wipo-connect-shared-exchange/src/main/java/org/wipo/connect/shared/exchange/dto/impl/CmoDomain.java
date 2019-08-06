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
 * The Class CmoDomain.
 *
 * @author fumagalli
 */
public class CmoDomain implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7847489230691741172L;

    /** The id cmo domain. */
    private Long idCmoDomain;

    /** The fk cmo. */
    private Long fkCmo;

    /** The fk ipi role. */
    private Long fkIpiRole;

    /** The fk creation class. */
    private Long fkCreationClass;

    /** The fk ipi right type. */
    private Long fkIpiRightType;



    /**
     * Gets the fk cmo.
     *
     * @return the fk cmo
     */
    public Long getFkCmo() {
        return this.fkCmo;
    }



    /**
     * Gets the fk creation class.
     *
     * @return the fk creation class
     */
    public Long getFkCreationClass() {
        return this.fkCreationClass;
    }



    /**
     * Gets the fk ipi right type.
     *
     * @return the fk ipi right type
     */
    public Long getFkIpiRightType() {
        return this.fkIpiRightType;
    }



    /**
     * Gets the fk ipi role.
     *
     * @return the fk ipi role
     */
    public Long getFkIpiRole() {
        return this.fkIpiRole;
    }




    @Override
    public Long getId() {
        return getIdCmoDomain();
    }



    /**
     * Gets the id cmo domain.
     *
     * @return the id cmo domain
     */
    public Long getIdCmoDomain() {
        return this.idCmoDomain;
    }



    /**
     * Sets the fk cmo.
     *
     * @param fkCmo
     *            the new fk cmo
     */
    public void setFkCmo(Long fkCmo) {
        this.fkCmo = fkCmo;
    }



    /**
     * Sets the fk creation class.
     *
     * @param fkCreationClass
     *            the new fk creation class
     */
    public void setFkCreationClass(Long fkCreationClass) {
        this.fkCreationClass = fkCreationClass;
    }



    /**
     * Sets the fk ipi right type.
     *
     * @param fkIpiRightType
     *            the new fk ipi right type
     */
    public void setFkIpiRightType(Long fkIpiRightType) {
        this.fkIpiRightType = fkIpiRightType;
    }



    /**
     * Sets the fk ipi role.
     *
     * @param fkIpiRole
     *            the new fk ipi role
     */
    public void setFkIpiRole(Long fkIpiRole) {
        this.fkIpiRole = fkIpiRole;
    }




    @Override
    public void setId(Long id) {
        setIdCmoDomain(id);
    }



    /**
     * Sets the id cmo domain.
     *
     * @param idCmoDomain
     *            the new id cmo domain
     */
    public void setIdCmoDomain(Long idCmoDomain) {
        this.idCmoDomain = idCmoDomain;
    }
}
