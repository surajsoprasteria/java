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

package org.wipo.connect.interestedparty.request;

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

import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.utils.XmlDateAdapter;


/**
 * The Class InternationallyRegisterRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "context",
    "taskCode",
    "taskItemRequest"
})
@XmlRootElement(name = "InternationallyRegisterRequest")
public class InternationallyRegisterRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected String taskCode;
    @XmlElement(required = true)
    protected List<InternationallyRegisterRequest.TaskItemRequest> taskItemRequest;


    /**
     * Gets the context.
     *
     * @return the context
     */
    public ContextType getContext() {
        return context;
    }


    /**
     * Sets the context.
     *
     * @param value the new context
     */
    public void setContext(ContextType value) {
        this.context = value;
    }


    /**
     * Gets the task code.
     *
     * @return the task code
     */
    public String getTaskCode() {
        return taskCode;
    }


    /**
     * Sets the task code.
     *
     * @param value the new task code
     */
    public void setTaskCode(String value) {
        this.taskCode = value;
    }


    /**
     * Gets the ip item detail request.
     *
     * @return the ip item detail request
     */
    public List<InternationallyRegisterRequest.TaskItemRequest> getTaskItemRequest() {
        if (taskItemRequest == null) {
        	taskItemRequest = new ArrayList<>();
        }
        return this.taskItemRequest;
    }

    /**
     * The Class TaskItemRequest.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "progr",
        "itemCode",
        "ipTaskItemDetail"
    })
    public static class TaskItemRequest {

        protected long progr;
        @XmlElement(required = true)
        protected String itemCode;
        @XmlElement(required = true)
        protected InternationallyRegisterRequest.TaskItemRequest.IpTaskItemDetail ipTaskItemDetail;


        /**
         * Gets the progr.
         *
         * @return the progr
         */
        public long getProgr() {
            return progr;
        }


        /**
         * Sets the progr.
         *
         * @param value the new progr
         */
        public void setProgr(long value) {
            this.progr = value;
        }


        /**
         * Gets the item code.
         *
         * @return the item code
         */
        public String getItemCode() {
            return itemCode;
        }


        /**
         * Sets the item code.
         *
         * @param value the new item code
         */
        public void setItemCode(String value) {
            this.itemCode = value;
        }


        /**
         * Gets the ip task detail.
         *
         * @return the work ip detail
         */
        public InternationallyRegisterRequest.TaskItemRequest.IpTaskItemDetail getIpTaskItemDetail() {
            return ipTaskItemDetail;
        }


        /**
         * Sets the ip task detail.
         *
         * @param value the new ip task detail
         */
        public void setIpTaskItemDetail(InternationallyRegisterRequest.TaskItemRequest.IpTaskItemDetail value) {
            this.ipTaskItemDetail = value;
        }



        /**
         * The Class IpDetailRequest.
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "birthDate",
            "deathDate",
            "birthPlace",
            "birthState",
            "birthCountryCode",
            "maritalStatus",
            "nameList",
            "affiliationList",
            "citizenshipCodeList",
            "interestedPartyIdentifierFlatList",
            "mainId",
            "creationDate",
            "amendmentTimestamp",
            "type",
            "sex"
        })
        public static class IpTaskItemDetail {

            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
            protected Date birthDate;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
            protected Date deathDate;
            @XmlElement(required = true)
            protected String birthPlace;
            @XmlElement(required = true)
            protected String birthState;
            @XmlElement(required = true)
            protected String birthCountryCode;
            @XmlElement(required = true)
            protected String maritalStatus;
            protected List<IpNamesType> nameList;
            protected List<AffiliationType> affiliationList;
            protected List<String> citizenshipCodeList;
            protected List<FlatType> interestedPartyIdentifierFlatList;
            @XmlElement(required = true)
            protected String mainId;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
            protected Date creationDate;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
            protected Date amendmentTimestamp;
            @XmlElement(required = true)
            protected String type;
            @XmlElement(required = true)
            protected String sex;


            public String getSex() {
				return sex;
			}


			public void setSex(String sex) {
				this.sex = sex;
			}


			public String getType() {
				return type;
			}


			public void setType(String type) {
				this.type = type;
			}


			public List<FlatType> getInterestedPartyIdentifierFlatList() {
            	if(interestedPartyIdentifierFlatList==null){
            		interestedPartyIdentifierFlatList=new ArrayList<>();
            	}
				return interestedPartyIdentifierFlatList;
			}


			public void setInterestedPartyIdentifierFlatList(List<FlatType> interestedPartyIdentifierFlatList) {
				this.interestedPartyIdentifierFlatList = interestedPartyIdentifierFlatList;
			}


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

            /**
             * Gets the birth date.
             *
             * @return the birth date
             */
            public Date getBirthDate() {
                return birthDate;
            }


            /**
             * Sets the birth date.
             *
             * @param value the new birth date
             */
            public void setBirthDate(Date value) {
                this.birthDate = value;
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


			public List<IpNamesType> getNameList() {
				if(nameList==null){
					nameList=new ArrayList<>();
				}
				return nameList;
			}


			public void setNameList(List<IpNamesType> nameList) {
				this.nameList = nameList;
			}


			public List<AffiliationType> getAffiliationList() {
				if(affiliationList==null){
					affiliationList=new ArrayList<>();
				}
				return affiliationList;
			}


			public void setAffiliationList(List<AffiliationType> affiliationList) {
				this.affiliationList = affiliationList;
			}

			public List<String> getCitizenshipCodeList() {
				if(citizenshipCodeList==null){
					citizenshipCodeList=new ArrayList<>();
				}
				return citizenshipCodeList;
			}


			public String getMainId() {
				return mainId;
			}


			public void setMainId(String mainId) {
				this.mainId = mainId;
			}


			public void setCitizenshipCodeList(List<String> citizenshipCodeList) {
				this.citizenshipCodeList = citizenshipCodeList;
			}
        }
    }
}
