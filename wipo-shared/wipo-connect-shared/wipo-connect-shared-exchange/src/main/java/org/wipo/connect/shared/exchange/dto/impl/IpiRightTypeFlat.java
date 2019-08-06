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
 * The Class IpiRightTypeFlat.
 */
public class IpiRightTypeFlat implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5211429756407315507L;

    /** The id ipi right type. */
    private Long idIpiRightType;

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

    /** The Creation Class List. */
    private List<Long> fkCcList;

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
        return getIdIpiRightType();
    }



    /**
     * Gets the id ipi right type.
     *
     * @return the id ipi right type
     */
    public Long getIdIpiRightType() {
        return this.idIpiRightType;
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
        setIdIpiRightType(id);
    }



    /**
     * Sets the id ipi right type.
     *
     * @param idIpiRightType
     *            the new id ipi right type
     */
    public void setIdIpiRightType(Long idIpiRightType) {
        this.idIpiRightType = idIpiRightType;
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


    public Long getFkName() {
		return fkName;
	}



	public void setFkName(Long fkName) {
		this.fkName = fkName;
	}



	public Long getFkDescription() {
		return fkDescription;
	}



	public void setFkDescription(Long fkDescription) {
		this.fkDescription = fkDescription;
	}



	public List<Long> getFkCcList() {
		return fkCcList;
	}



	public void setFkCcList(List<Long> fkCcList) {
		this.fkCcList = fkCcList;
	}



	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
