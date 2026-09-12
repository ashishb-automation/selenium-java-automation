package dev.ashish.qa.support;

import org.openqa.selenium.WebDriver;
import dev.ashish.qa.pages.LoginPage;

public final class TestContext {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private TestContext() {}
    public static void setDriver(WebDriver driver) { DRIVER.set(driver); }
    public static WebDriver driver() { return DRIVER.get(); }
    public static LoginPage loginPage() { return new LoginPage(driver()); }
    public static void clear() { DRIVER.remove(); }
}

