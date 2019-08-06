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



import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;



/**
 * The Interface IResolver.
 */
public interface IResolver {

    /**
     * Execute.
     *
     * @param input
     *            the input
     * @param rule
     *            the rule
     * @param args
     *            the args
     * @return the object
     * @throws RulesException
     *             the rules exception
     */
    public Object execute(ValueMap input, Rule rule, Object[] args)
            throws RulesException;

}
