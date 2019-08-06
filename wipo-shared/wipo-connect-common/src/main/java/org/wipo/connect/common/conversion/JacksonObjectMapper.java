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

package org.wipo.connect.common.conversion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;
import org.wipo.connect.common.utils.ConversionUtils;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 3897470022825096543L;

    private DateFormat customDateFormat = new SimpleDateFormat(ConversionUtils.DATE_PATTERN_ISO8601);

    public JacksonObjectMapper() {
	findAndRegisterModules();
	setDateFormat(this.customDateFormat);
	configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	// getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
    }

    public DateFormat getCustomDateFormat() {
	return this.customDateFormat;
    }

    public void setCustomDateFormat(DateFormat customDateFormat) {
	this.customDateFormat = customDateFormat;
    }

    public void setPrettyPrint(boolean prettyPrint) {
	configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }

    public static class HTMLCharacterEscapes extends CharacterEscapes {

	private static final long serialVersionUID = -5535439457149825006L;
	private final int[] asciiEscapes;

	public HTMLCharacterEscapes() {
	    // start with set of characters known to require escaping (double-quote, backslash etc)
	    asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
	    // and force escaping of a few others:
	    asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['"'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
	}

	@Override
	public int[] getEscapeCodesForAscii() {
	    return asciiEscapes;
	}

	// and this for others; we don't need anything special here
	@Override
	public SerializableString getEscapeSequence(int ch) {
	    return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));

	}
    }

}
