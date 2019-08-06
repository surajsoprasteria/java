package org.wipo.connect.ruleengine;

import groovy.transform.CompileStatic;
import groovy.transform.Field

import org.wipo.connect.ruleengine.rules.model.Step;
import org.wipo.connect.ruleengine.rules.model.Recording;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;

@CompileStatic
def log(
	String name,
	String condition,
	String formula,
	Map<String, Object> values,
	Boolean conditionResult,
	Object result,
	String formulaValue,
	String conditionValue,
	Recording recording,
	boolean completeFields){

	Step step = new Step( name, condition, formula, values);

	step.setConditionResult(conditionResult);
	step.setStringResult(""+ result);
	step.setResult(result);
	step.setFormulaValue(formulaValue);
	step.setConditionValue(conditionValue);

	recording.add(step);
	recording.updateCompleteFields(completeFields);

}

@CompileStatic
def Date DATA(String value){
	return new SimpleDateFormat("dd-MM-yyyy").parse(value);
}

@CompileStatic
def Date DATA(String value, String pattern){
	return new SimpleDateFormat(pattern).parse(value);
}

@CompileStatic
def Date TIMESTAMP(String value){
	return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(value);
}

@CompileStatic
def Date TIME(String value){
	return new SimpleDateFormat("hh:mm:ss").parse(value);
}

@CompileStatic
def BigDecimal ROUND_UP(String value, int scale){
	return ROUND( value, scale, RoundingMode.UP);
}

@CompileStatic
def BigDecimal ROUND_DOWN(String value, int scale){
	return ROUND( value, scale, RoundingMode.DOWN);
}

@CompileStatic
def BigDecimal ROUND_CEILING(String value, int scale){
	return ROUND( value, scale, RoundingMode.CEILING);
}

@CompileStatic
def BigDecimal ROUND_FLOOR(String value, int scale){
	return ROUND( value, scale, RoundingMode.FLOOR);
}

@CompileStatic
def BigDecimal ROUND_HALF_UP(String value, int scale){
	return ROUND( value, scale, RoundingMode.HALF_UP);
}

@CompileStatic
def BigDecimal ROUND_HALF_DOWN(String value, int scale){
	return ROUND( value, scale, RoundingMode.HALF_DOWN);
}

@CompileStatic
def BigDecimal ROUND_HALF_EVEN(String value, int scale){
	return ROUND( value, scale, RoundingMode.HALF_EVEN);
}

@CompileStatic
def BigDecimal ROUND(BigDecimal value, int scale, RoundingMode roundingMode){
	return value.setScale(scale, roundingMode);
}

@CompileStatic
def BigDecimal ROUND(String value, int scale, RoundingMode roundingMode){
	BigDecimal tmp = new BigDecimal(value);
	return tmp.setScale(scale, roundingMode);
}

def DIFFDATE(Date d1, Date d2)
{
	use(groovy.time.TimeCategory) {
		def duration = d1 - d2;
		return duration.days;
	}
}
