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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;



/**
 * The Class Rule.
 */
@SuppressWarnings({ "squid:S1948" })
public class Rule implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2779021919719005761L;

    /** The id. */
    private BigDecimal id;

    /** The bank. */
    private BigDecimal bank;

    /** The calculation elements. */
    private List<CalculationElement> calculationElements = new ArrayList<>();

    /** The name. */
    private String name;

    /** The parameter. */
    private Map<String, Object> parameter;



    /**
     * Instantiates a new rule.
     *
     * @param name
     *            the name
     */
    public Rule(String name) {
        this.name = name;
        this.parameter = new HashMap<>(0);
    }



    /**
     * Adds the.
     *
     * @param calculationElement
     *            the calculation element
     */
    public void add(CalculationElement calculationElement) {
        getCalculationElements().add(calculationElement);
    }



    /**
     * Adds the.
     *
     * @param input
     *            the input
     * @param variables
     *            the variables
     */
    private void add(Map<String, Object> input, Map<String, Object> variables) {
        if (variables == null) {
            return;
        }
        for (String key : variables.keySet()) {
            if (!input.containsKey(key)) {
                input.put(key, null);
            }
        }
    }



    /**
     * Adds the parameter.
     *
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void addParameter(String key, Object value) {

        if (this.parameter == null) {
            this.parameter = new HashMap<>(1);
        }

        this.parameter.put(key, value);
    }



    /**
     * Gets the bank.
     *
     * @return the bank
     */
    public BigDecimal getBank() {
        return this.bank;
    }



    /**
     * Gets the calculation elements.
     *
     * @return the calculation elements
     */
    public List<CalculationElement> getCalculationElements() {
        return this.calculationElements;
    }



    /**
     * Gets the id.
     *
     * @return the id
     */
    public BigDecimal getId() {
        return this.id;
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
     * Gets the parameter.
     *
     * @return the parameter
     */
    public Map<String, Object> getParameter() {
        return this.parameter;
    }



    /**
     * Input.
     *
     * @return the map
     */
    public Map<String, Object> input() {
        if (this.calculationElements == null) {
            return null;
        }
        Map<String, Object> input = new HashMap<>();
        for (CalculationElement ec : this.calculationElements) {

            add(input, FormulaHelper.variables(ec.getFormula()));

            if (ec.getConditions() != null) {
                for (String ccb : ec.getConditions()) {
                    add(input, FormulaHelper.variables(ccb));
                }
            }
        }
        return input;
    }








    /**
     * Sets the bank.
     *
     * @param bank
     *            the new bank
     */
    public void setBank(BigDecimal bank) {
        this.bank = bank;
    }



    /**
     * Sets the calculation elements.
     *
     * @param calculationElements
     *            the new calculation elements
     */
    public void setCalculationElements(
            List<CalculationElement> calculationElements) {
        this.calculationElements = calculationElements;
    }



    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(BigDecimal id) {
        this.id = id;
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
     * Sets the parameter.
     *
     * @param parameter
     *            the parameter
     */
    public void setParameter(Map<String, Object> parameter) {
        this.parameter = parameter;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this).append(this.id).append(this.name)
                .append(this.calculationElements).toString();
    }





}
