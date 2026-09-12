# Selenium Java Automation Portfolio

[![Selenium Cucumber Tests](https://github.com/ashishbsdet-sketch/selenium-java-automation/actions/workflows/selenium.yml/badge.svg)](https://github.com/ashishbsdet-sketch/selenium-java-automation/actions/workflows/selenium.yml)

A Java 17 UI automation framework for the Sauce Demo e-commerce application. It combines Selenium WebDriver, Cucumber and JUnit Platform to express customer behaviour in readable Gherkin while keeping browser logic maintainable.

## What this project demonstrates

- Behaviour-driven development with business-readable feature files
- Page Object Model with UI selectors separated from scenarios
- Chrome and Firefox execution through Selenium Manager
- Thread-safe WebDriver lifecycle using `ThreadLocal`
- Explicit waits instead of fixed sleeps
- Failure screenshots embedded in Cucumber reports
- GitHub Actions and Jenkins pipeline support
- Environment-driven URLs and credentials
- HTML, JSON and JUnit-compatible reports

## Test coverage

| Area | Scenarios |
| --- | --- |
| Authentication | Valid, invalid, locked-out, empty and performance-glitch users |
| Shopping | Add and remove a product, cart badge validation |
| Session | Successful logout |
| Checkout | Multi-product purchase and checkout cancellation |

## Project structure

```text
.
├── .github/workflows/          # Chrome and Firefox CI
├── src/test/java/
│   └── dev/ashish/qa/
│       ├── hooks/              # Browser lifecycle and screenshots
│       ├── pages/              # Page Object Model
│       ├── runner/             # JUnit Platform suite
│       ├── steps/              # Cucumber step definitions
│       └── support/            # Shared context and configuration
├── src/test/resources/features # Gherkin scenarios
├── Jenkinsfile                 # Jenkins pipeline
└── pom.xml                     # Maven build
```

## Run locally

Prerequisites: Java 17+, Maven 3.9+ and Chrome or Firefox.

Selenium Manager resolves compatible browser drivers automatically, so a separate ChromeDriver or GeckoDriver installation is normally unnecessary.

```bash
git clone https://github.com/ashishbsdet-sketch/selenium-java-automation.git
cd selenium-java-automation
mvn clean test
```

Run in Firefox:

```bash
BROWSER=firefox mvn clean test
```

Run a tagged suite:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## Configuration

| Variable | Purpose | Default |
| --- | --- | --- |
| `BASE_URL` | Application under test | `https://www.saucedemo.com` |
| `TEST_USERNAME` | Valid test account | `standard_user` |
| `TEST_PASSWORD` | Valid test password | `secret_sauce` |
| `BROWSER` | `chrome` or `firefox` | `chrome` |

The defaults are public Sauce Demo training credentials. For real applications, configure protected CI secrets rather than storing credentials in feature files or source code.

## Reports and debugging

After execution:

- Cucumber HTML: `target/cucumber-report.html`
- Enhanced Cucumber report: `target/cucumber-html-reports/`
- JUnit results: `target/surefire-reports/`

When a scenario fails, the browser hook captures a screenshot and attaches it to the Cucumber report. Generated reports remain available as CI artifacts but are intentionally excluded from source control.

## CI design

GitHub Actions executes the complete regression suite independently in Chrome and Firefox. Maven dependencies are cached, browser jobs do not fail fast, and reports are retained even when a test fails. The Jenkins pipeline provides the same browser choice for teams using Jenkins.

## Design decisions

- Gherkin describes customer outcomes rather than Selenium operations.
- Page objects own locators and browser interactions.
- Explicit waits are used only at synchronization boundaries.
- Each scenario receives an isolated browser session.
- Browser and environment selection remain outside feature files.

## Roadmap

- Introduce scenario-level parallel execution
- Add API-assisted test-data setup
- Add accessibility validation
- Add containerized execution with Selenium Grid

## Disclaimer

Sauce Demo is a public training application. This repository is an independent portfolio project and is not affiliated with Sauce Labs.
