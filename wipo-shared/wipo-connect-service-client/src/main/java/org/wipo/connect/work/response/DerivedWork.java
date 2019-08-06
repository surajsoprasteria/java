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

package org.wipo.connect.work.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The Class DerivedWork.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedWork", propOrder = {
    "sharedMainId",
    "weight",
    "trackNumber",
    "domain",
    "sharedId",
    "sharedOt",
    "sharedClass",
    "sharedRo",
    "sharedIdentifier"
})
public class DerivedWork {

	@XmlElement(required = true)
	protected String sharedMainId;
	@XmlElement(required = true)
	protected Long weight;
	@XmlElement(required = true)
	protected Long trackNumber;
	@XmlElement(required = true)
	protected String domain;
	@XmlElement(required = true)
	protected Long sharedId;
	@XmlElement(required = true)
	protected String sharedOt;
	@XmlElement(required = true)
	protected String sharedClass;
	@XmlElement(required = true)
	protected String sharedRo;
	@XmlElement(required = true)
	protected String sharedIdentifier;

	public String getSharedMainId() {
		return sharedMainId;
	}

	public void setSharedMainId(String sharedMainId) {
		this.sharedMainId = sharedMainId;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getSharedId() {
		return sharedId;
	}

	public void setSharedId(Long sharedId) {
		this.sharedId = sharedId;
	}

	public String getSharedOt() {
		return sharedOt;
	}

	public void setSharedOt(String sharedOt) {
		this.sharedOt = sharedOt;
	}

	public String getSharedClass() {
		return sharedClass;
	}

	public void setSharedClass(String sharedClass) {
		this.sharedClass = sharedClass;
	}

	public String getSharedRo() {
		return sharedRo;
	}

	public void setSharedRo(String sharedRo) {
		this.sharedRo = sharedRo;
	}

	public String getSharedIdentifier() {
	    return sharedIdentifier;
	}

	public void setSharedIdentifier(String sharedIdentifier) {
	    this.sharedIdentifier = sharedIdentifier;
	}

	public Long getTrackNumber() {
	    return trackNumber;
	}

	public void setTrackNumber(Long trackNumber) {
	    this.trackNumber = trackNumber;
	}


}
