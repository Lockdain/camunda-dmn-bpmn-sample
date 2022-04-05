package ru.asergeenko.camunda.dmn.util;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Util {

    public static DmnEngine createDmnEngine() {
        return DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration()
                .buildEngine();
    }

    public static DmnDecision parseDmn(DmnEngine dmnEngine, String dmnFile, String decisionName) {
        InputStream inputStream = null;
        try {
            inputStream = readNotationToInputStream(dmnFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DmnDecision decision = dmnEngine.parseDecision(decisionName, inputStream);

        return decision;
    }


    public static DmnDecisionTableResult evaluateDecisionTable(DmnEngine dmnEngine, DmnDecision dmnDecision, VariableMap variables) {
        return dmnEngine.evaluateDecisionTable(dmnDecision, variables);
    }

    private static InputStream readNotationToInputStream(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        return new FileInputStream(file);
    }

}
