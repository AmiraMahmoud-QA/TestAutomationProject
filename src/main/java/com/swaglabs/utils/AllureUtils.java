package com.swaglabs.utils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
public class AllureUtils {

    public static void attachLogsToAllure() {
        try {
            File logFile = FileUtil.getLatestFile(LogsUtil.LOGS_PATH);
            if (logFile == null || !logFile.exists()) {
                LogsUtil.logWarn("No log file found to attach to Allure report.");
                return;
            }

            try (InputStream stream = Files.newInputStream(logFile.toPath())) {

                Allure.addAttachment("logs", stream);
            }

            LogsUtil.logInfo("Logs attached safely to Allure report");
        } catch (IOException e) {
            LogsUtil.logError("Failed to attach logs to Allure report: " + e.getMessage());
        }
    }

    public static void attachScreenShotToAllure(String name, String path) {
        try {
            File screenshotFile = new File(path);
            if (screenshotFile.exists()) {
                try (InputStream stream = Files.newInputStream(Path.of(path))) {

                    Allure.addAttachment(name, stream);
                }
                LogsUtil.logInfo("Screenshot attached to Allure report: " + name);
            } else {
                LogsUtil.logWarn("Screenshot file not found: " + path);
            }
        } catch (IOException e) {
            LogsUtil.logError("Failed to attach screenshot to Allure report: " + e.getMessage());
        }
    }

    public static void generateAllureReport() {
        String osName = PropertiesUtils.getProperty("os.name");
        String allurePath = TerminalUtils.ALLURE_PATH;
        String allureReportPath = TerminalUtils.ALLURE_REPORT_PATH;
        String reportOutput = TerminalUtils.REPORT_PATH;

        if (osName != null && osName.toLowerCase().contains("win")) {
            String WIN = allurePath + ".bat";
            TerminalUtils.executeCommand(WIN, "generate", allureReportPath, "-o", reportOutput,"--single-file", "--clean");
            LogsUtil.logInfo("Allure report generated successfully on Windows");
        } else if (osName != null) {
            TerminalUtils.executeCommand(allurePath, "generate", allureReportPath, "-o", reportOutput, "--single-file","--clean");
            LogsUtil.logInfo("Allure report generated successfully on " + osName);
        } else {
            LogsUtil.logError("OS name not found in properties. Unable to generate Allure report.");
        }
    }
public static String renameReport(){
  File newName=new File("Report-"+System.currentTimeMillis()+".html");
  File oldName=new File(TerminalUtils.REPORT_PATH+File.separator+"index.html");
FileUtil.renameFile(oldName,newName);
    return newName.getName();

    }
    public static void openReport(String fileName) {
        String path = TerminalUtils.REPORT_PATH + File.separator + fileName;
        TerminalUtils.executeCommand("cmd.exe", "/c", "start", path);
        LogsUtil.logInfo("Allure report opened in browser");
    }

    @Attachment(value = "Logs", type = "text/plain")
    public static String attachLogsAsText() {
        try {
            File logFile = FileUtil.getLatestFile(LogsUtil.LOGS_PATH);
            if (logFile == null || !logFile.exists()) {
                return "No log file found.";
            }
            return Files.readString(logFile.toPath());
        }
        catch (IOException e) {
            return "Failed to attach logs: " + e.getMessage();
        }
    }
}