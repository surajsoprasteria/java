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
import java.math.BigDecimal;

/**
 * The Interface ICustomValidation.
 */
public interface ICustomValidation extends Serializable {

    Boolean getForced();

    String getCode();

    String getType();

    Boolean getInternational();

    Boolean getMandatory();

    Integer getMaxLength();

    BigDecimal getMaxValue();

    BigDecimal getMinValue();

    String getRegularExpression();

    Long getMaxFileSize();

    String getPossibleValues();

}
