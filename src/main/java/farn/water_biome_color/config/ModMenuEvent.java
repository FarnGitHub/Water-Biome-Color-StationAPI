package farn.water_biome_color.config;

import net.danygames2014.modmenu.api.ConfigScreenFactory;
import net.danygames2014.modmenu.api.ModMenuApi;

public class ModMenuEvent implements ModMenuApi {

    public ConfigScreenFactory<ConfigScreen> getModConfigScreenFactory() {
        return ConfigScreen::new;
    }
}
