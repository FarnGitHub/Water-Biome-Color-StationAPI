package farn.water_biome_color.mixin;

import farn.water_biome_color.station_texture.ArsenicFlowingWaterCustom;
import farn.water_biome_color.station_texture.ArsenicStillWaterCustom;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.impl.client.arsenic.Arsenic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Function;

@Mixin(Arsenic.class)
public class ArsenicMixin {

    @SuppressWarnings("all")
    @ModifyArg(method="registerTextures", at = @At(value="INVOKE", target = "Lnet/modificationstation/stationapi/api/client/texture/atlas/ExpandableAtlas;addTextureBinder(Lnet/modificationstation/stationapi/api/client/texture/atlas/Atlas$Sprite;Ljava/util/function/Function;)Lnet/modificationstation/stationapi/api/client/texture/binder/StationTextureBinder;", ordinal = 0), index=1)
    private static Function  overrideWaterTextureStill(Function par2) {
        return staticRef -> {
            return new ArsenicStillWaterCustom((Atlas.Sprite) staticRef);
        };
    }
    @SuppressWarnings("all")
    @ModifyArg(method="registerTextures", at = @At(value="INVOKE", target = "Lnet/modificationstation/stationapi/api/client/texture/atlas/ExpandableAtlas;addTextureBinder(Lnet/modificationstation/stationapi/api/client/texture/atlas/Atlas$Sprite;Ljava/util/function/Function;)Lnet/modificationstation/stationapi/api/client/texture/binder/StationTextureBinder;", ordinal = 1), index=1)
    private static Function overrideWaterTextureFlowing(Function par2) {
        return staticRef -> {
            return new ArsenicFlowingWaterCustom((Atlas.Sprite) staticRef);
        };
    }
}
