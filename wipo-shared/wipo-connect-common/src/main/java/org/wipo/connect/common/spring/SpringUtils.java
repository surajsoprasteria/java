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
package org.wipo.connect.common.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {

    @Autowired
    private ApplicationContext ctx;

    public static ApplicationContext applicationContext;

    @PostConstruct
    public void postConstruct() {
	SpringUtils.applicationContext = ctx;
    }

}
