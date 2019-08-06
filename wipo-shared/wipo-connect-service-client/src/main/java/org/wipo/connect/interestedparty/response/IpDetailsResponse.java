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

package org.wipo.connect.interestedparty.response;

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



/**
 * The Class IpDetailsResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ipResponse",
    "error"
})
@XmlRootElement(name = "IpDetailsResponse")
public class IpDetailsResponse {

    @XmlElement(required = true, nillable = true)
    protected IpDetailsResponse.IpResponse ipResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;


    /**
     * Gets the ip response.
     *
     * @return the ip response
     */
    public IpDetailsResponse.IpResponse getIpResponse() {
        return ipResponse;
    }


    /**
     * Sets the ip response.
     *
     * @param value the new ip response
     */
    public void setIpResponse(IpDetailsResponse.IpResponse value) {
        this.ipResponse = value;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public ErrorType getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param value the new error
     */
    public void setError(ErrorType value) {
        this.error = value;
    }


    /**
     * The Class IpResponse.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idInterestedParty",
        "identifierList",
        "statusCode",
        "birthFoundationDate",
        "deathDate",
        "gender",
        "maritalStatus",
        "type",
        "birthPlace",
        "birthCountryCode",
        "citizenshipCodeList",
        "amendmentTimestemp",
        "nameList",
        "birthState",
        "affiliationList",
        "isAffiliated",
        "affiliatedCmos",
        "dateUpdate",
        "creationDate",
        "mainId",
        "creationClassCodeList",
        "syncVersion"
    })
    public static class IpResponse {

        protected long idInterestedParty;
        @XmlElement(required = true)
        protected List<FlatType> identifierList;
        @XmlElement(required = true)
        protected String statusCode;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
        protected Date birthFoundationDate;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
        protected Date deathDate;
        @XmlElement(required = true)
        protected String gender;
        @XmlElement(required = true)
        protected String maritalStatus;
        @XmlElement(required = true)
        protected String type;
        @XmlElement(required = true)
        protected String birthPlace;
        @XmlElement(required = true)
        protected String birthCountryCode;
        @XmlElement(required = true)
        protected List<String> citizenshipCodeList;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
        protected Date amendmentTimestemp;
        @XmlElement(required = true)
        protected List<IpNamesType> nameList;
        @XmlElement(required = true)
        protected String birthState;
        @XmlElement(required = true)
        protected List<AffiliationType> affiliationList;
        protected boolean isAffiliated;
        protected List<String> affiliatedCmos;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
        protected Date dateUpdate;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
        protected Date creationDate;
        @XmlElement(required = true)
        protected String mainId;
        protected List<String> creationClassCodeList;
        protected Long syncVersion;


        public Long getSyncVersion() {
			return syncVersion;
		}

		public void setSyncVersion(Long syncVersion) {
			this.syncVersion = syncVersion;
		}

		/**
         * Gets the creation date.
         *
         * @return the creation date
         */
        public Date getCreationDate() {
			return creationDate;
		}

		/**
		 * Sets the creation date.
		 *
		 * @param creationDate the new creation date
		 */
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}


        /**
         * Gets the date update.
         *
         * @return the date update
         */
        public Date getDateUpdate() {
			return dateUpdate;
		}


		/**
		 * Sets the date update.
		 *
		 * @param dateUpdate the new date update
		 */
		public void setDateUpdate(Date dateUpdate) {
			this.dateUpdate = dateUpdate;
		}


        /**
         * Gets the id interested party.
         *
         * @return the id interested party
         */
        public long getIdInterestedParty() {
            return idInterestedParty;
        }

        /**
         * Sets the id interested party.
         *
         * @param value the new id interested party
         */
        public void setIdInterestedParty(long value) {
            this.idInterestedParty = value;
        }

        /**
         * Gets the identifier list.
         *
         * @return the identifier list
         */
        public List<FlatType> getIdentifierList() {
            if (identifierList == null) {
                identifierList = new ArrayList<FlatType>();
            }
            return this.identifierList;
        }

        /**
         * Gets the status code.
         *
         * @return the status code
         */
        public String getStatusCode() {
            return statusCode;
        }

        /**
         * Sets the status code.
         *
         * @param value the new status code
         */
        public void setStatusCode(String value) {
            this.statusCode = value;
        }

        /**
         * Gets the birth foundation date.
         *
         * @return the birth foundation date
         */
        public Date getBirthFoundationDate() {
            return birthFoundationDate;
        }

        /**
         * Sets the birth foundation date.
         *
         * @param value the new birth foundation date
         */
        public void setBirthFoundationDate(Date value) {
            this.birthFoundationDate = value;
        }

        /**
         * Gets the death date.
         *
         * @return the death date
         */
        public Date getDeathDate() {
            return deathDate;
        }

        /**
         * Sets the death date.
         *
         * @param value the new death date
         */
        public void setDeathDate(Date value) {
            this.deathDate = value;
        }

        /**
         * Gets the gender.
         *
         * @return the gender
         */
        public String getGender() {
            return gender;
        }

        /**
         * Sets the gender.
         *
         * @param value the new gender
         */
        public void setGender(String value) {
            this.gender = value;
        }

        /**
         * Gets the marital status.
         *
         * @return the marital status
         */
        public String getMaritalStatus() {
            return maritalStatus;
        }

        /**
         * Sets the marital status.
         *
         * @param value the new marital status
         */
        public void setMaritalStatus(String value) {
            this.maritalStatus = value;
        }

        /**
         * Gets the type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the type.
         *
         * @param value the new type
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the birth place.
         *
         * @return the birth place
         */
        public String getBirthPlace() {
            return birthPlace;
        }

        /**
         * Sets the birth place.
         *
         * @param value the new birth place
         */
        public void setBirthPlace(String value) {
            this.birthPlace = value;
        }

        /**
         * Gets the birth country code.
         *
         * @return the birth country code
         */
        public String getBirthCountryCode() {
            return birthCountryCode;
        }

        /**
         * Sets the birth country code.
         *
         * @param value the new birth country code
         */
        public void setBirthCountryCode(String value) {
            this.birthCountryCode = value;
        }

        /**
         * Gets the citizenship code list.
         *
         * @return the citizenship code list
         */
        public List<String> getCitizenshipCodeList() {
            if (citizenshipCodeList == null) {
                citizenshipCodeList = new ArrayList<String>();
            }
            return this.citizenshipCodeList;
        }

        /**
         * Gets the amendment timestemp.
         *
         * @return the amendment timestemp
         */
        public Date getAmendmentTimestemp() {
            return amendmentTimestemp;
        }

        /**
         * Sets the amendment timestemp.
         *
         * @param value the new amendment timestemp
         */
        public void setAmendmentTimestemp(Date value) {
            this.amendmentTimestemp = value;
        }

        /**
         * Gets the name list.
         *
         * @return the name list
         */
        public List<IpNamesType> getNameList() {
            if (nameList == null) {
                nameList = new ArrayList<IpNamesType>();
            }
            return this.nameList;
        }

        /**
         * Gets the birth state.
         *
         * @return the birth state
         */
        public String getBirthState() {
            return birthState;
        }

        /**
         * Sets the birth state.
         *
         * @param value the new birth state
         */
        public void setBirthState(String value) {
            this.birthState = value;
        }

        /**
         * Gets the affiliation list.
         *
         * @return the affiliation list
         */
        public List<AffiliationType> getAffiliationList() {
            if (affiliationList == null) {
                affiliationList = new ArrayList<>();
            }
            return this.affiliationList;
        }

        /**
         * Checks if is checks if is affiliated.
         *
         * @return true, if is checks if is affiliated
         */
        public boolean isIsAffiliated() {
            return isAffiliated;
        }

        /**
         * Sets the checks if is affiliated.
         *
         * @param value the new checks if is affiliated
         */
        public void setIsAffiliated(boolean value) {
            this.isAffiliated = value;
        }

        /**
         * Gets the affiliated cmos.
         *
         * @return the affiliated cmos
         */
        public List<String> getAffiliatedCmos() {
            if (affiliatedCmos == null) {
                affiliatedCmos = new ArrayList<>();
            }
            return this.affiliatedCmos;
        }

		/**
		 * Gets the main id.
         *
		 * @return the main id
         */
        public String getMainId() {
            return mainId;
        }

        /**
		 * Sets the main id.
         *
		 * @param mainId the new main id
         */
		public void setMainId(String mainId) {
			this.mainId = mainId;
        }

		public List<String> getCreationClassCodeList() {
			if(creationClassCodeList==null){
				creationClassCodeList=new ArrayList<>();
			}
			return creationClassCodeList;
		}

		public void setCreationClassCodeList(List<String> creationClassCodeList) {
			this.creationClassCodeList = creationClassCodeList;
		}

    }

}
