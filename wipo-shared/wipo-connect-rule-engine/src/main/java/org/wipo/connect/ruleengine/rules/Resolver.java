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



package org.wipo.connect.ruleengine.rules;



import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.ruleengine.rules.calculation.CalculationEngine;
import org.wipo.connect.ruleengine.rules.calculation.ICalculationEngine;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.Recording;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;

import groovy.lang.Binding;
import groovy.lang.Script;



/**
 * The Class Resolver.
 */
public class Resolver implements IResolver {

    /** The calculation engine. */
    private ICalculationEngine calculationEngine;

    //private Recording recording;

    /** The formula helper. */
    protected final FormulaHelper formulaHelper = FormulaHelper.getInstance();



    /**
     * Instantiates a new resolver.
     *
     * @param compileStatic
     *            the compile static
     * @param variables
     *            the variables
     */
    public Resolver(boolean compileStatic, List<Variable> variables) {
        this.calculationEngine = new CalculationEngine(compileStatic);
        this.calculationEngine.load(variables);
    }



    /**
     * Instantiates a new resolver.
     *
     * @param compileStatic
     *            the compile static
     * @param variables
     *            the variables
     * @param customFunction
     *            the custom function
     */
    public Resolver(boolean compileStatic, List<Variable> variables,
            InputStream... customFunction) {
        this.calculationEngine = new CalculationEngine(compileStatic);
        this.calculationEngine.load(variables, customFunction);
    }



    /**
     * Instantiates a new resolver.
     *
     * @param variables
     *            the variables
     */
    public Resolver(List<Variable> variables) {
        this.calculationEngine = new CalculationEngine();
        this.calculationEngine.load(variables);
    }



    /**
     * Instantiates a new resolver.
     *
     * @param variables
     *            the variables
     * @param customFunction
     *            the custom function
     */
    public Resolver(List<Variable> variables, InputStream... customFunction) {
        this.calculationEngine = new CalculationEngine();
        this.calculationEngine.load(variables, customFunction);
    }



    /**
     * Instantiates a new resolver.
     *
     * @param script
     *            the script
     */
    public Resolver(Script script) {
        this.calculationEngine = new CalculationEngine();
        this.calculationEngine.load(script);
    }



    /**
     * Execute.
     *
     * @param rule
     *            the rule
     * @param args
     *            the args
     * @return the object
     * @throws RulesException
     *             the rules exception
     */
    public Object execute(final Rule rule, Object... args) throws RulesException {
        return execute(new ValueMap(), rule, args);
    }

    /**
     * Execute.
     *
     * @param rule the rule
     * @param values the values
     * @return the object
     * @throws RulesException the rules exception
     */
    public Object execute(final Rule rule, ValueMap values) throws RulesException {
        return execute(values, rule, null);
    }

    /**
     * Execute.
     *
     * @param rule the rule
     * @param values the values
     * @param args the args
     * @return the object
     * @throws RulesException the rules exception
     */
    public Object execute(final Rule rule, ValueMap values, Object... args) throws RulesException {
        return execute(values, rule, args);
    }



    @Override
    public Object execute(final ValueMap values, final Rule rule, Object[] args)
            throws RulesException {

        if (rule == null) {
            throw new RulesException("The rule is null" + rule);
        }
        if (StringUtils.isEmpty(rule.getName())) {
            throw new RulesException("The rule must have a name" + rule);
        }

        //verifico se è un valore già calcolato
        Object result = values.get(rule.getName());
        if (result != null && !(isRule(result))) {
            return result;
        }

        //risolvo le regole
        return resolve(values, rule, args);

    }



    /**
     * Gets the recording.
     *
     * @return the recording
     */
    public Recording getRecording() {

        Binding binding = getScript().getBinding();

        return (Recording) binding.getProperty("recording");
    }



    /**
     * Gets the script.
     *
     * @return the script
     */
    public Script getScript() {
        return this.calculationEngine.getScript();
    }



    /**
     * Checks if is rule.
     *
     * @param o
     *            the o
     * @return true, if is rule
     */
    private boolean isRule(final Object o) {
        if (o == null) {
            return false;
        }
        return o instanceof Rule;
    }



    /**
     * Reset recording.
     *
     * @return true, if successful
     */
    public boolean resetRecording() {

        Binding binding = getScript().getBinding();

        binding.setProperty("recording", new Recording());

        return true;
    }



    /**
     * Resolve.
     *
     * @param values
     *            the values
     * @param rule
     *            the rule
     * @param args
     *            the args
     * @return the object
     * @throws RulesException
     *             the rules exception
     */
    @SuppressWarnings("squid:S1181")
    protected Object resolve(final ValueMap values, final Rule rule,
            Object[] args) throws RulesException {

        Map<String, Object> pars = values.getParameters();

        Object output = null;
        try {
            output = this.calculationEngine.evaluate(
                    ICalculationEngine.PARAMETERDELIMITER + CalculationEngine.toName(rule.getName()) + ICalculationEngine.PARAMETERDELIMITER, pars,
                    args);
        } catch (Throwable e) {
            throw new RulesException(e);
        }

        return output == null ? null : output;
    }

}
