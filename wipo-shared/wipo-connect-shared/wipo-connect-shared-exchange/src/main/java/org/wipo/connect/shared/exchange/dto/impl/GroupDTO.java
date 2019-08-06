package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.caseconverter.CaseConversion;
import org.wipo.connect.common.dto.Identifiable;

public class GroupDTO implements Serializable, Identifiable {

    private static final long serialVersionUID = 6075983241365455203L;

    private Long idGroup;

    @CaseConversion
    private String name;

    @CaseConversion
    private String firstName;

    private String nameType;

    private String mainId;

    private List<GroupDetailDTO> groupDetails;

    public Long getIdGroup() {
	return idGroup;
    }

    public void setIdGroup(Long idGroup) {
	this.idGroup = idGroup;
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

    public List<GroupDetailDTO> getGroupDetails() {
	if (null == groupDetails) {
	    groupDetails = new ArrayList<GroupDetailDTO>();
	}
	return groupDetails;
    }

    public void setGroupDetails(List<GroupDetailDTO> groupDetails) {
	this.groupDetails = groupDetails;
    }

    public String getFullName() {
	return StringUtils
		.removeEnd(StringUtils.defaultString(this.name) + ", " + StringUtils.trimToEmpty(this.firstName), ", ");
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    @Override
    public Long getId() {
	return getIdGroup();
    }

    @Override
    public void setId(Long id) {
	setIdGroup(id);
    }

}
