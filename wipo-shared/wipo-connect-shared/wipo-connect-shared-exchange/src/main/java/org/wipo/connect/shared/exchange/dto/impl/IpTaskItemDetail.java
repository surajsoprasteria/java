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



package org.wipo.connect.shared.exchange.dto.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class IpTaskItemDetail.
 *
 * @author minervini
 */
public class IpTaskItemDetail implements Identifiable,Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 382097952762840779L;

    /** The id ip task item detail. */
    private Long idIpTaskItemDetail;

    /** The fk ip task item. */
    private Long fkIpTaskItem;


    /** The type. */
    private String type;

    /** The sex. */
    private String sex;

    /** The birth date. */
    private Date birthDate;

    /** The death date. */
    private Date deathDate;

    /** The birth place. */
    private String birthPlace;

    /** The birth state. */
    private String birthState;

    /** The birth country code. */
    private String birthCountryCode;

    /** The marital status. */
    private String maritalStatus;

    private List<IpTaskDetailName> nameList;
    private List<IpTaskDetailAffiliation> affiliationList;
    private List<String> citizenshipCodeList;

    private List<IpTaskDetailIdent> interestedPartyIdentifierFlatList;
   	private String mainId;
   	private Date creationDate;
	private Date amendmentTimestamp;


	 public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getAmendmentTimestamp() {
		return amendmentTimestamp;
	}



	public void setAmendmentTimestamp(Date amendmentTimestamp) {
		this.amendmentTimestamp = amendmentTimestamp;
	}




   	 public List<IpTaskDetailIdent> getInterestedPartyIdentifierFlatList() {
   	    	if(interestedPartyIdentifierFlatList==null){
   	    		interestedPartyIdentifierFlatList=new ArrayList<>();
   	    	}
   			return interestedPartyIdentifierFlatList;
   		}



   		public void setInterestedPartyIdentifierFlatList(List<IpTaskDetailIdent> interestedPartyIdentifierFlatList) {
   			this.interestedPartyIdentifierFlatList = interestedPartyIdentifierFlatList;
   		}





    /**
     * Gets the birth country code.
     *
     * @return the birth country code
     */
    public String getBirthCountryCode() {
        return this.birthCountryCode;
    }



    /**
     * Gets the birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate() {
        return this.birthDate;
    }



    /**
     * Gets the birth place.
     *
     * @return the birth place
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }



    /**
     * Gets the birth state.
     *
     * @return the birth state
     */
    public String getBirthState() {
        return this.birthState;
    }



    /**
     * Gets the death date.
     *
     * @return the death date
     */
    public Date getDeathDate() {
        return this.deathDate;
    }

    /**
     * Gets the fk ip task item.
     *
     * @return the fk ip task item
     */
    public Long getFkIpTaskItem() {
        return this.fkIpTaskItem;
    }



    /**
     * Gets the id ip task item detail.
     *
     * @return the id ip task item detail
     */
    public Long getIdIpTaskItemDetail() {
        return this.idIpTaskItemDetail;
    }


    /**
     * Gets the marital status.
     *
     * @return the marital status
     */
    public String getMaritalStatus() {
        return this.maritalStatus;
    }


    /**
     * Gets the sex.
     *
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }



    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }



    /**
     * Sets the birth country code.
     *
     * @param birthCountryCode
     *            the new birth country code
     */
    public void setBirthCountryCode(String birthCountryCode) {
        this.birthCountryCode = birthCountryCode;
    }



    /**
     * Sets the birth date.
     *
     * @param birthDate
     *            the new birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }



    /**
     * Sets the birth place.
     *
     * @param birthPlace
     *            the new birth place
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }



    /**
     * Sets the birth state.
     *
     * @param birthState
     *            the new birth state
     */
    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }



    /**
     * Sets the death date.
     *
     * @param deathDate
     *            the new death date
     */
    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }




    /**
     * Sets the fk ip task item.
     *
     * @param fkIpTaskItem
     *            the new fk ip task item
     */
    public void setFkIpTaskItem(Long fkIpTaskItem) {
        this.fkIpTaskItem = fkIpTaskItem;
    }



    /**
     * Sets the id ip task item detail.
     *
     * @param idIpTaskItemDetail
     *            the new id ip task item detail
     */
    public void setIdIpTaskItemDetail(Long idIpTaskItemDetail) {
        this.idIpTaskItemDetail = idIpTaskItemDetail;
    }


    /**
     * Sets the marital status.
     *
     * @param maritalStatus
     *            the new marital status
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }



    /**
     * Sets the sex.
     *
     * @param sex
     *            the new sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }



    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(String type) {
        this.type = type;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	public List<IpTaskDetailName> getNameList() {
		if(nameList==null){
			nameList=new ArrayList<>();
		}
		return nameList;
	}



	public void setNameList(List<IpTaskDetailName> nameList) {
		this.nameList = nameList;
	}



	public List<IpTaskDetailAffiliation> getAffiliationList() {
		if(affiliationList==null){
			affiliationList=new ArrayList<>();
		}
		return affiliationList;
	}



	public void setAffiliationList(List<IpTaskDetailAffiliation> affiliationList) {
		this.affiliationList = affiliationList;
	}



	public List<String> getCitizenshipCodeList() {
		if(citizenshipCodeList==null){
			citizenshipCodeList=new ArrayList<>();
		}
		return citizenshipCodeList;
	}



	public void setCitizenshipCodeList(List<String> citizenshipCodeList) {
		this.citizenshipCodeList = citizenshipCodeList;
	}


	public String getMainId() {
		return mainId;
	}



	public void setMainId(String mainId) {
		this.mainId = mainId;
	}



	@Override
	public Long getId() {
		return getIdIpTaskItemDetail();
	}



	@Override
	public void setId(Long id) {
		setIdIpTaskItemDetail(id);
	}

}
