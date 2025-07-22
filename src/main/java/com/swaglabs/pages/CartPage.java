package com.swaglabs.pages;
import com.swaglabs.drivers.GuiDriver;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtil;
import com.swaglabs.utils.Scrolling;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class CartPage {
    //variables
    private GuiDriver driver;

    //constructor
    public CartPage(GuiDriver driver) {
        this.driver = driver;
    }

    //loccators

    By productName = By.cssSelector(".inventory_item_name");
    By productPrice = By.cssSelector(".inventory_item_price");
    By checkoutButton = By.xpath("//button[@id='checkout']");

    //actions
    @Step("Navigate to Cart Page")
private   String getProductName() {
    return driver.elementActions().getText( productName);

    }
    @Step("Get product price from cart")
    private String getProductPrice() {
    return driver.elementActions().getText( productPrice);
}

    @Step("Get product name from cart")
    public String fetchProductName() {
        return getProductName();
    }

    @Step("Get product price from cart")
    public String fetchProductPrice() {
        return getProductPrice();
    }


    @Step("Click on Checkout button")
    public InformationPage clickCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        driver.scrolling().scrollToElement( checkoutButton);
        btn.click();
        return new InformationPage(driver);
    }

//validations
    @Step("Assert product details in cart")
    public CartPage asserProductDetails(String productName ,String productPrice) {
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName,productName,"Product name does not match");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice, productPrice, "Product price does not match");
        return this;
    }

}