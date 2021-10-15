package ru.owopeef.vkinminecraft.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.owopeef.vkinminecraft.screens.VK.Selector;

@Mixin(GameMenuScreen.class)
public abstract class GMMixin extends Screen
{
    protected GMMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addCustomButton(CallbackInfo ci)
    {
        this.addDrawableChild(new ButtonWidget(0, 0, 20, 20, Text.of("VK"), (buttonWidget) ->
                MinecraftClient.getInstance().setScreen(new Selector())
        ));
    }
}
