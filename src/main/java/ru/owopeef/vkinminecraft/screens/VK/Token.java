package ru.owopeef.vkinminecraft.screens.VK;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import ru.owopeef.vkinminecraft.debug.ScreenChange;

public class Token extends Screen {
    public Token() {
        super(Text.of("Token Login"));
        ScreenChange.LOG(this.getClass().getSimpleName());
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
