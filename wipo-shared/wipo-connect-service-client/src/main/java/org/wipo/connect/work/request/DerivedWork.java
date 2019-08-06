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

package org.wipo.connect.work.request;

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
    "trackNumber"
})
public class DerivedWork {

    @XmlElement(required = true)
    protected String sharedMainId;
    @XmlElement(required = true)
    protected Long weight;
    @XmlElement(required = true)
    protected Long trackNumber;


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
	public Long getTrackNumber() {
	    return trackNumber;
	}
	public void setTrackNumber(Long trackNumber) {
	    this.trackNumber = trackNumber;
	}

}
