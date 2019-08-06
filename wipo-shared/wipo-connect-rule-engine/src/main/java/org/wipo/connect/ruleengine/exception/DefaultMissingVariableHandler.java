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
import java.util.Map.Entry;

import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.ValueMap;



/**
 * The Class DefaultMissingVariableHandler.
 */
public class DefaultMissingVariableHandler implements
        MissingVariableExceptionHandler {


    @Override
    public void execute(Map<String, Object> missingVariables, ValueMap values,
            String espressione) throws RulesException {

        StringBuilder builder = new StringBuilder();

        if (missingVariables != null) {
            for (Entry<String, Object> variable : missingVariables.entrySet()) {

                builder.append(" ");
                builder.append(variable);

            }
        }

        throw new RulesException("Uninitialized variables: "
                + builder.toString());

    }

}
