
package org.wipo.connect.ruleengine.test;

import static org.wipo.connect.ruleengine.rules.utils.Utils.createRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.ConstantUtils;
import org.wipo.connect.ruleengine.rules.Resolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.test.model.DynamicField;
import org.wipo.connect.ruleengine.test.model.FieldTypeEnum;

public class DynamicFieldsRulesTest {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(DynamicFieldsRulesTest.class);

    @Test
    public void testDynField() throws Exception {
	String ruleName = "dynField";

	String ruleCondition = "ROUND($df1$.value, 0, RoundingMode.FLOOR) == $df2$.value";
	String ruleFormula = "$df3$.value + 'Bar'";

	List<Variable> variables = new ArrayList<Variable>();
	Rule rule = createRule(ruleName, DataType.TEXT, ruleCondition, ruleFormula, variables);

	Resolver resolver = new Resolver(variables);

	String res = null;
	try {
	    ValueMap valMap = new ValueMap();
	    valMap.put("df1", new DynamicField("Dbl field", FieldTypeEnum.DOUBLE, 42.42));
	    valMap.put("df2", new DynamicField("Int field", FieldTypeEnum.INTEGER, 42));
	    valMap.put("df3", new DynamicField("Str field", FieldTypeEnum.STRING, "Foo"));
	    res = (String) resolver.execute(rule, valMap);
	    Assert.assertEquals("FooBar", res);
	    LOGGER.debug("Result:" + res);

	    valMap.put("df2", new DynamicField("Int field", FieldTypeEnum.INTEGER, ConstantUtils.DEFAULT_LIMIT));
	    res = (String) resolver.execute(rule, valMap);
	    Assert.assertNull(res);
	    LOGGER.debug("Result:" + res);
	} catch (RulesException e) {
	    LOGGER.error("Error executing the rule", e);
	    throw e;
	}

    }

}
