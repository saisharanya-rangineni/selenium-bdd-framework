package com.portfolio.steps;

import com.portfolio.pages.CartPage;
import com.portfolio.pages.CheckoutPage;
import com.portfolio.pages.InventoryPage;
import com.portfolio.utils.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @When("the user adds {string} to the cart")
    public void theUserAddsToTheCart(String itemName) {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addItemToCart(itemName);
    }

    @Then("the cart badge should show {string}")
    public void theCartBadgeShouldShow(String expectedCount) {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        assertEquals(expectedCount, inventoryPage.getCartBadgeCount(),
                "Cart badge count should be " + expectedCount);
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContain(String itemName) {
        cartPage = new CartPage(DriverFactory.getDriver());
        assertTrue(cartPage.containsItem(itemName),
                "Cart should contain '" + itemName + "'");
    }

    @And("the user removes {string} from the cart")
    public void theUserRemovesFromTheCart(String itemName) {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.removeItemFromCart(itemName);
    }

    @Then("the cart should not contain {string}")
    public void theCartShouldNotContain(String itemName) {
        cartPage = new CartPage(DriverFactory.getDriver());
        assertTrue(cartPage.doesNotContainItem(itemName),
                "Cart should not contain '" + itemName + "'");
    }

    @When("the user navigates to the cart")
    public void theUserNavigatesToTheCart() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.navigateToCart();
    }

    @When("the user clicks continue shopping")
    public void theUserClicksContinueShopping() {
        cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.clickContinueShopping();
    }

    @Then("the user should be on the inventory page")
    public void theUserShouldBeOnTheInventoryPage() {
        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory"),
                "User should be on the inventory page");
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.proceedToCheckout();
    }

    @When("the user enters checkout information:")
    public void theUserEntersCheckoutInformation(DataTable dataTable) {
        Map<String, String> info = dataTable.asMaps().get(0);
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.enterCheckoutInfo(
                info.get("firstName"),
                info.get("lastName"),
                info.get("postalCode")
        );
    }

    @When("the user completes the order")
    public void theUserCompletesTheOrder() {
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.completeOrder();
    }

    @Then("the order confirmation message should be displayed")
    public void theOrderConfirmationMessageShouldBeDisplayed() {
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        assertTrue(checkoutPage.isConfirmationDisplayed(),
                "Order confirmation message should be displayed");
    }

    @Then("the confirmation should contain {string}")
    public void theConfirmationShouldContain(String expectedText) {
        checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        String confirmationText = checkoutPage.getConfirmationText();
        assertTrue(confirmationText.contains(expectedText),
                "Confirmation should contain '" + expectedText + "' but was '" + confirmationText + "'");
    }
}
