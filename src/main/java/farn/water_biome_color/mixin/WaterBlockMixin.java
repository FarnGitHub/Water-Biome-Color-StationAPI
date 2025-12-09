package farn.water_biome_color.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.EnumWaterRGBStyle;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlock.class)
public abstract class WaterBlockMixin {

    @SuppressWarnings("all")
    @WrapMethod(method="getColorMultiplier")
    public int setColor(BlockView view, int x, int y, int z, Operation<Integer> original) {
        if(WaterBiomeColor.getRGBStyle() != EnumWaterRGBStyle.OFF && ((Block)(Object)this).material == Material.WATER) {
            view.method_1781().getBiomesInArea(x, z, 1, 1);
            double var5 = view.method_1781().temperatureMap[0];
            double var7 = view.method_1781().downfallMap[0];
            return WaterBiomeColor.getWaterColor(var5, var7);
        } else {
            return original.call(view, x,y,z);
        }
    }
}
