


package org.wipo.connect.ruleengine.test;



import static org.wipo.connect.ruleengine.rules.utils.Utils.createRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.ruleengine.rules.Resolver;
import org.wipo.connect.ruleengine.rules.calculation.RulesException;
import org.wipo.connect.ruleengine.rules.model.DataType;
import org.wipo.connect.ruleengine.rules.model.Rule;
import org.wipo.connect.ruleengine.rules.model.ValueMap;
import org.wipo.connect.ruleengine.rules.model.Variable;
import org.wipo.connect.ruleengine.test.model.Account;

public class ObjectRulesTest{

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ObjectRulesTest.class);


    @Test
    public void testAccount() throws RulesException {
        String ruleName = "accountRule";

        List<Variable> variables = new ArrayList<Variable>();
        Rule rule = createRule(ruleName, DataType.TEXT, "$acc$.active", "$acc$.email.replace('.it','.com')", variables);



        Resolver resolver = new Resolver(variables);

        String res = null;
        try {
            ValueMap valMap = new ValueMap();

            valMap.put("acc", new Account("andrea.fumagalli","andrea.fumagalli@finconsgroup.it",true));
            res = (String) resolver.execute(rule, valMap);
            Assert.assertEquals("andrea.fumagalli@finconsgroup.com",res);
            LOGGER.debug("Result:" + res);

            valMap.put("acc", new Account("pasquale.minervini","pasquale.minervini@finconsgroup.foo",true));
            res = (String) resolver.execute(rule, valMap);
            Assert.assertEquals("pasquale.minervini@finconsgroup.foo",res);
            LOGGER.debug("Result:" + res);

            valMap.put("acc", new Account("nunzia.porrelli","nunzia.porrelli@finconsgroup.it",true));
            res = (String) resolver.execute(rule, valMap);
            Assert.assertEquals("nunzia.porrelli@finconsgroup.com",res);
            LOGGER.debug("Result:" + res);

            valMap.put("acc", new Account("claudio.edossi","claudio.edossi@finconsgroup.it",false));
            res = (String) resolver.execute(rule, valMap);
            Assert.assertNull(res);
            LOGGER.debug("Result:" + res);
        } catch (RulesException e) {
            LOGGER.error("Error executing the rule", e);
            throw e;
        }



    }



}
