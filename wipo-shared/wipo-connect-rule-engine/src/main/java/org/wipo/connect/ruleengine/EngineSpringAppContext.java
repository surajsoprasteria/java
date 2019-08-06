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



package org.wipo.connect.ruleengine;



import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



/**
 * The Class EngineSpringAppContext.
 */
@Component
public class EngineSpringAppContext implements ApplicationContextAware {

    /** The context. */
    private static ApplicationContext CONTEXT;

    /** The Constant logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(EngineSpringAppContext.class);



    /**
     * Gets the bean.
     *
     * @param <T>
     *            the generic type
     * @param clazz
     *            the clazz
     * @return the bean
     */
    public static <T> Object getBean(Class<T> clazz) {
        if (CONTEXT == null) {
            LOGGER.warn("SpringAppContext CONTEXT null");
        }
        return CONTEXT.getBean(clazz);
    }



    /**
     * This is about the same as context.getBean("beanName"), except it has its
     * own static handle to the Spring context, so calling this method
     * statically will give access to the beans by name in the Spring
     * application context. As in the context.getBean("beanName") call, the
     * caller must cast to the appropriate target class. If the bean does not
     * exist, then a Runtime error will be thrown.
     *
     * @param beanName
     *            the name of the bean to get.
     * @return an Object reference to the named bean.
     */
    public static Object getBean(String beanName) {
        if (CONTEXT == null) {
            LOGGER.warn("SpringAppContext CONTEXT null");
        }
        return CONTEXT.getBean(beanName);
    }



    /**
     * This method is called from within the ApplicationContext once it is done
     * starting up, it will stick a reference to itself into this bean.
     *
     * @param context
     *            a reference to the ApplicationContext.
     * @throws BeansException
     *             the beans exception
     */

    @Override
    @SuppressWarnings("squid:S2696")
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        CONTEXT = context;
    }
}
