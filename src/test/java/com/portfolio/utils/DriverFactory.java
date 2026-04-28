package com.portfolio.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory class for creating and managing WebDriver instances.
 * Supports Chrome and Firefox with configurable headless mode.
 * Uses ThreadLocal for thread-safe parallel execution.
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final int IMPLICIT_WAIT_SECONDS = 10;

    /**
     * Initialise a new WebDriver instance.
     * @param browser Browser name: "chrome" or "firefox"
     * @return Configured WebDriver instance
     */
    public static WebDriver initDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless()) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                if (isHeadless()) {
                    chromeOptions.addArguments("--headless=new");
                }
                webDriver = new ChromeDriver(chromeOptions);
                break;
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        webDriver.manage().window().maximize();
        driver.set(webDriver);

        return webDriver;
    }

    /** Get the current thread's WebDriver instance */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /** Navigate to the base URL */
    public static void navigateToBaseUrl() {
        getDriver().get(BASE_URL);
    }

    /** Quit the WebDriver and clean up */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }
}
