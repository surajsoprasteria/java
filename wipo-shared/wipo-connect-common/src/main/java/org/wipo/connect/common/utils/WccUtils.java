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

package org.wipo.connect.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class WccUtils.
 */
public class WccUtils {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WccUtils.class);

    private WccUtils() {
	super();
    }

    /**
     * Gets the method name.
     *
     * @return the method name
     */
    public static String getMethodName() {
	return getMethodName(2);
    }

    /**
     * Get the method name for a depth in call stack. <br />
     * Utility function
     *
     * @param depth
     *            depth in the call stack (0 means current method, 1 means call method, ...)
     * @return method name
     */
    public static String getMethodName(final int depth) {
	final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	return ste[1 + depth].getMethodName();
    }

    /**
     * Util method to copy the map values into the field of an object.
     *
     * @param <T>
     *            the generic type
     * @param obj
     *            the obj
     * @param src
     *            the src
     * @return the t
     * @throws WccException
     *             the wcc exception
     */
    public static <T extends Serializable> T mapToObj(T obj, Map<String, Object> src) throws WccException {

	for (String key : src.keySet()) {
	    try {
		// BeanUtils.setProperty(obj, key, src.get(key));
		PropertyUtils.setProperty(obj, key, src.get(key));
	    } catch (Exception e) {
		LOGGER.warn("objToMap - " + e.getMessage());
		throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	    }
	}

	return obj;
    }

    public static Map<String, Object> objToMapNoException(Object obj) {
	try {
	    return objToMap(obj, new ArrayList<String>(), false);
	} catch (WccException e) {
	    return new HashMap<>();
	}
    }

    public static Map<String, Object> objToMap(Object obj) throws WccException {
	return objToMap(obj, new ArrayList<String>(), false);
    }

    public static Map<String, Object> objToMapEscapeChar(Object obj) throws WccException {
	return objToMap(obj, new ArrayList<String>(), true);
    }

    /**
     * Obj to map.
     *
     * @param obj
     *            the obj
     * @param exclutionList
     *            the exclution list
     * @return the map
     * @throws WccException
     *             the wcc exception
     */
    public static Map<String, Object> objToMap(Object obj, Collection<String> exclutionList, boolean escapeChar) throws WccException {
	Map<String, Object> result = new HashMap<>();
	try {
	    BeanInfo info = Introspector.getBeanInfo(obj.getClass());
	    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		if (!"class".equalsIgnoreCase(pd.getName()) && !exclutionList.contains(pd.getName())) {
		    Method reader = pd.getReadMethod();
		    if (reader != null) {
			String key = pd.getName();
			Object value = reader.invoke(obj);
			boolean isCollection = Collection.class.isAssignableFrom(pd.getPropertyType());

			if (value != null && isCollection && !((Collection<?>) value).isEmpty()) {
			    Object sampleObj = ((Collection<?>) value).iterator().next();
			    boolean skip = (sampleObj instanceof String) || (sampleObj instanceof Boolean) || (sampleObj instanceof Number) || (sampleObj instanceof Enum);
			    if (!skip) {
				List<Map<String, Object>> list = new ArrayList<>();
				for (Object listItem : (Collection<?>) value) {
				    list.add(objToMap(listItem, exclutionList, escapeChar));
				}
				value = list;
			    } else {
				if (escapeChar && value instanceof String) {
				    value = StringEscapeUtils.escapeHtml4(value.toString());
				}
			    }

			} else if (value != null && escapeChar && value instanceof Exception) {
			    value = StringEscapeUtils.escapeHtml4(((Exception) value).getMessage());
			}

			result.put(key, value);
		    }

		}
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	}
	return result;
    }

    public static Boolean objectFildsAreNull(Object obj, Collection<String> exclutionList) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	boolean isNull = true;
	if (obj != null) {
	    for (Method method : obj.getClass().getDeclaredMethods()) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0 && method.getReturnType() != void.class
			&& (method.getName().startsWith("get") && !exclutionList.contains(method.getName()) || method.getName().startsWith("is"))) {
		    Object value = method.invoke(obj);
		    if (value != null) {
			isNull = false;
		    }
		}
	    }
	}
	return isNull;
    }

    public static Boolean objectFildsAreNull(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	boolean isNull = true;
	if (obj != null) {
	    for (Method method : obj.getClass().getDeclaredMethods()) {
		if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0 && method.getReturnType() != void.class
			&& (method.getName().startsWith("get") || method.getName().startsWith("is"))) {
		    Object value = method.invoke(obj);
		    if (value != null) {
			isNull = false;
		    }
		}
	    }
	}
	return isNull;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static boolean isObjectEmpty(Object object) throws WccException {

	// null
	if (object == null) {
	    return true;
	}

	// String
	else if (object instanceof String) {
	    return StringUtils.isEmpty(StringUtils.trim((String) object));
	}

	// List
	else if (object instanceof List) {
	    boolean allEntriesStillEmpty = true;
	    final Iterator<Object> iter = ((List) object).iterator();
	    while (allEntriesStillEmpty && iter.hasNext()) {
		final Object listEntry = iter.next();
		allEntriesStillEmpty = isObjectEmpty(listEntry);
	    }
	    return allEntriesStillEmpty;
	}

	// arbitrary Object
	else {
	    try {
		boolean allPropertiesStillEmpty = true;
		final Map<String, Object> properties = PropertyUtils.describe(object);
		final Iterator<Entry<String, Object>> iter = properties.entrySet().iterator();
		while (allPropertiesStillEmpty && iter.hasNext()) {
		    final Entry<String, Object> entry = iter.next();
		    final String key = entry.getKey();
		    final Object value = entry.getValue();

		    // ignore the getClass() property
		    if ("class".equals(key))
			continue;

		    allPropertiesStillEmpty = isObjectEmpty(value);
		}
		return allPropertiesStillEmpty;
	    } catch (Exception e) {
		throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	    }
	}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setIdsToNull(Object object) throws WccException {
	// List
	try {
	    if (object instanceof List) {
		final Iterator<Object> iter = ((List) object).iterator();
		while (iter.hasNext()) {
		    final Object listEntry = iter.next();
		    Class<? extends Object> entryClass = listEntry.getClass();
		    // Object newInsance = entryClass.newInstance();
		    Method method = entryClass.getMethod("setId", Long.class);
		    method.invoke(listEntry, new Object[] { null });
		}
	    }
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.APPLICATION_ERROR, e);
	}

    }

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
	for (E e : enumClass.getEnumConstants()) {
	    if (e.name().equalsIgnoreCase(value)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Normalize filename.
     *
     * @param filename
     *            the filename
     * @return the string
     */
    public static String normalizeFilename(String filename) {
	return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    /**
     * Gets the number of decimal places.
     *
     * @param bigDecimal
     *            the big decimal
     * @return the number of decimal places
     */
    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	return Math.max(0, bigDecimal.stripTrailingZeros().scale());
    }

    /**
     * Checks if is valid date.
     *
     * @param dateToCheck
     *            the date to check
     * @return true, if is valid date
     */
    public static boolean isValidDate(String dateToCheck) {
	final String dateFormat = "^[0-9]{8}?";

	int year;
	int month;
	int day;

	if (!Pattern.matches(dateFormat, dateToCheck)) {
	    return false;
	}

	year = Integer.parseInt(dateToCheck.substring(0, 4));
	month = Integer.parseInt(dateToCheck.substring(4, 6));
	day = Integer.parseInt(dateToCheck.substring(6, 8));

	if (month < 1 || month > 12) {
	    return false;
	}

	if (day < 1) {
	    return false;
	}

	if (month == 2 && isLeapYear(year) && day > 29) {
	    return false;
	}

	if (month == 2 && !isLeapYear(year) && day > 28) {
	    return false;
	}

	if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
	    return false;
	}

	if (day > 31) {
	    return false;
	}

	return true;
    }

    /**
     * Checks if is leap year.
     *
     * @param year
     *            the year
     * @return true, if is leap year
     */
    public static boolean isLeapYear(int year) {
	return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

}
