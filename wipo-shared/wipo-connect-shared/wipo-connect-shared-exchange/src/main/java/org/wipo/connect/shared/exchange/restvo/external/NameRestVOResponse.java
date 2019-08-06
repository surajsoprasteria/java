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
package org.wipo.connect.shared.exchange.restvo.external;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author pasquale.minervini
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class NameRestVOResponse implements Serializable {

    private static final long serialVersionUID = 2124653875912159547L;

    private String name;

    private String firstName;

    private String nameType;

    private Date creationTimestamp;

    private Date amendmentTimestamp;

    private String mainId;

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

    public Date getCreationTimestamp() {
	return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
	this.creationTimestamp = creationTimestamp;
    }

    public Date getAmendmentTimestamp() {
	return amendmentTimestamp;
    }

    public void setAmendmentTimestamp(Date amendmentTimestamp) {
	this.amendmentTimestamp = amendmentTimestamp;
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }
}
