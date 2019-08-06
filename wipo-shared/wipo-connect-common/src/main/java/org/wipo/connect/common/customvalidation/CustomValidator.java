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

package org.wipo.connect.common.customvalidation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.springframework.util.ReflectionUtils;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.exception.WccValidationException;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class CustomValidator.
 */
public class CustomValidator {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CustomValidator.class);

    private CustomValidator() {
	super();
    }

    /**
     * Executes the custom validation on the object using the given set of rules.
     *
     * @param obj
     *                  - The object to validate
     * @param rules
     *                  - The set of rules
     * @return true, if successful
     * @throws WccException
     *                          - WccValidationException is thrown in case of validation error
     */
    public static boolean validate(final Object obj, final Collection<? extends ICustomValidation> rules) throws WccException {
	return validate(obj, rules, true, CustomValidationTypeEnum.LOCAL, false);
    }

    /**
     * Executes the custom validation on the object using the given set of rules.
     *
     * @param obj
     *                  - The object to validate
     * @param rules
     *                  - The set of rules
     * @return true, if successful
     * @throws WccException
     *                          - WccValidationException is thrown in case of validation error
     */
    public static boolean validate(final Object obj, final Map<String, ? extends ICustomValidation> rules) throws WccException {
	return validate(obj, rules, true, CustomValidationTypeEnum.LOCAL);
    }

    public static boolean validate(final Object obj, final Collection<? extends ICustomValidation> rules, final Boolean useInnerValidation,
	    final CustomValidationTypeEnum validationType) throws WccException {
	return validate(obj, rules, useInnerValidation, validationType, false);
    }

    /**
     * Executes the custom validation on the object using the given set of rules.
     *
     * @param obj
     *                               - The object to validate
     * @param rules
     *                               - The set of rules
     * @param useInnerValidation
     *                               - If true the validation is extended to children objects
     * @param validationType
     *                               LOCAL - INTERNATIONAL
     * @return true, if successful
     * @throws WccException
     *                          - WccValidationException is thrown in case of validation error
     */
    public static boolean validate(final Object obj, final Collection<? extends ICustomValidation> rules, final Boolean useInnerValidation,
	    final CustomValidationTypeEnum validationType, final boolean noValidationException) throws WccException {
	Map<String, ICustomValidation> ruleMap = new LinkedHashMap<>(rules.size());
	try {
	    // tranforms the collection into a map
	    for (ICustomValidation r : rules) {
		ruleMap.put(r.getCode(), r);
	    }
	    return validate(obj, ruleMap, useInnerValidation, validationType);
	} catch (WccValidationException e) {
	    if (noValidationException) {
		return false;
	    }
	    throw e;
	} catch (WccException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    /**
     * Executes the custom validation on the object using the given set of rules.
     *
     * @param obj
     *                               - The object to validate
     * @param rules
     *                               - The set of rules
     * @param useInnerValidation
     *                               - If true the validation is extended to children objects
     * @param validationType
     *                               LOCAL - INTERNATIONAL
     * @return true, if successful
     * @throws WccException
     *                          - WccValidationException is thrown in case of validation error
     */
    public static boolean validate(final Object obj, final Map<String, ? extends ICustomValidation> rules, final Boolean useInnerValidation, final CustomValidationTypeEnum validationType)
	    throws WccException {
	List<CustomValidationError> errorList = null;
	try {
	    // executes local validation
	    errorList = executeValidation(obj, rules, useInnerValidation, validationType);

	    // if the error list is not empty log and throw an exception
	    if (errorList != null && !errorList.isEmpty()) {
		StringBuilder errorMessageList = new StringBuilder();
		errorMessageList.append("Custom validation errors:\n");
		for (CustomValidationError em : errorList) {
		    errorMessageList.append(em.toString() + "\n");
		}
		LOGGER.debug(errorMessageList.toString());
		throw new WccValidationException(errorList, validationType);
	    }

	    return true;
	} catch (WccValidationException e) {
	    throw e;
	} catch (Exception e) {
	    throw new WccException(WccExceptionCodeEnum.GENERIC_ERROR, e);
	}
    }

    @SuppressWarnings("unchecked")
    private static List<CustomValidationError> executeValidation(final Object obj, final Map<String, ? extends ICustomValidation> rules, final Boolean useInnerValidation,
	    final CustomValidationTypeEnum validationType) throws IllegalAccessException {
	List<CustomValidationError> errorList = new ArrayList<>();
	// in case of null, skip
	if (obj == null) {
	    return errorList;
	}

	// if the object is a collection
	if (obj instanceof Collection) {

	    // itereates and invoke valication for each item
	    for (Object item : (Collection<Object>) obj) {
		errorList.addAll(executeValidation(item, rules, useInnerValidation, validationType));
	    }
	} else {

	    // TODO:if l'invocazione se obj di del getter execute delete ritorna
	    // true skip this object
	    boolean doValidate = true;
	    Method method = ReflectionUtils.findMethod(obj.getClass(), "getExecDelete");
	    if (method != null) {
		Object val = ReflectionUtils.invokeMethod(method, obj);
		if (val != null && val.equals(true)) {
		    doValidate = false;
		}
	    }

	    if (doValidate) {
		// gets all the fields annotated with @CustomValidatedField
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(obj.getClass(), CustomValidatedField.class);

		// loops on found fields
		validationLoop: for (Field f : fields) {
		    CustomValidatedField annotation = f.getAnnotation(CustomValidatedField.class);

		    String fieldName = obj.getClass().getSimpleName() + "." + f.getName();
		    String fieldCode = annotation.fieldCode();
		    boolean innerValidation = annotation.innerValidation();
		    boolean dynamicFields = annotation.dynamicFields();
		    Object fieldValue = FieldUtils.readField(f, obj, true);
		    ExclusionRule[] exclusionRules = annotation.exclusionRules();

		    for (ExclusionRule exclusionRule : exclusionRules) {

			try {
			    String field = exclusionRule.field();
			    String value = exclusionRule.value();
			    if (field != null) {

				Field excludeField = obj.getClass().getDeclaredField(field);
				String excludeFieldValue = (String) FieldUtils.readField(excludeField, obj, true);

				if (StringUtils.equals(excludeFieldValue, value)) {
				    LOGGER.trace("Valid exclusion rule found for field {}. Validation skip", fieldName);
				    continue validationLoop;
				}
			    }

			} catch (Exception e) {
			    LOGGER.warn("Exclusion rule not valid: {} = {}", exclusionRule.field(), exclusionRule.value());
			}

		    }

		    // log the current field
		    LOGGER.trace("Validating: {} - code:{} - inner:{}", new Object[] { fieldName, fieldCode, innerValidation });

		    if (dynamicFields) {
			Map<String, ? extends IDynamicFieldValidation> dynFields = (Map<String, ? extends IDynamicFieldValidation>) fieldValue;

			if (fieldValue != null) {
			    switch (validationType) {
				case LOCAL:
				    for (IDynamicFieldValidation dyn : dynFields.values()) {
					CustomValidationError error;
					error = validateFieldLocal(dyn);
					if (error != null) {
					    errorList.add(error);
					}
				    }
				    break;

				case INTERNATIONAL:
				    // Dynamic fields won't be validated
				    // internationally
				    LOGGER.trace("Dynamic fields won't be validated internationally");
				    break;

				case FORMAL:
				    for (IDynamicFieldValidation dyn : dynFields.values()) {
					CustomValidationError error;
					error = validateFieldFormal(dyn);
					if (error != null) {
					    errorList.add(error);
					}
				    }
				    break;
				default:
				    throw new IllegalArgumentException("Unknown validation type: " + validationType);
			    }
			}

		    } else if (useInnerValidation && annotation.innerValidation()) {
			// if the field is annotated with option
			// "innerValidation" executes the validation deep in the
			// object
			errorList.addAll(executeValidation(fieldValue, rules, useInnerValidation, validationType));

		    } else if (StringUtils.isNotBlank(fieldCode) && rules.containsKey(fieldCode)) {
			// if the annotation code is not empty and it's
			// contained into the map executes the field validation
			ICustomValidation rule = rules.get(fieldCode);
			CustomValidationError error;

			// executes the specific validation type
			switch (validationType) {
			    case LOCAL:
				error = validateFieldLocal(fieldValue, fieldName, fieldCode, rule);
				break;

			    case INTERNATIONAL:
				error = validateFieldInternational(fieldValue, fieldName, fieldCode, rule);
				break;

			    case FORMAL:
				error = validateFieldFormal(fieldValue, fieldName, fieldCode, rule);
				break;
			    default:
				throw new IllegalArgumentException("Unknown validation type: " + validationType);
			}

			// if an error is returned the validation has failed.
			if (error != null) {
			    errorList.add(error);
			}
		    } else if (StringUtils.isNotBlank(fieldCode) && !rules.containsKey(fieldCode)) {
			// Annotation field code is not contained in the rule
			// map.
			// WRONG MAPPING ???

			LOGGER.warn("Validation rule not found for {} - code:{}", new Object[] { fieldName, fieldCode });
		    } else {
			LOGGER.warn("CustomValidator - Unhandled case - [fieldName:{}, fieldCode:{}, innerValidation:{}, dynamicFields:{}]",
				new Object[] { fieldName, fieldCode, innerValidation, dynamicFields });
		    }
		}
	    }
	}

	return errorList;
    }

    private static CustomValidationError validateFieldLocal(final IDynamicFieldValidation dynamicField) {
	CustomValidationError cvError = null;
	if (dynamicField == null) {
	    return null;
	}

	// check the validation rule and executes validation methsds
	if (BooleanUtils.isTrue(dynamicField.getMandatory()) && !checkMandatory(dynamicField.getValue())) {
	    // The field is mandatory but the value is empty / null
	    cvError = new CustomValidationError(dynamicField.getCode(), dynamicField.getCode(), CustomValidationErrorTypeEnum.MANDATORY, dynamicField.getValue());

	} else if (dynamicField.getMaxLength() != null && !checkMaxLength(dynamicField.getValue() == null ? null : dynamicField.getValue().toString(), dynamicField.getMaxLength())) {
	    // The length of the string exceeds the limit
	    cvError = new CustomValidationError(dynamicField.getCode(), dynamicField.getCode(), CustomValidationErrorTypeEnum.MAX_LENGTH, dynamicField.getMaxLength(), dynamicField.getValue());

	}

	return cvError;
    }

    private static CustomValidationError validateFieldFormal(final IDynamicFieldValidation dynamicField) {
	CustomValidationError cvError = null;

	if (dynamicField == null) {
	    return null;
	}

	// check the validation rule and executes validation methsds
	if (dynamicField.getMaxLength() != null && !checkMaxLength((String) dynamicField.getValue(), dynamicField.getMaxLength())) {
	    // The length of the string exceeds the limit
	    cvError = new CustomValidationError(dynamicField.getCode(), dynamicField.getCode(), CustomValidationErrorTypeEnum.MAX_LENGTH, dynamicField.getMaxLength(), dynamicField.getValue());
	}

	return cvError;
    }

    private static CustomValidationError validateFieldLocal(final Object fieldValue, final String fieldName, final String fieldCode, final ICustomValidation rule) {
	CustomValidationError cvError = null;

	// check the validation rule and executes validation methsds
	if (BooleanUtils.isTrue(rule.getMandatory()) && !checkMandatory(fieldValue)) {
	    // The field is mandatory but the value is empty / null
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MANDATORY, fieldValue);

	} else if (rule.getMaxLength() != null && !checkMaxLength((String) fieldValue, rule.getMaxLength())) {
	    // The length of the string exceeds the limit
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MAX_LENGTH, rule.getMaxLength(), fieldValue);

	} else if (rule.getMinValue() != null && rule.getMaxValue() != null && !checkRange((Number) fieldValue, rule.getMinValue(), rule.getMaxValue())) {
	    // The the value is out of range
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.RANGE, new Number[] { rule.getMinValue(), rule.getMaxValue() }, fieldValue);

	} else if (rule.getMinValue() != null && !checkMinValue((Number) fieldValue, rule.getMinValue())) {
	    // The the value is too small
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MIN_VALUE, rule.getMinValue(), fieldValue);

	} else if (rule.getMaxValue() != null && !checkMaxValue((Number) fieldValue, rule.getMaxValue())) {
	    // the value is too big
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MAX_VALUE, rule.getMaxValue(), fieldValue);

	} else if (StringUtils.isNotEmpty(rule.getRegularExpression()) && !checkRegularExpression((String) fieldValue, rule.getRegularExpression())) {
	    // String doesn't match the regex
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.REGULAR_EXPRESSION, rule.getRegularExpression(), fieldValue);

	} else if (rule.getMaxFileSize() != null && !checkFileSize((byte[]) fieldValue, rule.getMaxFileSize())) {
	    // file too big
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.FILE_SIZE, rule.getMaxFileSize(), ((byte[]) fieldValue).length);

	}

	return cvError;
    }

    private static CustomValidationError validateFieldFormal(final Object fieldValue, final String fieldName, final String fieldCode, final ICustomValidation rule) {
	CustomValidationError cvError = null;

	if (BooleanUtils.isTrue(rule.getMandatory()) && BooleanUtils.isTrue(rule.getForced()) && !checkMandatory(fieldValue)) {
	    // The field is mandatory but the value is empty / null
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MANDATORY, true, null, fieldValue);

	} else if (rule.getMaxLength() != null && !checkMaxLength((String) fieldValue, rule.getMaxLength())) {
	    // The length of the string exceeds the limit
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MAX_LENGTH, rule.getMaxLength(), fieldValue);

	} else if (rule.getMinValue() != null && rule.getMaxValue() != null && !checkRange((Number) fieldValue, rule.getMinValue(), rule.getMaxValue())) {
	    // The the value is out of range
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.RANGE, new Number[] { rule.getMinValue(), rule.getMaxValue() }, fieldValue);

	} else if (rule.getMinValue() != null && !checkMinValue((Number) fieldValue, rule.getMinValue())) {
	    // The the value is too small
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MIN_VALUE, rule.getMinValue(), fieldValue);

	} else if (rule.getMaxValue() != null && !checkMaxValue((Number) fieldValue, rule.getMaxValue())) {
	    // the value is too big
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MAX_VALUE, rule.getMaxValue(), fieldValue);

	} else if (StringUtils.isNotEmpty(rule.getRegularExpression()) && !checkRegularExpression((String) fieldValue, rule.getRegularExpression())) {
	    // String doesn't match the regex
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.REGULAR_EXPRESSION, rule.getRegularExpression(), fieldValue);
	} else if (rule.getMaxFileSize() != null && !checkFileSize((byte[]) fieldValue, rule.getMaxFileSize())) {
	    // file too big
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.FILE_SIZE, rule.getMaxFileSize(), ((byte[]) fieldValue).length);

	}

	return cvError;
    }

    private static CustomValidationError validateFieldInternational(final Object fieldValue, final String fieldName, final String fieldCode, final ICustomValidation rule) {
	CustomValidationError cvError = null;

	// check the validation rule and executes validation methsds
	if (BooleanUtils.isTrue(rule.getInternational()) && !checkMandatory(fieldValue)) {
	    // The field is mandatory but the value is empty / null
	    cvError = new CustomValidationError(fieldName, fieldCode, CustomValidationErrorTypeEnum.MANDATORY, fieldValue);
	}

	return cvError;
    }

    @SuppressWarnings("unchecked")
    private static boolean checkMandatory(final Object fieldValue) {
	if (fieldValue == null) {
	    return false;
	} else if (fieldValue instanceof String) {
	    return StringUtils.isNotBlank((String) fieldValue);
	} else if (fieldValue instanceof Collection) {
	    return !((Collection<? extends Object>) fieldValue).isEmpty();
	} else {
	    return true;
	}
    }

    private static boolean checkMaxLength(final String fieldValue, final Integer maxLength) {
	return StringUtils.isEmpty(fieldValue) || StringUtils.length(fieldValue) <= maxLength.intValue();
    }

    private static boolean checkRange(final Number fieldValue, final Number minValue, final Number maxValue) {
	return fieldValue == null || (fieldValue.doubleValue() >= minValue.doubleValue()) && (fieldValue.doubleValue() <= maxValue.doubleValue());
    }

    private static boolean checkMinValue(final Number fieldValue, final Number minValue) {
	return fieldValue == null || fieldValue.doubleValue() >= minValue.doubleValue();
    }

    private static boolean checkMaxValue(final Number fieldValue, final Number maxValue) {
	return fieldValue == null || fieldValue.doubleValue() <= maxValue.doubleValue();
    }

    private static boolean checkRegularExpression(final String fieldValue, final String regularExpression) {
	return StringUtils.isEmpty(fieldValue) || fieldValue.matches("(?i)" + regularExpression);
    }

    private static boolean checkFileSize(final byte[] fieldValue, final Long fileSize) {
	return fieldValue == null || fieldValue.length <= fileSize.longValue();
    }

}
