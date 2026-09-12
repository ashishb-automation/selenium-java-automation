package dev.ashish.qa.hooks;

import dev.ashish.qa.support.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserHooks {
    @Before
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        if (System.getenv("CI") != null) options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        TestContext.setDriver(new ChromeDriver(options));
    }

    @After
    public void stopBrowser(Scenario scenario) {
        if (TestContext.driver() == null) return;
        if (scenario.isFailed()) {
            byte[] image = ((TakesScreenshot) TestContext.driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", "failure");
        }
        TestContext.driver().quit();
        TestContext.clear();
    }
}

