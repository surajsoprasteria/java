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



package org.wipo.connect.ruleengine.exception;



import java.util.Map;

import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.ValueMap;



/**
 * The Interface MissingVariableExceptionHandler.
 */
public interface MissingVariableExceptionHandler {

    /**
     * Execute.
     *
     * @param missingVariables
     *            the missing variables
     * @param values
     *            the values
     * @param espressione
     *            the espressione
     * @throws RulesException
     *             the rules exception
     */
    public void execute(Map<String, Object> missingVariables, ValueMap values,
            String espressione) throws RulesException;

}
