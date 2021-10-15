package ru.owopeef.urls;

import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.vkinminecraft.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests {
    public static JSONObject AUTH(String USERNAME, String PASSWORD) throws IOException, JSONException
    {
        String request_url = "https://oauth.vk.com/token?grant_type=password&client_id=" + Main.CLIENT_ID + "&client_secret=" + Main.CLIENT_SECRET + "&username="+USERNAME+"&password="+PASSWORD+"&v=5.131&2fa_supported=1";
        URL url = new URL(request_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }
    public static JSONObject GET(String method, String params)
    {
        try {
            String request_url = "https://api.vk.com/method/" + method + "?v=5.131&access_token=" + Config.ACCESS_TOKEN + params;
            URL url = new URL(request_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
