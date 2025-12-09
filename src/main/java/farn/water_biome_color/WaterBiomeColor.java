package farn.water_biome_color;

import farn.water_biome_color.config.ConfigHandler;
import farn.water_biome_color.config.EnumWaterRGBStyle;
import farn.water_biome_color.mixin.WaterColorAccessor;
import net.fabricmc.loader.api.FabricLoader;

public class WaterBiomeColor {

    public static boolean gcapi;

    public static int getWaterColor(double var0, double var2) {
        var2 *= var0;
        int var4 = (int)((1.0D - var0) * 255.0D);
        int var5 = (int)((1.0D - var2) * 255.0D);
        return WaterColorAccessor.getColors()[var5 << 8 | var4];
    }

    public static EnumWaterRGBStyle getRGBStyle() {
        if(gcapi) {
            return ConfigHandler.getInst.waterRGB;
        }
        return EnumWaterRGBStyle.TMC;
    }

    static {
        gcapi = FabricLoader.getInstance().isModLoaded("gcapi3");
    }
}
