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
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * The Class EngineContext.
 */
public class EngineContext {

    /** The variables. */
    private Map<String, Variable> variables = new HashMap<String, Variable>();

    /** The parameters. */
    private List<String> parameters = new ArrayList<String>();

    /** The regs. */
    private List<String> regs = new ArrayList<String>();

    /** The constants. */
    private List<String> constants = new ArrayList<String>();



    /**
     * Adds the constants.
     *
     * @param constant
     *            the constant
     */
    public void addConstants(String constant) {

        if (this.constants == null) {
            this.constants = new ArrayList<String>();
        }

        this.constants.add(constant);
    }



    /**
     * Adds the parameters.
     *
     * @param parametro
     *            the parametro
     */
    public void addParameters(String parametro) {

        if (this.parameters == null) {
            this.parameters = new ArrayList<String>();
        }

        this.parameters.add(parametro);
    }



    /**
     * Adds the regs.
     *
     * @param reg
     *            the reg
     */
    public void addRegs(String reg) {

        if (this.regs == null) {
            this.regs = new ArrayList<String>();
        }

        this.regs.add(reg);
    }



    /**
     * Adds the variables.
     *
     * @param variable
     *            the variable
     */
    public void addVariables(Variable variable) {

        if (this.variables == null) {
            this.variables = new HashMap<String, Variable>();
        }

        this.variables.put(variable.getName(), variable);
    }



    /**
     * Gets the constants.
     *
     * @return the constants
     */
    public List<String> getConstants() {
        return this.constants;
    }



    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public List<String> getParameters() {
        return this.parameters;
    }



    /**
     * Gets the regs.
     *
     * @return the regs
     */
    public List<String> getRegs() {
        return this.regs;
    }



    /**
     * Gets the variables.
     *
     * @return the variables
     */
    public Map<String, Variable> getVariables() {
        return this.variables;
    }

}
