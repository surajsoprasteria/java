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
package org.wipo.connect.scriptengine.script;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class JSExternalScript implements Script {

    private static final long serialVersionUID = 927920941558270069L;
    private final Script baseScript;
    private final String sourceCode;
    private final List<String> externalSources;

    public JSExternalScript(Script baseScript, String... externalSources) {
	super();
	this.baseScript = baseScript;
	this.externalSources = Arrays.asList(externalSources);
	this.sourceCode = generateSourceCode();
    }

    public JSExternalScript(Script baseScript, Reader... externalSources) throws IOException {
	this.baseScript = baseScript;
	this.externalSources = new ArrayList<>();
	for (Reader reader : externalSources) {
	    String extSrcCode = IOUtils.toString(reader);
	    this.externalSources.add(extSrcCode);
	}
	this.sourceCode = generateSourceCode();
    }

    private String generateSourceCode() {
	StringBuilder sb = new StringBuilder();
	sb.append("(function(){");

	for (String ext : externalSources) {
	    sb.append("\n\n\t");
	    sb.append(ext);
	}

	sb.append("\n\n/* BASE SCRIPT */");
	sb.append("\n\n\treturn " + baseScript.getSourceCode() + ";");

	sb.append("\n})()");
	return sb.toString();
    }

    @Override
    public String getSourceCode() {
	return sourceCode;
    }

}
