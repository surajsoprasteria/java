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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.scriptengine.engine.JSInvoker;
import org.wipo.connect.scriptengine.script.JSExternalScript;
import org.wipo.connect.scriptengine.script.Script;
import org.wipo.connect.scriptengine.script.SimpleScript;

public class JSExternalScriptTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(JSExternalScriptTest.class);

    private String extScript1;
    private String extScript2;
    private String baseScript1;
    private String baseScript2;

    @Before
    public void setUp() throws Exception {
	extScript1 = "function foo(f){ return f * -1;}";
	extScript2 = "function bar(b){ return b * b * b;}";
	baseScript1 = "foo(-5) * bar(2) + 2";
	baseScript2 = "replaceAll('00 11 22 33', '11', 'XX')";
    }

    @Test
    public void testJSExternalScriptScriptStringArray() throws ScriptException {
	Script baseScript = new SimpleScript(baseScript1);
	Script mainScript = new JSExternalScript(baseScript, extScript1, extScript2);
	JSInvoker invoker = new JSInvoker(mainScript, true);
	Integer res = invoker.invokeForInt();
	Assert.assertEquals(new Integer(42), res);
    }

    @Test
    public void testJSExternalScriptScriptReaderArray() throws IOException, ScriptException {
	Script baseScript = new SimpleScript(baseScript2);
	Reader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/external-functions.js")));
	Script mainScript = new JSExternalScript(baseScript, reader);
	JSInvoker invoker = new JSInvoker(mainScript, true);
	String res = invoker.invokeForString();
	Assert.assertEquals("00 XX 22 33", res);
    }

    @Test
    public void testGetSourceCode() {
	Script baseScript = new SimpleScript(baseScript1);
	Script mainScript = new JSExternalScript(baseScript, extScript1, extScript2);

	Assert.assertNotNull(mainScript.getSourceCode());
	Assert.assertTrue(StringUtils.contains(mainScript.getSourceCode(), baseScript1));
	Assert.assertTrue(StringUtils.contains(mainScript.getSourceCode(), extScript1));
	Assert.assertTrue(StringUtils.contains(mainScript.getSourceCode(), extScript2));
    }

    @Test
    public void testMissingScript() throws ScriptException {
	Script baseScript = new SimpleScript(baseScript1);
	Script mainScript = new JSExternalScript(baseScript, extScript1);

	boolean error = false;
	try {
	    new JSInvoker(mainScript, true);
	    JSInvoker invoker = new JSInvoker(mainScript, true);
	    invoker.invoke();
	} catch (ScriptException e) {
	    LOGGER.error("Error", e);
	    error = true;
	}
	Assert.assertTrue(error);
    }

}
