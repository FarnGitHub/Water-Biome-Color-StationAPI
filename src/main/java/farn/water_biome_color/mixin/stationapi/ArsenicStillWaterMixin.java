package farn.water_biome_color.mixin.stationapi;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import farn.water_biome_color.WaterBiomeColor;
import net.modificationstation.stationapi.impl.client.arsenic.renderer.render.binder.ArsenicStillWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ArsenicStillWater.class)
public class ArsenicStillWaterMixin {

    @Inject(method="tick", at = @At(value = "FIELD", target = "Lnet/modificationstation/stationapi/impl/client/arsenic/renderer/render/binder/ArsenicStillWater;anaglyph:Z", shift = At.Shift.BEFORE))
    public void setNewColor(CallbackInfo ci, @Local(type=float.class, ordinal = 1) float waterValue, @Local(type=int.class, ordinal=3) LocalIntRef r, @Local(type=int.class, ordinal=4) LocalIntRef g, @Local(type=int.class, ordinal=5) LocalIntRef b, @Local(type=int.class, ordinal=6) LocalIntRef a) {
        WaterBiomeColor.setDynamicTextureColor(waterValue, r, g, b, a);
    }

}
