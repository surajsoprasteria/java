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
import java.util.Arrays;
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
import org.wipo.connect.ruleengine.test.model.Account;
import org.wipo.connect.ruleengine.test.model.AccountGroup;

public class ClosureFormulaTest{

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ClosureFormulaTest.class);


    @Test
    public void closureTest() throws RulesException {
    	final String ruleName = "closureTestFormula";

		final String ruleCondition = "true";

		final String ruleFormula = "{"
				+ "if($var1$ == $var2$){"
				+ "return $var1$ * $var2$;"
				+ " }else{ "
				+ "return $var1$ + $var2$"
				+ "}}()";
		final List<Variable> variables = new ArrayList<>();
		final Rule rule = createRule(ruleName , DataType.DOUBLE, ruleCondition, ruleFormula, variables);
		final Resolver resolver = new Resolver(variables);

		ValueMap valMap;
		Object objRes;
		BigDecimal res;

		valMap = new ValueMap();
		valMap.put("$var1$", Integer.valueOf(200));
		valMap.put("$var2$", Integer.valueOf(200));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + objRes);
		Assert.assertEquals("Case 1",BigDecimal.valueOf(40000), res);

		valMap = new ValueMap();
		valMap.put("$var1$", Integer.valueOf(40));
		valMap.put("$var2$", Integer.valueOf(2));
		objRes = resolver.execute(rule, valMap);
		res = new BigDecimal(objRes.toString());
		LOGGER.debug("RES:" + objRes);
		Assert.assertEquals("Case 2",BigDecimal.valueOf(42), res);

    }

    @Test
    public void closureAdvancedTest() throws RulesException {
    	final String ruleName = "closureAdvancedTest";
		final String ruleCondition = "true";

		final String ruleFormula =
				"{                                    " +
				"	int countActive = 0;              " +
				"	$group$.accounts.each{ account -> " +
				"		if(account.active == true){   " +
				"			countActive++;            " +
				"		};                            " +
				"	};                                " +
				"	return countActive;               " +
				"}()                                  ";


		final List<Variable> variables = new ArrayList<>();
		final Rule rule = createRule(ruleName , DataType.TEXT, ruleCondition, ruleFormula, variables);
		final Resolver resolver = new Resolver(variables);

		ValueMap valMap;
		Object objRes;
		Integer res;

		valMap = new ValueMap();
		valMap.put("$group$", new AccountGroup("Group_001", Arrays.asList(
				new Account("User 01", "user01@gmail.com", true),
				new Account("User 02", "user02@gmail.com", false),
				new Account("User 03", "user03@gmail.com", true),
				new Account("User 04", "user04@gmail.com", false),
				new Account("User 05", "user05@gmail.com", true),
				new Account("User 06", "user06@gmail.com", true)
				)));
		objRes = resolver.execute(rule, valMap);
		LOGGER.debug("RES:" + objRes);
		res = (Integer) objRes;
		Assert.assertEquals("Active accounts",Integer.valueOf(4), res);

    }


}
