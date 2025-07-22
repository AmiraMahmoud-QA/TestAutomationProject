
/*package com.swaglabs.utils;

public class TerminalUtils {
    public static void executeCommand(String...command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // This will print the output to the console
            Process process = processBuilder.start();
            process.waitFor();
           LogsUtil.logInfo("Commeng exectued successfully  "+String.join(" ",command));
        } catch (Exception e) {
            LogsUtil.logError("Failed to execute command: " + String.join(" ", command) + " - " + e.getMessage());
        }
    }
}*/
package com.swaglabs.utils;

import java.io.IOException;
public class TerminalUtils {
public static final String ALLURE_PATH = "C:\\Users\\DELL\\allure-2.34.1\\bin\\allure";
    public static final String ALLURE_REPORT_PATH = "test-outputs/allure-results";
    public static final String REPORT_PATH = "test-outputs/allure-report";

    public static void executeCommand(String... commandParts) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();

            LogsUtil.logInfo("Commeng exectued successfully  " + String.join(" ", commandParts));
        } catch (IOException | InterruptedException e) {
            LogsUtil.logError("Failed to execute terminal command: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
