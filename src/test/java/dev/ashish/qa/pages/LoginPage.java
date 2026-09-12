package dev.ashish.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By pageTitle = By.className("title");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void open() { driver.get("https://www.saucedemo.com"); wait.until(ExpectedConditions.visibilityOfElementLocated(username)); }
    public void signIn(String user, String pass) { driver.findElement(username).sendKeys(user); driver.findElement(password).sendKeys(pass); driver.findElement(loginButton).click(); }
    public boolean inventoryIsVisible() { return wait.until(ExpectedConditions.textToBe(pageTitle, "Products")); }
    public boolean authenticationErrorIsVisible() { return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed(); }
}

