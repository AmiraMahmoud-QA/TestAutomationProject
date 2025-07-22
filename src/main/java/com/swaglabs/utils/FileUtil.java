package com.swaglabs.utils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {
    private FileUtil() {
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsUtil.logWarn("No files found in directory: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.isFile() && file.lastModified() > latestFile.lastModified()) {
                latestFile = file;

            }
        }
        return latestFile;
    }
    public static void deleteFile(File direPath ) {
        if (direPath == null || !direPath.exists()) {
            LogsUtil.logWarn("File does not exist: " + direPath);
            return;
        }
        File [] fileList = direPath.listFiles();
        if (fileList == null) {
            LogsUtil.logWarn("No files found in directory: " + direPath.getAbsolutePath());
            return;
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                deleteFile(file);
            } else {
                try {
                    Files.delete(file.toPath());

                } catch (IOException e) {
                    LogsUtil.logError("Failed to delete file: " + file.getAbsolutePath() + " due to " + e.getMessage());
                }
            }
        }
    }
public static void cleanDirectory(File file){
        try {
            FileUtils.cleanDirectory(file);
        }
        catch (Exception exception){
            LogsUtil.logError(exception.getMessage());
        }
}

    public static void renameFile(File oldName, File newName) {
    try {
        File targetFile = oldName.getParentFile().getAbsoluteFile();
        String targetDirectory = targetFile+ File.separator + newName;
       FileUtils.copyFile(oldName, new File(targetDirectory));
       FileUtils.deleteQuietly(oldName);
       LogsUtil.logInfo("File renamed successfully from " + oldName.getName() + " to " + newName.getName());
    }
    catch (Exception e){
        LogsUtil.logError("Failed to rename file:  due to " + e.getMessage());
    }
    }

    public static void createDirectoryIfNotExists(File path) {
       if (!path.exists()){
           try {
               Files.createDirectories(path.toPath());
               LogsUtil.logInfo("Directory created: " + path.getAbsolutePath());
           } catch (IOException e) {
               LogsUtil.logError("Failed to create directory: " + path.getAbsolutePath() + " due to " + e.getMessage());
           }
       }
       else {
           LogsUtil.logInfo("Directory already exists: " + path.getAbsolutePath());
       }
    }
}
