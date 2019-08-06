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
package org.wipo.connect.scriptengine.engine;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.slf4j.Logger;
import org.wipo.connect.common.conversion.JacksonObjectMapper;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.scriptengine.ScriptInvoker;
import org.wipo.connect.common.spring.SpringUtils;
import org.wipo.connect.scriptengine.script.Script;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class JSInvoker implements ScriptInvoker {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(JSInvoker.class);

    private static final String JAVA_PARAM_PREFIX = "J_";
    private static final String MAIN_FUNCTION_NAME = "__main";
    private static final String STRINGIFY_FUNCTION_NAME = "__stringify";
    private static final String OBJECT_MAPPER_NAME = "__objMapper";
    private static final String CACHE_NAME = "JSInvoker";

    private final List<String> parameters;
    private final Invocable invocable;
    private final Compilable compilable;
    private Boolean enableCache;
    private CacheManager cacheManager;
    private Cache<String, Object> cache;
    private final String scriptCode;

    private JacksonObjectMapper jacksonObjectMapper;

    public JSInvoker(Script script, boolean convertToJson, List<String> parameters, boolean compileOnly) throws ScriptException {
	this.parameters = parameters;
	ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

	if (jacksonObjectMapper == null) {
	    jacksonObjectMapper = new JacksonObjectMapper();
	}

	if (convertToJson) {
	    engine.put(OBJECT_MAPPER_NAME, jacksonObjectMapper);
	    scriptCode = wrapSourceCodeJson(script.getSourceCode(), parameters);
	} else {
	    scriptCode = wrapSourceCode(script.getSourceCode(), parameters);
	}

	invocable = (Invocable) engine;
	compilable = (Compilable) engine;

	engine.eval(scriptCode);

	enableCache = false;

	synchronized (this) {

	    try {
		cacheManager = SpringUtils.applicationContext.getBean(CacheManager.class);
	    } catch (Exception e) {
		LOGGER.warn("Cannot retrieve cacheManager from spring application context", e);
		CachingProvider provider = Caching.getCachingProvider();
		cacheManager = provider.getCacheManager();
	    }

	    cache = cacheManager.getCache(CACHE_NAME, String.class, Object.class);

	    if (cache == null) {
		CacheConfigurationBuilder<String, Object> cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,
			ResourcePoolsBuilder.newResourcePoolsBuilder()
				.heap(200, EntryUnit.ENTRIES)
				.offheap(100, MemoryUnit.MB))
			.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(60)));

		cache = cacheManager.createCache(CACHE_NAME, Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig));
	    }
	}

    }

    public JSInvoker(Script script, boolean convertToJson, List<String> parameters) throws ScriptException {
	this(script, convertToJson, parameters, false);
    }

    public JSInvoker(Script script, boolean convertToJson, String... parameters) throws ScriptException {
	this(script, convertToJson, Arrays.asList(parameters), false);
    }

    @Override
    public void clearCache() {
	cache.clear();
	LOGGER.debug("Cache cleared: {}", CACHE_NAME);
    }

    private String getBaseScriptTrace(Object[] values) {
	StringBuilder sb = new StringBuilder();
	try {
	    sb.append("--- SCRIPT START ---\n");
	    Integer lineNumber = 1;
	    for (String srcLine : StringUtils.splitPreserveAllTokens(scriptCode, "\n")) {
		sb.append(StringUtils.leftPad(lineNumber.toString(), 5));
		sb.append("|");
		sb.append(srcLine);
		sb.append("\n");
		lineNumber++;
	    }
	    sb.append("--- SCRIPT END ---\n\n");

	    sb.append("--- PARAMETERS START ---\n");
	    if (parameters != null) {
		int i = 0;
		for (String p : parameters) {
		    sb.append("[" + i + "] ");
		    sb.append(p);
		    sb.append(" --> ");
		    if (values != null && i < values.length) {
			String jsonParam = jacksonObjectMapper.writeValueAsString(values[i]);
			sb.append(jsonParam);
			sb.append("\n");
		    }
		    i++;
		}
	    }
	    sb.append("--- PARAMETERS END ---\n\n");

	} catch (Exception e) {
	    LOGGER.error("Error in getBaseScriptTrace", e);
	}

	return sb.toString();
    }

    @Override
    public Boolean getEnableCache() {
	return enableCache;
    }

    private String getErrorExecutionTrace(Object[] values, ScriptException scriptException) {
	StringBuilder sb = new StringBuilder();
	try {

	    sb.append("Error invoking the script:\n");
	    sb.append("--- ERROR START ---\n");
	    sb.append("Message: ");
	    sb.append(scriptException.getMessage());
	    sb.append("\n");

	    sb.append("Line: ");
	    sb.append(scriptException.getLineNumber());
	    sb.append("\n");

	    sb.append("Column: ");
	    sb.append(scriptException.getColumnNumber());
	    sb.append("\n");

	    sb.append("--- ERROR END ---\n\n");

	    sb.append(getBaseScriptTrace(values));

	} catch (Exception e) {
	    LOGGER.error("Error in getErrorExecutionTrace", e);
	}

	return sb.toString();
    }

    @Override
    public List<String> getParameters() {
	return parameters;
    }

    private String getResultExecutionTrace(Object[] values, Object result, boolean fromCache) {
	StringBuilder sb = new StringBuilder();
	try {

	    sb.append("--- RESULT START ---\n");
	    sb.append("result: ");
	    sb.append(jacksonObjectMapper.writeValueAsString(result));
	    sb.append("\n");

	    sb.append("From cache: ");
	    sb.append(fromCache);
	    sb.append("\n");

	    sb.append("--- RESULT END ---\n\n");

	    sb.append(getBaseScriptTrace(values));

	} catch (Exception e) {
	    LOGGER.error("Error in getResultExecutionTrace", e);
	}

	return sb.toString();
    }

    private String getStringifyFunction() {
	return "function " + STRINGIFY_FUNCTION_NAME + "(obj) { return JSON.stringify(obj); }\n";
    }

    @Override
    public Object compile() throws ScriptException {
	CompiledScript compiled = compilable.compile(scriptCode);
	return compiled;
    }

    @Override
    public Object invoke(List<Object> values) throws ScriptException {
	return invoke(values.toArray());
    }

    @Override
    public Object invoke(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invoke(valList);
    }

    @SuppressWarnings("restriction")
    @Override
    public synchronized Object invoke(Object... values) throws ScriptException {
	String key = null;

	try {
	    Object result;
	    boolean fromCache = false;
	    if (enableCache) {
		try {
		    key = DigestUtils.md5Hex(jacksonObjectMapper.writeValueAsBytes(values));
		} catch (JsonProcessingException e) {
		    LOGGER.error("Error getting cache key", e);
		}

		if (key != null && cache.containsKey(key)) {
		    result = cache.get(key);
		    fromCache = true;
		} else {
		    result = invocable.invokeFunction(MAIN_FUNCTION_NAME, values);

		    if (key != null && !(result != null && result instanceof jdk.nashorn.api.scripting.AbstractJSObject)) {
			cache.put(key, result);
		    }
		}
	    } else {
		result = invocable.invokeFunction(MAIN_FUNCTION_NAME, values);
	    }

	    if (LOGGER.isTraceEnabled()) {
		LOGGER.trace(getResultExecutionTrace(values, result, fromCache));
	    }

	    return result;
	} catch (NoSuchMethodException e) {
	    throw new IllegalStateException(e);
	} catch (ScriptException e) {
	    LOGGER.error(getErrorExecutionTrace(values, e));
	    throw e;
	}
    }

    @Override
    public Boolean invokeForBoolean(List<Object> values) throws ScriptException {
	return invokeForBoolean(values.toArray());
    }

    @Override
    public Boolean invokeForBoolean(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForBoolean(valList);
    }

    @Override
    public Boolean invokeForBoolean(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    return null;
	}

	return BooleanUtils.toBooleanObject(result.toString());
    }

    @Override
    public BigDecimal invokeForDecimal(List<Object> values) throws ScriptException {
	return invokeForDecimal(values.toArray());
    }

    @Override
    public BigDecimal invokeForDecimal(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForDecimal(valList);
    }

    @Override
    public BigDecimal invokeForDecimal(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    return null;
	}

	return new BigDecimal(result.toString());
    }

    @Override
    public BigDecimal invokeForNotNullDecimal(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    throw new ScriptException("Value returned by formula is null");
	}

	return new BigDecimal(result.toString());
    }

    @Override
    public BigDecimal invokeForNotNullDecimal(List<Object> values) throws ScriptException {
	Object result = invoke(values.toArray());

	if (result == null) {
	    throw new ScriptException("Value returned by formula is null");
	}

	return new BigDecimal(result.toString());
    }

    @Override
    public Integer invokeForInt(List<Object> values) throws ScriptException {
	return invokeForInt(values.toArray());
    }

    @Override
    public Integer invokeForInt(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForInt(valList);
    }

    @Override
    public Integer invokeForInt(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    return null;
	}

	return new BigDecimal(result.toString()).intValue();
    }

    public String invokeForJsonString(List<Object> values) throws ScriptException {
	return invokeForJsonString(values.toArray());
    }

    public String invokeForJsonString(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForJsonString(valList);
    }

    public String invokeForJsonString(Object... values) throws ScriptException {
	Object result = invoke(values);
	if (result == null) {
	    return null;
	}

	String strObj = null;
	try {

	    strObj = invocable.invokeFunction(STRINGIFY_FUNCTION_NAME, result).toString();

	} catch (NoSuchMethodException e) {
	    throw new IllegalStateException(e);
	} catch (ScriptException e) {
	    LOGGER.error(getErrorExecutionTrace(values, e));
	    throw e;
	}

	return strObj;
    }

    @Override
    public Long invokeForLong(List<Object> values) throws ScriptException {
	return invokeForLong(values.toArray());
    }

    @Override
    public Long invokeForLong(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForLong(valList);
    }

    @Override
    public Long invokeForLong(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    return null;
	}

	return new BigDecimal(result.toString()).longValue();
    }

    @Override
    public Map<String, Object> invokeForMap(List<Object> values) throws ScriptException {
	return invokeForMap(values.toArray());
    }

    @Override
    public Map<String, Object> invokeForMap(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForMap(valList);
    }

    @Override
    public Map<String, Object> invokeForMap(Object... values) throws ScriptException {
	String result = invokeForJsonString(values);
	if (result == null) {
	    return null;
	}

	try {
	    return jacksonObjectMapper.readValue(result, new TypeReference<Map<String, String>>() {
	    });
	} catch (Exception e) {
	    throw new IllegalStateException(e);
	}

    }

    @Override
    public <T> T invokeForObject(Class<T> clazz, List<Object> values) throws ScriptException {
	return invokeForObject(clazz, values.toArray());
    }

    @Override
    public <T> T invokeForObject(Class<T> clazz, Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForObject(clazz, valList);
    }

    @Override
    public <T> T invokeForObject(Class<T> clazz, Object... values) throws ScriptException {
	String result = invokeForJsonString(values);
	if (result == null) {
	    return null;
	}

	try {
	    return jacksonObjectMapper.readValue(result, clazz);
	} catch (Exception e) {
	    throw new IllegalStateException(e);
	}

    }

    @Override
    public String invokeForString(List<Object> values) throws ScriptException {
	return invokeForString(values.toArray());
    }

    @Override
    public String invokeForString(Map<String, Object> values) throws ScriptException {
	List<Object> valList = new ArrayList<>();
	for (String p : parameters) {
	    valList.add(values.get(p));
	}
	return invokeForString(valList);
    }

    @Override
    public String invokeForString(Object... values) throws ScriptException {
	Object result = invoke(values);

	if (result == null) {
	    return null;
	}

	return result.toString();
    }

    @Override
    public void setEnableCache(Boolean enableCache) {
	this.enableCache = enableCache;
    }

    private String wrapSourceCode(String sourceCode, List<String> parameters) {
	StringBuilder sb = new StringBuilder();
	sb.append(getStringifyFunction());
	sb.append("function " + MAIN_FUNCTION_NAME + "(");
	sb.append(StringUtils.join(parameters, " , "));
	sb.append("){\n return ");
	sb.append(sourceCode);
	sb.append(";\n}");

	return sb.toString();
    }

    private String wrapSourceCodeJson(String sourceCode, List<String> parameters) {
	List<String> jParameters = parameters.stream().map(p -> JAVA_PARAM_PREFIX + p).collect(Collectors.toList());

	StringBuilder sb = new StringBuilder();
	sb.append(getStringifyFunction());
	sb.append("function " + MAIN_FUNCTION_NAME + "(");
	sb.append(StringUtils.join(jParameters, ", "));
	sb.append("){\n ");
	for (String p : parameters) {
	    sb.append("\tvar ");
	    sb.append(p);
	    sb.append(" = JSON.parse(" + OBJECT_MAPPER_NAME + ".writeValueAsString(" + JAVA_PARAM_PREFIX + p + "));\n");
	}
	sb.append("\treturn ");
	sb.append(sourceCode);
	sb.append(";\n}");

	return sb.toString();
    }

}
