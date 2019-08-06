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



package org.wipo.connect.ruleengine.rules;



import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.wipo.connect.ruleengine.exception.MissingVariableExceptionHandler;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.ValueMap;



/**
 * The Class MissingVariableHandler.
 */
public class MissingVariableHandler implements MissingVariableExceptionHandler {


    @Override
    public void execute(Map<String, Object> missingVariables, ValueMap values,
            String espressione) throws RulesException {

        if (missingVariables != null) {
            for (Entry<String, Object> variable : missingVariables.entrySet()) {

                String key = variable.getKey();
                values.put(key, BigDecimal.ZERO);

            }
        }

    }

}
