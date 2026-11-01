package farn.water_biome_color.config.icon_handler;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public interface IconHandler {

    IconHandler INSTANCE = FabricLoader.getInstance().isModLoaded("stationapi") ?
            new StapiIconHandler() : new VanillaIconHandler();

    void drawIcon(Minecraft mc, Screen screen);
}
