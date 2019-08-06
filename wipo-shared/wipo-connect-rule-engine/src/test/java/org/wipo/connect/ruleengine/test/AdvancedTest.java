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



package org.wipo.connect.ruleengine.test;



import static org.wipo.connect.ruleengine.rules.utils.Utils.createRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.rules.Resolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;

public class AdvancedTest{

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(AdvancedTest.class);


    @Test
    public void customFunction() throws Exception {
        String ruleName = "customFunction";

        String ruleCondition = "true";
        String ruleFormula = "myCustomAvg($val1$,$val2$)";

        List<Variable> variables = new ArrayList<>();
        Rule rule = createRule(ruleName, DataType.DOUBLE, ruleCondition, ruleFormula, variables);

        Resolver resolver = new Resolver(variables, this.getClass().getResourceAsStream("/Custom.groovy"));

        Double res = null;
        try {
            ValueMap valMap = new ValueMap();
            valMap.put("val1", 52);
            valMap.put("val2", 32);
            res = (Double) resolver.execute(rule, valMap);
            Assert.assertEquals(Double.valueOf(-42), res);
            LOGGER.debug("Result:" + res);

        } catch (RulesException e) {
            LOGGER.error("Error executing the rule", e);
            throw e;
        }

    }

    @Test
    public void concurrencyTest() throws Exception {
        String ruleName = "concurrencyTest";

        String ruleCondition = "true";
        String ruleFormula = "myCustomMultiply($v1$ , $v2$ , $t1$) + myCustomMultiply($v1$ , $v2$ , $t1$)";

        List<Variable> variables = new ArrayList<>();

        final Rule rule = createRule(ruleName, DataType.INTEGER, ruleCondition, ruleFormula, variables);
        final Resolver resolver = new Resolver(variables, this.getClass().getResourceAsStream("/Custom.groovy"));
        final ValContainer res1 = new ValContainer();
        final ValContainer res2 = new ValContainer();

        try {

            Thread op1 = (new Thread() {
                @Override
				public void run() {
                    try {
                        ValueMap valMap = new ValueMap();
                        valMap.put("v1", 2);
                        valMap.put("v2", 50);
                        valMap.put("t1", 10);
                        Integer res = (Integer) resolver.execute(rule, valMap);
                        LOGGER.debug("Result:" + res);
                        res1.setValue(res);
                    } catch (RulesException e) {
                    	LOGGER.error("Error:" , e);
                    }
                }
               });

            Thread op2 = (new Thread() {
                @Override
				public void run() {
                    try {
                        ValueMap valMap = new ValueMap();
                        valMap.put("v1", 4);
                        valMap.put("v2", 10);
                        valMap.put("t1", 20);
                        Integer res = (Integer) resolver.execute(rule, valMap);
                        LOGGER.debug("Result:" + res);
                        res2.setValue(res);
                    } catch (RulesException e) {
                    	LOGGER.error("Error:" , e);
                    }
                }
               });

            op1.run();
            Assert.assertEquals(Integer.valueOf(200), res1.getValue());
            op2.run();
            Assert.assertEquals(Integer.valueOf(80), res2.getValue());

            op1.start();
            Thread.sleep(100);
            op2.start();
            Thread.sleep(100);
            op1.join();
            op2.join();

            boolean sameRes = res1.getValue().compareTo(200) == 0 && res2.getValue().compareTo(80) == 0;
            Assert.assertFalse(sameRes);


        } catch (Exception e) {
            LOGGER.error("Error executing the rule", e);
            throw e;
        }

    }

    private class ValContainer{
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer res) {
            this.value = res;
        }
    }

}
