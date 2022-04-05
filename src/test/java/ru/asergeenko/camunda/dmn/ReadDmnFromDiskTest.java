package ru.asergeenko.camunda.dmn;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.junit.Test;
import ru.asergeenko.camunda.dmn.util.Util;

public class ReadDmnFromDiskTest {

    public static final String DMN_PATH = "/Users/alexsergeenko/Documents/Work/Alfabank/DMN/camunda-dmn/dmn/ex_1.dmn";
    public static final String DECISION_NAME = "Decision_1jmw56q";

    @Test
    public void readDmnFromDiskShouldBeOk() {
        DmnEngine dmnEngine = Util.createDmnEngine();
        Util.parseDmn(dmnEngine, DMN_PATH, DECISION_NAME);
    }
}
