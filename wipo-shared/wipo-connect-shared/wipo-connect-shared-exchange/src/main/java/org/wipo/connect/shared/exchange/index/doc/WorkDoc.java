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

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.wipo.connect.common.index.doc.BaseSolrDoc;

/**
 * The Class WorkDoc.
 */
public class WorkDoc extends BaseSolrDoc {

    private static final long serialVersionUID = -2242899829908960146L;

    @Id
    private Long id;

    @Field("title")
    private Set<String> title;

    // Filed not stored on Solr, indexed only
    @Field("mainTitle")
    private String mainTitle;

    @Field("identifier")
    private Set<String> identifiers;

    @Field("mainId")
    private String mainId;

    @Field("statusCode")
    private String statusCode;

    @Field("rightOwners")
    private Set<String> rightOwners;

    @Field("creationClassCode")
    private String creationClassCode;

    private BigDecimal finalScore;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<String> getTitles() {
	if (title == null) {
	    title = new HashSet<>();
	    ;
	}
	return title;
    }

    public void setTitles(Set<String> title) {
	this.title = title;
    }

    public Set<String> getIdentifiers() {
	if (identifiers == null) {
	    identifiers = new HashSet<>();
	}
	return identifiers;
    }

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
     * Gets the right owners.
     *
     * @return the right owners
     */
    public Set<String> getRightOwners() {
	if (rightOwners == null) {
	    rightOwners = new HashSet<>();
	}
	return rightOwners;
    }

    /**
     * Sets the right owners.
     *
     * @param rightOwners
     *            the new right owners
     */
    public void setRightOwners(Set<String> rightOwners) {
	this.rightOwners = rightOwners;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public Set<String> getTitle() {
	return title;
    }

    /**
     * Sets the title.
     *
     * @param title
     *            the new title
     */
    public void setTitle(Set<String> title) {
	this.title = title;
    }

    /**
     * Gets the final score.
     *
     * @return the final score
     */
    public BigDecimal getFinalScore() {
	return finalScore;
    }

    /**
     * Sets the final score.
     *
     * @param finalScore
     *            the new final score
     */
    public void setFinalScore(BigDecimal finalScore) {
	this.finalScore = finalScore;
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    public String getCreationClassCode() {
	return creationClassCode;
    }

    public void setCreationClassCode(String creationClassCode) {
	this.creationClassCode = creationClassCode;
    }

    public String getMainTitle() {
	return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
	this.mainTitle = mainTitle;
    }

}
