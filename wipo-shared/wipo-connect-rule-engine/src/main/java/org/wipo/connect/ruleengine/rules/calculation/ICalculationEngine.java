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



package org.wipo.connect.ruleengine.rules.calculation;



import groovy.lang.Script;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.wipo.connect.ruleengine.rules.model.Variable;



/**
 * The Interface ICalculationEngine.
 */
public interface ICalculationEngine {

    /** The Constant PARAMETERDELIMITER. */
    String PARAMETERDELIMITER = "$";

    /** The def functions. */
    String DEF_FUNCTIONS = "";



    /**
     * Evaluate.
     *
     * @param functionName
     *            the function name
     * @param parameters
     *            the parameters
     * @param pars
     *            the pars
     * @return the object
     * @throws Throwable
     *             the throwable
     */
    Object evaluate(String functionName, Map<String, Object> parameters,
            Object[] pars) throws Throwable;



    /**
     * Gets the script.
     *
     * @return the script
     */
    Script getScript();



    /**
     * Load.
     *
     * @param variables
     *            the variables
     * @return true, if successful
     */
    boolean load(final List<Variable> variables);



    /**
     * Load.
     *
     * @param variables
     *            the variables
     * @param customFunctions
     *            the custom functions
     */
    void load(List<Variable> variables, InputStream... customFunctions);



    /**
     * Load.
     *
     * @param script
     *            the script
     * @return true, if successful
     */
    boolean load(Script script);

}
