package com.aeroshide.boostedexperience;

import net.fabricmc.api.ModInitializer;
import com.aeroshide.rose_bush.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BoostedExperience implements ModInitializer {
    public static final Logger LOG = LogManager.getLogger("BoostedExperience");
    public static int xpMultiplier = 5;
    public static Config config = new Config("config/BoostedExperience.json");
    @Override
    public void onInitialize() {

        if (config.getOption("multiplier") == null)
        {
            config.setOption("multiplier", 5.0);
        }

        xpMultiplier = ((Double) config.getOption("multiplier")).intValue();

        LOG.info("Loaded, for debugging purposes, the current value of the multiplier is: " + xpMultiplier);
    }
}
