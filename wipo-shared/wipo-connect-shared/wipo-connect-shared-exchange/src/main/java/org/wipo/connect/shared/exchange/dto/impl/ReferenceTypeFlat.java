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
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class ReferenceFlat.
 */
public class ReferenceTypeFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8096258291443902962L;

    /** The id reference. */
    private Long idReferenceType;

    /** The code. */
    private String code;

    private String managedBy;
    
    private Long fkName;
    
    private String name;
    
    private Long fkDescription;
    
    private String description;
    
    private List<ReferenceFlat> referenceValueList;


    @Override
    public Long getId() {
        return getIdReferenceType();
    }



    /**
     * Gets the id reference.
     *
     * @return the id reference
     */
    public Long getIdReferenceType() {
        return this.idReferenceType;
    }


	@Override
    public void setId(Long id) {
        setIdReferenceType(id);
    }



    public void setIdReferenceType(Long idReferenceType) {
        this.idReferenceType = idReferenceType;
    }




	public String getManagedBy() {
		return managedBy;
	}



	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
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



	public List<ReferenceFlat> getReferenceValueList() {
		return referenceValueList;
	}



	public void setReferenceValueList(List<ReferenceFlat> referenceValueList) {
		this.referenceValueList = referenceValueList;
	}
	
	
	

}
