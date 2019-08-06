import groovy.transform.CompileStatic;
import groovy.transform.Field

import org.wipo.connect.ruleengine.rules.model.Step;
import org.wipo.connect.ruleengine.rules.model.Recording;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;


def double myCustomAvg(double a, double b){
	return ((a+b)/2)*-1;
}

def int myCustomMultiply(int a, int b, long c){
	System.out.print("${a}|");
	sleep(c);
	if(b > 0){
		return a + myCustomMultiply(a, b-1, c)
	}else{
		return 0;
	}
}