


package org.wipo.connect.ruleengine.test;



import static org.wipo.connect.ruleengine.rules.utils.Utils.createRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.rules.Resolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Recording;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.model.VariableType;

public class SimpleTest{

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(SimpleTest.class);


    @Test
    public void testSumRule() throws RulesException {
        String ruleName = "testSumRule";

        Variable a = new Variable();
        a.setName("a");
        a.setVariableType(VariableType.PARAMETER);
        a.setDataType(DataType.INTEGER);

        Variable b = new Variable();
        b.setName("b");
        b.setVariableType(VariableType.PARAMETER);
        b.setDataType(DataType.INTEGER);

        List<Variable> variables = new ArrayList<Variable>();
        Rule rule = createRule(ruleName, DataType.INTEGER, "$a$ > $b$", "$a$ + $b$", variables);

        variables.add(a);
        variables.add(b);

        Object[] args = new Object[3];
        args[0] = new Recording();
        args[1] = new Integer(3);
        args[2] = new Integer(1);

        Resolver resolver = new Resolver(variables);

        Object res = null;
        try {
            res = resolver.execute(rule, args);
        } catch (RulesException e) {
            LOGGER.error("Error executing the rule", e);
            throw e;
        }
        LOGGER.debug("Result:" + res);

        Assert.assertEquals(new Integer("4"),res);
    }

    @Test
    public void testCondition() throws RulesException {
        String ruleNameFalse = "testConditionFalse";
        String ruleNameTrue = "testConditionTrue";

        Variable var1 = new Variable("var1", VariableType.PARAMETER, DataType.INTEGER);
        Variable var2 = new Variable("var2", VariableType.PARAMETER, DataType.INTEGER);


        List<Variable> variables = new ArrayList<Variable>();
        Rule ruleFalse = createRule(ruleNameFalse, DataType.INTEGER, "false", "$var1$ * $var2$", variables);
        Rule ruleTrue = createRule(ruleNameTrue, DataType.INTEGER, "true", "$var1$ * $var2$", variables);

        variables.addAll(Arrays.asList(var1,var2));

        Object[] args = new Object[3];
        args[0] = new Recording();
        args[1] = 10;
        args[2] = 42;

        Resolver risolutore = new Resolver(variables);
        Object res = null;
        try {
            res = risolutore.execute( ruleFalse, args);
        } catch (RulesException e) {
            LOGGER.error("Error executing " + ruleFalse.getName() , e);
            throw e;
        }
        LOGGER.debug("Result:" + res);
        Assert.assertNull(res);


        try {
            res = risolutore.execute(ruleTrue, args);
        } catch (RulesException e) {
            LOGGER.error("Error executing " + ruleTrue.getName() , e);
            throw e;
        }

        LOGGER.debug("Result:" + res);
        Assert.assertEquals(420,res);
    }

    @Test
    public void testConditionVar() throws RulesException {
        String ruleName = "testConditionVar";

        Variable var1 = new Variable("var1", VariableType.PARAMETER, DataType.INTEGER);
        Variable var2 = new Variable("var2", VariableType.PARAMETER, DataType.INTEGER);
        Variable cond = new Variable("cond", VariableType.PARAMETER, DataType.BOOL);


        List<Variable> variables = new ArrayList<Variable>();
        Rule rule = createRule(ruleName, DataType.INTEGER, "$cond$", "$var1$ * $var2$", variables);
        variables.addAll(Arrays.asList(var1,var2,cond));

        Resolver risolutore = new Resolver(variables);
        Object res = null;
        try {
            res = risolutore.execute(rule, new Object[]{
                    new Recording(),
                    true,
                    10,
                    42
            });
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(420,res);

            res = risolutore.execute(rule, new Object[]{
                    new Recording(),
                    true,
                    20,
                    5
            });
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(100,res);

            res = risolutore.execute(rule, new Object[]{
                    new Recording(),
                    false,
                    1,
                    1
            });
            LOGGER.debug("Result:" + res);
            Assert.assertNull(res);

        } catch (RulesException e) {
            LOGGER.error("Error executing " + rule.getName() , e);
            throw e;
        }

    }

    @Test
    public void testValueMap() throws RulesException {
        String ruleName = "testValueMap";

        List<Variable> variables = new ArrayList<Variable>();
        Rule rule = createRule(ruleName, DataType.INTEGER, "$cond$", "$var1$ * $var2$", variables);

        Resolver risolutore = new Resolver(variables);
        Object res = null;
        try {
            ValueMap valMap = new ValueMap();
            valMap.put("var1", 10);
            valMap.put("var2", 99);
            valMap.put("cond", true);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(990,res);


            valMap.put("var1", 5);
            valMap.put("var2", 3);
            valMap.put("cond", true);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(15,res);

            valMap.put("var1", 5);
            valMap.put("var2", 3);
            valMap.put("cond", false);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertNull(res);

        } catch (RulesException e) {
            LOGGER.error("Error executing " + rule.getName() , e);
            throw e;
        }

    }

    @Test
    public void testRecursiveRule() throws RulesException {
        String ruleName = "testValueMap";

        List<Variable> variables = new ArrayList<Variable>();
        Rule rule = createRule(ruleName, DataType.INTEGER, "$cond$", "$var1$ * $var2$", variables);

        Resolver risolutore = new Resolver(variables);
        Object res = null;
        try {
            ValueMap valMap = new ValueMap();
            valMap.put("var1", 10);
            valMap.put("var2", 99);
            valMap.put("cond", true);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(990,res);


            valMap.put("var1", 5);
            valMap.put("var2", 3);
            valMap.put("cond", true);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertEquals(15,res);

            valMap.put("var1", 5);
            valMap.put("var2", 3);
            valMap.put("cond", false);
            valMap.add(rule);
            res = risolutore.execute(rule,valMap);
            LOGGER.debug("Result:" + res);
            Assert.assertNull(res);

        } catch (RulesException e) {
            LOGGER.error("Error executing " + rule.getName() , e);
            throw e;
        }

    }

}
