package dev.ashish.qa.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dev.ashish.qa.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps {
    @When("the customer adds {string} to the cart")
    public void addToCart(String itemName) {
        TestContext.inventoryPage().addItemToCart(itemName);
    }

    @When("the customer removes {string} from the cart")
    public void removeFromCart(String itemName) {
        TestContext.inventoryPage().removeItemFromCart(itemName);
    }

    @When("the customer opens the shopping cart")
    public void openCart() {
        TestContext.inventoryPage().openCart();
    }

    @When("the customer logs out")
    public void logout() {
        TestContext.inventoryPage().logout();
    }

    @Then("the cart badge should display {int}")
    public void verifyCartBadge(int expectedCount) {
        assertEquals(expectedCount, TestContext.inventoryPage().cartItemCount());
    }

    @Then("the login page should be displayed")
    public void verifyLoginPage() {
        assertTrue(TestContext.loginPage().loginButtonIsVisible());
    }
}
