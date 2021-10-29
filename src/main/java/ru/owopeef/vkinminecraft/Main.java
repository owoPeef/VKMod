package ru.owopeef.vkinminecraft;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.config.ModConfig;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("VKInMinecraft");

	// CLIENT_ID AND CLIENT_SECRET TAKEN FROM https://github.com/Kutabe/vk/blob/master/vk.go#L14#L15
	public static int CLIENT_ID = 3140623;
	public static String CLIENT_SECRET = "VeWdmVclDCtn6ihuP1nt";

	@Override
	public void onInitialize() {
		LOGGER.info("Mod initialized!");
		ModConfig.ConfigInit();
		try {
			JSONObject json = ModConfig.ConfigRead();
			if (json != null) {
				ru.owopeef.vkinminecraft.debug.Logger.Log("CONFIG_ACCESS_TOKEN=" + json.getString("access_token"));
				Config.ACCESS_TOKEN = json.getString("access_token");
				Config.USER_ID = json.getInt("user_id");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
