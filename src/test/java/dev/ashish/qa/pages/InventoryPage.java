package dev.ashish.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By title = By.className("title");
    private final By cartButton = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By burgerButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean inventoryIsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).isDisplayed();
    }

    public void addItemToCart(String itemName) {
        By itemButton = By.xpath("//div[contains(@class,'inventory_item') and .//div[contains(normalize-space(.), '" + itemName + "')]]//button");
        wait.until(ExpectedConditions.elementToBeClickable(itemButton)).click();
    }

    public void removeItemFromCart(String itemName) {
        By itemButton = By.xpath("//div[contains(@class,'inventory_item') and .//div[contains(normalize-space(.), '" + itemName + "')]]//button");
        wait.until(ExpectedConditions.elementToBeClickable(itemButton)).click();
    }

    public int cartItemCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public void openCart() {
        driver.findElement(cartButton).click();
    }

    public void logout() {
        driver.findElement(burgerButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
