package org.wipo.connect.scriptengine.script;

public class SimpleScript implements Script {

    private static final long serialVersionUID = 7024827310746046763L;

    private final String sourceCode;

    public SimpleScript(String sourceCode) {
   	super();
   	this.sourceCode = sourceCode;
       }


    @Override
    public String getSourceCode() {
	return sourceCode;
    }


}
