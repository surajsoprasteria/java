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



package org.wipo.connect.shared.exchange.vo;



import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Deletable;



/**
 * The Class RightOwnerSearchVO.
 */
public class RightOwnerSearchVO implements Serializable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9182713939808616074L;

    /** The id name. */
    private Long idName;

    /** The name. */
    private String name;

    /** The ipin. */
    private String ipin;

    /** The id role. */
    private Long idRole;

    /** The role code. */
    private String roleCode;

    /** The exec delete. */
    private Boolean execDelete = false;



    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public String getRoleCode() {
        return this.roleCode;
    }




    @Override
    public Boolean getExecDelete() {
        return this.execDelete;
    }




    @Override
    public Long getId() {
        return null; //no id, just to use cleanDeletableCollection
    }



    /**
     * Gets the id role.
     *
     * @return the id role
     */
    public Long getIdRole() {
        return this.idRole;
    }



    /**
     * Gets the id name.
     *
     * @return the id name
     */
    public Long getIdName() {
        return this.idName;
    }



    /**
     * Gets the ipin.
     *
     * @return the ipin
     */
    public String getIpin() {
        return this.ipin;
    }



    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }



    /**
     * Sets the role code.
     *
     * @param roleCode
     *            the new role code
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }




    @Override
    public void setExecDelete(Boolean execDelete) {
        this.execDelete = execDelete;
    }




    @Override
    public void setId(Long id) {
        //EMPTY !
    }



    /**
     * Sets the id role.
     *
     * @param idRole
     *            the new id role
     */
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }



    /**
     * Sets the id name.
     *
     * @param idName
     *            the new id name
     */
    public void setIdName(Long idName) {
        this.idName = idName;
    }



    /**
     * Sets the ipin.
     *
     * @param ipin
     *            the new ipin
     */
    public void setIpin(String ipin) {
        this.ipin = ipin;
    }



    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
