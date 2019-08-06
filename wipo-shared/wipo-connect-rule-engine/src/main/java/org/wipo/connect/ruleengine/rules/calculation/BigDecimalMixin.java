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



package org.wipo.connect.ruleengine.rules.calculation;



import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 * The Class BigDecimalMixin.
 */
public class BigDecimalMixin {

    /**
     * Sets the scale.
     *
     * @param number
     *            the number
     * @param newScale
     *            the new scale
     * @param roundingModeCode
     *            the rounding mode code
     * @return the big decimal
     */
    public BigDecimal setScale(BigDecimal number, int newScale,
            String roundingModeCode) {

        RoundingMode roundingMode = RoundingMode.valueOf(roundingModeCode);

        return number.setScale(newScale, roundingMode);
    }

}
