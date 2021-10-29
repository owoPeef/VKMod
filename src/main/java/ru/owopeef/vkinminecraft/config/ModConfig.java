package ru.owopeef.vkinminecraft.config;

import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.debug.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig {
    public static String defaultConfigDirectory = System.getProperty("user.dir") + "\\config\\VKInMinecraft";
    public static Path defaultConfigPath = Paths.get(defaultConfigDirectory);
    public static String defaultConfigFilePath = defaultConfigDirectory + "\\config.json";
    public static File defaultConfigFile = new File(defaultConfigFilePath);
    public static void ConfigInit() {
        boolean configDirectoryExists = Files.exists(defaultConfigPath);
        Logger.Log("CONFIG_DIRECTORY_EXISTS=" + configDirectoryExists);
        if (!configDirectoryExists) {
            try {
                Files.createDirectories(defaultConfigPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (defaultConfigFile.createNewFile()) {
                Logger.Log("CONFIG_FILE_EXISTS=" + false);
            } else {
                Logger.Log("CONFIG_FILE_EXISTS=" + true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ConfigWrite(String MESSAGE_TO_WRITE) {
        try {
            FileWriter fileWriter = new FileWriter(defaultConfigFilePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(MESSAGE_TO_WRITE);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject ConfigRead() {
        try {
            FileReader fr = new FileReader(defaultConfigFilePath);
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = fr.read()) != -1) {
                sb.append((char)i);
            }
            if (sb.length() > 0) {
                return new JSONObject(sb.toString());
            } else {
                return null;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
