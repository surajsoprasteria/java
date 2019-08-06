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



package org.wipo.connect.ruleengine;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.wipo.connect.ruleengine.rules.calculation.ICalculationEngine;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.model.VariableType;



/**
 * The Class Utility.
 */
public class Utility {

    /**
     * Convert.
     *
     * @param regole
     *            the regole
     * @return the list
     */
    public static List<Variable> convert(Map<String, Rule> regole) {

        List<Variable> variables = new ArrayList<Variable>();

        for (String key : regole.keySet()) {
            Rule rule = regole.get(key);
            Variable variable = new Variable(ICalculationEngine.PARAMETERDELIMITER + rule.getName() + ICalculationEngine.PARAMETERDELIMITER,
                    VariableType.RULE, DataType.AMOUNT);
            variable.setCalculationElements(rule.getCalculationElements());
            variables.add(variable);
        }

        return variables;
    }



    /**
     * Convert.
     *
     * @param values
     *            the values
     * @return the list
     */
    public static List<Variable> convert(ValueMap values) {

        List<Variable> variables = new ArrayList<Variable>();

        Set<String> chiavi = values.keySet();
        if (chiavi != null) {
            for (String chiave : chiavi) {

                Object value = values.get(chiave);

                Variable variable = new Variable(chiave, VariableType.CONSTANT,
                        DataType.AMOUNT);
                variable.setValue("" + value);
                variables.add(variable);

            }
        }

        return variables;
    }



    /**
     * Instantiates a new utility.
     */
    private Utility() {
        super();
    }

}
