package dev.ashish.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By itemTotal = By.cssSelector(".summary_subtotal_label");
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillCustomerInfo(String first, String last, String postalCodeValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(zipCode).sendKeys(postalCodeValue);
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public void cancelCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public boolean orderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).isDisplayed();
    }

    public boolean overviewIsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotal)).isDisplayed();
    }
}
