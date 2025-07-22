package com.swaglabs.utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
public class ScreenShotsUtils {

    private ScreenShotsUtils() {
    }
    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    public static void takeScreenShot(WebDriver driver, String screenShotName) throws IOException {
        try {
            File screenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenShotName + ".png");
            FileUtils.copyFile(screenShot, screenshotFile);
           AllureUtils.attachScreenShotToAllure(screenShotName,screenshotFile.getPath());
        }
catch (Exception e){
      LogsUtil.logError("Failed to take screenshot: " + e.getMessage());
}

    }

}

