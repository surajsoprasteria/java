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

public class JSConditionalScript implements Script {

    private static final long serialVersionUID = 6887477874979949148L;

    private final Script condition;
    private final Script thenScript;
    private final Script elseScript;
    private final String sourceCode;

    public JSConditionalScript(Script condition, Script thenScript, Script elseScript) {
	super();
	this.condition = condition;
	this.thenScript = thenScript;
	this.elseScript = elseScript;
	this.sourceCode = generateSourceCode();
    }

    private String generateSourceCode() {
	StringBuilder sb = new StringBuilder();
	sb.append("(function(){");
	sb.append("\n\tvar __conditionresult = " + condition.getSourceCode() + ";");
	sb.append("\n\tif(__conditionresult){");
	sb.append("\n\t\treturn " + thenScript.getSourceCode() + ";");
	sb.append("\n\t}else{");
	sb.append("\n\t\treturn " + elseScript.getSourceCode() + ";");
	sb.append("\n\t}");
	sb.append("\n})()");
	return sb.toString();
    }

    public JSConditionalScript(String condition, String thenScript, String elseScript) {
	this(new SimpleScript(condition), new SimpleScript(thenScript), new SimpleScript(elseScript));
    }

    public Script getCondition() {
	return condition;
    }

    public Script getThenScript() {
	return thenScript;
    }

    public Script getElseScript() {
	return elseScript;
    }

    @Override
    public String getSourceCode() {
	return sourceCode;
    }

}
