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

package org.wipo.connect.interestedparty.response;

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
 *         &lt;element name="nameListResponse" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idName" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nameType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sourceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ipMainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ipId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="affiliatedCmos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
	"nameListResponse",
	"error",
	"paginationRecordsTotal",
	"paginationDraw"
})
@XmlRootElement(name = "NameLookupResponse")
public class NameLookupResponse {

    @XmlElement(nillable = true)
    protected List<NameLookupResponse.NameListResponse> nameListResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;
    protected Integer paginationRecordsTotal;
    protected Integer paginationDraw;

    /**
     * Gets the value of the nameListResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
     * not a <CODE>set</CODE> method for the nameListResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getNameListResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link NameLookupResponse.NameListResponse }
     * 
     * 
     */
    public List<NameLookupResponse.NameListResponse> getNameListResponse() {
	if (nameListResponse == null) {
	    nameListResponse = new ArrayList<NameLookupResponse.NameListResponse>();
	}
	return this.nameListResponse;
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
     *         &lt;element name="idName" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nameType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="mainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sourceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ipMainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ipId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="affiliatedCmos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
	    "idName",
	    "name",
	    "firstName",
	    "nameType",
	    "statusCode",
	    "mainId",
	    "sourceType",
	    "birthDate",
	    "type",
	    "ipMainId",
	    "ipId",
	    "affiliatedCmos",
	    "creationClassCodeList"
    })
    public static class NameListResponse {

	protected long idName;
	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String firstName;
	@XmlElement(required = true)
	protected String nameType;
	@XmlElement(required = true)
	protected String statusCode;
	@XmlElement(required = true)
	protected String mainId;
	@XmlElement(required = true)
	protected String sourceType;
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar birthDate;
	@XmlElement(required = true)
	protected String type;
	@XmlElement(required = true)
	protected String ipMainId;
	protected long ipId;
	protected List<String> affiliatedCmos;
	@XmlElement(nillable = true)
	protected List<String> creationClassCodeList;

	/**
	 * Recupera il valore della proprietà idName.
	 * 
	 */
	public long getIdName() {
	    return idName;
	}

	/**
	 * Imposta il valore della proprietà idName.
	 * 
	 */
	public void setIdName(long value) {
	    this.idName = value;
	}

	/**
	 * Recupera il valore della proprietà name.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
	    return name;
	}

	/**
	 * Imposta il valore della proprietà name.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
	    this.name = value;
	}

	/**
	 * Recupera il valore della proprietà firstName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFirstName() {
	    return firstName;
	}

	/**
	 * Imposta il valore della proprietà firstName.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFirstName(String value) {
	    this.firstName = value;
	}

	/**
	 * Recupera il valore della proprietà nameType.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNameType() {
	    return nameType;
	}

	/**
	 * Imposta il valore della proprietà nameType.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNameType(String value) {
	    this.nameType = value;
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
	 * Recupera il valore della proprietà sourceType.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceType() {
	    return sourceType;
	}

	/**
	 * Imposta il valore della proprietà sourceType.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSourceType(String value) {
	    this.sourceType = value;
	}

	/**
	 * Recupera il valore della proprietà birthDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getBirthDate() {
	    return birthDate;
	}

	/**
	 * Imposta il valore della proprietà birthDate.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setBirthDate(XMLGregorianCalendar value) {
	    this.birthDate = value;
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
	 * Recupera il valore della proprietà ipMainId.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIpMainId() {
	    return ipMainId;
	}

	/**
	 * Imposta il valore della proprietà ipMainId.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIpMainId(String value) {
	    this.ipMainId = value;
	}

	/**
	 * Recupera il valore della proprietà ipId.
	 * 
	 */
	public long getIpId() {
	    return ipId;
	}

	/**
	 * Imposta il valore della proprietà ipId.
	 * 
	 */
	public void setIpId(long value) {
	    this.ipId = value;
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
		affiliatedCmos = new ArrayList<String>();
	    }
	    return this.affiliatedCmos;
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
		creationClassCodeList = new ArrayList<String>();
	    }
	    return this.creationClassCodeList;
	}

    }

}
