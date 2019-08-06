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
package org.wipo.connect.common.report.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class AsyncRequest.
 */
public class AsyncRequest implements Serializable {
    private static final long serialVersionUID = -6662971491321152271L;

    private RequestBean requestBean;

    private Long uidElaboration;
    private String owner;
    private String elaborationType;
    private Integer priority;
    private String status;
    private String messageDescription;
    private String userIns;
    private Date timeIns;
    private String note;
    private String userUpd;
    private Date timeUpd;
    private String reportBeanName;
    private Long fkDistribution;

    private String datePattern;

    private String reportOutputTypeCode;
    private String reportTypeCode;

    private byte[] document;

    /**
     * Gets the request bean.
     *
     * @return the request bean
     */
    public RequestBean getRequestBean() {
	return requestBean;
    }

    /**
     * Sets the request bean.
     *
     * @param requestBean
     *            the new request bean
     */
    public void setRequestBean(RequestBean requestBean) {
	this.requestBean = requestBean;
    }

    /**
     * Gets the uid elaboration.
     *
     * @return the uid elaboration
     */
    public Long getUidElaboration() {
	return uidElaboration;
    }

    /**
     * Sets the uid elaboration.
     *
     * @param uidElaboration
     *            the new uid elaboration
     */
    public void setUidElaboration(Long uidElaboration) {
	this.uidElaboration = uidElaboration;
    }

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    public String getOwner() {
	return owner;
    }

    /**
     * Sets the owner.
     *
     * @param owner
     *            the new owner
     */
    public void setOwner(String owner) {
	this.owner = owner;
    }

    /**
     * Gets the elaboration type.
     *
     * @return the elaboration type
     */
    public String getElaborationType() {
	return elaborationType;
    }

    /**
     * Sets the elaboration type.
     *
     * @param elaborationType
     *            the new elaboration type
     */
    public void setElaborationType(String elaborationType) {
	this.elaborationType = elaborationType;
    }

    /**
     * Gets the priority.
     *
     * @return the priority
     */
    public Integer getPriority() {
	return priority;
    }

    /**
     * Sets the priority.
     *
     * @param priority
     *            the new priority
     */
    public void setPriority(Integer priority) {
	this.priority = priority;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * Gets the message description.
     *
     * @return the message description
     */
    public String getMessageDescription() {
	return messageDescription;
    }

    /**
     * Sets the message description.
     *
     * @param messageDescription
     *            the new message description
     */
    public void setMessageDescription(String messageDescription) {
	this.messageDescription = messageDescription;
    }

    /**
     * Gets the user ins.
     *
     * @return the user ins
     */
    public String getUserIns() {
	return userIns;
    }

    /**
     * Sets the user ins.
     *
     * @param userIns
     *            the new user ins
     */
    public void setUserIns(String userIns) {
	this.userIns = userIns;
    }

    /**
     * Gets the time ins.
     *
     * @return the time ins
     */
    public Date getTimeIns() {
	return timeIns;
    }

    /**
     * Sets the time ins.
     *
     * @param timeIns
     *            the new time ins
     */
    public void setTimeIns(Date timeIns) {
	this.timeIns = timeIns;
    }

    /**
     * Gets the note.
     *
     * @return the note
     */
    public String getNote() {
	return note;
    }

    /**
     * Sets the note.
     *
     * @param note
     *            the new note
     */
    public void setNote(String note) {
	this.note = note;
    }

    /**
     * Gets the user upd.
     *
     * @return the user upd
     */
    public String getUserUpd() {
	return userUpd;
    }

    /**
     * Sets the user upd.
     *
     * @param userUpd
     *            the new user upd
     */
    public void setUserUpd(String userUpd) {
	this.userUpd = userUpd;
    }

    /**
     * Gets the time upd.
     *
     * @return the time upd
     */
    public Date getTimeUpd() {
	return timeUpd;
    }

    /**
     * Sets the time upd.
     *
     * @param timeUpd
     *            the new time upd
     */
    public void setTimeUpd(Date timeUpd) {
	this.timeUpd = timeUpd;
    }

    /**
     * Gets the report bean name.
     *
     * @return the report bean name
     */
    public String getReportBeanName() {
	return reportBeanName;
    }

    /**
     * Sets the report bean name.
     *
     * @param reportBeanName
     *            the new report bean name
     */
    public void setReportBeanName(String reportBeanName) {
	this.reportBeanName = reportBeanName;
    }

    /**
     * Gets the report output type code.
     *
     * @return the report output type code
     */
    public String getReportOutputTypeCode() {
	return reportOutputTypeCode;
    }

    /**
     * Sets the report output type code.
     *
     * @param reportOutputTypeCode
     *            the new report output type code
     */
    public void setReportOutputTypeCode(String reportOutputTypeCode) {
	this.reportOutputTypeCode = reportOutputTypeCode;
    }

    /**
     * Gets the report type code.
     *
     * @return the report type code
     */
    public String getReportTypeCode() {
	return reportTypeCode;
    }

    /**
     * Sets the report type code.
     *
     * @param reportTypeCode
     *            the new report type code
     */
    public void setReportTypeCode(String reportTypeCode) {
	this.reportTypeCode = reportTypeCode;
    }

    /**
     * Gets the document.
     *
     * @return the document
     */
    public byte[] getDocument() {
	return document;
    }

    /**
     * Sets the document.
     *
     * @param document
     *            the new document
     */
    public void setDocument(byte[] document) {
	this.document = document;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toStringExclude(this, "document");
    }

    /**
     * Gets the fk distribution.
     *
     * @return the fk distribution
     */
    public Long getFkDistribution() {
	return fkDistribution;
    }

    /**
     * Sets the fk distribution.
     *
     * @param fkDistribution
     *            the new fk distribution
     */
    public void setFkDistribution(Long fkDistribution) {
	this.fkDistribution = fkDistribution;
    }

    /**
     * Gets the date pattern.
     *
     * @return the date pattern
     */
    public String getDatePattern() {
	return datePattern;
    }

    /**
     * Sets the date pattern.
     *
     * @param datePattern
     *            the new date pattern
     */
    public void setDatePattern(String datePattern) {
	this.datePattern = datePattern;
    }
}
