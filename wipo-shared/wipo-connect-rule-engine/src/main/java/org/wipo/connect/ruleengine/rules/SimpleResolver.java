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

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.ruleengine.rules.calculation.CalculationEngine;
import org.wipo.connect.ruleengine.rules.calculation.ICalculationEngine;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.model.VariableType;
import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;

public class SimpleResolver {

    private final ICalculationEngine calculationEngine;
    protected final FormulaHelper formulaHelper = FormulaHelper.getInstance();
    private final Rule rule;

    public SimpleResolver(final Rule rule) {
	this.calculationEngine = new CalculationEngine();
	//
	this.rule = rule;

	Variable ruleVar = new Variable();
	ruleVar.setName(rule.getName());
	ruleVar.setVariableType(VariableType.RULE);
	ruleVar.setCalculationElements(rule.getCalculationElements());

	this.calculationEngine.load(Arrays.asList(ruleVar));
    }

    public Object execute(final Object... values) throws RulesException {
	return execute(new ValueMap(values));
    }

    public Object execute(final ValueMap values) throws RulesException {

	if (rule == null) {
	    throw new RulesException("The rule is null" + rule);
	}
	if (StringUtils.isEmpty(rule.getName())) {
	    throw new RulesException("The rule must have a name" + rule);
	}

	// risolvo le regole
	return resolve(values, rule, null);
    }

    @SuppressWarnings("squid:S1181")
    protected Object resolve(final ValueMap values, final Rule rule, Object[] args) throws RulesException {

	Map<String, Object> pars = values.getParameters();

	Object output = null;
	try {
	    output = this.calculationEngine.evaluate(ICalculationEngine.PARAMETERDELIMITER + CalculationEngine.toName(rule.getName()) + ICalculationEngine.PARAMETERDELIMITER, pars, args);
	} catch (Throwable e) {
	    throw new RulesException(e);
	}

	return output == null ? null : output;
    }

}
