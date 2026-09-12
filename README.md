# Selenium Cucumber Automation Framework

This project is a Java-based UI automation framework built with:

- Java 17
- Maven
- Selenium WebDriver
- Cucumber
- JUnit Platform
- Page Object Model (POM)

It tests a real web app flow (SauceDemo) using behavior-driven scenarios and generates HTML test reports.

## Overview

The framework is designed to validate end-to-end user behaviors in a readable format using Gherkin feature files. Each scenario maps to step definitions and page objects, keeping test logic separated from business behavior and UI selectors.

## Project structure

- `src/test/resources/features/` - Cucumber feature files
- `src/test/java/dev/ashish/qa/pages/` - Page Object classes
- `src/test/java/dev/ashish/qa/steps/` - Step definitions
- `src/test/java/dev/ashish/qa/hooks/` - Browser setup/teardown and failure capture
- `src/test/java/dev/ashish/qa/support/` - Shared test context
- `src/test/java/dev/ashish/qa/runner/` - Cucumber/JUnit runner
- `pom.xml` - Maven dependencies and plugins

## Prerequisites

Before running the tests, ensure the following are installed:

- Java 17+
- Maven 3.9+
- Google Chrome (latest stable)
- ChromeDriver matching your Chrome version

## Run locally

From the project root:

```bash
mvn clean test
```

You can also run a specific build without cleaning:

```bash
mvn test
```

## Reports

After the test run, reports are generated in the `target` directory.

- Cucumber HTML report: `target/cucumber-report.html`
- JSON report: `target/cucumber.json`
- Rich HTML suite: `target/cucumber-html-reports/cucumber-html-reports/`

Open the generated HTML report in a browser to view the result details and failure screenshots.

## Browser handling

The framework launches Chrome in a clean browser session and disables password-manager prompts to avoid flaky UI interruptions during automation.

Failure screenshots are attached automatically in the Cucumber hook and included in the HTML report for failed scenarios.

## Example flow covered

The project includes scenarios for:

- login success/failure
- locked-out users
- invalid or empty credentials
- cart add/remove flow
- logout flow
- checkout completion and cancellation

## Jenkins CI/CD

A Jenkins pipeline file is included at the repository root as `Jenkinsfile`.

The pipeline performs the following:

1. Checks out the repo
2. Installs Java and Maven dependencies if needed
3. Runs `mvn test`
4. Publishes the generated HTML and Cucumber reports

You can configure a Jenkins job to use this file as a Pipeline script from SCM or direct pipeline source.

## Useful commands

```bash
# Clean and run all tests
mvn clean test

# Re-run without rebuilding
mvn test

# View generated report
open target/cucumber-report.html
```

## Notes

- This project uses the Page Object Model for maintainability.
- Gherkin steps are easy to read for manual QA and automation alike.
- The setup is CI-friendly and suitable for Jenkins, GitHub Actions, or other build systems.
