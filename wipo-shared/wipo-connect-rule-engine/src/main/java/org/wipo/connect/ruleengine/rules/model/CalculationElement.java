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

package org.wipo.connect.ruleengine.rules.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class CalculationElement.
 */
public class CalculationElement implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 9140631273190212056L;

    /** The name. */
    private String name;

    /** The formula. */
    private String formula;

    /** The conditions. */
    private List<String> conditions;

    /** The description. */
    private String description;

    /**
     * Instantiates a new calculation element.
     *
     * @param condition
     *            the condition
     * @param n
     *            the n
     */
    public CalculationElement(String condition, double n) {
	this(condition, Double.toString(n));
    }

    /**
     * Instantiates a new calculation element.
     *
     * @param condition
     *            the condition
     * @param n
     *            the n
     */
    public CalculationElement(String condition, int n) {
	this(condition, Integer.toString(n));
    }

    /**
     * Instantiates a new calculation element.
     *
     * @param condition
     *            the condition
     * @param n
     *            the n
     */
    public CalculationElement(String condition, long n) {
	this(condition, Long.toString(n));
    }

    /**
     * Instantiates a new calculation element.
     *
     * @param condition
     *            the condition
     * @param formula
     *            the formula
     */
    public CalculationElement(String condition, String formula) {
	addCondition(condition);
	this.formula = formula;

    }

    /**
     * Instantiates a new calculation element.
     *
     * @param formula
     *            the formula
     */
    public CalculationElement(String formula) {
	this.formula = formula;
    }

    /**
     * Adds the all.
     *
     * @param conditions
     *            the conditions
     */
    public void addAll(List<String> conditions) {
	if (conditions == null) {
	    conditions = new ArrayList<String>();
	}
	this.conditions.addAll(conditions);
    }

    /**
     * Adds the condition.
     *
     * @param condition
     *            the condition
     */
    private void addCondition(String condition) {
	if (this.conditions == null) {
	    this.conditions = new ArrayList<String>();
	}
	this.conditions.add(condition);
    }

    /**
     * Gets the condition.
     *
     * @return the condition
     */
    public String getCondition() {
	StringBuffer sb = new StringBuffer();
	if (this.conditions == null || this.conditions.isEmpty()) {
	    return Boolean.TRUE.toString();
	}
	for (String ccb : this.conditions) {
	    if (StringUtils.isEmpty(ccb)) {
		continue;
	    }
	    sb.append(" ( ");
	    sb.append(ccb);
	    sb.append(" ) &&");
	}
	String out = sb.toString();
	if (StringUtils.isEmpty(out)) {
	    return Boolean.TRUE.toString();
	}
	;
	return out.substring(0, out.length() - 2);
    }

    /**
     * Gets the conditions.
     *
     * @return the conditions
     */
    public List<String> getConditions() {
	this.conditions = ObjectUtils.defaultIfNull(this.conditions, new ArrayList<String>());
	return this.conditions;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
	return this.description;
    }

    /**
     * Gets the formula.
     *
     * @return the formula
     */
    public String getFormula() {
	return this.formula;
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
     * Checks if is condition present.
     *
     * @return true, if is condition present
     */
    public boolean isConditionPresent() {
	return (this.conditions != null && !this.conditions.isEmpty() ? true : false);
    }

    /**
     * Sets the conditions.
     *
     * @param conditions
     *            the new conditions
     */
    public void setConditions(List<String> conditions) {
	this.conditions = conditions;
    }

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Sets the formula.
     *
     * @param formula
     *            the new formula
     */
    public void setFormula(String formula) {
	this.formula = formula;
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

    @Override
    public String toString() {
	return "[name:" + this.name + ", condition:" + getCondition() + ", formula:" + this.formula + "]";
    }

}