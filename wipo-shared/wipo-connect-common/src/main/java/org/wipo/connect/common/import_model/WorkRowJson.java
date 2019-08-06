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
package org.wipo.connect.common.import_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_EMPTY)
public class WorkRowJson implements Serializable {

    private static final long serialVersionUID = 7750473363934835819L;

    private String id;
    private String rowType;
    private String transaction;
    // MAIN ROW
    private String workMainId;
    private String duration;
    private String genre;
    private String workType;
    private String instrument;
    private String catalogueNumber;
    private String support;
    private String countryOfProduction;
    private String category;
    private String label;
    private String language;
    private String performer;
    private List<String> tags;
    private String territory;

    // TITLE ROW
    @JsonProperty("titles")
    private List<TitleRow> titleRows;
    // IDENTIFIER ROW
    @JsonProperty("identifiers")
    private List<IdentifierRow> identifierRows;
    // SHARE ROW
    @JsonProperty("shares")
    private List<ShareRow> shareRows;
    // DYNAMICFIELD ROW
    @JsonProperty("dynamicFields")
    private List<DynamicFieldRow> dynamicFieldRows;
    // DATE ROW
    @JsonProperty("dates")
    private List<DateRow> dateRows;
    // GROUP ROW
    @JsonProperty("groups")
    private List<GroupRow> groupRows;
    // COMPONENT ROW
    @JsonProperty("components")
    private List<ComponentRow> componentRows;

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

    public String getWorkType() {
	return workType;
    }

    public void setWorkType(String workType) {
	this.workType = workType;
    }

    public String getInstrument() {
	return instrument;
    }

    public void setInstrument(String instrument) {
	this.instrument = instrument;
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

    public String getPerformer() {
	return performer;
    }

    public void setPerformer(String performer) {
	this.performer = performer;
    }

    public List<String> getTags() {
	return tags;
    }

    public void setTags(List<String> tags) {
	this.tags = tags;
    }

    public List<TitleRow> getTitleRows() {
	if (null == titleRows) {
	    titleRows = new ArrayList<>();
	}
	return titleRows;
    }

    public void setTitleRows(List<TitleRow> titleRows) {
	this.titleRows = titleRows;
    }

    public List<IdentifierRow> getIdentifierRows() {
	if (null == identifierRows) {
	    identifierRows = new ArrayList<>();
	}
	return identifierRows;
    }

    public void setIdentifierRows(List<IdentifierRow> identifierRows) {
	this.identifierRows = identifierRows;
    }

    public List<ShareRow> getShareRows() {
	if (null == shareRows) {
	    shareRows = new ArrayList<>();
	}
	return shareRows;
    }

    public void setShareRows(List<ShareRow> shareRows) {
	this.shareRows = shareRows;
    }

    public List<DynamicFieldRow> getDynamicFieldRows() {
	if (null == dynamicFieldRows) {
	    dynamicFieldRows = new ArrayList<>();
	}
	return dynamicFieldRows;
    }

    public void setDynamicFieldRows(List<DynamicFieldRow> dynamicFieldRows) {
	this.dynamicFieldRows = dynamicFieldRows;
    }

    public List<DateRow> getDateRows() {
	if (null == dateRows) {
	    dateRows = new ArrayList<>();
	}
	return dateRows;
    }

    public void setDateRows(List<DateRow> dateRows) {
	this.dateRows = dateRows;
    }

    public List<GroupRow> getGroupRows() {
	if (null == groupRows) {
	    groupRows = new ArrayList<>();
	}
	return groupRows;
    }

    public void setGroupRows(List<GroupRow> groupRows) {
	this.groupRows = groupRows;
    }

    public List<ComponentRow> getComponentRows() {
	if (null == componentRows) {
	    componentRows = new ArrayList<>();
	}
	return componentRows;
    }

    public void setComponentRows(List<ComponentRow> componentRows) {
	this.componentRows = componentRows;
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class TitleRow {
	private String workTitle;
	private String type;

	public TitleRow() {
	}

	public String getWorkTitle() {
	    return workTitle;
	}

	public void setWorkTitle(String workTitle) {
	    this.workTitle = workTitle;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class IdentifierRow {

	public IdentifierRow() {
	}

	private String type;
	private String value;

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class ShareRow {

	private String nameMainId;
	private String role;
	private String creatorRef;
	private String type;
	private String rightCategory;
	private String creationClass;
	private String value;

	public ShareRow() {
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

	public String getRightCategory() {
	    return rightCategory;
	}

	public void setRightCategory(String rightCategory) {
	    this.rightCategory = rightCategory;
	}

	public String getCreationClass() {
	    return creationClass;
	}

	public void setCreationClass(String creationClass) {
	    this.creationClass = creationClass;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DynamicFieldRow {
	private String type;
	private String value;

	public DynamicFieldRow() {
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DateRow {
	private String type;
	private String value;

	public DateRow() {
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class GroupRow {
	private String workMainId;

	public GroupRow() {
	}

	public String getWorkMainId() {
	    return workMainId;
	}

	public void setWorkMainId(String workMainId) {
	    this.workMainId = workMainId;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class ComponentRow {
	private String workMainId;
	private String weight;

	public ComponentRow() {
	}

	public String getWorkMainId() {
	    return workMainId;
	}

	public void setWorkMainId(String workMainId) {
	    this.workMainId = workMainId;
	}

	public String getWeight() {
	    return weight;
	}

	public void setWeight(String weight) {
	    this.weight = weight;
	}

    }

    public String getTerritory() {
	return territory;
    }

    public void setTerritory(String territory) {
	this.territory = territory;
    }

}
