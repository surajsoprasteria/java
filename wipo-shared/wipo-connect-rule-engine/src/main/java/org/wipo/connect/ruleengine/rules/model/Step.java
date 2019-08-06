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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;



/**
 * The Class Step.
 */
@SuppressWarnings({ "squid:S1948" })
public class Step implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3368209737408346228L;

    /** The name. */
    private String name;

    /** The description. */
    private String description;

    /** The full description. */
    private String fullDescription;

    /** The condition. */
    private String condition;

    /** The condition value. */
    private String conditionValue;

    /** The formula. */
    private String formula;

    /** The formula value. */
    private String formulaValue;

    /** The result. */
    private Object result;

    /** The condition result. */
    private Boolean conditionResult;

    /** The string result. */
    private String stringResult;

    /** The parameters. */
    private Map<String, Object> parameters;



    /**
     * Instantiates a new step.
     */
    public Step() {
        super();
    }



    /**
     * Instantiates a new step.
     *
     * @param name
     *            the name
     * @param condition
     *            the condition
     * @param formula
     *            the formula
     * @param values
     *            the values
     */
    public Step(String name, String condition, String formula,
            Map<String, Object> values) {
        this.name = name;
        this.condition = condition;
        this.formula = formula;
        Map<String, Object> variables = FormulaHelper.variables(condition);
        variables.putAll(FormulaHelper.variables(formula));
        this.parameters = new HashMap<>();
        for (String key : variables.keySet()) {
            Object value = values.get(key);
            if (value != null && !(value instanceof Rule)
                    && !(value instanceof List)) {
                this.parameters.put(key, value);
            }
        }

    }



    /**
     * Gets the condition.
     *
     * @return the condition
     */
    public String getCondition() {
        return this.condition;
    }



    /**
     * Gets the condition result.
     *
     * @return the condition result
     */
    public Boolean getConditionResult() {
        return this.conditionResult;
    }



    /**
     * Gets the condition value.
     *
     * @return the condition value
     */
    public String getConditionValue() {
        return this.conditionValue;
    }



    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description == null ? this.name : this.description;
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
     * Gets the formula value.
     *
     * @return the formula value
     */
    public String getFormulaValue() {
        return this.formulaValue;
    }



    /**
     * Gets the full description.
     *
     * @return the full description
     */
    public String getFullDescription() {
        return this.fullDescription == null ? "" : this.fullDescription;
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
     * Gets the parameters.
     *
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return this.parameters;
    }



    /**
     * Gets the result.
     *
     * @return the result
     */
    public Object getResult() {
        return this.result;
    }



    /**
     * Gets the string result.
     *
     * @return the string result
     */
    public String getStringResult() {
        return this.stringResult == null ? "" : this.stringResult;
    }








    /**
     * Sets the condition.
     *
     * @param condition
     *            the new condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }



    /**
     * Sets the condition result.
     *
     * @param conditionResult
     *            the new condition result
     */
    public void setConditionResult(Boolean conditionResult) {
        this.conditionResult = conditionResult;
    }



    /**
     * Sets the condition value.
     *
     * @param conditionValue
     *            the new condition value
     */
    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
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
     * Sets the formula value.
     *
     * @param formulaValue
     *            the new formula value
     */
    public void setFormulaValue(String formulaValue) {
        this.formulaValue = formulaValue;
    }



    /**
     * Sets the full description.
     *
     * @param fullDescription
     *            the new full description
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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
     * Sets the parameters.
     *
     * @param parameters
     *            the parameters
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }



    /**
     * Sets the result.
     *
     * @param result
     *            the new result
     */
    public void setResult(Object result) {
        this.result = result;
    }



    /**
     * Sets the string result.
     *
     * @param stringResult
     *            the new string result
     */
    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }





}
