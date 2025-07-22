package com.swaglabs.utils;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    private String jsonReader;

    public JsonUtils(String jsonFileName) {
        try (InputStream inputStream = new FileInputStream(JSON_FILE_PATH + "/" + jsonFileName)) {
            jsonReader = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LogsUtil.logError("Error while reading JSON file: " + e.getMessage());
        }
    }

    public String getJsonData(String jsonPath) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtil.logError(e.getMessage() + " No result for json path: " + jsonPath);
        }
        return testData;
    }
}
