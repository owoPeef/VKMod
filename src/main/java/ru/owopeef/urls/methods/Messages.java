package ru.owopeef.urls.methods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.urls.Requests;
import ru.owopeef.urls.longpoll.LPThread;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.vkinminecraft.Main;
import ru.owopeef.vkinminecraft.debug.Logger;

import java.util.Objects;

public class Messages {
    public static void getLongPollServer() {
        try {
            JSONObject lp = Requests.GET("messages.getLongPollServer", "");
            assert lp != null;
            JSONObject resp = lp.getJSONObject("response");
            LPThread.server = resp.getString("server");
            LPThread.key = resp.getString("key");
            LPThread.ts = resp.getInt("ts");
            Logger.Log("LP_server=" + LPThread.server);
            Logger.Log("LP_key=" + LPThread.key);
            Logger.Log("LP_ts=" + LPThread.ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConversationsById(int peer_ids) {
        JSONObject getter = Objects.requireNonNull(Requests.GET("messages.getConversationsById", "&peer_ids=" + peer_ids));
        Logger.Log("messages.getConversationsById()=" + getter);
        JSONArray items = getter.getJSONObject("response").getJSONArray("items");
        JSONObject chat_settings = items.getJSONObject(0);
        String title;
        if (chat_settings.has("chat_settings")) {
            title = items.getJSONObject(0).getJSONObject("chat_settings").getString("title");
        } else {
            JSONObject profiles = getter.getJSONObject("response").getJSONArray("profiles").getJSONObject(0);
            title = profiles.getString("first_name") + " " + profiles.getString("last_name");
        }
        return title;
    }

    public static void getDialogs(int count, int offset)
    {
        try {
            JSONArray dialogs = Objects.requireNonNull(Requests.GET("messages.getDialogs", "&count=" + count + "&offset=" + offset)).getJSONObject("response").getJSONArray("items");
            int j = 0;
            while (true)
            {
                try {
                    JSONObject dialogue = dialogs.getJSONObject(j);
                    try {
                        int unread = dialogue.getInt("unread");
                        Config.UNREAD.add(unread);
                    } catch (JSONException e) {
                        Config.UNREAD.add(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSONObject msg = dialogue.getJSONObject("message");
                    String title = msg.getString("title");
                    String body = msg.getString("body");
                    if (title.length() == 0)
                    {
                        int user_id = msg.getInt("user_id");
                        if (String.valueOf(user_id).startsWith("-"))
                        {
                            JSONObject group = Groups.getById(Integer.parseInt(String.valueOf(user_id).substring(1)));
                            assert group != null;
                            title = group.getString("name");
                        }
                        else
                        {
                            JSONObject user = Users.get(user_id);
                            assert user != null;
                            String first_name = user.getString("first_name");
                            String last_name = user.getString("last_name");
                            title = first_name + " " + last_name;
                        }
                    }
                    if (body.length() == 0)
                    {
                        String attach_type = msg.getJSONArray("attachments").getJSONObject(0).getString("type");
                        if (Objects.equals(attach_type, "photo")) {
                            body = "Фотография";
                        } else if (Objects.equals(attach_type, "video")) {
                            body = "Видео";
                        } else if (Objects.equals(attach_type, "audio_message")) {
                            body = "Голосовое сообщение";
                        } else if (Objects.equals(attach_type, "audio")) {
                            body = "Аудиозапись";
                        } else if (Objects.equals(attach_type, "doc")) {
                            body = "Файл";
                        } else if (Objects.equals(attach_type, "sticker")) {
                            body = "Стикер";
                        }
                    }
                    if (body.length() > 15)
                    {
                        body = body.replace("\n", " ").substring(0, 16) + "...";
                    }
                    Config.TITLES.add(title);
                    Config.MESSAGES.add(body);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    break;
                }
                j++;
            }
        } catch (JSONException e) {
            if (!e.getMessage().startsWith("JSONArray"))
            {
                Main.LOGGER.info("VK API ERROR: " + e.getMessage());
            }
        } catch (Exception e) {
            Main.LOGGER.info("CODE ERROR: " + e.getMessage());
        }
    }
}
