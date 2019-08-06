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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Used as input for rule engine share validation.
 */
public class WorkShareREVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2454079905238898561L;

    /** The cwr role. */
    private String role;

    /** The share. */
    private BigDecimal share;

    /** The right type. */
    private String rightType;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The territory. */
    private String territory;

    /** The work genre. */
    private String workGenre;

    /** The work type. */
    private String workType;

    /** The work creation date. */
    // private Date workCreationDate;

    /** The work duration. */
    private Long workDuration;

    /** The ip name. */
    private String ipName;

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
	return this.endDate;
    }

    /**
     * Gets the ip name.
     *
     * @return the ip name
     */
    public String getIpName() {
	return this.ipName;
    }

    /**
     * Gets the share.
     *
     * @return the share
     */
    public BigDecimal getShare() {
	return this.share;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
	return this.startDate;
    }

    /**
     * Gets the territory.
     *
     * @return the territory
     */
    public String getTerritory() {
	return this.territory;
    }

    /**
     * Gets the work creation date.
     *
     * @return the work creation date
     */
    // public Date getWorkCreationDate() {
    // return this.workCreationDate;
    // }

    /**
     * Gets the work duration.
     *
     * @return the work duration
     */
    public Long getWorkDuration() {
	return this.workDuration;
    }

    /**
     * Gets the work genre.
     *
     * @return the work genre
     */
    public String getWorkGenre() {
	return this.workGenre;
    }

    /**
     * Gets the work type.
     *
     * @return the work type
     */
    public String getWorkType() {
	return this.workType;
    }

    /**
     * Sets the end date.
     *
     * @param endDate
     *            the new end date
     */
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    /**
     * Sets the ip name.
     *
     * @param ipName
     *            the new ip name
     */
    public void setIpName(String ipName) {
	this.ipName = ipName;
    }

    /**
     * Sets the share.
     *
     * @param share
     *            the new share
     */
    public void setShare(BigDecimal share) {
	this.share = share;
    }

    /**
     * Sets the start date.
     *
     * @param startDate
     *            the new start date
     */
    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    /**
     * Sets the territory.
     *
     * @param territory
     *            the new territory
     */
    public void setTerritory(String territory) {
	this.territory = territory;
    }

    /**
     * Sets the work creation date.
     *
     * @param workCreationDate
     *            the new work creation date
     */
    // public void setWorkCreationDate(Date workCreationDate) {
    // this.workCreationDate = workCreationDate;
    // }

    /**
     * Sets the work duration.
     *
     * @param workDuration
     *            the new work duration
     */
    public void setWorkDuration(Long workDuration) {
	this.workDuration = workDuration;
    }

    /**
     * Sets the work genre.
     *
     * @param workGenre
     *            the new work genre
     */
    public void setWorkGenre(String workGenre) {
	this.workGenre = workGenre;
    }

    /**
     * Sets the work type.
     *
     * @param workType
     *            the new work type
     */
    public void setWorkType(String workType) {
	this.workType = workType;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    /**
     * Gets the right type.
     *
     * @return the right type
     */
    public String getRightType() {
	return rightType;
    }

    /**
     * Sets the right type.
     *
     * @param rightType
     *            the new right type
     */
    public void setRightType(String rightType) {
	this.rightType = rightType;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
	return role;
    }

    /**
     * Sets the role.
     *
     * @param role
     *            the new role
     */
    public void setRole(String role) {
	this.role = role;
    }

}
