package com.aeroshide.boostedexperience.config.screen;

import com.aeroshide.boostedexperience.BoostedExperience;
import com.aeroshide.rose_bush.gui.DoubleFieldWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.io.IOException;

public class SettingsGUI extends Screen {

    public static final Component TITLE = Component.translatable("boostedexperience.configScreen");

    private final Screen parent;
    private DoubleFieldWidget xpMultiplierField;
    private Button resetXPField;

    private static final Component XP_MULTIPLIER_TEXT = Component.translatable("boostedexperience.xpMultiplier");

    public SettingsGUI(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    @Override
    public void init() {

        this.xpMultiplierField = new DoubleFieldWidget(this.font, this.width / 2 - 100, 44, 200, 20, Component.translatable("boostedexperience.xpMultiplier"));
        this.xpMultiplierField.setMaxLength(4);
        this.xpMultiplierField.setValue(String.valueOf(BoostedExperience.config.getOption("multiplier"))); // Default value, adjust as needed

        this.resetXPField = this.addRenderableWidget(Button.builder(Component.literal("R"), (button) -> {
            xpMultiplierField.setValue("5.0"); // Reset to default value
        }).bounds(this.xpMultiplierField.getX() + 205, this.xpMultiplierField.getY(), 20, 20).build());

        Button discardButton = this.addRenderableWidget(Button.builder(Component.translatable("boostedexperience.discard"), (button) -> {
            minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 - 110, this.height / 2 + 90, 100, 20).build());

        Button acceptButton = this.addRenderableWidget(Button.builder(Component.translatable("boostedexperience.save"), (button) -> {
            BoostedExperience.xpMultiplier = this.xpMultiplierField.getDouble();
            try {
                BoostedExperience.config.setOption("multiplier", this.xpMultiplierField.getDouble());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            minecraft.setScreen(this.parent);
        }).bounds(this.width / 2 + 20, this.height / 2 + 90, 100, 20).build());

        this.setInitialFocus(this.xpMultiplierField);

        this.addRenderableWidget(this.xpMultiplierField);
    }

    @Override
    public void extractRenderState(final @NonNull GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        graphics.centeredText(this.font, this.title, this.width / 2, 20, 0xFFFFFFFF);

        graphics.text(this.font, XP_MULTIPLIER_TEXT, this.width / 2 - 100, this.xpMultiplierField.getY() - 10, 0xFFA0A0A0);
    }
}