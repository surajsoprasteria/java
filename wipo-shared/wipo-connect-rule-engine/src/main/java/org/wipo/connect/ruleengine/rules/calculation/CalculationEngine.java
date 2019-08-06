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

package org.wipo.connect.ruleengine.rules.calculation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.exception.RulesConfigurationException;
import org.wipo.connect.ruleengine.exception.ScriptConfigurationException;
import org.wipo.connect.ruleengine.rules.model.CalculationElement;
import org.wipo.connect.ruleengine.rules.model.EngineContext;
import org.wipo.connect.ruleengine.rules.model.Recording;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.rules.model.VariableType;
import org.wipo.connect.ruleengine.rules.utils.FormulaHelper;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * The Class CalculationEngine.
 */
public class CalculationEngine implements ICalculationEngine {

    /** The Constant logger. */
    private static final Logger logger = WipoLoggerFactory.getLogger(CalculationEngine.class);

    /** The Constant PROP_DEF_FUNCTIONS. */
    private static final String PROP_DEF_FUNCTIONS = "default-functions";

    /** The Constant PROP_COMPILE_STATIC. */
    private static final String PROP_COMPILE_STATIC = "compile-static";

    /** The Constant PROP_DEBUG_MODE. */
    private static final String PROP_DEBUG_MODE = "debug-mode";

    /** The Constant PROP_TARGET_BYTECODE. */
    private static final String PROP_TARGET_BYTECODE = "target-bytecode";

    /** The Constant PROP_VERBOSE. */
    private static final String PROP_VERBOSE = "verbose";

    /** The Constant FH. */
    private static final FormulaHelper FH = FormulaHelper.getInstance();

    /**
     * To name.
     *
     * @param name
     *            the name
     * @return the string
     */
    public static String toName(final String name) {
	if (StringUtils.isEmpty(name)) {
	    return name;
	}
	String method = name.toLowerCase().replace('_', ' ');
	method = WordUtils.capitalize(method).replace(" ", "");
	return WordUtils.uncapitalize(method);
    }

    /** The script. */
    private Script script;

    /** The sorted rules. */
    private List<String> sortedRules;

    /** The parameters. */
    private List<String> parameters;

    /** The default engine properties. */
    private Properties defaultEngineProperties;

    /** The default functions. */
    private String defaultFunctions;

    /** The compile static. */
    private boolean compileStatic;

    /** The debug mode. */
    private boolean debugMode;

    /** The target bytecode. */
    private String targetBytecode;

    /** The verbose. */
    private boolean verbose;

    /**
     * Instantiates a new calculation engine.
     */
    public CalculationEngine() {
	super();

	InputStream defaultEnginePropertiesIS = CalculationEngine.class.getResourceAsStream("/default-engine.properties");
	this.defaultEngineProperties = new Properties();

	try {
	    this.defaultEngineProperties.load(defaultEnginePropertiesIS);
	} catch (IOException e) {
	    logger.error("Error loading default-engine.properties", e);
	}

	this.defaultFunctions = this.defaultEngineProperties.getProperty(PROP_DEF_FUNCTIONS, "/Default.groovy");
	this.compileStatic = BooleanUtils.toBoolean(this.defaultEngineProperties.getProperty(PROP_COMPILE_STATIC, "true"));
	this.debugMode = BooleanUtils.toBoolean(this.defaultEngineProperties.getProperty(PROP_DEBUG_MODE, "false"));
	this.targetBytecode = this.defaultEngineProperties.getProperty(PROP_TARGET_BYTECODE, "1.7");
	this.verbose = BooleanUtils.toBoolean(this.defaultEngineProperties.getProperty(PROP_VERBOSE, "false"));
    }

    /**
     * Instantiates a new calculation engine.
     *
     * @param compileStatic
     *            the compile static
     */
    public CalculationEngine(boolean compileStatic) {
	this();
	this.compileStatic = compileStatic;
    }

    /**
     * Compile.
     *
     * @param scriptText
     *            the script text
     * @return the script
     * @throws Throwable
     *             the throwable
     */
    public Script compile(final String scriptText) throws Throwable {

	Script script = null;

	ImportCustomizer importCustomizer = new ImportCustomizer();
	importCustomizer.addImports(Element.class.getName());
	importCustomizer.addImports(Exp.class.getName());
	importCustomizer.addImports(BigDecimalMixin.class.getName());

	CompilerConfiguration configuration = new CompilerConfiguration();
	configuration.addCompilationCustomizers(importCustomizer);
	configuration.setDebug(this.debugMode);
	configuration.setTargetBytecode(this.targetBytecode);
	configuration.setVerbose(this.verbose);

	GroovyShell gShell = new GroovyShell(configuration);

	Recording recording = new Recording();

	Binding binding = new Binding();
	binding.setProperty("recording", recording);

	script = gShell.parse(scriptText);

	script.setBinding(binding);

	return script;
    }

    /**
     * Constant definitions.
     *
     * @param sb
     *            the sb
     * @param engineContext
     *            the engine context
     */
    private void constantDefinitions(final StringBuilder sb, EngineContext engineContext) {

	for (String constant : engineContext.getConstants()) {

	    Variable v = engineContext.getVariables().get(constant);

	    sb.append("\n").append(" @Field ").append(v.getDataType().getTypeReference().getName()).append(" ").append(v.getName()).append(" = ").append(v.getValue()).append(";\n");

	}

    }

    /**
     * Creates the engine context.
     *
     * @param variables
     *            the variables
     * @return the engine context
     */
    private EngineContext createEngineContext(final List<Variable> variables) {

	Map<String, Variable> map = new HashMap<>();

	EngineContext engineContext = new EngineContext();

	for (Variable variable : variables) {

	    Variable varPrecedenteContesto = map.get(variable.getName());

	    if (varPrecedenteContesto == null) {

		// variabile non presente nel contesto, la inserisco
		map.put(variable.getName(), variable);
		engineContext.addVariables(variable);

	    } else {

		// aggiorno una var esistente

		if (variable.getCalculationElements() != null) {

		    if (varPrecedenteContesto.getCalculationElements() == null) {
			varPrecedenteContesto.setCalculationElements(new ArrayList<CalculationElement>());
		    }

		    varPrecedenteContesto.getCalculationElements().addAll(variable.getCalculationElements());

		    engineContext.addVariables(varPrecedenteContesto);
		}

	    }

	    // gestisco il contesto in base alla tipologia di variabile
	    switch (variable.getVariableType()) {
		case RULE:
		    engineContext.addRegs(variable.getName());
		    break;
		case CONSTANT:
		    engineContext.addConstants(variable.getName());
		    break;
		default:
		    engineContext.addParameters(variable.getName());
		    break;
	    }

	}

	return engineContext;

    }

    /**
     * Creates the step.
     *
     * @param name
     *            the name
     * @param condition
     *            the condition
     * @param formula
     *            the formula
     * @param sb
     *            the sb
     * @param engineContext
     *            the engine context
     */
    private void createStep(String name, String condition, String formula, StringBuilder sb, EngineContext engineContext) {

	sb.append("\n log(\"" + name + "\", \"" + FormulaHelper.logVariableValue(condition, engineContext) + "\", \"" + FormulaHelper.logVariableValue(formula, engineContext)
		+ "\", null, conditionResult, result, formulaValue, conditionValue, recording, completeFields); \n");

    }

    /**
     * Definizione regole.
     *
     * @param sb
     *            the sb
     * @param engineContext
     *            the engine context
     */
    private void definizioneRegole(final StringBuilder sb, final EngineContext engineContext) {

	Map<String, Variable> variables = engineContext.getVariables();

	for (Entry<String, Variable> entry : variables.entrySet()) {

	    Variable v = entry.getValue();

	    // consideriamo solo le regole
	    if (v.getVariableType() != VariableType.RULE) {
		continue;
	    }

	    List<String> parameters = parameters(v, variables);

	    if (this.compileStatic) {
		sb.append("\n @CompileStatic");
	    }

	    sb.append("\n @groovy.transform.Synchronized \n def ").append(toName(v.getName())).append("( Recording recording ");

	    StringBuffer sbl = new StringBuffer();

	    // inserisco i parametri della funzione con i relativi tipi
	    int count = 0;
	    for (String p : parameters) {

		Variable tmp = variables.get(p);

		if (count++ == 0) {
		    sbl.append(",");
		}

		sbl.append(tmp.getDataType().getTypeReference().getName()); // tipo
		sbl.append(' ').append(p).append(','); // param name
	    }

	    int n = sbl.lastIndexOf(",");
	    if (n >= 0) {
		sbl.replace(n, n + 1, " ){");
	    } else {
		sbl.append("){");
	    }
	    sb.append(sbl);

	    sb.append("\n");
	    sb.append("\n boolean conditionResult = false; String formulaValue = \"\"; String conditionValue = \"\";Object result = null; boolean completeFields = true;");
	    sb.append("\n");

	    sb.append("\n");

	    if (v.getCalculationElements() != null) {

		List<CalculationElement> list = v.getCalculationElements();

		String condition = null;
		String formula = null;

		for (int i = 0, max = list.size(); i < max; i++) {

		    CalculationElement ec = list.get(i);

		    // verico la mancanza di campi, nel tracciato unico
		    // boolean campi = $REM$.pippo !=null &&
		    // this.gestisciCampiCompleti(ec.getCondition() + ec.getFormula(), sb);

		    boolean aperta = !StringUtils.isEmpty(ec.getCondition()) && !"true".equalsIgnoreCase(ec.getCondition().trim());

		    if (aperta) {

			sb.append("\n conditionValue = \"").append(FormulaHelper.logVariableValue(ec.getCondition(), engineContext)).append("\"; ");

			condition = redable(ec.getCondition());
			sb.append("\nif ").append(ec.getCondition()).append("{");
			sb.append("\n conditionResult = true;");

		    }

		    // if (BigDecimal.class == v.getDataType().getTypeReference()) {

		    String support = rimpiazzaRegole(ec.getFormula(), variables);

		    sb.append("\n formulaValue = \"").append(FormulaHelper.logVariableValue(ec.getFormula(), engineContext)).append("\"; ");
		    sb.append("\n result = ").append(support).append(";");

		    // gestisco il log
		    formula = redable(support);
		    createStep(ec.getName(), ec.getCondition(), formula, sb, engineContext);

		    // gestisco il risultato
		    sb.append("\n return result; ");
		    /*
		     * } else {
		     *
		     * //gestisco il log this.createStep(ec.getName(), condition, formula, sb, engineContext);
		     *
		     * sb.append("\n return ").append(ec.getFormula()) .append(";");
		     *
		     * }
		     */

		    if (aperta) {
			sb.append("\n} ");

			if (i == (max - 1)) {

			    createStep(ec.getName(), condition, formula, sb, engineContext);

			    sb.append("\n return null;  \n ");
			}

		    }

		}

	    }
	    sb.append("\n}");
	}

    }

    /**
     * Evaluate.
     *
     * @param script
     *            the script
     * @param parameters
     *            the parameters
     * @return the map
     * @throws Throwable
     *             the throwable
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> evaluate(final Script script, final Map<String, Object> parameters) throws Throwable {

	Binding binding = script.getBinding();

	for (String var : parameters.keySet()) {
	    binding.setVariable(FH.toVarGroovy(var), parameters.get(var));
	}

	return (Map<String, Object>) script.run();

    }

    @Override
    public Object evaluate(String functionName, final Map<String, Object> parameters, Object[] pars) throws Throwable {

	if (this.script == null) {
	    throw new RulesException("script obbilgatorio per l'esecuzione del motore");
	}

	Binding binding = this.script.getBinding();
	for (String var : parameters.keySet()) {
	    binding.setVariable(FH.toMarkedVar(var), parameters.get(var));
	}

	if (binding.getProperty("recording") == null) {
	    binding.setVariable("recording", new Recording());
	}

	if (pars == null || pars.length < 1 || !(pars[0] instanceof Recording)) {
	    pars = ArrayUtils.add(pars, 0, binding.getProperty("recording"));
	    // pars = ArrayUtils.insert(0, pars, binding.getProperty("recording"));
	}

	Object obj = null;
	try {
	    obj = this.script.invokeMethod(functionName, pars);
	} catch (Exception e) {
	    logger.error("Error in evaluate", e);
	    throw new ScriptConfigurationException(e);
	}

	return obj;

    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public List<String> getParameters() {
	return this.parameters;
    }

    @Override
    public Script getScript() {
	return this.script;
    }

    /**
     * Gets the sorted rules.
     *
     * @return the sorted rules
     */
    public List<String> getSortedRules() {
	return this.sortedRules;
    }

    /**
     * Checks if is initialized.
     *
     * @return true, if is initialized
     */
    public boolean isInitialized() {
	return this.script != null;
    }

    @Override
    public boolean load(final List<Variable> variables) {

	this.load(variables, new InputStream[] {});

	return true;
    }

    @Override
    @SuppressWarnings("squid:S1181")
    public void load(List<Variable> variables, InputStream... customFunction) {

	String scriptText = "";
	try {

	    // creo lo scipt
	    scriptText = make(variables, customFunction);

	    if (logger.isTraceEnabled()) {
		logger.trace(scriptText);
	    }

	    // lo compilo
	    this.script = compile(scriptText);

	} catch (Throwable e) {// NOSONAR
	    logger.error("scriptText:" + scriptText);
	    logger.error("Error in load", e);
	    throw new RulesConfigurationException(e);
	}

    }

    @Override
    public boolean load(Script script) {
	this.script = script;
	return true;
    }

    /**
     * Make.
     *
     * @param items
     *            the items
     * @param customFunction
     *            the custom function
     * @return the string
     * @throws Throwable
     *             the throwable
     */
    public String make(final List<Variable> items, InputStream... customFunction) throws Throwable {

	StringBuilder sb = new StringBuilder();

	// funzioni di utilita'
	sb.append(ICalculationEngine.DEF_FUNCTIONS);

	InputStream defaultFunction = CalculationEngine.class.getResourceAsStream(this.defaultFunctions);
	sb.append(IOGroovyMethods.getText(defaultFunction));

	if (customFunction != null) {
	    for (InputStream function : customFunction) {
		if (function != null) {
		    sb.append(IOGroovyMethods.getText(function));
		}
	    }
	}

	// divido le varibili passate in funzione della tipologia (REGOLA, COSTANTI, VARIABILI)
	EngineContext engineContext = createEngineContext(items);

	// definizione costanti
	constantDefinitions(sb, engineContext);

	// definizione regole
	definizioneRegole(sb, engineContext);

	return sb.toString();

    }

    /**
     * Parameters.
     *
     * @param v
     *            the v
     * @param map
     *            the map
     * @return the list
     */
    private List<String> parameters(final Variable v, final Map<String, Variable> map) {

	List<String> pars = new ArrayList<>();
	List<String> pp = null;

	if (v.getCalculationElements() == null || v.getCalculationElements().isEmpty()) {
	    return pars;
	}

	for (CalculationElement ec : v.getCalculationElements()) {

	    List<String> vars = FormulaHelper.extractVariableList(ec.getCondition());

	    for (String p : vars) {

		Variable variable = map.get(p);

		if (variable == null) {
		    p = ICalculationEngine.PARAMETERDELIMITER + p + ICalculationEngine.PARAMETERDELIMITER;
		    variable = map.get(p);
		}

		if (variable == null) {
		    continue;
		}

		if (variable.getVariableType() == VariableType.CONSTANT) {
		    continue;
		}

		if (variable.getVariableType() != VariableType.RULE && !pars.contains(p)) {
		    pars.add(p);
		}

		pp = parameters(variable, map);

		for (String a : pp) {
		    if (!pars.contains(a)) {
			pars.add(a);
		    }
		}

	    }

	    vars = FormulaHelper.extractVariableList(ec.getFormula());
	    for (String p : vars) {

		Variable variable = map.get(p);

		if (variable == null) {
		    p = ICalculationEngine.PARAMETERDELIMITER + p + ICalculationEngine.PARAMETERDELIMITER;
		    variable = map.get(p);
		}

		if (variable == null) {
		    continue;
		}
		if (variable.getVariableType() == VariableType.CONSTANT) {
		    continue;
		}

		if (variable.getVariableType() != VariableType.RULE && !pars.contains(p)) {
		    pars.add(p);
		}

		pp = parameters(variable, map);

		for (String a : pp) {
		    if (!pars.contains(a)) {
			pars.add(a);
		    }
		}
	    }

	}
	return pars;
    }

    /**
     * Redable.
     *
     * @param param
     *            the param
     * @return the string
     */
    private String redable(String param) {

	if (param == null) {
	    return "";
	}

	return param.replaceAll("\\" + ICalculationEngine.PARAMETERDELIMITER, "");
    }

    // gestione chiamata altre regole all'interno della formula della regola in questione
    /**
     * Rimpiazza regole.
     *
     * @param formula
     *            the formula
     * @param map
     *            the map
     * @return the string
     */
    private String rimpiazzaRegole(String formula, final Map<String, Variable> map) {

	if (formula == null) {
	    return "";
	}

	List<String> vars = null;

	vars = FormulaHelper.extractVariableList(formula);
	for (String p : vars) {

	    p = FH.toMarkedVar(p);

	    Variable variable = map.get(p);

	    if (variable == null) {
		continue;
	    }

	    if (variable.getVariableType() == VariableType.RULE) {

		formula = formula.replace(p, toName(p) + "()");

	    }

	}

	return formula;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters
     *            the new parameters
     */
    public void setParameters(List<String> parameters) {
	this.parameters = parameters;
    }

    /**
     * Sets the script.
     *
     * @param script
     *            the new script
     */
    public void setScript(Script script) {
	this.script = script;
    }

    /**
     * Sets the sorted rules.
     *
     * @param sortedRules
     *            the new sorted rules
     */
    public void setSortedRules(List<String> sortedRules) {
	this.sortedRules = sortedRules;
    }

}
