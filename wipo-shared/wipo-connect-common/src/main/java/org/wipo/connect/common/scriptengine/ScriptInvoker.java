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
package org.wipo.connect.common.scriptengine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

public interface ScriptInvoker {

    Object compile() throws ScriptException;

    Object invoke(Map<String, Object> values) throws ScriptException;

    Object invoke(List<Object> values) throws ScriptException;

    Object invoke(Object... values) throws ScriptException;

    Integer invokeForInt(Map<String, Object> values) throws ScriptException;

    Integer invokeForInt(List<Object> values) throws ScriptException;

    Integer invokeForInt(Object... values) throws ScriptException;

    Long invokeForLong(Map<String, Object> values) throws ScriptException;

    Long invokeForLong(List<Object> values) throws ScriptException;

    Long invokeForLong(Object... values) throws ScriptException;

    BigDecimal invokeForDecimal(Map<String, Object> values) throws ScriptException;

    BigDecimal invokeForDecimal(List<Object> values) throws ScriptException;

    BigDecimal invokeForDecimal(Object... values) throws ScriptException;

    BigDecimal invokeForNotNullDecimal(Object... values) throws ScriptException;

    BigDecimal invokeForNotNullDecimal(List<Object> values) throws ScriptException;

    String invokeForString(Map<String, Object> values) throws ScriptException;

    String invokeForString(List<Object> values) throws ScriptException;

    String invokeForString(Object... values) throws ScriptException;

    Boolean invokeForBoolean(Map<String, Object> values) throws ScriptException;

    Boolean invokeForBoolean(Object... values) throws ScriptException;

    Boolean invokeForBoolean(List<Object> values) throws ScriptException;

    Map<String, Object> invokeForMap(Map<String, Object> values) throws ScriptException;

    Map<String, Object> invokeForMap(Object... values) throws ScriptException;

    Map<String, Object> invokeForMap(List<Object> values) throws ScriptException;

    <T> T invokeForObject(Class<T> clazz, Map<String, Object> values) throws ScriptException;

    <T> T invokeForObject(Class<T> clazz, Object... values) throws ScriptException;

    <T> T invokeForObject(Class<T> clazz, List<Object> values) throws ScriptException;

    List<String> getParameters();

    void clearCache();

    Boolean getEnableCache();

    void setEnableCache(Boolean enableCache);

}