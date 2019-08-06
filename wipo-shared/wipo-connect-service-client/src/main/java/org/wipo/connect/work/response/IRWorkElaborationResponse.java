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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.wipo.connect.common.output.ErrorType;



/**
 * The Class IRWorkElaborationResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "elaborationResponse",
    "error"
})
@XmlRootElement(name = "IRWorkElaborationResponse")
public class IRWorkElaborationResponse {

    @XmlElement(nillable = true)
    protected List<IRWorkElaborationResponse.ElaborationResponse> elaborationResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;


    /**
     * Gets the elaboration response.
     *
     * @return the elaboration response
     */
    public List<IRWorkElaborationResponse.ElaborationResponse> getElaborationResponse() {
        if (elaborationResponse == null) {
            elaborationResponse = new ArrayList<IRWorkElaborationResponse.ElaborationResponse>();
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
        "itemCode",
        "taskStatus",
        "csiStatus",
        "sharedWorkId",
        "sharedWorkMainId",
        "workResponseStatus"
    })
    public static class ElaborationResponse {

        @XmlElement(required = true)
        protected String itemCode;
        @XmlElement(required = true)
        protected String taskStatus;
        protected String csiStatus;
        protected String sharedWorkId;
        protected String sharedWorkMainId;
        protected String workResponseStatus;


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
         * Gets the task status.
         *
         * @return the task status
         */
        public String getTaskStatus() {
            return taskStatus;
        }


        /**
         * Sets the task status.
         *
         * @param value the new task status
         */
        public void setTaskStatus(String value) {
            this.taskStatus = value;
        }


        /**
         * Gets the csi status.
         *
         * @return the csi status
         */
        public String getCsiStatus() {
            return csiStatus;
        }


        /**
         * Sets the csi status.
         *
         * @param value the new csi status
         */
        public void setCsiStatus(String value) {
            this.csiStatus = value;
        }


        /**
         * Gets the shared work id.
         *
         * @return the shared work id
         */
        public String getSharedWorkId() {
            return sharedWorkId;
        }


        /**
         * Sets the shared work id.
         *
         * @param value the new shared work id
         */
        public void setSharedWorkId(String value) {
            this.sharedWorkId = value;
        }


		/**
		 * Gets the work response status.
		 *
		 * @return the work response status
		 */
		public String getWorkResponseStatus() {
			return workResponseStatus;
		}


		/**
		 * Sets the work response status.
		 *
		 * @param workResponseStatus the new work response status
		 */
		public void setWorkResponseStatus(String workResponseStatus) {
			this.workResponseStatus = workResponseStatus;
		}

    }

}
