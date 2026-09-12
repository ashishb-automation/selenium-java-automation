package dev.ashish.qa.support;

import org.openqa.selenium.WebDriver;
import dev.ashish.qa.pages.LoginPage;
import dev.ashish.qa.pages.InventoryPage;
import dev.ashish.qa.pages.CartPage;
import dev.ashish.qa.pages.CheckoutPage;

public final class TestContext {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private TestContext() {}
    public static void setDriver(WebDriver driver) { DRIVER.set(driver); }
    public static WebDriver driver() { return DRIVER.get(); }
    public static LoginPage loginPage() { return new LoginPage(driver()); }
    public static InventoryPage inventoryPage() { return new InventoryPage(driver()); }
    public static CartPage cartPage() { return new CartPage(driver()); }
    public static CheckoutPage checkoutPage() { return new CheckoutPage(driver()); }
    public static void clear() { DRIVER.remove(); }
}

