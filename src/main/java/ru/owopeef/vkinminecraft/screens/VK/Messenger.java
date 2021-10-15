package ru.owopeef.vkinminecraft.screens.VK;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.urls.Requests;

@SuppressWarnings("IntegerDivisionInFloatingPointContext")
public class Messenger extends Screen {
    public Messenger() {
        super(Text.of("Messenger"));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, Text.of("VK"), this.width / 2, 20, 16777215);
        if (Config.FULLNAME != null)
        {
            TextRenderer tr = MinecraftClient.getInstance().textRenderer;
            String text = "Вы вошли как " + Config.FULLNAME;
            tr.draw(matrices, text, this.width / 2, 40, 16777215);
        }
        else
        {
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
        super.render(matrices, mouseX, mouseY, delta);
    }
}
