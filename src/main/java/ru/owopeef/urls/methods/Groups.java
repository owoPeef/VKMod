package ru.owopeef.urls.methods;

import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.urls.Requests;
import ru.owopeef.vkinminecraft.Main;

import java.util.Objects;

public class Groups {
    public static JSONObject getById(int group_id)
    {
        try {
            return Objects.requireNonNull(Requests.GET("groups.getById", "&group_id=" + group_id)).getJSONArray("response").getJSONObject(0);
        } catch (JSONException e) {
            Main.LOGGER.info("VK API ERROR: " + e.getMessage());
            return null;
        }
    }
}
