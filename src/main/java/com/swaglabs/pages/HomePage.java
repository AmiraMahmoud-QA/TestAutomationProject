package com.swaglabs.pages;
import com.swaglabs.drivers.GuiDriver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //code
    //variables
    private GuiDriver driver;
private final By cartIcon=By.cssSelector("[data-test='shopping-cart-link']");
    //constructor
    public HomePage(GuiDriver driver) {
        this.driver = driver;
    }
    //locators

    //actions
@Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        driver.browserActions().navigateToUrl( PropertiesUtils.getProperty("homeURL"));
        return this;
    }
@Step("Add product to cart")
    public HomePage addProductToCart(String productName) {
        LogsUtil.logInfo("Adding product to cart: " + productName);
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        driver.elementActions().clickElement(addToCartButton);
        return this;
    }
    @Step("Assert product details in cart")
public CartPage clickCartIcon() {
        LogsUtil.logInfo("Clicking on cart icon");
        driver.elementActions().clickElement( cartIcon);
        return new CartPage(driver);
    }
    //Validations
    @Step("Assert product details in cart")
    public HomePage assertProductAddedToCart(String productName) {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        String actualValue = driver.elementActions().getText( addToCartButton);
        LogsUtil.logInfo("Actual value in cart: " + actualValue);
        driver.validations.validateEquals("Remove",actualValue, "Product not added to cart");
        LogsUtil.logInfo("product added to cart: " + productName);
        return this;
    }
}

