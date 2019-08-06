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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class RightTypeFlat.
 */
public class RightTypeFlat implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2801471418273823066L;

	/** The id right type. */
	private Long idRightType;

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

	private List<CreationClassFlat> creationClassList;

	private List<Long> fkCcList;

	private List<Long> fkIpiRightTypeList;

	private List<IpiRightTypeFlat> ipiRightTypeFlat;

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
		return getIdRightType();
	}

	/**
	 * Gets the id right type.
	 *
	 * @return the id right type
	 */
	public Long getIdRightType() {
		return this.idRightType;
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
		setIdRightType(id);
	}

	/**
	 * Sets the id right type.
	 *
	 * @param idRightType
	 *            the new id right type
	 */
	public void setIdRightType(Long idRightType) {
		this.idRightType = idRightType;
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

	public List<Long> getFkIpiRightTypeList() {
		return fkIpiRightTypeList;
	}

	public void setFkIpiRightTypeList(List<Long> fkIpiRightTypeList) {
		this.fkIpiRightTypeList = fkIpiRightTypeList;
	}

	public List<IpiRightTypeFlat> getIpiRightTypeFlat() {
		return ipiRightTypeFlat;
	}

	public void setIpiRightTypeFlat(List<IpiRightTypeFlat> ipiRightTypeFlat) {
		this.ipiRightTypeFlat = ipiRightTypeFlat;
	}

	public List<CreationClassFlat> getCreationClassList() {
		if (null == creationClassList) {
			creationClassList = new ArrayList<CreationClassFlat>();
		}
		return creationClassList;
	}

	public void setCreationClassList(List<CreationClassFlat> creationClassList) {
		this.creationClassList = creationClassList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
