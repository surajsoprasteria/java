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
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class Cmo.
 *
 * @author fumagalli
 */
public class Cmo implements Identifiable, Serializable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7398955698602513488L;

    /** The id cmo. */
    private Long idCmo;

    /** The name. */
    private String name;

    /** The code. */
    private String code;

    /** The acronym. */
    private String acronym;

    /** The fk origin country. */
    private Long fkOriginCountry;

    /** The contact. */
    private String contact;

    /** The description. */
    private String description;

    /** The international name. */
    private String internationalName;
    
    /** The cmo fkType. */
    private Long fkType;

    /** The cmo domain list. */
    private List<CmoDomain> cmoDomainList;

    private Boolean isDataSource;

    private Boolean execDelete;



    /**
     * Gets the checks if is data source.
     *
     * @return the checks if is data source
     */
    public Boolean getIsDataSource() {
		return isDataSource;
	}



	/**
	 * Sets the checks if is data source.
	 *
	 * @param isDataSource the new checks if is data source
	 */
	public void setIsDataSource(Boolean isDataSource) {
		this.isDataSource = isDataSource;
	}



	/**
     * Gets the acronym.
     *
     * @return the acronym
     */
    public String getAcronym() {
        return this.acronym;
    }



    /**
     * Gets the cmo domain list.
     *
     * @return the cmo domain list
     */
    public List<CmoDomain> getCmoDomainList() {
        if (this.cmoDomainList == null) {
            this.cmoDomainList = new ArrayList<CmoDomain>();
        }
        return this.cmoDomainList;
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
     * Gets the contact.
     *
     * @return the contact
     */
    public String getContact() {
        return this.contact;
    }



    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }



    /**
     * Gets the fk origin country.
     *
     * @return the fk origin country
     */
    public Long getFkOriginCountry() {
        return this.fkOriginCountry;
    }




    @Override
    public Long getId() {
        return getIdCmo();
    }



    /**
     * Gets the id cmo.
     *
     * @return the id cmo
     */
    public Long getIdCmo() {
        return this.idCmo;
    }



    /**
     * Gets the international name.
     *
     * @return the international name
     */
    public String getInternationalName() {
        return this.internationalName;
    }

    
	public Long getFkType() {
		return fkType;
	}



	public void setFkType(Long fkType) {
		this.fkType = fkType;
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
     * Sets the acronym.
     *
     * @param acronym
     *            the new acronym
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }



    /**
     * Sets the cmo domain list.
     *
     * @param cmoDomainList
     *            the new cmo domain list
     */
    public void setCmoDomainList(List<CmoDomain> cmoDomainList) {
        this.cmoDomainList = cmoDomainList;
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
     * Sets the contact.
     *
     * @param contact
     *            the new contact
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * Sets the fk origin country.
     *
     * @param fkOriginCountry
     *            the new fk origin country
     */
    public void setFkOriginCountry(Long fkOriginCountry) {
        this.fkOriginCountry = fkOriginCountry;
    }




    @Override
    public void setId(Long id) {
        setIdCmo(id);
    }



    /**
     * Sets the id cmo.
     *
     * @param idCmo
     *            the new id cmo
     */
    public void setIdCmo(Long idCmo) {
        this.idCmo = idCmo;
    }



    /**
     * Sets the international name.
     *
     * @param internationalName
     *            the new international name
     */
    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
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
	public Boolean getExecDelete() {
		return execDelete;
	}



	@Override
	public void setExecDelete(Boolean execDelete) {
		this.execDelete = execDelete;
	}


}
