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



package org.wipo.connect.shared.exchange.utils;



import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wipo.connect.common.conversion.AbstractNumericConversionUtils;
import org.wipo.connect.common.dto.INumberFormatParam;
import org.wipo.connect.shared.exchange.business.ParametersManagementBusiness;



/**
 * The Class NumericConversionUtils.
 */
@Component
public class NumericConversionUtils extends AbstractNumericConversionUtils{

	private static final Logger LOGGER = WipoLoggerFactory.getLogger(NumericConversionUtils.class);

    @Autowired
    private ParametersManagementBusiness parametersManagementBusinessImpl;


    @Override
	public void init(){
    	try{
    		LOGGER.debug("Initializing NumericConversionUtils");
    		INumberFormatParam numericFormatParams = null;
    		 try {
    			numericFormatParams = parametersManagementBusinessImpl.findNumberFormat();
    			super.init(numericFormatParams);
    		    } catch (Exception e) {
    			super.init(numericFormatParams);
    		    }
    	}catch (Exception e) {
    		throw new IllegalStateException("Error initializing NumericConversionUtils", e);
    	}
    }

}
