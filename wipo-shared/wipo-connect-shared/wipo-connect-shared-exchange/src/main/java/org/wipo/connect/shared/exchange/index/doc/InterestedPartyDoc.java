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
package org.wipo.connect.shared.exchange.index.doc;

import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.beans.Field;
import org.wipo.connect.common.index.doc.BaseSolrDoc;

/**
 * The Class InterestedPartyDoc.
 */
public class InterestedPartyDoc extends BaseSolrDoc {

    private static final long serialVersionUID = 4357340873521116553L;

    @Field("id")
    private Long id;

    @Field("type")
    private String type;

    // Filed not stored on Solr, indexed only
    @Field("mainName")
    private String mainName;

    @Field("fullName")
    private Set<String> fullNames;

    @Field("fullNameRev")
    private Set<String> fullNameRevs;

    @Field("mainId")
    private String mainId;

    @Field("identifier")
    private Set<String> identifiers;

    @Field("statusCode")
    private String statusCode;

    @Field("affiliatedCmo")
    private Set<String> affiliatedCmos;

    @Field("birthDateStr")
    private String birthDateStr;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(Long id) {
	this.id = id;
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
     * @param type
     *            the new type
     */
    public void setType(String type) {
	this.type = type;
    }

    /**
     * Gets the full names.
     *
     * @return the full names
     */
    public Set<String> getFullNames() {
	if (fullNames == null) {
	    fullNames = new HashSet<>();
	}
	return fullNames;
    }

    /**
     * Sets the full names.
     *
     * @param fullNames
     *            the new full names
     */
    public void setFullNames(Set<String> fullNames) {
	this.fullNames = fullNames;
    }

    /**
     * Gets the full name revs.
     *
     * @return the full name revs
     */
    public Set<String> getFullNameRevs() {
	if (fullNameRevs == null) {
	    fullNameRevs = new HashSet<>();
	}
	return fullNameRevs;
    }

    /**
     * Sets the full name revs.
     *
     * @param fullNameRevs
     *            the new full name revs
     */
    public void setFullNameRevs(Set<String> fullNameRevs) {
	this.fullNameRevs = fullNameRevs;
    }

    /**
     * Gets the identifiers.
     *
     * @return the identifiers
     */
    public Set<String> getIdentifiers() {
	if (identifiers == null) {
	    identifiers = new HashSet<>();
	}
	return identifiers;
    }

    /**
     * Sets the identifiers.
     *
     * @param identifiers
     *            the new identifiers
     */
    public void setIdentifiers(Set<String> identifiers) {
	this.identifiers = identifiers;
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
     * @param statusCode
     *            the new status code
     */
    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    /**
     * Gets the affiliated cmos.
     *
     * @return the affiliated cmos
     */
    public Set<String> getAffiliatedCmos() {
	if (affiliatedCmos == null) {
	    affiliatedCmos = new HashSet<>();
	}
	return affiliatedCmos;
    }

    /**
     * Sets the affiliated cmos.
     *
     * @param affiliatedCmos
     *            the new affiliated cmos
     */
    public void setAffiliatedCmos(Set<String> affiliatedCmos) {
	this.affiliatedCmos = affiliatedCmos;
    }

    /**
     * Gets the birth date str.
     *
     * @return the birth date str
     */
    public String getBirthDateStr() {
	return birthDateStr;
    }

    /**
     * Sets the birth date str.
     *
     * @param birthDateStr
     *            the new birth date str
     */
    public void setBirthDateStr(String birthDateStr) {
	this.birthDateStr = birthDateStr;
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    public String getMainName() {
	return mainName;
    }

    public void setMainName(String mainName) {
	this.mainName = mainName;
    }

}
