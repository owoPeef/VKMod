package ru.owopeef.urls.longpoll;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.vkinminecraft.debug.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class TestLP {
    // event ids : (3 || пользователь прочитал сообщение)
    // e_i = event_id
    // m_i = message_id
    // m = mask
    // p_i = peer_id
    // [{e_i},{m_i},{m},{p_i}],[{}, {p_i},{m_i},{}],[{c}]
    // [3,144880,1,579385782],[6,579385782,144880,0],[80,1,0]
    public static void LongPoll() {
        try {
            String request_url = "https://" + LPThread.server + "?act=a_check&key=" + LPThread.key + "&ts=" + LPThread.ts + "&wait=0&mode=128&version=3";
            URL url = new URL(request_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            LPThread.ts = jsonResponse.getInt("ts");
            JSONArray updates = jsonResponse.getJSONArray("updates");
            int a = 0;
            while (updates.length() != a) {
                JSONArray array = updates.getJSONArray(a);
                Logger.Log("LP_UPDATES_ARRAY=" + array);
                int b = 0;
                while (array.length() != b) {
                    // DialogueInitialization.Init();
                    String curr = "";
                    // СОБЫТИЕ #4
                    if (array.getInt(0) == 4) {
                        int c = 0;
                        for (String title: Config.TITLES) {
                            // int f = array.getInt(3);
                            int id_in_list;
                            String new_title = Config.TITLES.get(c);
                            if (Objects.equals(title, new_title)) {
                                id_in_list = c;
                                int d = 0;
                                int k = 0;
                                List<String> stringTitles = Config.TITLES;
                                List<Integer> stringUnread = Config.UNREAD;
                                List<String> stringMessages = Config.MESSAGES;
                                while (Config.MESSAGES.size() != d) {
                                    if (id_in_list != d) {
                                        k++;
                                        String currTitle = stringTitles.get(d);
                                        int unread = stringUnread.get(d);
                                        String msg = stringMessages.get(d);
                                        Config.TITLES.set(k, currTitle);
                                        Config.UNREAD.set(k, unread);
                                        Config.MESSAGES.set(k, msg);
                                    }
                                    d++;
                                }
                                Config.TITLES.set(0, new_title);
                                Config.UNREAD.set(0, stringUnread.get(id_in_list) + 1);
                                Config.MESSAGES.set(0, array.getString(5));
                            }
                            c++;
                        }
                        if (b == 5) {
                            curr = array.getString(b);
                        } else {
                            curr = String.valueOf(array.getInt(b));
                        }
                    }
                    Logger.Log("LP_CURR=" + curr);
                    if (b == 0) {
                        Logger.Log("MESSAGES_LP_CODE=" + curr);
                    }
                    b++;
                }
                a++;
            }
            Logger.Log(jsonResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
