package ru.asergeenko.camunda.dmn;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.test.DmnEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.asergeenko.camunda.dmn.util.Util;

public class DmnAgeSalaryExecutionTest {
    private static Logger logger = LoggerFactory.getLogger("DmnExecutionTest");

    public static final String DMN_FILE = "/Users/alexsergeenko/Documents/Work/Alfabank/DMN/camunda-dmn/dmn/ex_1.dmn";
    public static final String DECISION_NAME = "age_salary";

    @Rule
    public DmnEngineRule dmnEngineRule = new DmnEngineRule();

    public DmnEngine dmnEngine;
    public DmnDecision dmnDecision;

    @Before
    public void parseDecision() {
        dmnEngine = Util.createDmnEngine();
        dmnDecision = Util.parseDmn(dmnEngine, DMN_FILE, DECISION_NAME);
    }

    @Test
    public void shouldReturnCForOlderThan18() {
        VariableMap variables = Variables
                .putValue("age", 20)
                .putValue("salary", 30000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals("c", singleEntry);

    }

    @Test
    public void shouldReturnBForYoungerThan18() {
        VariableMap variables = Variables
                .putValue("age", 16)
                .putValue("salary", 28000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals("b", singleEntry);

    }

    @Test
    public void shouldReturnABetween21And35() {
        VariableMap variables = Variables
                .putValue("age", 24)
                .putValue("salary", 55000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals("a", singleEntry);

    }

    @Test
    public void shouldReturnAaBetween35And50() {
        VariableMap variables = Variables
                .putValue("age", 36)
                .putValue("salary", 75000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals("aa", singleEntry);

    }

    @Test
    public void shouldReturnNoCatForOther() {
        VariableMap variables = Variables
                .putValue("age", 16)
                .putValue("salary", 32000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals("no cat", singleEntry);

    }

    private Object getDmnDecisionRuleResults(VariableMap variables) {
        DmnDecisionTableResult result = Util.evaluateDecisionTable(dmnEngine, dmnDecision, variables);
        Object singleEntry = result.getSingleResult().getSingleEntry();
        logger.info("Result rate: {}", singleEntry.toString());
        return singleEntry;
    }

}
