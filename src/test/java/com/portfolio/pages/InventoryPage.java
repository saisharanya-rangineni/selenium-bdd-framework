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

/**
 * Page Object for the SauceDemo Inventory (Products) page.
 * Handles product listing, cart operations, and navigation.
 */
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isOnInventoryPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return driver.getCurrentUrl().contains("inventory");
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public void addItemToCart(String itemName) {
        String buttonId = "add-to-cart-" + itemName.toLowerCase()
                .replace(" ", "-")
                .replace("sauce-labs-", "sauce-labs-");
        WebElement addButton = driver.findElement(By.id(buttonId));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addButton.click();
    }

    public void removeItemFromCart(String itemName) {
        String buttonId = "remove-" + itemName.toLowerCase().replace(" ", "-");
        WebElement removeButton = driver.findElement(By.id(buttonId));
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void navigateToCart() {
        cartLink.click();
    }

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }
}
