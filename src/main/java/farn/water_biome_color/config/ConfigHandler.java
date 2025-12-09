package farn.water_biome_color.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class ConfigHandler {
    @ConfigRoot(value="water_biome_color_option", visibleName = "Water Biome Color")
    public static Instance getInst = new Instance();

    public static class Instance {
        @ConfigEntry(name = "Water's Biome Color Style")
        public EnumWaterRGBStyle waterRGB = EnumWaterRGBStyle.TMC;
    }
}
