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
package org.wipo.connect.common.cache;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * The Class CustomKeyGenerator.
 */
@Component
public class CustomKeyGenerator implements KeyGenerator {
    // @Override
    // public Object generate(Object target, Method method, Object... params) {
    // StringBuilder sb = new StringBuilder();
    // sb.append(target.getClass().getName());
    // sb.append(method.getName());
    // for (Object param : params) {
    // if(param!=null){
    // sb.append(param.toString());
    // }
    // }
    // return sb.toString();
    /**
     * The Constant NO_PARAM_KEY.
     */
    // }
    public static final int NO_PARAM_KEY = 0;

    /**
     * The Constant NULL_PARAM_KEY.
     */
    public static final int NULL_PARAM_KEY = 53;

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    @Override
    public Object generate(Object target, Method method, Object... params) {

	StringBuilder sb = new StringBuilder();
	sb.append(target.getClass().getName());
	sb.append(method.getName());

	if (params.length == 0) {

	    return sb.append(NO_PARAM_KEY).toString();
	}

	if (params.length == 1 && null == params[0]) {
	    sb.append(NULL_PARAM_KEY);
	}

	if (params.length == 1 && null != params[0] && isWrapperType(params[0].getClass())) {
	    return sb.append(params[0]).toString();
	}

	int hashCode = 17;
	for (Object object : params) {
	    hashCode = 31 * hashCode + (object == null ? NULL_PARAM_KEY : object.hashCode());
	}

	return sb.append(hashCode).toString();
    }

    /**
     * Checks if is wrapper type.
     *
     * @param clazz
     *            the clazz
     * @return true, if is wrapper type
     */
    public static boolean isWrapperType(Class<?> clazz) {
	return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
	Set<Class<?>> ret = new HashSet<Class<?>>();
	ret.add(Boolean.class);
	ret.add(Character.class);
	ret.add(Byte.class);
	ret.add(Short.class);
	ret.add(Integer.class);
	ret.add(Long.class);
	ret.add(Float.class);
	ret.add(Double.class);
	ret.add(Void.class);

	return ret;
    }
}
