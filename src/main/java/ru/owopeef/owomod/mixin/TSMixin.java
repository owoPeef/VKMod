package ru.owopeef.owomod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.owopeef.owomod.screens.VK.Selector;

@Mixin(TitleScreen.class)
public abstract class TSMixin extends Screen
{
    protected TSMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void addCustomButton(int y, int spacingY, CallbackInfo ci)
    {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100 + 205, y + 24, 50, 20, Text.of("VK"), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Selector())
        ));
    }
}
