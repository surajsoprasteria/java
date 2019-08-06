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
 * The Class Translation.
 */
public class Translation implements Serializable, Identifiable {
	
	private static final long serialVersionUID = 7408466597101108825L;

    private Long idTranslation;
    
    private String defaultValue;
   



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }




	@Override
	public Long getId() {
		return getIdTranslation();
	}




	@Override
	public void setId(Long id) {
		setIdTranslation(id);
	}




	/**
	 * Gets the id translation.
	 *
	 * @return the id translation
	 */
	public Long getIdTranslation() {
		return idTranslation;
	}




	/**
	 * Sets the id translation.
	 *
	 * @param idTranslation the new id translation
	 */
	public void setIdTranslation(Long idTranslation) {
		this.idTranslation = idTranslation;
	}




	/**
	 * Gets the default value.
	 *
	 * @return the default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}




	/**
	 * Sets the default value.
	 *
	 * @param defaultValue the new default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	public String getValue() {
		return getDefaultValue();
	}

}
