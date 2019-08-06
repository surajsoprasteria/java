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
 * The Class ReferenceFlat.
 */
public class ReferenceFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8096258291443902962L;

    /** The id reference. */
    private Long idReference;

    /** The code. */
    private String code;

    private Long fkName;

    private String name;
    
    private Long fkRefType;

    private String refType;

    private Long fkDescription;

    private String description;


    @Override
    public Long getId() {
        return getIdReference();
    }



    /**
     * Gets the id reference.
     *
     * @return the id reference
     */
    public Long getIdReference() {
        return this.idReference;
    }
	

	public Long getFkName() {
		return fkName;
	}



	public void setFkName(Long fkName) {
		this.fkName = fkName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getFkRefType() {
		return fkRefType;
	}



	public void setFkRefType(Long fkRefType) {
		this.fkRefType = fkRefType;
	}



	public String getRefType() {
		return refType;
	}

	public String getRefCode() {
		return getCode();
	}


	public void setRefType(String refType) {
		this.refType = refType;
	}



	@Override
    public void setId(Long id) {
        setIdReference(id);
    }



    /**
     * Sets the id reference.
     *
     * @param idReference
     *            the new id reference
     */
    public void setIdReference(Long idReference) {
        this.idReference = idReference;
    }





    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}



	public Long getFkDescription() {
		return fkDescription;
	}



	public void setFkDescription(Long fkDescription) {
		this.fkDescription = fkDescription;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	
	
	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link #getName()} instead.  
	 */
	@Deprecated
	public String getValue() {
		return getName();
	}
	
	
	/**
	 * Does some thing in old style.
	 *
	 * @deprecated use {@link #setName()} instead.  
	 */
	@Deprecated
	public void setValue(String value) {
		setName(value);
	}

}
