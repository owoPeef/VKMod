package ru.owopeef.urls.methods;

import org.json.JSONObject;
import ru.owopeef.urls.Requests;
import ru.owopeef.vkinminecraft.Main;

import java.util.Objects;

public class Users {
    public static JSONObject get(int user_id)
    {
        try {
            return Objects.requireNonNull(Requests.GET("users.get", "&user_ids=" + user_id)).getJSONArray("response").getJSONObject(0);
        } catch (Exception e) {
            Main.LOGGER.info("CODE ERROR: " + e.getMessage());
            return null;
        }
    }
}
