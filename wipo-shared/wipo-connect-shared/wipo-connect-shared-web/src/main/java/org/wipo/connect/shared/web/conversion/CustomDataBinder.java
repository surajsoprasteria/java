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

package org.wipo.connect.shared.web.conversion;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.shared.exchange.utils.NumericConversionUtils;

/**
 * The Class CustomDataBinder.
 */
@ControllerAdvice
public class CustomDataBinder {

    /** The Constant logger. */
    private static final Logger logger = WipoLoggerFactory.getLogger(CustomDataBinder.class);

    /** The date conversion utils. */
    @Autowired
    private DateConversionUtils dateConversionUtils;

    @Autowired
    private NumericConversionUtils numericConversionUtils;

    /**
     * Binder.
     *
     * @param binder
     *            the binder
     */
    @InitBinder
    public void binder(WebDataBinder binder) {

	binder.setAutoGrowCollectionLimit(ConstantUtils.AUTO_GROW_COLLECTION_LIMIT);

	// STRING BNDER - TRIM TO NULL
	StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
	binder.registerCustomEditor(String.class, stringtrimmer);

	// DATE DATA BINDER
	binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		Date date = (Date) getValue();
		String strValue = null;
		strValue = CustomDataBinder.this.dateConversionUtils.formatDate(date);
		return strValue;
	    }

	    @Override
	    public void setAsText(String strValue) {
		try {
		    setValue(CustomDataBinder.this.dateConversionUtils.parseDate(strValue));
		} catch (ParseException e) {
		    logger.error("Error parsing date", e);
		    setValue(null);
		}
	    }
	});

	binder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		BigDecimal value = (BigDecimal) getValue();
		return numericConversionUtils.format(value);
	    }

	    @Override
	    public void setAsText(String strValue) {
		setValue(numericConversionUtils.parseDecimal(strValue));
	    }
	});

	binder.registerCustomEditor(BigInteger.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		BigInteger value = (BigInteger) getValue();
		return numericConversionUtils.format(value);
	    }

	    @Override
	    public void setAsText(String strValue) {
		setValue(numericConversionUtils.parseBigInteger(strValue));
	    }
	});

	binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		Long value = (Long) getValue();
		return numericConversionUtils.format(value);
	    }

	    @Override
	    public void setAsText(String strValue) {
		setValue(numericConversionUtils.parseLong(strValue));
	    }
	});

	binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		Integer value = (Integer) getValue();
		return numericConversionUtils.format(value);
	    }

	    @Override
	    public void setAsText(String strValue) {
		setValue(numericConversionUtils.parseInteger(strValue));
	    }
	});

	binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
	    @Override
	    public String getAsText() {
		Double value = (Double) getValue();
		return numericConversionUtils.format(value);
	    }

	    @Override
	    public void setAsText(String strValue) {
		setValue(numericConversionUtils.parseDouble(strValue));
	    }
	});

    }

}
