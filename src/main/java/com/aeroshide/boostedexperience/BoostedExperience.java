package com.aeroshide.boostedexperience;

import net.fabricmc.api.ModInitializer;
import com.aeroshide.rose_bush.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class BoostedExperience implements ModInitializer {
    public static final Logger LOG = LogManager.getLogger("BoostedExperience");
    public static double xpMultiplier = 5d;
    public static Config config;

    static {
        try {
            config = new Config(Path.of("config/BoostedExperience.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitialize() {

        if (config.getOption("multiplier") == null)
        {
            try {
                config.setOption("multiplier", 5.0d);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        xpMultiplier = ((Double) config.getOption("multiplier")).intValue();

        LOG.info("Loaded, for debugging purposes, the current value of the multiplier is: " + xpMultiplier);
    }
}
