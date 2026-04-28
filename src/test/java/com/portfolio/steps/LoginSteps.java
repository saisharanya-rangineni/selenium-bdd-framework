package com.portfolio.steps;

import com.portfolio.pages.LoginPage;
import com.portfolio.pages.InventoryPage;
import com.portfolio.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step definitions for login feature scenarios.
 * Maps Gherkin steps to Java automation code.
 */
public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        DriverFactory.navigateToBaseUrl();
        loginPage = new LoginPage(DriverFactory.getDriver());
        assertTrue(loginPage.isOnLoginPage(), "User should be on the login page");
    }

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        DriverFactory.navigateToBaseUrl();
        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(username, "secret_sauce");
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        assertTrue(inventoryPage.isOnInventoryPage(), "User should be on inventory page after login");
    }

    @When("the user enters username {string}")
    public void theUserEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the inventory page")
    public void theUserShouldBeRedirectedToTheInventoryPage() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        assertTrue(inventoryPage.isOnInventoryPage(), "User should be on the inventory page");
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver().getTitle();
        assertTrue(actualTitle.contains(expectedTitle),
                "Page title should contain '" + expectedTitle + "' but was '" + actualTitle + "'");
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be visible");
    }

    @Then("the error message should contain {string}")
    public void theErrorMessageShouldContain(String expectedText) {
        String errorText = loginPage.getErrorMessage();
        assertTrue(errorText.contains(expectedText),
                "Error message should contain '" + expectedText + "' but was '" + errorText + "'");
    }

    @Then("the login result should be {string}")
    public void theLoginResultShouldBe(String expectedResult) {
        if ("success".equals(expectedResult)) {
            inventoryPage = new InventoryPage(DriverFactory.getDriver());
            assertTrue(inventoryPage.isOnInventoryPage(), "Login should succeed");
        } else {
            assertTrue(loginPage.isErrorDisplayed(), "Login should fail with error");
        }
    }
}
