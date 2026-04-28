package com.portfolio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "[data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private void ensureOnCartPage() {
        if (!driver.getCurrentUrl().contains("/cart.html")) {
            driver.findElement(By.className("shopping_cart_link")).click();
            wait.until(ExpectedConditions.urlContains("/cart.html"));
        }
    }

    public boolean containsItem(String itemName) {
        ensureOnCartPage();
        return cartItems.stream()
                .anyMatch(item -> item.findElement(By.className("inventory_item_name"))
                        .getText().equalsIgnoreCase(itemName));
    }

    public boolean doesNotContainItem(String itemName) {
        ensureOnCartPage();
        return cartItems.stream()
                .noneMatch(item -> item.findElement(By.className("inventory_item_name"))
                        .getText().equalsIgnoreCase(itemName));
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}
