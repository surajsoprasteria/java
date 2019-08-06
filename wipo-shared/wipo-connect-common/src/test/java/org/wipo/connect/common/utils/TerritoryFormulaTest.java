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
package org.wipo.connect.common.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TerritoryFormulaTest {

    @Test
    public void createFormula() {
	Set<String> temp = new HashSet<>();
	temp.add("IT");
	temp.add("FR");
	temp.add("EN");
	temp.add("EU");
	temp.add("DE");

	System.out.println(StringUtils.join("+", StringUtils.join(temp, "+")));
    }
}
