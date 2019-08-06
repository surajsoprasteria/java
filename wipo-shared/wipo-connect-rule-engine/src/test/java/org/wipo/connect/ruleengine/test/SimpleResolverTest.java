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

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.rules.SimpleResolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.utils.Utils;

public class SimpleResolverTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(SimpleResolverTest.class);

    @Test
    public void testSimpleResolver() throws RulesException {
	Rule rule = Utils.createRule("testSimpleResolver", "$v1$ + $v2$");
	SimpleResolver resolver = new SimpleResolver(rule);

	ValueMap valMap = new ValueMap();
	valMap.put("v1", 40);
	valMap.put("$v2$", 2);
	Object objOut = resolver.execute(valMap);
	Integer result = (Integer) objOut;

	Assert.assertEquals("sum test", Integer.valueOf(42), result);
    }

    @Test
    public void testSimpleResolver_loop() throws RulesException {
	Rule rule = Utils.createRule("testSimpleResolver", "$v1$ + $v2$");
	SimpleResolver resolver = new SimpleResolver(rule);

	for (int i = 0; i < 1_000; i++) {
	    Object objOut = resolver.execute("v1", 42, "v2", i);
	    Integer result = (Integer) objOut;

	    Integer check = result - i;

	    LOGGER.debug("testSimpleResolver_loop - {} - {}", i, check);
	    Assert.assertEquals("loop test " + i, Integer.valueOf(42), check);
	}

    }

}
