package com.portfolio.steps;

import com.portfolio.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber hooks for test lifecycle management.
 * Handles WebDriver setup before each scenario and cleanup after.
 * Captures screenshots on failure for debugging.
 */
public class Hooks {

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverFactory.initDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        DriverFactory.quitDriver();
    }
}
