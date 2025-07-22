package com.swaglabs.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TimestampUtils {
    public static String getTimestamp() {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    }

    public static String getShortStamp() {

        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    public static String getDateStamp() {

        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
