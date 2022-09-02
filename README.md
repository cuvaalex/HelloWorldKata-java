# HelloWorld Kata - Java

[![CI](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml/badge.svg)](https://github.com/valentinacupac/banking-kata-java/actions/workflows/ci.yaml)

## Overview

This project illustrates TDD & Clean Architecture implementation in Java, showing the Use Case Driven Development
Approach from Alexandre Cuva. It was used to create a base application to show good TDD & BDD practices

We implement a Hello World system with the following use cases:

- Create a name

The following feature still need to be implemented
- view hello world
- view a list of name

## Prerequisites

- OpenJDK 17

Running build with automated tests:

```
./mvn test
```
Running JaCoCo code coverage:

```
./mvn jacoco:prepare-agent jacoco:report
```

Running PIT mutation testing:

```
./mvn pitest:mutationCoverage
```

Running App Map coverage :

It will automatically create during the test phase, to read the file check
on https://appland.com/docs/appmap-overview.html,

## Reports

See the `target` directory for the generated reports for test results, code coverage and mutation testing.

Reports:

- target/surefire-reports
- target/site/jacoco
- target/pit-reports
- target/appmap

## Swagger API
You can find Swagger API UI under `http://localhost:8080/swagger-ui/index.html`
