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

import java.util.ArrayList;

public class DmnTotalIncomeExecutionTest {
    final private static Logger logger = LoggerFactory.getLogger("DmnTotalIncomeExecutionTest");

    public static final String DMN_FILE = "src/main/resources/ex_1.dmn";
    public static final String DECISION_NAME = "total_income";

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
    public void shouldReturnTotalSalary() {
        ArrayList<Double> salaries = new ArrayList<>();
        salaries.add(15000.5);
        salaries.add(28000.3);
        VariableMap variables = Variables
                .putValue("salaries", salaries)
                .putValue("age", 35)
                .putValue("salary", 50000);

        Object singleEntry = getDmnDecisionRuleResults(variables);
        Assert.assertEquals(43000.8, singleEntry);

    }

    private Object getDmnDecisionRuleResults(VariableMap variables) {
        DmnDecisionTableResult result = Util.evaluateDecisionTable(dmnEngine, dmnDecision, variables);
        Object singleEntry = result.getSingleResult().getSingleEntry();
        logger.info("Total salary: {}", singleEntry.toString());
        return singleEntry;
    }
}
