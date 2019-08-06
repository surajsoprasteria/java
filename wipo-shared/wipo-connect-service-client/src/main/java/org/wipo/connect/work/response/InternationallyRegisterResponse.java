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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.wipo.connect.common.output.ErrorType;



/**
 * The Class InternationallyRegisterResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "workResponse",
    "error"
})
@XmlRootElement(name = "InternationallyRegisterResponse")
public class InternationallyRegisterResponse {

    @XmlElement(required = true, nillable = true)
    protected InternationallyRegisterResponse.WorkResponse workResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;

    
    /**
     * Gets the work response.
     *
     * @return the work response
     */
    public InternationallyRegisterResponse.WorkResponse getWorkResponse() {
        return workResponse;
    }

    
    /**
     * Sets the work response.
     *
     * @param value the new work response
     */
    public void setWorkResponse(InternationallyRegisterResponse.WorkResponse value) {
        this.workResponse = value;
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
     * The Class WorkResponse.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "registrationAck"
    })
    public static class WorkResponse {

        @XmlElement(required = true)
        protected String registrationAck;

        
        /**
         * Gets the registration ack.
         *
         * @return the registration ack
         */
        public String getRegistrationAck() {
            return registrationAck;
        }

        
        /**
         * Sets the registration ack.
         *
         * @param value the new registration ack
         */
        public void setRegistrationAck(String value) {
            this.registrationAck = value;
        }

    }

}
