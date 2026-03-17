package farn.water_biome_color.config;

import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.PreConfigSavedListener;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import net.minecraft.client.Minecraft;

public class ReloadWorldListener implements PreConfigSavedListener {
    private static Minecraft mc;

    @SuppressWarnings("all")
    @Override
    public void onPreConfigSaved(int source, GlassYamlFile oldValues, GlassYamlFile newValues) {
        if(FabricLoader.getInstance().getGameInstance() != null) {
            if(mc == null)
                mc = (Minecraft) FabricLoader.getInstance().getGameInstance();
            if(mc.world != null)
                mc.worldRenderer.reload();
        }
    }
}
