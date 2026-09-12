package dev.ashish.qa.hooks;

import dev.ashish.qa.support.Configuration;
import dev.ashish.qa.support.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserHooks {
    @Before
    public void startBrowser() {
        WebDriver driver = createDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
        TestContext.setDriver(driver);
    }

    @After
    public void stopBrowser(Scenario scenario) {
        WebDriver driver = TestContext.driver();
        if (driver == null) {
            return;
        }

        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure");
            }
        } finally {
            driver.quit();
            TestContext.clear();
        }
    }

    private WebDriver createDriver() {
        return switch (Configuration.browser()) {
            case "chrome" -> new ChromeDriver(chromeOptions());
            case "firefox" -> new FirefoxDriver(firefoxOptions());
            default -> throw new IllegalArgumentException(
                    "Unsupported BROWSER value: " + Configuration.browser());
        };
    }

    private ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--disable-notifications");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        if (isCi()) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }
        return options;
    }

    private FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (isCi()) {
            options.addArguments("-headless");
        }
        return options;
    }

    private boolean isCi() {
        return System.getenv("CI") != null;
    }
}
