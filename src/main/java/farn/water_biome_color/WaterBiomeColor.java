package farn.water_biome_color;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import farn.water_biome_color.config.ConfigHandler;
import farn.water_biome_color.config.EnumWaterRGBStyle;
import farn.water_biome_color.mixin.WaterColorAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;

public class WaterBiomeColor {

    public static boolean gcapi;

    private static int getWaterColor(double var0, double var2) {
        var2 *= var0;
        int var4 = (int)((1.0D - var0) * 255.0D);
        int var5 = (int)((1.0D - var2) * 255.0D);
        return WaterColorAccessor.getColors()[var5 << 8 | var4];
    }

    private static EnumWaterRGBStyle getRGBStyle() {
        if(gcapi)
            return ConfigHandler.getInst.waterRGB;
        return EnumWaterRGBStyle.TMC;
    }

    public static void setDynamicTextureColor(float waterValue, LocalIntRef r, LocalIntRef g, LocalIntRef b, LocalIntRef a) {
        switch(getRGBStyle()) {
            case TMC: {
                r.set((int)(111.0F + waterValue * 76.0F));
                g.set((int)(139.0F + waterValue * 116.0F));
                b.set(255);
                a.set((int)(146.0F + waterValue * 50.0F));
                break;
            }
            case VIVIAN: {
                r.set(128);
                g.set(128);
                b.set(128);
                a.set((int)(146F + waterValue * 50F));
                break;
            }
            case VIBRANT: {
                r.set(192);
                g.set(192);
                b.set(255);
                a.set((int)(146F + waterValue * 50F));
                break;
            }
        }
    }

    public static int getBiomeColor(Block block, BlockView view, int x, int y, int z, Operation<Integer> original) {
        if(getRGBStyle() != EnumWaterRGBStyle.OFF && block.material == Material.WATER) {
            view.method_1781().getBiomesInArea(x, z, 1, 1);
            double var5 = view.method_1781().temperatureMap[0];
            double var7 = view.method_1781().downfallMap[0];
            return WaterBiomeColor.getWaterColor(var5, var7);
        } else {
            return original.call(view, x,y,z);
        }
    }

    static {
        gcapi = FabricLoader.getInstance().isModLoaded("gcapi3");
    }
}
