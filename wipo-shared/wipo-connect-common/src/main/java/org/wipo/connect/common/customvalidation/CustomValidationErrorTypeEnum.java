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

/**
 * The Enum CustomValidationErrorTypeEnum.
 */
public enum CustomValidationErrorTypeEnum {

    /**
     * The mandatory.
     */
    MANDATORY,
    
    /**
     * The max length.
     */
    MAX_LENGTH,
    
    /**
     * The min value.
     */
    MIN_VALUE,
    
    /**
     * The max value.
     */
    MAX_VALUE,
    
    /**
     * The range.
     */
    RANGE,
    
    /**
     * The regular expression.
     */
    REGULAR_EXPRESSION,
    
    /**
     * The file size.
     */
    FILE_SIZE;

}
