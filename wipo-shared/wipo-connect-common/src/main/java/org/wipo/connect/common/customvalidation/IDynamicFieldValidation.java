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

import java.io.Serializable;

/**
 * The Interface IDynamicFieldValidation.
 */
public interface IDynamicFieldValidation extends Serializable {

    String getTypeCode();

    String getCode();

    Boolean getMandatory();

    Integer getMaxLength();

    Object getValue();
}
