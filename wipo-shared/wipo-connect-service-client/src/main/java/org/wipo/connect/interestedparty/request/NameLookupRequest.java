/*
 * Copyright (C) 2015 World Intellectual Property Organizzation (WIPO).
 * All Rights Reserved.
 * 
 * This file is part of WIPO Connect.
 * 
 * 
 * @author Fincons
 * 
 */

package org.wipo.connect.interestedparty.request;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.querypagination.PageInfo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"context",
	"nameRequest"
})
@XmlRootElement(name = "NameLookupRequest")
public class NameLookupRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(name = "NameRequest", required = true)
    protected NameLookupRequest.NameRequest nameRequest;

    public ContextType getContext() {
	return context;
    }

    public void setContext(ContextType value) {
	this.context = value;
    }

    public NameLookupRequest.NameRequest getNameRequest() {
	return nameRequest;
    }

    public void setNameRequest(NameLookupRequest.NameRequest value) {
	this.nameRequest = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "valueForm",
	    "onlyRegistered",
	    "creationClassCodeList",
	    "pageInfo"
    })
    public static class NameRequest {

	@XmlElement(required = true)
	protected String valueForm;
	protected boolean onlyRegistered;
	@XmlElement(nillable = true)
	protected List<String> creationClassCodeList;
	protected PageInfo pageInfo;

	public String getValueForm() {
	    return valueForm;
	}

	public void setValueForm(String value) {
	    this.valueForm = value;
	}

	public boolean isOnlyRegistered() {
	    return onlyRegistered;
	}

	public void setOnlyRegistered(boolean value) {
	    this.onlyRegistered = value;
	}

	public List<String> getCreationClassCodeList() {
	    if (creationClassCodeList == null) {
		creationClassCodeList = new ArrayList<String>();
	    }
	    return this.creationClassCodeList;
	}

	public void setCreationClassCodeList(List<String> creationClassCodeList) {
	    this.creationClassCodeList = creationClassCodeList;
	}

	public PageInfo getPageInfo() {
	    return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
	    this.pageInfo = pageInfo;
	}

    }

}
