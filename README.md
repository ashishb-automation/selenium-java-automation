# Selenium Cucumber Automation Framework

BDD automation framework using Java 17, Selenium WebDriver, Cucumber and JUnit Platform. It demonstrates readable Gherkin scenarios, Page Object design, explicit waits, hooks, tagged execution, CI-safe headless testing and failure reporting.

```bash
mvn clean test
```

## Structure

- `features/` contains business-readable acceptance scenarios.
- `pages/` owns selectors and browser interactions.
- `steps/` maps Gherkin intent to automation code.
- `hooks/` manages browser lifecycle and captures failure evidence.
- GitHub Actions runs the suite for pushes and pull requests.
