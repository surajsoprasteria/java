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



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.wipo.connect.shared.web.utils.SharedConfigUtils;



/**
 * The Class DateConversionUtils.
 */
@Component
public class DateConversionUtils {

    /** The message source. */
    @Autowired
    private MessageSource messageSource;



    /**
     * Format date.
     *
     * @param value
     *            the value
     * @return the string
     */
    public String formatDate(Date value) {
        String strValue = null;
        if (value != null) {
            Locale locale = LocaleContextHolder.getLocale();
            String pattern = this.messageSource.getMessage(
                    SharedConfigUtils.CODE_DATE_FORMAT, null, locale);
            SimpleDateFormat df = new SimpleDateFormat(pattern, locale);

            strValue = df.format(value);
        }
        return strValue;
    }



    /**
     * Parses the date.
     *
     * @param strValue
     *            the str value
     * @return the date
     * @throws ParseException
     *             the parse exception
     */
    public Date parseDate(String strValue) throws ParseException {
        Date date = null;
        if (StringUtils.isNotEmpty(strValue)) {
            Locale locale = LocaleContextHolder.getLocale();
            String pattern = this.messageSource.getMessage(
                    SharedConfigUtils.CODE_DATE_FORMAT, null, locale);
            SimpleDateFormat df = new SimpleDateFormat(pattern, locale);

            date = df.parse(strValue);
        }
        return date;
    }

}
