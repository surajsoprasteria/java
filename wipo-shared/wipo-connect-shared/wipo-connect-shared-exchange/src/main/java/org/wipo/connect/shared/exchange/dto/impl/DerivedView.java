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
import org.wipo.connect.common.customvalidation.CustomValidatedField;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class DerivedView.
 */
public class DerivedView implements Identifiable, Serializable, Deletable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7691604497229904389L;

	/** The id derived view. */
	private Long idDerivedView;

	/** The fk work. */
	private Long fkWork;

	/** The territory formula. */
	@CustomValidatedField(fieldCode = "TERRITORY_FORMULA_VALUE")
	private String territoryFormula;

	/** The derived view name list. */
	@CustomValidatedField(innerValidation = true)
	private List<DerivedViewName> derivedViewNameList;

	/** The exec delete. */
	private Boolean execDelete = false;

	/**
	 * Gets the derived view name list.
	 *
	 * @return the derived view name list
	 */
	public List<DerivedViewName> getDerivedViewNameList() {
		if (this.derivedViewNameList == null) {
			this.derivedViewNameList = new ArrayList<>();
		}
		return this.derivedViewNameList;
	}


	@Override
	public Boolean getExecDelete() {
		return this.execDelete;
	}

	/**
	 * Gets the fk work.
	 *
	 * @return the fk work
	 */
	public Long getFkWork() {
		return this.fkWork;
	}

	/**
	 * Gets the territory formula.
	 *
	 * @return the territory formula
	 */
	public String getTerritoryFormula() {
		return territoryFormula;
	}

	/**
	 * Sets the territory formula.
	 *
	 * @param territoryFormula the new territory formula
	 */
	public void setTerritoryFormula(String territoryFormula) {
		this.territoryFormula = territoryFormula;
	}


	@Override
	public Long getId() {
		return getIdDerivedView();
	}

	/**
	 * Gets the id derived view.
	 *
	 * @return the id derived view
	 */
	public Long getIdDerivedView() {
		return this.idDerivedView;
	}

	
	

	/**
	 * Sets the derived view name list.
	 *
	 * @param derivedViewNameList
	 *            the new derived view name list
	 */
	public void setDerivedViewNameList(List<DerivedViewName> derivedViewNameList) {
		this.derivedViewNameList = derivedViewNameList;
	}


	@Override
	public void setExecDelete(Boolean execDelete) {
		this.execDelete = execDelete;
	}

	/**
	 * Sets the fk work.
	 *
	 * @param fkWork
	 *            the new fk work
	 */
	public void setFkWork(Long fkWork) {
		this.fkWork = fkWork;
	}


	@Override
	public void setId(Long id) {
		setIdDerivedView(id);
	}

	/**
	 * Sets the id derived view.
	 *
	 * @param idDerivedView
	 *            the new id derived view
	 */
	public void setIdDerivedView(Long idDerivedView) {
		this.idDerivedView = idDerivedView;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	
	
}
