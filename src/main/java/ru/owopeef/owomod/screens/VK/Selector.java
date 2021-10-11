package ru.owopeef.owomod.screens.VK;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class Selector extends Screen {
    public Selector() {
        super(Text.of("Select"));
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100 + 205, 66, 50, 20, Text.of("Login via Password"), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Login())
        ));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100 + 205, 106, 50, 20, Text.of("Login via Token"), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Token())
        ));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
