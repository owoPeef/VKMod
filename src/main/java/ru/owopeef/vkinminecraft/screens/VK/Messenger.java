package ru.owopeef.vkinminecraft.screens.VK;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.json.JSONException;
import org.json.JSONObject;
import ru.owopeef.urls.DialogueInitialization;
import ru.owopeef.urls.Requests;
import ru.owopeef.urls.longpoll.LPThread;
import ru.owopeef.urls.methods.Messages;
import ru.owopeef.vkinminecraft.Config;
import ru.owopeef.vkinminecraft.debug.ScreenChange;

import java.util.Iterator;

public class Messenger extends Screen {
    public String text;
    LPThread th = new LPThread();
    public Messenger() {
        super(Text.of("Messenger"));
        ScreenChange.LOG(this.getClass().getSimpleName());
    }

    @Override
    protected void init() {
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
        DialogueInitialization.Init();
        Messages.getLongPollServer();
        th.start();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        text = Config.FULLNAME;

        Iterator<String> i1 = Config.TITLES.iterator();
        Iterator<String> i2 = Config.MESSAGES.iterator();
        int a = 0;
        int offset = 0;
        while(i1.hasNext() && i2.hasNext())
        {
            String title = Config.TITLES.get(a);
            String msg = Config.MESSAGES.get(a);
            int unread = Config.UNREAD.get(a);
            drawStringWithShadow(matrices, this.textRenderer, title, 10, 60 + offset, 16777215);
            drawStringWithShadow(matrices, this.textRenderer, msg, 10, 60 + 10 + offset, 6450554);
            if (unread > 0) {
                drawStringWithShadow(matrices, this.textRenderer, "●", this.width - 10, 65 + offset, 5341624);
                drawStringWithShadow(matrices, this.textRenderer, String.valueOf(unread), this.width - 10, 65 + offset, 16777215);
            }
            offset = offset + 40;
            a++;
            i1.next();
            i2.next();
        }

        drawCenteredText(matrices, this.textRenderer, text, this.width / 2, 40, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.of("VK"), this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
