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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.wipo.connect.common.output.ErrorType;



/**
 * The Class IRElaborationResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"elaborationResponse",
    "error"
})
@XmlRootElement(name = "IRElaborationResponse")
public class IRElaborationResponse {

	@XmlElement(nillable = true)
    protected List<IRElaborationResponse.ElaborationResponse> elaborationResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;


    /**
     * Gets the task item list.
     *
     * @return the task item list
     */
    public List<IRElaborationResponse.ElaborationResponse> getElaborationResponse() {
        if (elaborationResponse == null) {
        	elaborationResponse = new ArrayList<IRElaborationResponse.ElaborationResponse>();
        }
        return this.elaborationResponse;
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
     * The Class ElaborationResponse.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
		"fkInterestedParty",
        "progr",
        "itemStatus",
        "itemCode",
        "responseStatus"
    })
    public static class ElaborationResponse {

    	@XmlElement(required = true)
        protected Long fkInterestedParty;
        @XmlElement(required = true)
        protected String itemCode;
        @XmlElement(required = true)
        protected String itemStatus;
        protected String progr;
        protected String responseStatus;
		public Long getFkInterestedParty() {
			return fkInterestedParty;
		}
		public void setFkInterestedParty(Long fkInterestedParty) {
			this.fkInterestedParty = fkInterestedParty;
		}
		public String getItemCode() {
			return itemCode;
		}
		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}
		public String getItemStatus() {
			return itemStatus;
		}
		public void setItemStatus(String itemStatus) {
			this.itemStatus = itemStatus;
		}
		public String getProgr() {
			return progr;
		}
		public void setProgr(String progr) {
			this.progr = progr;
		}
		public String getResponseStatus() {
			return responseStatus;
		}
		public void setResponseStatus(String responseStatus) {
			this.responseStatus = responseStatus;
		}

    }

}
