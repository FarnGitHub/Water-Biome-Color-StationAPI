package farn.water_biome_color;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import farn.water_biome_color.config.WaterStyle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.color.world.WaterColors;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.source.BiomeSource;

public class WaterBiomeColor {

    public static WaterStyle STYLE = WaterStyle.TMC;

    public static int getWaterColor(double temp, double humid) {
        humid *= temp;
        int tempIndex = (int)((1.0D - temp) * 255D);
        int humidIndex = (int)((1.0D - humid) * 255D);
        return WaterColors.colorMap[humidIndex << 8 | tempIndex];
    }

    public static void setDynamicTextureColor(float waterValue, LocalIntRef r, LocalIntRef g, LocalIntRef b, LocalIntRef a) {
        WaterBiomeColor.STYLE.modifier.apply(waterValue,r,g,b,a);
    }

    public static int getBiomeColor(Block block, BlockView view, int x, int y, int z, Operation<Integer> original) {
        if(STYLE != WaterStyle.OFF && block.material == Material.WATER) {
            BiomeSource biomeSource = view.method_1781();
            biomeSource.getBiomesInArea(x, z, 1, 1);
            double temp = biomeSource.temperatureMap[0];
            double humid = biomeSource.downfallMap[0];
            return WaterBiomeColor.getWaterColor(temp, humid);
        } else return original.call(view, x,y,z);
    }

    static {
        WaterStyle.readConfig();
    }
}
