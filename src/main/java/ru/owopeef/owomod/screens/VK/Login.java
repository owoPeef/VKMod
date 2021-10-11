package ru.owopeef.owomod.screens.VK;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class Login extends Screen {
    private TextFieldWidget loginField;
    private TextFieldWidget passwordField;
    private ButtonWidget addButton;
    public static String login;
    public static String password;

    public Login() {
        super(Text.of("Login"));
    }

    public void tick() {
        this.loginField.tick();
        this.passwordField.tick();
    }

    protected void init() {
        assert this.client != null;
        this.client.keyboard.setRepeatEvents(true);
        this.loginField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 66, 200, 20, Text.of("Login"));
        this.loginField.setTextFieldFocused(true);
        this.loginField.setText("");
        this.loginField.setChangedListener((serverName) -> this.updateAddButton());
        this.addSelectableChild(this.loginField);
        this.passwordField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 106, 200, 20, Text.of("Password"));
        this.passwordField.setMaxLength(128);
        this.passwordField.setText("");
        this.passwordField.setChangedListener((address) -> this.updateAddButton());
        this.addSelectableChild(this.passwordField);
        this.addButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 96 + 18, 200, 20, new TranslatableText("addServer.add"), (button) -> this.addAndClose()));
        this.updateAddButton();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
        this.loginField.render(matrices, mouseX, mouseY, delta);
        this.passwordField.render(matrices, mouseX, mouseY, delta);
    }

    private void addAndClose() {
        login = this.loginField.getText();
        password = this.passwordField.getText();
    }

    private void updateAddButton() {
        this.addButton.active = ServerAddress.isValid(this.loginField.getText()) && !this.passwordField.getText().isEmpty();
    }
}
