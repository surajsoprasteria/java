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
package org.wipo.connect.common.import_model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class WorkRow.
 */
public class WorkRow extends BaseWorkRow {

    private static final long serialVersionUID = -1105312053552705928L;

    private String id;
    private String rowType;
    private String transaction;

    private String workMainId;
    private String workTitle;
    // private String workTitleType;
    private String duration;
    private String genre;
    // private String workCreationDate;
    private String workType;
    private String instrument;

    private String type;
    private String value;

    private String performer;

    private String territory;
    private String nameMainId;
    private String role;
    private String creatorRef;
    // private String sharePer;
    // private String shareMec;

    // private String shareValue;
    private String rightCategory;

    private String creationClass;

    private String catalogueNumber;
    private String support;
    private String countryOfProduction;
    private String category;
    private String label;
    private String language;
    // private String dateType;
    // private String dateValue;

    private String weight;

    private List<String> tags;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getRowType() {
	return rowType;
    }

    public void setRowType(String rowType) {
	this.rowType = rowType;
    }

    public String getTransaction() {
	return transaction;
    }

    public void setTransaction(String transaction) {
	this.transaction = transaction;
    }

    public String getWorkMainId() {
	return workMainId;
    }

    public void setWorkMainId(String workMainId) {
	this.workMainId = workMainId;
    }

    public String getWorkTitle() {
	return workTitle;
    }

    public void setWorkTitle(String workTitle) {
	this.workTitle = workTitle;
    }

    public String getDuration() {
	return duration;
    }

    public void setDuration(String duration) {
	this.duration = duration;
    }

    public String getGenre() {
	return genre;
    }

    public void setGenre(String genre) {
	this.genre = genre;
    }

    public String getInstrument() {
	return instrument;
    }

    public void setInstrument(String instrument) {
	this.instrument = instrument;
    }

    public String getPerformer() {
	return performer;
    }

    public void setPerformer(String performer) {
	this.performer = performer;
    }

    public String getNameMainId() {
	return nameMainId;
    }

    public void setNameMainId(String nameMainId) {
	this.nameMainId = nameMainId;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getCreatorRef() {
	return creatorRef;
    }

    public void setCreatorRef(String creatorRef) {
	this.creatorRef = creatorRef;
    }

    public String getValue() {
	return value;
    }

    public String getRightCategory() {
	return rightCategory;
    }

    public void setRightCategory(String rightCategory) {
	this.rightCategory = rightCategory;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public String getCreationClass() {
	return creationClass;
    }

    public void setCreationClass(String creationClass) {
	this.creationClass = creationClass;
    }

    public String getCatalogueNumber() {
	return catalogueNumber;
    }

    public void setCatalogueNumber(String catalogueNumber) {
	this.catalogueNumber = catalogueNumber;
    }

    public String getSupport() {
	return support;
    }

    public void setSupport(String support) {
	this.support = support;
    }

    public String getCountryOfProduction() {
	return countryOfProduction;
    }

    public void setCountryOfProduction(String countryOfProduction) {
	this.countryOfProduction = countryOfProduction;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getLabel() {
	return label;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public String getWeight() {
	return weight;
    }

    public void setWeight(String weight) {
	this.weight = weight;
    }

    public List<String> getTags() {
	if (tags == null) {
	    tags = new ArrayList<>();
	}
	return tags;
    }

    public void setTags(List<String> tags) {
	this.tags = tags;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getTerritory() {
	return territory;
    }

    public void setTerritory(String territory) {
	this.territory = territory;
    }

    public String getWorkType() {
	return workType;
    }

    public void setWorkType(String workType) {
	this.workType = workType;
    }

}
