package com.swaglabs.drivers;
import io.qameta.allure.Step;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxFactory extends AbstractDriver implements WebDriverOptionsAbstract<FirefoxOptions>{

    @Override
    public FirefoxOptions getOptions() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-infobars"); //اخفاء شريط المعلومات اللي يظهر أعلى المتصفح
        firefoxOptions.addArguments("--remote-allow-origins=*");//الاتصالات من أي عنوان
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.addArguments("--headless"); //شغل المتصفح في الوضع "الخفي" بدون واجهة رسومية
        return firefoxOptions;
    }


    @Step("Starting FirefoxDriver with custom options")
    public WebDriver startDriver() {
        return new FirefoxDriver(getOptions());
    }

}
