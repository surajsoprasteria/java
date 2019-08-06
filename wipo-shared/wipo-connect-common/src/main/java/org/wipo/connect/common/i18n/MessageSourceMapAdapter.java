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

package org.wipo.connect.common.i18n;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.wipo.connect.common.conversion.JacksonObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The Class MessageSourceMapAdapter.
 */
@Component
public class MessageSourceMapAdapter implements Map<String, String> {

    private CustomReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @Override
    public void clear() {
	throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
	return true;
    }

    @Override
    public boolean containsValue(Object value) {
	throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
	throw new UnsupportedOperationException();
    }

    @Override
    public String get(Object key) {
	Locale locale = LocaleContextHolder.getLocale();
	return this.messageSource.getMessage(String.valueOf(key), null, locale);
    }

    public MessageSource getMessageSource() {
	return this.messageSource;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

    @Override
    public Set<String> keySet() {
	return messageSource.getMessageKeys(LocaleContextHolder.getLocale(), false);
    }

    @Override
    public String put(String key, String value) {
	throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
	throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
	throw new UnsupportedOperationException();
    }

    public void setMessageSource(CustomReloadableResourceBundleMessageSource messageSource) {
	this.messageSource = messageSource;
    }

    @Override
    public int size() {
	throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
	throw new UnsupportedOperationException();
    }

    public String getJson() throws JsonProcessingException {
	return jacksonObjectMapper.writeValueAsString(messageSource.getAllProperties(LocaleContextHolder.getLocale(), false));
    }

    public String get(String key, String... args) {
	Locale locale = LocaleContextHolder.getLocale();
	return this.messageSource.getMessage(String.valueOf(key), args, locale);
    }
}