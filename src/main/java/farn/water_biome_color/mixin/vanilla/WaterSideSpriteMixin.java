package farn.water_biome_color.mixin.vanilla;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import farn.water_biome_color.WaterBiomeColor;
import net.minecraft.client.render.texture.WaterSideSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaterSideSprite.class)
public class WaterSideSpriteMixin {

    @Inject(method="tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/texture/WaterSideSprite;anaglyph:Z", shift = At.Shift.BEFORE))
    public void setNewColor(CallbackInfo ci, @Local(type=float.class, ordinal = 1) float waterValue, @Local(type=int.class, ordinal=1) LocalIntRef r, @Local(type=int.class, ordinal=2) LocalIntRef g, @Local(type=int.class, ordinal=3) LocalIntRef b, @Local(type=int.class, ordinal=4) LocalIntRef a) {
        WaterBiomeColor.setDynamicTextureColor(waterValue, r, g, b, a);
    }
}
