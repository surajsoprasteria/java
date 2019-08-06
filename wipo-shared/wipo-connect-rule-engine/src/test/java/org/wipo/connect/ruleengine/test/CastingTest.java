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

import java.math.BigDecimal;
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

public class CastingTest{

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CastingTest.class);


    @Test
    public void castingTest() throws RulesException {
    	final String ruleName = "castingTestFormula";
		final String ruleCondition = "true";
		final String ruleFormula = "$var$";
		final List<Variable> variables = new ArrayList<>();
		final Rule rule = createRule(ruleName , DataType.DOUBLE, ruleCondition, ruleFormula, variables);
		final Resolver resolver = new Resolver(variables);

		ValueMap valMap;
		Object objRes;
		BigDecimal res;

		valMap = new ValueMap();
		valMap.put("$var$", Integer.valueOf(42));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("Integer",BigDecimal.valueOf(42), res);


		valMap = new ValueMap();
		valMap.put("$var$", Long.valueOf(42));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("Long",BigDecimal.valueOf(42), res);

		valMap = new ValueMap();
		valMap.put("$var$", Double.valueOf(42.42));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("Double",BigDecimal.valueOf(42.42), res);


		valMap = new ValueMap();
		valMap.put("$var$", "42.42");
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("String Double",BigDecimal.valueOf(42.42), res);

		valMap = new ValueMap();
		valMap.put("$var$", "42");
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("String Integer",BigDecimal.valueOf(42), res);


		valMap = new ValueMap();
		valMap.put("$var$", BigDecimal.valueOf(42));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + res);
		Assert.assertEquals("BigDecimal",BigDecimal.valueOf(42), res);





    }


}
