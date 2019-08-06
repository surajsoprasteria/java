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
import java.util.Map;



/**
 * The Class Recording.
 */
@SuppressWarnings({ "squid:S1948" })
public class Recording implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3851797258563651852L;

    /** The Constant NULL. */
    private static final String NULL = "null";

    /** The parameters. */
    private Map<String, Object> parameters;

    /** The steps. */
    private List<Step> steps = new ArrayList<>();

    /** The result. */
    private Object result;

    /** The complete fields. */
    private boolean completeFields = true;



    /**
     * Adds the.
     *
     * @param step
     *            the step
     */
    public void add(Step step) {
        this.steps.add(step);
    }



    /**
     * Gets the.
     *
     * @param name
     *            the name
     * @return the step
     */
    public Step get(String name) {
        if (this.steps == null) {
            return null;
        }
        for (Step step : this.steps) {
            if (step.getName().equals(name)) {
                return step;
            }
        }
        return null;
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
        if (this.result != null) {
            return this.result;
        }
        if (this.steps == null || this.steps.isEmpty()) {
            return null;
        }
        return this.steps.get(this.steps.size() - 1).getResult();
    }



    /**
     * Gets the steps.
     *
     * @return the steps
     */
    public List<Step> getSteps() {
        return this.steps;
    }



    /**
     * Have coplete fields simply mode.
     *
     * @return true, if successful
     */
    public boolean haveCopleteFieldsSimplyMode() {

        if (this.steps == null) {
            return true;
        }

        for (Step step : this.steps) {
            if ((step.getConditionValue() + step.getFormulaValue())
                    .contains(NULL)) {
                return false;
            }
        }

        return true;
    }



    /**
     * Checks if is complete fields.
     *
     * @return true, if is complete fields
     */
    public boolean isCompleteFields() {
        return this.completeFields;
    }








    /**
     * Sets the complete fields.
     *
     * @param completeFilelds
     *            the new complete fields
     */
    public void setCompleteFields(boolean completeFilelds) {
        this.completeFields = completeFilelds;
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
     * Sets the steps.
     *
     * @param steps
     *            the new steps
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }




    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("parameters:" + this.parameters).append('\n');
        if (this.steps != null) {
            int k = 1;
            for (Step p : this.steps) {
                sb.append(k++).append(". ").append(p.getName()).append(" | ")
                        .append("condition:").append(p.getCondition())
                        .append(" | ").append("formula:")
                        .append(p.getFormula()).append(" | ")
                        .append("condition value:")
                        .append(p.getConditionValue()).append(" | ")
                        .append("condition result:")
                        .append(p.getConditionResult()).append(" | ")
                        .append("result:").append(p.getResult()).append(" | ")
                        .append("full description:")
                        .append(p.getFullDescription()).append(" | ")
                        .append("result:").append(p.getStringResult())
                        .append('\n');
            }
        }
        return sb.toString();
    }



    /**
     * Update complete fields.
     *
     * @param completeFields
     *            the complete fields
     */
    public void updateCompleteFields(boolean completeFields) {
        this.completeFields &= completeFields;
    }






}
