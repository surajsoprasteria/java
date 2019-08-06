/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.dto.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.shared.exchange.enumerator.DomainEnum;

public class DerivedWork extends BaseDTO implements Identifiable, Deletable {

	private static final long serialVersionUID = 7498379650884024924L;
	public static final String SEP_MAIN = "~~~";
	public static final String SEP_SUB = "^^^";

	private Long idDerivedWork;
	private Long fkWork;
	private String mainIdWork;
	private Long fkParentWork;
	private Work work;

	private Long weight;

	private Long trackNumber;

	private Boolean execDelete;

	@Override
	public Long getId() {
		return getIdDerivedWork();
	}

	@Override
	public void setId(Long id) {
		setIdDerivedWork(id);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Long getIdDerivedWork() {
		return idDerivedWork;
	}

	public void setIdDerivedWork(Long idDerivedWork) {
		this.idDerivedWork = idDerivedWork;
	}

	public Long getFkWork() {
		return fkWork;
	}

	public void setFkWork(Long fkWork) {
		this.fkWork = fkWork;
	}

	public String getMainIdWork() {
		if(work != null) {
			mainIdWork = work.getMainId();
		}
		return mainIdWork;
	}

	public void setMainIdWork(String mainIdWork) {
		if(work != null) {
			this.work.setMainId(mainIdWork);
		}
		this.mainIdWork = mainIdWork;
	}

	public Long getFkParentWork() {
		return fkParentWork;
	}

	public void setFkParentWork(Long fkParentWork) {
		this.fkParentWork = fkParentWork;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getDomain() {
		return DomainEnum.INTERNATIONAL.name();
	}

	public Long getSharedId() {
		if (work == null) {
			return null;
		}
		return work.getId();
	}

	public String getSharedOt() {
		if (work == null) {
			return null;
		}
		return work.getOriginalTitle();
	}

	public String getSharedMainId() {
		if (work == null) {
			return null;
		}
		return work.getMainId();
	}

	public String getSharedClass() {
		if (work == null) {
			return null;
		}
		return work.getCreationClassCode();
	}

	@Override
	public Boolean getExecDelete() {
		return execDelete;
	}

	@Override
	public void setExecDelete(Boolean execDelete) {
		this.execDelete = execDelete;
	}

	public String getSharedRo() {
		if (work == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		work.getDerivedViewList().forEach(dv -> {
			dv.getDerivedViewNameList().forEach(dvn -> {
				sb.append(dvn.getName().getFullName());
				sb.append(SEP_SUB);
				sb.append(dvn.getRoleCode());
				sb.append(SEP_MAIN);
			});
		});

		return sb.toString();
	}

	public String getSharedIdentifier() {
		if (work == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		work.getWorkIdentifierList().forEach(idf -> {
			sb.append(idf.getCode());
			sb.append(SEP_SUB);
			sb.append(idf.getValue());
			sb.append(SEP_MAIN);
		});

		return sb.toString();
	}

	public Long getTrackNumber() {
	    return trackNumber;
	}

	public void setTrackNumber(Long trackNumber) {
	    this.trackNumber = trackNumber;
	}

}
