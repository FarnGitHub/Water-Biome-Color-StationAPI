package farn.water_biome_color.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import farn.water_biome_color.WaterBiomeColor;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlock.class)
public class WaterBlockMixin {

    @WrapMethod(method="getColorMultiplier")
    public int setColor(BlockView view, int x, int y, int z, Operation<Integer> original) {
        return WaterBiomeColor.getBiomeColor((Block)(Object)this, view, x, y, z, original);
    }
}
