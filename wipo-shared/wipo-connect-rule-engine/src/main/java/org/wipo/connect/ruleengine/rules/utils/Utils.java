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
package org.wipo.connect.ruleengine.rules.utils;

import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.ruleengine.rules.model.CalculationElement;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.model.VariableType;

/**
 * The Class Utils.
 */
public class Utils {
    private Utils() {
	super();
    }

    /**
     * Creates the rule.
     *
     * @param ruleName
     *            the rule name
     * @param retType
     *            the ret type
     * @param condition
     *            the condition
     * @param formula
     *            the formula
     * @param variables
     *            the variables
     * @return the rule
     */
    public static Rule createRule(String ruleName, DataType retType, String condition, String formula, List<Variable> variables) {

	CalculationElement elem = new CalculationElement(condition, formula);
	elem.setName(ruleName);

	List<CalculationElement> listElementoCalcolo = new ArrayList<>();
	listElementoCalcolo.add(elem);

	Variable var = new Variable();
	var.setName(ruleName);
	var.setVariableType(VariableType.RULE);
	var.setDataType(retType);
	var.setCalculationElements(listElementoCalcolo);

	Rule regola = new Rule(ruleName);
	regola.setCalculationElements(listElementoCalcolo);

	variables.add(var);

	return regola;
    }

    public static Rule createRule(String ruleName, String formula) {

	CalculationElement elem = new CalculationElement(formula);
	elem.setName(ruleName);

	List<CalculationElement> listElementoCalcolo = new ArrayList<>();
	listElementoCalcolo.add(elem);

	Rule regola = new Rule(ruleName);
	regola.setCalculationElements(listElementoCalcolo);

	return regola;
    }

}
