package com.swaglabs.drivers;
import com.swaglabs.utils.*;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.fail;

public class GuiDriver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public Validations validations;


    public GuiDriver(String browserName) {
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);
        this.validations = new Validations(get());
    }


    public static WebDriver getInstance()
    {

        return driverThreadLocal.get();
    }


    private AbstractDriver getDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "firefox" -> new FireFoxFactory();
            case "edge" -> new EdgeFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }


    private void setDriver(WebDriver driver)
    {
        driverThreadLocal.set(driver);
    }


    public WebDriver get() {
        if (driverThreadLocal.get() == null) {
            LogsUtil.logError("Driver is null");
            fail("Driver is null");
            return null;
        }
        return driverThreadLocal.get();
    }


    public Validations validations() {
        return validations;
    }


    public ElementActions elementActions()
    {
        return new ElementActions(this);
    }

    public BrowserActions browserActions()
    {
        return new BrowserActions(get());
    }

    public Scrolling scrolling()
    {
        return new Scrolling(this);
    }


    public void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            LogsUtil.logInfo("Driver quit and removed from thread.");
        }
    }
}
