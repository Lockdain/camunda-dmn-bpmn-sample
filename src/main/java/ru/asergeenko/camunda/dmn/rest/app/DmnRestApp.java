package ru.asergeenko.camunda.dmn.rest.app;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DmnRestApp {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(DmnRestApp.class, args);
    }

    @Bean
    public ProcessEnginePlugin statusPlugin() {
        return new AbstractProcessEnginePlugin() {
            @Override
            public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
                logger.warn("post init");
            }

            @Override
            public void postProcessEngineBuild(ProcessEngine processEngine) {
                logger.warn("post build");
            }

            @Override
            public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
                logger.warn("pre init");
            }
        };
    }
}
