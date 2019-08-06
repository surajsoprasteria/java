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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.output.ErrorType;
import org.wipo.connect.common.utils.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"workResponse",
	"workDetailResponse",
	"error",
	"paginationRecordsTotal",
	"paginationDraw"
})
@XmlRootElement(name = "WorkListResponse")
public class WorkListResponse {

    @XmlElement(nillable = true)
    protected List<WorkListResponse.WorkResponse> workResponse;
    @XmlElement(nillable = true)
    protected List<WorkDetailResponse.WorkResponse> workDetailResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;
    protected Integer paginationRecordsTotal;
    protected Integer paginationDraw;

    public List<WorkListResponse.WorkResponse> getWorkResponse() {
	if (workResponse == null) {
	    workResponse = new ArrayList<>();
	}
	return this.workResponse;
    }

    public List<WorkDetailResponse.WorkResponse> getWorkDetailResponse() {
	if (workDetailResponse == null) {
	    workDetailResponse = new ArrayList<>();
	}
	return this.workDetailResponse;
    }

    public ErrorType getError() {
	return error;
    }

    public void setError(ErrorType value) {
	this.error = value;
    }

    public Integer getPaginationRecordsTotal() {
	return paginationRecordsTotal;
    }

    public void setPaginationRecordsTotal(Integer value) {
	this.paginationRecordsTotal = value;
    }

    public Integer getPaginationDraw() {
	return paginationDraw;
    }

    public void setPaginationDraw(Integer value) {
	this.paginationDraw = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "idWork",
	    "registrationDate",
	    "statusCode",
	    "domain",
	    "titleList",
	    "identifierList",
	    "derivedViewList",
	    "mainId",
	    "creationClassCode",
	    "catalogueNumber",
	    "label",
	    "countryOfProductionCode",
	    "workDateList",
	    "score"
    })
    public static class WorkResponse {

	protected long idWork;
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date registrationDate;
	@XmlElement(required = true)
	protected String statusCode;
	@XmlElement(required = true)
	protected String domain;
	@XmlElement(required = true)
	protected List<TitleType> titleList;
	@XmlElement(required = true)
	protected List<FlatType> identifierList;
	protected List<DerivedViewType> derivedViewList;
	@XmlElement(required = true)
	protected String mainId;
	protected String creationClassCode;
	protected String catalogueNumber;
	protected String label;
	protected String countryOfProductionCode;
	protected List<WorkDateType> workDateList;
	@XmlElement(required = true)
	protected BigDecimal score;

	public String getCatalogueNumber() {
	    return catalogueNumber;
	}

	public void setCatalogueNumber(String catalogueNumber) {
	    this.catalogueNumber = catalogueNumber;
	}

	public String getLabel() {
	    return label;
	}

	public void setLabel(String label) {
	    this.label = label;
	}

	public String getCountryOfProductionCode() {
	    return countryOfProductionCode;
	}

	public void setCountryOfProductionCode(String countryOfProductionCode) {
	    this.countryOfProductionCode = countryOfProductionCode;
	}

	public List<WorkDateType> getWorkDateList() {
	    return workDateList;
	}

	public void setWorkDateList(List<WorkDateType> workDateList) {
	    this.workDateList = workDateList;
	}

	public String getCreationClassCode() {
	    return creationClassCode;
	}

	public void setCreationClassCode(String creationClassCode) {
	    this.creationClassCode = creationClassCode;
	}

	public long getIdWork() {
	    return idWork;
	}

	public void setIdWork(long value) {
	    this.idWork = value;
	}

	public Date getRegistrationDate() {
	    return registrationDate;
	}

	public void setRegistrationDate(Date value) {
	    this.registrationDate = value;
	}

	public String getStatusCode() {
	    return statusCode;
	}

	public void setStatusCode(String value) {
	    this.statusCode = value;
	}

	public String getDomain() {
	    return domain;
	}

	public void setDomain(String value) {
	    this.domain = value;
	}

	public List<TitleType> getTitleList() {
	    if (titleList == null) {
		titleList = new ArrayList<>();
	    }
	    return this.titleList;
	}

	public List<FlatType> getIdentifierList() {
	    if (identifierList == null) {
		identifierList = new ArrayList<>();
	    }
	    return this.identifierList;
	}

	public List<DerivedViewType> getDerivedViewList() {
	    if (derivedViewList == null) {
		derivedViewList = new ArrayList<>();
	    }
	    return this.derivedViewList;
	}

	public String getMainId() {
	    return mainId;
	}

	public void setMainId(String mainId) {
	    this.mainId = mainId;
	}

	public BigDecimal getScore() {
	    return score;
	}

	public void setScore(BigDecimal score) {
	    this.score = score;
	}

    }

}
