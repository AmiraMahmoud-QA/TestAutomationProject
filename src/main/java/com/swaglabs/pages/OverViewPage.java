package com.swaglabs.pages;
import com.swaglabs.drivers.GuiDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class OverViewPage {
    //variables
    private GuiDriver driver;
    //constructor
    public OverViewPage(GuiDriver driver){
        this.driver=driver;
    }
    //locators
    private final By finishButton=  By.id("finish");

    @Step("Click on finish button")
    public ConfirmationPage clickOnFinishButton() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        driver.scrolling().scrollToElement( finishButton);
        btn.click();
        return new ConfirmationPage(driver);
    }
    //validations
}
