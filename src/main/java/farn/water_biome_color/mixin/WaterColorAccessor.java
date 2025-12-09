package farn.water_biome_color.mixin;

import net.minecraft.client.color.world.WaterColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WaterColors.class)
public interface WaterColorAccessor {

    @Accessor("colorMap")
    static int[] getColors() {
        throw new AssertionError();
    }
}
