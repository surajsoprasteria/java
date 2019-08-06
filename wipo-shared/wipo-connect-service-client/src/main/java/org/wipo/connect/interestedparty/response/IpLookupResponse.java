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

package org.wipo.connect.interestedparty.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.output.ErrorType;

/**
 * <p>
 * Classe Java per anonymous complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interestedPartyListResponse" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idInterestedParty" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="identifierList" type="{http://response.interestedParty.connect.wipo.org}FlatType" maxOccurs="unbounded"/>
 *                   &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="birthFoundationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nameList" type="{http://response.interestedParty.connect.wipo.org}IpNamesType" maxOccurs="unbounded"/>
 *                   &lt;element name="affiliatedCmos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="birthCountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="creationClassCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Error" type="{http://output.common.connect.wipo.org}ErrorType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"interestedPartyListResponse",
	"error",
	"paginationRecordsTotal",
	"paginationDraw"
})
@XmlRootElement(name = "IpLookupResponse")
public class IpLookupResponse {

    @XmlElement(nillable = true)
    protected List<IpLookupResponse.InterestedPartyListResponse> interestedPartyListResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;
    protected Integer paginationRecordsTotal;
    protected Integer paginationDraw;

    /**
     * Gets the value of the interestedPartyListResponse property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
     * not a <CODE>set</CODE> method for the interestedPartyListResponse property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getInterestedPartyListResponse().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link IpLookupResponse.InterestedPartyListResponse }
     *
     *
     */
    public List<IpLookupResponse.InterestedPartyListResponse> getInterestedPartyListResponse() {
	if (interestedPartyListResponse == null) {
	    interestedPartyListResponse = new ArrayList<>();
	}
	return this.interestedPartyListResponse;
    }

    /**
     * Recupera il valore della proprietà error.
     *
     * @return possible object is {@link ErrorType }
     *
     */
    public ErrorType getError() {
	return error;
    }

    /**
     * Imposta il valore della proprietà error.
     *
     * @param value
     *            allowed object is {@link ErrorType }
     *
     */
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

    /**
     * <p>
     * Classe Java per anonymous complex type.
     *
     * <p>
     * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="idInterestedParty" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="identifierList" type="{http://response.interestedParty.connect.wipo.org}FlatType" maxOccurs="unbounded"/>
     *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="birthFoundationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nameList" type="{http://response.interestedParty.connect.wipo.org}IpNamesType" maxOccurs="unbounded"/>
     *         &lt;element name="affiliatedCmos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="birthCountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="mainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="creationClassCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "idInterestedParty",
	    "identifierList",
	    "statusCode",
	    "birthFoundationDate",
	    "type",
	    "nameList",
	    "affiliatedCmos",
	    "birthCountryCode",
	    "mainId",
	    "creationClassCodeList",
	    "score"
    })
    public static class InterestedPartyListResponse {

	protected long idInterestedParty;
	@XmlElement(required = true)
	protected List<FlatType> identifierList;
	@XmlElement(required = true)
	protected String statusCode;
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar birthFoundationDate;
	@XmlElement(required = true)
	protected String type;
	@XmlElement(required = true)
	protected List<IpNamesType> nameList;
	protected List<String> affiliatedCmos;
	@XmlElement(required = true)
	protected String birthCountryCode;
	@XmlElement(required = true)
	protected String mainId;
	@XmlElement(nillable = true)
	protected List<String> creationClassCodeList;
	@XmlElement(nillable = true)
	protected BigDecimal score;

	/**
	 * Recupera il valore della proprietà idInterestedParty.
	 *
	 */
	public long getIdInterestedParty() {
	    return idInterestedParty;
	}

	/**
	 * Imposta il valore della proprietà idInterestedParty.
	 *
	 */
	public void setIdInterestedParty(long value) {
	    this.idInterestedParty = value;
	}

	/**
	 * Gets the value of the identifierList property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there
	 * is not a <CODE>set</CODE> method for the identifierList property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getIdentifierList().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link FlatType }
	 *
	 *
	 */
	public List<FlatType> getIdentifierList() {
	    if (identifierList == null) {
		identifierList = new ArrayList<>();
	    }
	    return this.identifierList;
	}

	/**
	 * Recupera il valore della proprietà statusCode.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getStatusCode() {
	    return statusCode;
	}

	/**
	 * Imposta il valore della proprietà statusCode.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setStatusCode(String value) {
	    this.statusCode = value;
	}

	/**
	 * Recupera il valore della proprietà birthFoundationDate.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getBirthFoundationDate() {
	    return birthFoundationDate;
	}

	/**
	 * Imposta il valore della proprietà birthFoundationDate.
	 *
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setBirthFoundationDate(XMLGregorianCalendar value) {
	    this.birthFoundationDate = value;
	}

	/**
	 * Recupera il valore della proprietà type.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getType() {
	    return type;
	}

	/**
	 * Imposta il valore della proprietà type.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setType(String value) {
	    this.type = value;
	}

	/**
	 * Gets the value of the nameList property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there
	 * is not a <CODE>set</CODE> method for the nameList property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNameList().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link IpNamesType }
	 *
	 *
	 */
	public List<IpNamesType> getNameList() {
	    if (nameList == null) {
		nameList = new ArrayList<>();
	    }
	    return this.nameList;
	}

	/**
	 * Gets the value of the affiliatedCmos property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there
	 * is not a <CODE>set</CODE> method for the affiliatedCmos property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAffiliatedCmos().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getAffiliatedCmos() {
	    if (affiliatedCmos == null) {
		affiliatedCmos = new ArrayList<>();
	    }
	    return this.affiliatedCmos;
	}

	/**
	 * Recupera il valore della proprietà birthCountryCode.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getBirthCountryCode() {
	    return birthCountryCode;
	}

	/**
	 * Imposta il valore della proprietà birthCountryCode.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setBirthCountryCode(String value) {
	    this.birthCountryCode = value;
	}

	/**
	 * Recupera il valore della proprietà mainId.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getMainId() {
	    return mainId;
	}

	/**
	 * Imposta il valore della proprietà mainId.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setMainId(String value) {
	    this.mainId = value;
	}

	/**
	 * Gets the value of the creationClassCodeList property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there
	 * is not a <CODE>set</CODE> method for the creationClassCodeList property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getCreationClassCodeList().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getCreationClassCodeList() {
	    if (creationClassCodeList == null) {
		creationClassCodeList = new ArrayList<>();
	    }
	    return this.creationClassCodeList;
	}

	public BigDecimal getScore() {
	    return score;
	}

	public void setScore(BigDecimal score) {
	    this.score = score;
	}

    }

}
