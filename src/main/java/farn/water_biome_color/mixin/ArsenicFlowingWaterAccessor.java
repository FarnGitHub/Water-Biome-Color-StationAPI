package farn.water_biome_color.mixin;

import net.modificationstation.stationapi.impl.client.arsenic.renderer.render.binder.ArsenicFlowingWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ArsenicFlowingWater.class)
public interface ArsenicFlowingWaterAccessor {

    @Accessor("ticks")
    int getTick();

    @Accessor("ticks")
    void setTick(int newTick);
}
