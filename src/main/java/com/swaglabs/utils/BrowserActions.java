package com.swaglabs.utils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;
    public BrowserActions(WebDriver driver) {
       this.driver=driver;
    }
    @Step("Navigate to URL: {0}")
    public  void navigateToUrl( String url) {
        driver.get(url);
        LogsUtil.logInfo("Navigated to URL: " + url);
    }
// get current URL
@Step("Get current URL")
    public  String getCurrentUrl() {
       LogsUtil.logInfo("Getting current URL");
        return driver.getCurrentUrl();
    }
// get page title
    @Step("Get page title")
    public  String getPageTitle() {
       LogsUtil.logInfo("Getting page title");
        return driver.getTitle();
    }
// refresh the page
    @Step("Refresh the page")
    public  void refreshPage() {
        LogsUtil.logInfo("Refreshing the page");
        LogsUtil.logInfo("Refreshing the page");
        driver.navigate().refresh();
    }

    // closeBrowser
    @Step("Close the browser")
    public  void closeBrowser() {
        LogsUtil.logInfo("Closing the browser");
        driver.quit();
    }

}