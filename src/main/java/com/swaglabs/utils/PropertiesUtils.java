package com.swaglabs.utils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils() {
        super();
    }


    private static final String PROPERTIES_FILE_PATH = "src/main/resources/";
    private static final Properties properties = new Properties();


    public static void loadPropertiesFile() {
        try {
            Collection<File> propertiesFilesList = FileUtils.listFiles(
                    new File(PROPERTIES_FILE_PATH),
                    new String[]{"properties"},
                    true
            );

            for (File propertyFile : propertiesFilesList) {
                try (FileInputStream fis = new FileInputStream(propertyFile)) {
                    properties.load(fis);
                } catch (IOException e) {
                    LogsUtil.logError("Error while loading properties file: " + e.getMessage());
                }
            }

            LogsUtil.logInfo("Loaded properties files successfully.");
        } catch (Exception e) {
            LogsUtil.logError("Error while loading properties files: " + e.getMessage());
        }
    }


    public static String getProperty(String key) {
        try {
            return properties.getProperty(key);
        } catch (Exception e) {
            LogsUtil.logError("Error while getting property: " + e.getMessage());
            return "";
        }
    }
}
