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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wipo.connect.scriptengine.engine.JSInvoker;
import org.wipo.connect.scriptengine.script.Script;
import org.wipo.connect.scriptengine.script.SimpleScript;
import org.wipo.connect.scriptengine.test.model.ResultDTO;

public class JSInvokerTest {

    private Script script;
    private String[] parameters;
    private JSInvoker invoker;

    @Before
    public void setUp() throws Exception {
	script = new SimpleScript("v1 + Math.sqrt(v2)");
	parameters = new String[] { "v1", "v2" };
	invoker = new JSInvoker(script, true, parameters);
    }

    @Test
    public void testJSInvokerScriptStringArray() throws ScriptException {
	JSInvoker invoker = new JSInvoker(script, true, parameters);
	Assert.assertNotNull(invoker);
    }

    @Test
    public void testJSInvokerScriptListOfString() throws ScriptException {
	JSInvoker invoker = new JSInvoker(script, true, Arrays.asList(parameters));
	Assert.assertNotNull(invoker);
    }

    @Test
    public void testInvokeListOfObject() throws NoSuchMethodException, ScriptException {
	Object res = invoker.invoke(Arrays.asList(40, 4));
	Assert.assertNotNull(res);
	Assert.assertEquals(new Double(42), res);
    }

    @Test
    public void testInvokeObjectArray() throws NoSuchMethodException, ScriptException {
	Object res = invoker.invoke(40, 4);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Double(42), res);
    }

    @Test
    public void testInvokeForIntListOfObject() throws NoSuchMethodException, ScriptException {
	Integer res = invoker.invokeForInt(Arrays.asList(40, 4));
	Assert.assertNotNull(res);
	Assert.assertEquals(new Integer(42), res);
    }

    @Test
    public void testInvokeForIntObjectArray() throws NoSuchMethodException, ScriptException {
	Integer res = invoker.invokeForInt(40, 4);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Integer(42), res);
    }

    @Test
    public void testInvokeForLongListOfObject() throws NoSuchMethodException, ScriptException {
	Long res = invoker.invokeForLong(Arrays.asList(40, 4));
	Assert.assertNotNull(res);
	Assert.assertEquals(new Long(42), res);
    }

    @Test
    public void testInvokeForLongObjectArray() throws NoSuchMethodException, ScriptException {
	Long res = invoker.invokeForLong(40, 4);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Long(42), res);
    }

    @Test
    public void testInvokeForDecimalListOfObject() throws NoSuchMethodException, ScriptException {
	BigDecimal res = invoker.invokeForDecimal(Arrays.asList(40, 4));
	Assert.assertNotNull(res);
	Assert.assertTrue(res.compareTo(new BigDecimal("42")) == 0);
    }

    @Test
    public void testInvokeForDecimalObjectArray() throws NoSuchMethodException, ScriptException {
	BigDecimal res = invoker.invokeForDecimal(40, 4);
	Assert.assertNotNull(res);
	Assert.assertTrue(res.compareTo(new BigDecimal("42")) == 0);
    }

    @Test
    public void testInvokeForStringListOfObject() throws NoSuchMethodException, ScriptException {
	String res = invoker.invokeForString(Arrays.asList(40, 4));
	Assert.assertNotNull(res);
	Assert.assertEquals("42.0", res);
    }

    @Test
    public void testInvokeForStringObjectArray() throws NoSuchMethodException, ScriptException {
	String res = invoker.invokeForString(40, 4);
	Assert.assertNotNull(res);
	Assert.assertEquals("42.0", res);
    }

    @Test
    public void testInvokeForBooleanListOfObject() throws NoSuchMethodException, ScriptException {
	SimpleScript script = new SimpleScript("(v1 == v2) && v3");
	String[] parameters = new String[] { "v1", "v2", "v3" };
	JSInvoker invoker = new JSInvoker(script, true, parameters);

	Boolean res = invoker.invokeForBoolean(42, new BigDecimal("42.0"), true);
	Assert.assertNotNull(res);
	Assert.assertTrue(res);
    }

    @Test
    public void testInvokeForBooleanObjectArray() throws NoSuchMethodException, ScriptException {
	SimpleScript script = new SimpleScript("(v1 == v2) && v3");
	String[] parameters = new String[] { "v1", "v2", "v3" };
	JSInvoker invoker = new JSInvoker(script, true, parameters);

	Boolean res = invoker.invokeForBoolean(Arrays.asList(42, new BigDecimal("42.0"), true));
	Assert.assertNotNull(res);
	Assert.assertTrue(res);
    }

    @Test
    public void testNullParams() throws NoSuchMethodException, ScriptException {
	SimpleScript script = new SimpleScript("(v1 == v2) && v3");
	String[] parameters = new String[] { "v1", "v2", "v3" };
	JSInvoker invoker = new JSInvoker(script, true, parameters);

	Boolean res = invoker.invokeForBoolean(Arrays.asList(42, new BigDecimal("42.0"), null));
	Assert.assertNull(res);
    }

    @Test
    public void testGetParameters() {
	List<String> parmList = invoker.getParameters();
	Assert.assertNotNull(parmList);
	Assert.assertEquals(2, parmList.size());
	Assert.assertTrue(parmList.contains("v1"));
	Assert.assertTrue(parmList.contains("v2"));
    }

    @Test
    public void parseObject() throws ScriptException {

	SimpleScript script = new SimpleScript("obj.v1 + Math.sqrt(obj.v2)");
	String[] parameters = new String[] { "obj" };
	JSInvoker invoker = new JSInvoker(script, true, parameters);

	Integer res = invoker.invokeForInt(new MyTestDTO());
	Assert.assertNotNull(res);
	Assert.assertEquals(Integer.valueOf(42), res);
    }

    @Test
    public void outObj() throws ScriptException {

	SimpleScript script = new SimpleScript("{'code':1, 'message':'This is the message!'}");
	JSInvoker invoker = new JSInvoker(script, true);

	Map<String, Object> outMap = invoker.invokeForMap();
	Assert.assertNotNull(outMap);

	ResultDTO outDto = invoker.invokeForObject(ResultDTO.class);
	Assert.assertNotNull(outDto);
    }

    @Test
    public void testInvokeMapOfStringObject() throws NoSuchMethodException, ScriptException {
	Map<String, Object> map = new HashMap<>();
	map.put("v2", 4);
	map.put("v1", 40);
	map.put("XXX", -3);
	Object res = invoker.invoke(map);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Double(42), res);
    }

    @Test
    public void testInvokeForBooleanMapOfStringObject() throws NoSuchMethodException, ScriptException {
	SimpleScript script = new SimpleScript("(v1 == v2) && v3");
	String[] parameters = new String[] { "v1", "v2", "v3" };
	JSInvoker invoker = new JSInvoker(script, true, parameters);

	Map<String, Object> map = new HashMap<>();
	map.put("v2", new BigDecimal("42.0"));
	map.put("v1", 42);
	map.put("XXX", -3);
	map.put("v3", true);

	Boolean res = invoker.invokeForBoolean(map);
	Assert.assertNotNull(res);
	Assert.assertTrue(res);

    }

    @Test
    public void testInvokeForDecimalMapOfStringObject() throws NoSuchMethodException, ScriptException {
	Map<String, Object> map = new HashMap<>();
	map.put("v2", 4);
	map.put("v1", 40);
	map.put("XXX", -3);
	BigDecimal res = invoker.invokeForDecimal(map);
	Assert.assertNotNull(res);
	Assert.assertTrue(res.compareTo(new BigDecimal("42")) == 0);
    }

    @Test
    public void testInvokeForIntMapOfStringObject() throws NoSuchMethodException, ScriptException {
	Map<String, Object> map = new HashMap<>();
	map.put("v2", 4);
	map.put("v1", 40);
	map.put("XXX", -3);
	Integer res = invoker.invokeForInt(map);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Integer(42), res);
    }

    @Test
    public void testInvokeForLongMapOfStringObject() throws NoSuchMethodException, ScriptException {
	Map<String, Object> map = new HashMap<>();
	map.put("v2", 4);
	map.put("v1", 40);
	map.put("XXX", -3);
	Long res = invoker.invokeForLong(map);
	Assert.assertNotNull(res);
	Assert.assertEquals(new Long(42), res);
    }

    @Test
    public void testInvokeForStringMapOfStringObject() throws NoSuchMethodException, ScriptException {
	Map<String, Object> map = new HashMap<>();
	map.put("v2", 4);
	map.put("v1", 40);
	map.put("XXX", -3);
	String res = invoker.invokeForString(map);
	Assert.assertNotNull(res);
	Assert.assertEquals("42.0", res);
    }

    public class MyTestDTO {
	private String string = "string";
	private Long longInteger = 4L;
	private Integer integer = 2;
	private Date date = new Date();
	private BigDecimal decimal = new BigDecimal("42.42");

	private BigDecimal v1 = new BigDecimal("40");
	private Long v2 = 4L;

	public String getString() {
	    return string;
	}

	public void setString(String string) {
	    this.string = string;
	}

	public Long getLongInteger() {
	    return longInteger;
	}

	public void setLongInteger(Long longInteger) {
	    this.longInteger = longInteger;
	}

	public Integer getInteger() {
	    return integer;
	}

	public void setInteger(Integer integer) {
	    this.integer = integer;
	}

	public Date getDate() {
	    return date;
	}

	public void setDate(Date date) {
	    this.date = date;
	}

	public BigDecimal getDecimal() {
	    return decimal;
	}

	public void setDecimal(BigDecimal decimal) {
	    this.decimal = decimal;
	}

	public BigDecimal getV1() {
	    return v1;
	}

	public void setV1(BigDecimal v1) {
	    this.v1 = v1;
	}

	public Long getV2() {
	    return v2;
	}

	public void setV2(Long v2) {
	    this.v2 = v2;
	}
    }

}
