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
import java.util.List;

import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.querypagination.PageInfo;

/**
 * The Class NameSearchVO.
 */
public class NameSearchVO implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4553419072357918783L;

    /** The id name. */
    private Long idName;

    /** The name. */
    private String name;

    /** The first name. */
    private String firstName;

    /** The value form. */
    private String valueForm;

    /** the id to exclude. */
    private Long excludeId;

    /** The only registered. */
    private Boolean onlyRegistered;
    /** The only patronym. */
    private Boolean isOnlyPatronym;

    /** The is only legal entity. */
    private Boolean isOnlyLegalEntity;

    private List<String> nameTypeList;

    private List<String> creationClassCodeList;

    private PageInfo pageInfo;

    /**
     * Gets the checks if is only patronym.
     *
     * @return the checks if is only patronym
     */
    public Boolean getIsOnlyPatronym() {
	return isOnlyPatronym;
    }

    /**
     * Sets the checks if is only patronym.
     *
     * @param isOnlyPatronym
     *            the new checks if is only patronym
     */
    public void setIsOnlyPatronym(Boolean isOnlyPatronym) {
	this.isOnlyPatronym = isOnlyPatronym;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
	return this.firstName;
    }

    @Override
    public Long getId() {
	return getIdName();
    }

    /**
     * Gets the id name.
     *
     * @return the id name
     */
    public Long getIdName() {
	return this.idName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * Gets the only registered.
     *
     * @return the only registered
     */
    public Boolean getOnlyRegistered() {
	return this.onlyRegistered;
    }

    /**
     * Sets the first name.
     *
     * @param firstName
     *            the new first name
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    @Override
    public void setId(Long id) {
	setIdName(id);
    }

    /**
     * Sets the id name.
     *
     * @param idName
     *            the new id name
     */
    public void setIdName(Long idName) {
	this.idName = idName;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Sets the only registered.
     *
     * @param onlyRegistered
     *            the new only registered
     */
    public void setOnlyRegistered(Boolean onlyRegistered) {
	this.onlyRegistered = onlyRegistered;
    }

    /**
     * Gets the value form.
     *
     * @return the value form
     */
    public String getValueForm() {
	return valueForm;
    }

    /**
     * Sets the value form.
     *
     * @param valueForm
     *            the new value form
     */
    public void setValueForm(String valueForm) {
	this.valueForm = valueForm;
    }

    /**
     * Gets the exclude id.
     *
     * @return the exclude id
     */
    public Long getExcludeId() {
	return excludeId;
    }

    /**
     * Sets the exclude id.
     *
     * @param excludeId
     *            the new exclude id
     */
    public void setExcludeId(Long excludeId) {
	this.excludeId = excludeId;
    }

    /**
     * Gets the checks if is only legal entity.
     *
     * @return the checks if is only legal entity
     */
    public Boolean getIsOnlyLegalEntity() {
	return isOnlyLegalEntity;
    }

    /**
     * Sets the checks if is only legal entity.
     *
     * @param isOnlyLegalEntity
     *            the new checks if is only legal entity
     */
    public void setIsOnlyLegalEntity(Boolean isOnlyLegalEntity) {
	this.isOnlyLegalEntity = isOnlyLegalEntity;
    }

    public List<String> getNameTypeList() {
	return nameTypeList;
    }

    public void setNameTypeList(List<String> nameTypeList) {
	this.nameTypeList = nameTypeList;
    }

    public List<String> getCreationClassCodeList() {
	return creationClassCodeList;
    }

    public void setCreationClassCodeList(List<String> creationClassCodeList) {
	this.creationClassCodeList = creationClassCodeList;
    }

    public PageInfo getPageInfo() {
	return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
	this.pageInfo = pageInfo;
    }

}
