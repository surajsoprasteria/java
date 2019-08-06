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



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.ruleengine.rules.calculation.ICalculationEngine;
import org.wipo.connect.ruleengine.rules.model.EngineContext;



/**
 * The Class FormulaHelper.
 */
public class FormulaHelper {

    /** The pattern recrem variable. */
    private static Pattern PATTERN_RECREM_VARIABLE = Pattern.compile("\\"+ICalculationEngine.PARAMETERDELIMITER
            + "REC_REM\\" +ICalculationEngine.PARAMETERDELIMITER+"\\.[a-zA-Z0-9]*");


    /** The me. */
    private static FormulaHelper me;



    /**
     * Extract variable list.
     *
     * @param expression
     *            the expression
     * @return the list
     */
    public static List<String> extractVariableList(String expression) {
        return getInstance().extractVariables(expression);
    }



    /**
     * Gets the single instance of FormulaHelper.
     *
     * @return single instance of FormulaHelper
     */
    public static FormulaHelper getInstance() {
        if (me == null) {
            me = new FormulaHelper();
        }
        return me;
    }



    /**
     * Log variable value.
     *
     * @param formula
     *            the formula
     * @param engineContext
     *            the engine context
     * @return the object
     */
    public static Object logVariableValue(String formula,
            EngineContext engineContext) {

        Map<String, String> replaced = new HashMap<String, String>(5);

        List<String> list = FormulaHelper.extractVariableList(formula);
        for (String element : list) {

            if ("REC_REM".equals(element)) {

                Matcher matcher = PATTERN_RECREM_VARIABLE.matcher(formula);

                while (matcher.find()) {
                    String gv = matcher.group(0);

                    if (replaced.containsKey(gv)) {
                        continue;
                    }

                    formula = formula.replace(gv, "${" + gv + "}");
                    replaced.put(gv, gv);
                }

            } else if (formula.startsWith("$FUNCTION_")) {
                //TODO MEGA PEZZA
                String tmp = element.replaceAll("\\(", "")
                        .replaceAll("\\)", "");
                formula = formula
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .replaceAll("\\$" + tmp + "\\$",
                                "\\${\\$" + tmp + "\\$}");

            } else {
                formula = formula.replaceAll("\\$" + element + "\\$", "\\${\\$"
                        + element + "\\$}");
            }

        }

        //elimino i singoli apici
        formula = formula.replaceAll("\\\"", "\\\\\"");

        return formula;
    }



    /**
     * To boolean.
     *
     * @param v
     *            the v
     * @return true, if successful
     */
    public static boolean toBoolean(Object v) {
        return getInstance().getBoolean(v);
    }



    /**
     * Variables.
     *
     * @param expression
     *            the expression
     * @return the map
     */
    public static Map<String, Object> variables(String expression) {
        return getInstance().parser(expression);
    }



    /**
     * Variables rec rem.
     *
     * @param formula
     *            the formula
     * @return the list
     */
    public static List<String> variablesRecRem(String formula) {

        Matcher matcher = PATTERN_RECREM_VARIABLE.matcher(formula);

        List<String> variables = new ArrayList<String>();

        while (matcher.find()) {

            String gv = matcher.group(0);
            variables.add(gv);

        }

        return variables;

    }



    /**
     * Extract variables.
     *
     * @param expression
     *            the expression
     * @return the list
     */
    public List<String> extractVariables(String expression) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isEmpty(expression)) {
            return list;
        }

        Pattern pattern = Pattern.compile("\\" + ICalculationEngine.PARAMETERDELIMITER + ".*?\\" + ICalculationEngine.PARAMETERDELIMITER);
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String gv = matcher.group(0);
            gv = toVar(gv);
            if (!list.contains(gv)) {
                list.add(gv);
            }
        }
        return list;
    }



    /**
     * Gets the boolean.
     *
     * @param v
     *            the v
     * @return the boolean
     */
    public boolean getBoolean(Object v) {

        if (v == null) {
            return false;
        }

        if (!(v instanceof Boolean)) {
            return false;
        }

        return ((Boolean) v).booleanValue();
    }



    /**
     * Parser.
     *
     * @param expression
     *            the expression
     * @return the map
     */
    public Map<String, Object> parser(String expression) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(expression)) {
            return map;
        }
        List<String> items = extractVariables(expression);
        for (String v : items) {
            map.put(v, null);
        }
        return map;
    }



    /**
     * To marked var.
     *
     * @param var
     *            the var
     * @return the string
     */
    public String toMarkedVar(String var) {
        if (StringUtils.isEmpty(var)) {
            return null;
        }
        var = var.trim();
        if (!var.startsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = ICalculationEngine.PARAMETERDELIMITER + var;
        }
        if (!var.endsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = var + ICalculationEngine.PARAMETERDELIMITER;
        }
        return var;
    }



    /**
     * To simple number.
     *
     * @param object
     *            the object
     * @return the object
     */
    public Object toSimpleNumber(final Object object) {
        if (object != null && object instanceof Number) {
            if (object instanceof BigDecimal || object instanceof Double
                    || object instanceof Float) {
                return ((Number) object).doubleValue();
            } else {
                return ((Number) object).intValue();
            }
        }
        return object;
    }



    /**
     * To var.
     *
     * @param var
     *            the var
     * @return the string
     */
    public String toVar(String var) {
        if (StringUtils.isEmpty(var)) {
            return null;
        }
        var = var.trim();
        if (var.startsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = var.substring(1);
        }
        if (var.endsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = var.substring(0, var.length() - 1);
        }
        return var;
    }



    /**
     * To var groovy.
     *
     * @param var
     *            the var
     * @return the string
     */
    public String toVarGroovy(String var) {
        if (StringUtils.isEmpty(var)) {
            return null;
        }
        var = var.trim();
        if (!var.startsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = ICalculationEngine.PARAMETERDELIMITER + var;
        }
        if (var.endsWith(ICalculationEngine.PARAMETERDELIMITER)) {
            var = var.substring(0, var.length() - 1);
        }
        return var;
    }

}
