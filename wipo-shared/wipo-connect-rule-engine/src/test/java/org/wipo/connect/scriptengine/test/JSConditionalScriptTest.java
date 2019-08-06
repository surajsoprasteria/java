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
package org.wipo.connect.scriptengine.test;

import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wipo.connect.scriptengine.engine.JSInvoker;
import org.wipo.connect.scriptengine.script.JSConditionalScript;

public class JSConditionalScriptTest {

    private String conditionA;
    private String conditionB;
    private String thenScript;
    private String elseScript;

    private String[] parameters;

    @Before
    public void setUp() throws Exception {
	conditionA = "v1 > v2";
	conditionB = "(function(){return v1 > v2})();";

	thenScript = "Math.pow(v1, v2);";
	elseScript = "(function(){return v2-v1+42;})();";
	parameters = new String[] { "v1", "v2" };
    }

    @Test
    public void testJSConditionalScriptScriptScriptScript() throws ScriptException {
	JSConditionalScript scriptA = new JSConditionalScript(conditionA, thenScript, elseScript);
	Assert.assertNotNull(scriptA);
	JSInvoker invokerA = new JSInvoker(scriptA, true, parameters);
	Long resA1 = invokerA.invokeForLong(3, 2);
	Assert.assertNotNull(resA1);
	Assert.assertEquals(Long.valueOf(9), resA1);
	Long resA2 = invokerA.invokeForLong(5, 10);
	Assert.assertNotNull(resA2);
	Assert.assertEquals(Long.valueOf(47), resA2);

	JSConditionalScript scriptB = new JSConditionalScript(conditionB, thenScript, elseScript);
	Assert.assertNotNull(scriptB);
	JSInvoker invokerB = new JSInvoker(scriptB, true, parameters);
	Long resB1 = invokerB.invokeForLong(3, 2);
	Assert.assertNotNull(resB1);
	Assert.assertEquals(Long.valueOf(9), resB1);
	Long resB2 = invokerA.invokeForLong(5, 10);
	Assert.assertNotNull(resB2);
	Assert.assertEquals(Long.valueOf(47), resB2);

	Assert.assertEquals(resA1, resB1);
	Assert.assertEquals(resA2, resB2);
    }

    @Test
    public void testGetCondition() {
	JSConditionalScript script = new JSConditionalScript(conditionA, thenScript, elseScript);
	Assert.assertNotNull(script);
	Assert.assertNotNull(script.getCondition());
	Assert.assertEquals(conditionA, script.getCondition().getSourceCode());
    }

    @Test
    public void testGetThenScript() {
	JSConditionalScript script = new JSConditionalScript(conditionA, thenScript, elseScript);
	Assert.assertNotNull(script);
	Assert.assertNotNull(script.getThenScript());
	Assert.assertEquals(thenScript, script.getThenScript().getSourceCode());
    }

    @Test
    public void testGetElseScript() {
	JSConditionalScript script = new JSConditionalScript(conditionA, thenScript, elseScript);
	Assert.assertNotNull(script);
	Assert.assertNotNull(script.getElseScript());
	Assert.assertEquals(elseScript, script.getElseScript().getSourceCode());
    }

    @Test
    public void testGetSourceCode() {
	JSConditionalScript script = new JSConditionalScript(conditionA, thenScript, elseScript);
	Assert.assertNotNull(script);
	Assert.assertNotNull(script.getCondition());
	Assert.assertNotNull(script.getThenScript());
	Assert.assertNotNull(script.getElseScript());
	Assert.assertNotNull(script.getSourceCode());
	Assert.assertTrue(StringUtils.contains(script.getSourceCode(), conditionA));
	Assert.assertTrue(StringUtils.contains(script.getSourceCode(), thenScript));
	Assert.assertTrue(StringUtils.contains(script.getSourceCode(), elseScript));
    }

}
