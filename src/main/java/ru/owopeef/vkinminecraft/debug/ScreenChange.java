package ru.owopeef.vkinminecraft.debug;

import org.apache.logging.log4j.Logger;
import ru.owopeef.vkinminecraft.Main;

public class ScreenChange {
    static Logger LOGGER = Main.LOGGER;
    public static void LOG(String SCREEN) {
        LOGGER.info("SCREEN=" + SCREEN);
    }
}
