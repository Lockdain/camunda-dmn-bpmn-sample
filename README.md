
#Camunda DMN/BPMN Demo
A simple yet illustrative project demonstrating a basic functionality of 
Camunda BPMN/DMN using Java.

##Build
The build process is straightforward - use `maven build`.

## Run
There are few approaches are available. You can run any unit tests from 
`/test/ru/asergeenko/camunda/dmn` to see the low-level DMN API usage.

Or you can run the REST API using Spring Boot by running the 
`ru.asergeenko.camunda.dmn.DmnRestApp`. Use shell-scripts given at
the `/sh` folder to execute HTTP-requests against the endpoint.

Use Camunda Modeler to view and edit the source `dmn` at `/src/main/resources`.