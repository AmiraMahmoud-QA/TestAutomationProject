package com.swaglabs.listeners;
import com.swaglabs.utils.*;
import io.qameta.allure.*;
import org.testng.*;

import java.io.File;
@Epic("Regression Execution")
@Feature("Full Test Cycle")
@Severity(SeverityLevel.CRITICAL)
public class TestNGListeners implements IExecutionListener, ITestListener {

    @Override
    public void onExecutionStart() {
        LogsUtil.logInfo("Test Execution started");
        PropertiesUtils.loadPropertiesFile();

        FileUtil.cleanDirectory(new File("test-outputs/Logs"));
        FileUtil.cleanDirectory(new File("test-outputs/screenshots"));
        FileUtil.cleanDirectory(new File("test-outputs/allure-results"));

    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.logInfo("Test Execution finished");
        AllureUtils.generateAllureReport();
        String reportName= AllureUtils.renameReport();
        AllureUtils.openReport(reportName);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LogsUtil.logInfo("Test case " + methodName + " passed");
        Allure.addAttachment("Test Result", "text/plain", "Test case passed: " + methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LogsUtil.logInfo("Test case " + methodName + " failed");
        Allure.addAttachment("Test Result", "text/plain", "Test case failed: " + methodName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LogsUtil.logInfo("Test case " + methodName + " skipped");
        Allure.addAttachment("Test Result", "text/plain", "Test case skipped: " + methodName);
    }
}
