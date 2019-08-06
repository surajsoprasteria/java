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
 * The Class RightSplit.
 */
public class RightSplit implements Serializable, Identifiable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4633018634903408285L;
	
	/** The id right split. */
	private Long idRightSplit;
	
	/** The fk ipi right type. */
	private Long fkIpiRightType;
	
	/** The percentage. */
	private Double percentage;
	
	/** The fk right type. */
	private Long fkRightType;
	
	
	/**
	 * Gets the id right split.
	 *
	 * @return the id right split
	 */
	public Long getIdRightSplit() {
		return idRightSplit;
	}
	
	/**
	 * Sets the id right split.
	 *
	 * @param idRightSplit the new id right split
	 */
	public void setIdRightSplit(Long idRightSplit) {
		this.idRightSplit = idRightSplit;
	}
	
	/**
	 * Gets the fk ipi right type.
	 *
	 * @return the fk ipi right type
	 */
	public Long getFkIpiRightType() {
		return fkIpiRightType;
	}
	
	/**
	 * Sets the fk ipi right type.
	 *
	 * @param fkIpiRightType the new fk ipi right type
	 */
	public void setFkIpiRightType(Long fkIpiRightType) {
		this.fkIpiRightType = fkIpiRightType;
	}
	
	/**
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public Double getPercentage() {
		return percentage;
	}
	
	/**
	 * Sets the percentage.
	 *
	 * @param percentage the new percentage
	 */
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	/**
	 * Gets the fk right type.
	 *
	 * @return the fk right type
	 */
	public Long getFkRightType() {
		return fkRightType;
	}
	
	/**
	 * Sets the fk right type.
	 *
	 * @param fkRightType the new fk right type
	 */
	public void setFkRightType(Long fkRightType) {
		this.fkRightType = fkRightType;
	}
	

	@Override
	public Long getId() {
		return getIdRightSplit();
	}
	

	@Override
	public void setId(Long id) {
		setIdRightSplit(id);
	}
	

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
