package ru.owopeef.owomod.screens.VK;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import ru.owopeef.owomod.Config;

public class Selector extends Screen {
    public Selector() {
        super(Text.of("Selector"));
    }

    protected void init() {
        if (Config.ACCESS_TOKEN != null) {
            MinecraftClient.getInstance().setScreen(new Messenger());
        }
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 30, 66, 100, 20, Text.of(I18n.translate("vk.selector.login_number")), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Login())
        ));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 30, 106, 100, 20, Text.of(I18n.translate("vk.selector.login_token")), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Token())
        ));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, Text.of(I18n.translate("vk.selector.choose_auth")), this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
