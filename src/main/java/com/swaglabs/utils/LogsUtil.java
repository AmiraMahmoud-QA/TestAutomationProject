package com.swaglabs.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtil {
public static final String LOGS_PATH="test-outputs/logs";
    private LogsUtil() {
    }

    private static Logger logger() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        return LogManager.getLogger(className);
    }
public static void logTrace(String...message) {

        logger().trace(String.join(" ",message));
    }
    public static void logInfo(String...message) {

        logger().info(String.join(" ",message));
    }

    public static void logDebug(String...message) {
        logger().debug(String.join(" ",message));
    }

    public static void logWarn(String...message) {
        logger().warn(String.join(" ",message));
    }

    public static void logError(String...message) {
        logger().error(String.join(" ",message));
    }
    public static void logFatal(String...message) {
        logger().fatal(String.join(" ",message));
    }
}



