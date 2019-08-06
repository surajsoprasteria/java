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

package org.wipo.connect.enginecompare;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.rules.SimpleResolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.utils.Utils;
import org.wipo.connect.scriptengine.engine.JSInvoker;
import org.wipo.connect.scriptengine.script.Script;
import org.wipo.connect.scriptengine.script.SimpleScript;

public class ScriptLoopCompareTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ScriptLoopCompareTest.class);

    private static final int LOOP = 1_000;
    private static final int MOD = 5;

    @Test
    public void loop_base() throws RulesException {
	for (int i = 0; i < LOOP; i++) {
	    int v2 = i % MOD;
	    Integer result = 42 + v2;
	    Integer check = result - v2;

	    LOGGER.debug("loop_base - {} - {}", i, check);
	    Assert.assertEquals("loop_base" + i, Integer.valueOf(42), check);
	}

    }

    @Test
    public void loop_ruleengine() throws RulesException {
	Rule rule = Utils.createRule("testSimpleResolver", "$v1$ + $v2$");
	SimpleResolver resolver = new SimpleResolver(rule);

	for (int i = 0; i < LOOP; i++) {
	    int v2 = i % MOD;
	    Object objOut = resolver.execute("v1", 42, "v2", v2);
	    Integer result = (Integer) objOut;

	    Integer check = result - v2;

	    LOGGER.debug("loop_ruleengine - {} - {}", i, check);
	    Assert.assertEquals("loop_ruleengine" + i, Integer.valueOf(42), check);
	}

    }

    @Test
    public void loop_scriptinvoker_cache() throws ScriptException {
	Script script = new SimpleScript("v1 + v2");
	JSInvoker invoker = new JSInvoker(script, true, "v1", "v2");
	invoker.setEnableCache(true);

	for (int i = 0; i < LOOP; i++) {
	    int v2 = i % MOD;
	    Integer result = invoker.invokeForInt(42, v2);

	    Integer check = result - v2;

	    LOGGER.debug("loop_scriptinvoker_cache - {} - {}", i, check);
	    Assert.assertEquals("loop_scriptinvoker_cache" + i, Integer.valueOf(42), check);
	}
    }

    @Test
    public void loop_scriptinvoker_nocache() throws ScriptException {
	Script script = new SimpleScript("v1 + v2");
	JSInvoker invoker = new JSInvoker(script, true, "v1", "v2");
	invoker.setEnableCache(false);

	for (int i = 0; i < LOOP; i++) {
	    int v2 = i % MOD;
	    Integer result = invoker.invokeForInt(42, v2);

	    Integer check = result - v2;

	    LOGGER.debug("loop_scriptinvoker_nocache - {} - {}", i, check);
	    Assert.assertEquals("loop_scriptinvoker_nocache" + i, Integer.valueOf(42), check);
	}
    }

}
