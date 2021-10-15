package ru.owopeef.vkinminecraft;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("VKInMinecraft");
	public static int CLIENT_ID = 3140623;
	public static String CLIENT_SECRET = "VeWdmVclDCtn6ihuP1nt";
	@Override
	public void onInitialize() {
		LOGGER.info("Mod initialized!");
	}
}
