package com.swaglabs.pages;
import com.swaglabs.drivers.GuiDriver;
import com.swaglabs.utils.CustomSoftAssertion;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.swaglabs.utils.PropertiesUtils.getProperty;

public class LoginPage {
    private final GuiDriver driver;

    public LoginPage(GuiDriver driver) {
        this.driver = driver;
    }

    //  locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    //  actions
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        driver.elementActions().sendKeys(usernameField, username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        driver.elementActions().sendKeys(passwordField, password);
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
        return this;
    }

    public String getPageTitle() {
        return driver.get().getTitle();
    }

    public String getErrorMessage() {
        return driver.elementActions().getText(errorMessage);
    }

    @Step("Navigate to login page: {url}")
    public void navigateToLoginPage() {
        driver.browserActions().navigateToUrl(getProperty("baseURL"));
    }

    //  soft Validations
    @Step("Assert login page URL")
    public LoginPage assertLoginPgeUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(
                driver.browserActions().getCurrentUrl(),
                getProperty("homeURL"),
                "Page URL is not as expected"
        );
        return this;
    }

    @Step("Assert login page title")
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertion.softAssertion.assertEquals(
                getPageTitle(),
                getProperty("appTitle"),
                "Page title is not as expected"
        );
        return this;
    }

    @Step("Assert successful login using soft assertions")
    public LoginPage assertSuccessfullLoginSoft() {
        return assertLoginPgeUrl().assertLoginPageTitle();
    }

    public boolean isLoginSuccessful() {
        return driver.get().getTitle().equals("Swag Labs");
    }

    //  assertions using Validations
    @Step("Assert successful login")
    public HomePage assertSuccessfullLogin() {
        driver.validations().validatePageUrl(getProperty("homeURL"));
        return new HomePage(driver);
    }

    @Step("Assert unsuccessful login")
    public HomePage assertUnsuccessfulLogin() {
        driver.validations().validateEquals(
                getProperty("errorMessage"),
                getErrorMessage(),
                "error message is not as expected"
        );
        driver.validations().validatePageUrl(getProperty("baseURL"));
        driver.validations().validatePageTitle(getProperty("appTitle"));
        Assert.assertFalse("Login should not be successful", isLoginSuccessful());
        return new HomePage(driver);
    }
}
