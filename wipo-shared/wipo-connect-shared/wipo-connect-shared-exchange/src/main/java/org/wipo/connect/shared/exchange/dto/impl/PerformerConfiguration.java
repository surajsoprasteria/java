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


public class PerformerConfiguration implements Serializable, Identifiable{
	private static final long serialVersionUID = 3759606407897296761L;
	
	private Long idPerformerConfiguration;
	private String fkCreationClass;
	private Boolean hidePerformers;
	
	public Long getIdPerformerConfiguration() {
		return idPerformerConfiguration;
	}
	public void setIdPerformerConfiguration(Long idPerformerConfiguration) {
		this.idPerformerConfiguration = idPerformerConfiguration;
	}
	public String getFkCreationClass() {
		return fkCreationClass;
	}
	public void setFkCreationClass(String fkCreationClass) {
		this.fkCreationClass = fkCreationClass;
	}
	public Boolean getHidePerformers() {
		return hidePerformers;
	}
	public void setHidePerformers(Boolean hidePerformers) {
		this.hidePerformers = hidePerformers;
	}
	@Override
	public Long getId() {
		return getIdPerformerConfiguration();
	}
	@Override
	public void setId(Long id) {
		setIdPerformerConfiguration(id);
	}
	
}
