package ru.owopeef.vkinminecraft.screens.VK;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.urls.Requests;

public class Messenger extends Screen {
    public Messenger() {
        super(Text.of("Messenger"));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, Text.of("VK"), this.width / 2, 20, 16777215);
        String text = "Вы вошли как " + Config.FULLNAME;
        if (Config.FULLNAME == null) {
            try {
                JSONObject fullname_obj = Requests.GET("users.get", "&user_id=" + Config.USER_ID);
                assert fullname_obj != null;
                JSONObject response = fullname_obj.getJSONArray("response").getJSONObject(0);
                String firstname = response.getString("first_name");
                String lastname = response.getString("last_name");
                Config.FULLNAME = firstname + " " + lastname;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        drawCenteredText(matrices, this.textRenderer, text, this.width / 2, 40, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
