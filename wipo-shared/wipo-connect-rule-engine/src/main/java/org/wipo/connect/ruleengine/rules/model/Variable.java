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



import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;



/**
 * The Class Variable.
 */
public class Variable {

    /** The name. */
    private String name;

    /** The variable type. */
    private VariableType variableType;

    /** The data type. */
    private DataType dataType;

    /** The calculation elements. */
    private List<CalculationElement> calculationElements;

    /** The value. */
    private String value;



    /**
     * Instantiates a new variable.
     */
    public Variable() {
        super();
    }



    /**
     * Instantiates a new variable.
     *
     * @param name
     *            the name
     * @param variableType
     *            the variable type
     */
    public Variable(String name, int variableType) {
        super();
        this.setName(name);
        this.setVariableType(VariableType.values()[variableType]);
    }



    /**
     * Instantiates a new variable.
     *
     * @param name
     *            the name
     * @param variableType
     *            the variable type
     * @param dataType
     *            the data type
     */
    public Variable(String name, VariableType variableType, DataType dataType) {
        super();
        this.setName(name);
        this.setVariableType(variableType);
        this.setDataType(dataType);
    }



    /**
     * Adds the.
     *
     * @param ec
     *            the ec
     */
    public void add(CalculationElement ec) {
        if (getCalculationElements() == null) {
            this.calculationElements = new ArrayList<CalculationElement>();
        }
        this.calculationElements.add(ec);
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
     * Gets the data type.
     *
     * @return the data type
     */
    public DataType getDataType() {
        return this.dataType;
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
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }



    /**
     * Gets the variable type.
     *
     * @return the variable type
     */
    public VariableType getVariableType() {
        return this.variableType;
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
     * Sets the data type.
     *
     * @param dataType
     *            the new data type
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }



    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = FormulaHelper.getInstance().toMarkedVar(name);
    }



    /**
     * Sets the value.
     *
     * @param value
     *            the new value
     */
    public void setValue(String value) {
        this.value = value;
    }



    /**
     * Sets the variable type.
     *
     * @param variableType
     *            the new variable type
     */
    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }
}
