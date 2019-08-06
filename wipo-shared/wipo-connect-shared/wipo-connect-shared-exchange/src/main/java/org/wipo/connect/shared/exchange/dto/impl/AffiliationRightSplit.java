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
 * The Class AffiliationRightSplit.
 */
public class AffiliationRightSplit implements Serializable, Identifiable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7085551234266950303L;

	/** The id affiliation right split. */
	private Long idAffiliationRightSplit;
	
	/** The fk affiliation. */
	private Long fkAffiliation;
	
	/** The cmo. */
	private Cmo cmo;
	
	/** The right type flat. */
	private RightTypeFlat rightTypeFlat;
	
	/** The ipi right type flat. */
	private IpiRightTypeFlat ipiRightTypeFlat;

	/**
	 * Gets the cmo.
	 *
	 * @return the cmo
	 */
	public Cmo getCmo() {
		return cmo;
	}

	/**
	 * Sets the cmo.
	 *
	 * @param cmo the new cmo
	 */
	public void setCmo(Cmo cmo) {
		this.cmo = cmo;
	}

	/**
	 * Gets the right type flat.
	 *
	 * @return the right type flat
	 */
	public RightTypeFlat getRightTypeFlat() {
		return rightTypeFlat;
	}

	/**
	 * Sets the right type flat.
	 *
	 * @param rightTypeFlat the new right type flat
	 */
	public void setRightTypeFlat(RightTypeFlat rightTypeFlat) {
		this.rightTypeFlat = rightTypeFlat;
	}

	/**
	 * Gets the ipi right type flat.
	 *
	 * @return the ipi right type flat
	 */
	public IpiRightTypeFlat getIpiRightTypeFlat() {
		return ipiRightTypeFlat;
	}

	/**
	 * Sets the ipi right type flat.
	 *
	 * @param ipiRightTypeFlat the new ipi right type flat
	 */
	public void setIpiRightTypeFlat(IpiRightTypeFlat ipiRightTypeFlat) {
		this.ipiRightTypeFlat = ipiRightTypeFlat;
	}

	/**
	 * Gets the id affiliation right split.
	 *
	 * @return the id affiliation right split
	 */
	public Long getIdAffiliationRightSplit() {
		return idAffiliationRightSplit;
	}

	/**
	 * Sets the id affiliation right split.
	 *
	 * @param idAffiliationRightSplit the new id affiliation right split
	 */
	public void setIdAffiliationRightSplit(Long idAffiliationRightSplit) {
		this.idAffiliationRightSplit = idAffiliationRightSplit;
	}


	@Override
	public Long getId() {
		return getIdAffiliationRightSplit();
	}


	@Override
	public void setId(Long id) {
		setIdAffiliationRightSplit(id);
	}

	/**
	 * Gets the fk affiliation.
	 *
	 * @return the fk affiliation
	 */
	public Long getFkAffiliation() {
		return fkAffiliation;
	}

	/**
	 * Sets the fk affiliation.
	 *
	 * @param fkAffiliation the new fk affiliation
	 */
	public void setFkAffiliation(Long fkAffiliation) {
		this.fkAffiliation = fkAffiliation;
	}

}
