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
package org.wipo.connect.common.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.WccUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A factory for creating WccException objects.
 */
public class WccExceptionFactory {

    /**
     * The Constant CLASS_TYPE_FIELD.
     */
    public static final String CLASS_TYPE_FIELD = "_class";
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(WccExceptionFactory.class);

    private WccExceptionFactory(){
        super();
    }

    /**
     * Gets the wcc exception.
     *
     * @param jsonString the json string
     * @param sourceException the source exception
     * @return the wcc exception
     */
    public static WccException getWccException(String jsonString, Exception sourceException){
        WccException outObj;
        String resonseJavaType;
        ObjectMapper mapper;

        try {
            mapper = new ObjectMapper();
            LOGGER.trace("Source exception", sourceException);

            LOGGER.debug(WccUtils.getMethodName() + " - REST RESPONSE -" + jsonString);

            JavaType mapType = mapper.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class);
            Map<String,Object> respMap = mapper.readValue(jsonString, mapType);
            if(respMap.containsKey(CLASS_TYPE_FIELD)){
                resonseJavaType = respMap.get(CLASS_TYPE_FIELD).toString();
            }else{
                resonseJavaType = WccException.class.getName();
            }


            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            outObj = (WccException) mapper.readValue(jsonString, Class.forName(resonseJavaType));



        } catch (Exception e) {
            LOGGER.error("Error deserializing rest exeption", e);
            outObj = new WccException(WccExceptionCodeEnum.UNKNOW_ERROR, e);
        }

        return outObj;
    }

}
