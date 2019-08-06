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
 * The Class CreationClassFlat.
 */
public class CreationClassFlat extends BaseDTO implements Serializable,
        Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4150431553102669240L;

    /** The id creation class. */
    private Long idCreationClass;

    /** The code. */
    private String code;

    /** The fkName. */
    private Long fkName;
    
    /** The name. */
    private String name;
    
    /** The fkDescription. */
    private Long fkDescription;

    /** The description. */
    private String description;

    /** The examples. */
    private String examples;



    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
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
     * Gets the examples.
     *
     * @return the examples
     */
    public String getExamples() {
        return this.examples;
    }




    @Override
    public Long getId() {
        return getIdCreationClass();
    }



    /**
     * Gets the id creation class.
     *
     * @return the id creation class
     */
    public Long getIdCreationClass() {
        return this.idCreationClass;
    }

    
    


    public Long getFkName() {
		return fkName;
	}



	public void setFkName(Long fkName) {
		this.fkName = fkName;
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
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
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



    
    public Long getFkDescription() {
		return fkDescription;
	}



	public void setFkDescription(Long fkDescription) {
		this.fkDescription = fkDescription;
	}



	/**
     * Sets the examples.
     *
     * @param examples
     *            the new examples
     */
    public void setExamples(String examples) {
        this.examples = examples;
    }




    @Override
    public void setId(Long id) {
        setIdCreationClass(id);

    }



    /**
     * Sets the id creation class.
     *
     * @param idCreationClass
     *            the new id creation class
     */
    public void setIdCreationClass(Long idCreationClass) {
        this.idCreationClass = idCreationClass;
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
}
