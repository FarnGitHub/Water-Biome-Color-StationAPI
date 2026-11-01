package farn.water_biome_color.util;

import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

public interface RGBAModifier {

    void apply(float waterValue, LocalIntRef r, LocalIntRef g, LocalIntRef b, LocalIntRef a);
}
