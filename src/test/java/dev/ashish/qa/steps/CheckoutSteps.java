package dev.ashish.qa.steps;

import dev.ashish.qa.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {
    @When("the customer adds multiple products to the cart")
    public void addMultipleProducts() {
        TestContext.inventoryPage().addItemToCart("Sauce Labs Backpack");
        TestContext.inventoryPage().addItemToCart("Sauce Labs Bike Light");
        TestContext.inventoryPage().addItemToCart("Sauce Labs Bolt T-Shirt");
    }

    @When("the customer proceeds to checkout")
    public void proceedToCheckout() {
        TestContext.inventoryPage().openCart();
        TestContext.cartPage().proceedToCheckout();
    }

    @When("the customer enters checkout details with first name {string}, last name {string}, and zip code {string}")
    public void enterCheckoutDetails(String firstName, String lastName, String zipCode) {
        TestContext.checkoutPage().fillCustomerInfo(firstName, lastName, zipCode);
    }

    @When("the customer continues checkout")
    public void continueCheckout() {
        TestContext.checkoutPage().continueCheckout();
    }

    @When("the customer finishes checkout")
    public void finishCheckout() {
        TestContext.checkoutPage().finishCheckout();
    }

    @When("the customer cancels checkout")
    public void cancelCheckout() {
        TestContext.checkoutPage().cancelCheckout();
    }

    @Then("the checkout overview should be displayed")
    public void verifyOverview() {
        Assert.assertTrue(TestContext.checkoutPage().overviewIsVisible());
    }

    @Then("the order confirmation should be displayed")
    public void verifyConfirmation() {
        Assert.assertTrue(TestContext.checkoutPage().orderConfirmed());
    }

    @Then("the cart page should be displayed")
    public void verifyCartPage() {
        Assert.assertTrue(TestContext.cartPage().cartIsVisible());
    }

    @Then("the inventory page should be displayed")
    public void verifyInventoryPage() {
        Assert.assertTrue(TestContext.inventoryPage().inventoryIsVisible());
    }
}
