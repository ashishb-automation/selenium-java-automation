package dev.ashish.qa.steps;

import dev.ashish.qa.support.Configuration;
import dev.ashish.qa.support.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    @Given("the customer is on the login page")
    public void openLoginPage() {
        TestContext.loginPage().open();
    }

    @When("the customer signs in with valid credentials")
    public void signInWithValidCredentials() {
        TestContext.loginPage().signIn(Configuration.username(), Configuration.password());
    }

    @When("the customer signs in with username {string} and password {string}")
    public void signIn(String username, String password) {
        TestContext.loginPage().signIn(username, password);
    }

    @Then("the product inventory should be displayed")
    public void verifyInventory() {
        Assert.assertTrue(TestContext.loginPage().inventoryIsVisible());
    }

    @Then("an authentication error should be displayed")
    public void verifyError() {
        Assert.assertTrue(TestContext.loginPage().authenticationErrorIsVisible());
    }
}
