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
package com.sun.script.javascript;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

import org.apache.commons.lang3.NotImplementedException;

public class RhinoScriptEngineFactory implements ScriptEngineFactory {

    private static final String ENGINE_NAME = "rhino_placeholder";
    private static final String ENGINE_VERSION = "0.0";

    @Override
    public String getEngineName() {
	return ENGINE_NAME;
    }

    @Override
    public String getEngineVersion() {
	return ENGINE_VERSION;
    }

    @Override
    public List<String> getExtensions() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public List<String> getMimeTypes() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public List<String> getNames() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public String getLanguageName() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public String getLanguageVersion() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public Object getParameter(String key) {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public String getOutputStatement(String toDisplay) {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public String getProgram(String... statements) {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

    @Override
    public ScriptEngine getScriptEngine() {
	throw new NotImplementedException("This is just a placeholder version, if you are using it... there's a problem!");
    }

}
