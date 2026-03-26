package com.aeroshide.boostedexperience.config;

import com.aeroshide.boostedexperience.config.screen.SettingsGUI;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screens.Screen;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (ConfigScreenFactory<@org.jetbrains.annotations.NotNull Screen>) SettingsGUI::new;
    }
}