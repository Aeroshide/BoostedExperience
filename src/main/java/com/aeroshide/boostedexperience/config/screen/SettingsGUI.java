package com.aeroshide.boostedexperience.config.screen;

import com.aeroshide.boostedexperience.BoostedExperience;
import com.aeroshide.rose_bush.gui.DoubleFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import com.aeroshide.rose_bush.gui.IntFieldWidget;

public class SettingsGUI extends Screen {

    private final Screen parent;
    private DoubleFieldWidget xpMultiplierField;
    private ButtonWidget resetXPField;

    private static final Text XP_MULTIPLIER_TEXT = Text.translatable("boostedexperience.xpMultiplier");

    public SettingsGUI(Screen parent) {
        super(Text.translatable("boostedexperience.configScreen"));
        this.parent = parent;
    }

    @Override
    public void init() {
        this.xpMultiplierField = new DoubleFieldWidget(this.textRenderer, this.width / 2 - 100, 44, 200, 20, Text.translatable("boostedexperience.xpMultiplier"));
        this.xpMultiplierField.setMaxLength(4);
        this.xpMultiplierField.setText(String.valueOf(BoostedExperience.config.getOption("multiplier"))); // Default value, adjust as needed

        this.resetXPField = this.addDrawableChild(ButtonWidget.builder(Text.literal("R"), (button) -> {
            xpMultiplierField.setText("5.0"); // Reset to default value
        }).dimensions(this.xpMultiplierField.getX() + 205, this.xpMultiplierField.getY(), 20, 20).build());

        ButtonWidget discardButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("boostedexperience.discard"), (button) -> {
            client.setScreen(this.parent);
        }).dimensions(this.width / 2 - 110, this.height / 2 + 90, 100, 20).build());

        ButtonWidget acceptButton = this.addDrawableChild(ButtonWidget.builder(Text.translatable("boostedexperience.save"), (button) -> {
            BoostedExperience.xpMultiplier = this.xpMultiplierField.getDouble();
            BoostedExperience.config.setOption("multiplier", this.xpMultiplierField.getDouble());
            client.setScreen(this.parent);
        }).dimensions(this.width / 2 + 20, this.height / 2 + 90, 100, 20).build());

        this.setInitialFocus(this.xpMultiplierField);
        this.addSelectableChild(this.xpMultiplierField);
    }

    @Override
    public void render(final DrawContext context, final int mouseX, final int mouseY, final float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 16777215);
        context.drawTextWithShadow(this.textRenderer, XP_MULTIPLIER_TEXT, this.width / 2 - 100, this.xpMultiplierField.getY() - 10, 10526880);

        this.xpMultiplierField.render(context, mouseX, mouseY, delta);
    }
}
