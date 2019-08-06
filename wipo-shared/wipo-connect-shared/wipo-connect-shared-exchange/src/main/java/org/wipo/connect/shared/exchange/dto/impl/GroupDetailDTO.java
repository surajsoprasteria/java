package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.caseconverter.CaseConversion;

public class GroupDetailDTO implements Serializable {

	private static final long serialVersionUID = -8275936970090150770L;

	private Long idGroup;
	private Long interestedPartyId;
	@CaseConversion
	private String name;
	@CaseConversion
	private String firstName;

	private String nameType;

	private String mainId;
	private Boolean execDelete = false;
	private Boolean execInsert = false;

	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public Long getInterestedPartyId() {
		return interestedPartyId;
	}

	public void setInterestedPartyId(Long interestedPartyId) {
		this.interestedPartyId = interestedPartyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public Boolean getExecDelete() {
		return execDelete;
	}

	public void setExecDelete(Boolean execDelete) {
		this.execDelete = execDelete;
	}

	public Boolean getExecInsert() {
		return execInsert;
	}

	public void setExecInsert(Boolean execInsert) {
		this.execInsert = execInsert;
	}

	public String getFullName() {
		return StringUtils.removeEnd(StringUtils.defaultString(this.name) + ", " + StringUtils.trimToEmpty(this.firstName), ", ");
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
