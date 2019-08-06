/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.common.caseconverter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class CaseConverter.
 */
public class CaseConverter {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CaseConverter.class);

    private CaseConverter() {
	super();
    }

    /**
     * Convert case.
     *
     * @param obj
     *                           the obj
     * @param caseConversion
     *                           the case conversion
     * @param packageList
     *                           the package list
     * @throws IllegalAccessException
     *                                    the illegal access exception
     */
    public static void convertCase(final Object obj, final CaseConversionEnum caseConversion, String[] packageList) throws IllegalAccessException {
	Set<Integer> processedObjects = new HashSet<>();
	convertCase(obj, caseConversion, packageList, processedObjects);
    }

    private static void convertCase(final Collection<?> objList, final CaseConversionEnum caseConversion, String[] packageList, Set<Integer> processedObjects) throws IllegalAccessException {
	for (Object obj : objList) {
	    convertCase(obj, caseConversion, packageList, processedObjects);
	}
    }

    private static void convertCase(final Object obj, final CaseConversionEnum caseConversion, String[] packageList, Set<Integer> processedObjects) throws IllegalAccessException {

	// in case of null, skip
	if (obj == null) {
	    return;
	}

	if (obj.getClass().isEnum()) {
	    return;
	}

	if (caseConversion.equals(CaseConversionEnum.NONE)) {
	    return;
	}

	if (obj instanceof Collection<?>) {
	    convertCase((Collection<?>) obj, caseConversion, packageList, processedObjects);
	    return;
	}

	if (processedObjects.contains(obj.hashCode())) {
	    return;
	}

	processedObjects.add(obj.hashCode());

	List<Field> fieldsToConvert = FieldUtils.getFieldsListWithAnnotation(obj.getClass(), CaseConversion.class);

	// loops on found fields
	for (Field f : fieldsToConvert) {
	    String fieldName = obj.getClass().getName() + "." + f.getName();
	    Object fieldValue = FieldUtils.readField(f, obj, true);

	    if (fieldValue == null) {
		continue;
	    }

	    LOGGER.trace("CaseConverter: " + fieldName + " -> " + caseConversion);

	    if (fieldValue instanceof String) {
		String strValue = convertValue((String) fieldValue, caseConversion);
		FieldUtils.writeField(f, obj, strValue, true);
	    } else {
		// convertCase(fieldValue, caseConversion, packageList);
		throw new IllegalArgumentException("The field " + fieldName + " is annotated with @CaseConversion but is not a string");
	    }
	}

	List<Field> fields = FieldUtils.getAllFieldsList(obj.getClass());
	for (Field f : fields) {
	    String fieldName = obj.getClass().getName() + "." + f.getName();
	    Object fieldValue = FieldUtils.readField(f, obj, true);

	    if (fieldValue == null) {
		continue;
	    }

	    if (!StringUtils.startsWithAny(fieldValue.getClass().getName(), packageList) && !(fieldValue instanceof Collection<?>)) {
		continue;
	    }

	    LOGGER.trace("CaseConverter: " + fieldName + " -> go deep");

	    convertCase(fieldValue, caseConversion, packageList, processedObjects);
	}

    }

    private static String convertValue(final String value, final CaseConversionEnum caseConversion) {
	switch (caseConversion) {
	    case NONE:
		return value;
	    case ALL_CAPITAL:
		return WordUtils.capitalizeFully(value);
	    case FIRST_CAPITAL:
		return StringUtils.capitalize(StringUtils.lowerCase(value));
	    case LOWERCASE:
		return StringUtils.lowerCase(value);
	    case UPPERCASE:
		// never convert HtmlEntity. Ex: &amp; -> &AMP;
		return StringUtils.upperCase(StringEscapeUtils.unescapeHtml4(value));
	    default:
		throw new IllegalArgumentException("Invalid caseConversion: " + caseConversion);
	}
    }

}
