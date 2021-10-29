package ru.owopeef.vkinminecraft.debug;

import ru.owopeef.vkinminecraft.Main;

public class Logger {
    static org.apache.logging.log4j.Logger LOGGER = Main.LOGGER;
    public static void Log(String MESSAGE) {
        LOGGER.info(MESSAGE);
    }
}
