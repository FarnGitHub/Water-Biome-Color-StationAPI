package farn.water_biome_color.config;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.icon_handler.IconHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.TranslationStorage;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;


public class ConfigScreen extends Screen {
    private final Screen parent;

    public ConfigScreen(Screen parent) {
        this.parent = parent;
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(0);
        super.render(mouseX, mouseY, delta);
        this.drawCenteredTextWithShadow(minecraft.textRenderer, "Water Biome's Color Configuration", this.width / 2, 12, 16777215);
        IconHandler.INSTANCE.drawIcon(minecraft, this);
    }

    @SuppressWarnings("unchecked")
    public void init() {
        super.init();
        TranslationStorage i18n = TranslationStorage.getInstance();
        ButtonWidget toggleButton = centeredButton(new ButtonWidget(0, 0, 0, getWaterStyleDisplayText()));
        this.buttons.add(toggleButton);
        this.buttons.add(new ButtonWidget(1, this.width / 2 - 100, this.height / 6 + 168, i18n.get("gui.done")));
    }

    protected void buttonClicked(ButtonWidget button) {
        if (button.active) {
            if(button.id == 0) {
                int newOrdinal = (WaterBiomeColor.STYLE.ordinal() + 1) % WaterStyle.values().length;
                WaterBiomeColor.STYLE = WaterStyle.values()[newOrdinal];
                button.text = getWaterStyleDisplayText();
                if(minecraft.world != null) minecraft.worldRenderer.reload();
            } else if (button.id == 1) {
                this.minecraft.setScreen(this.parent);
            }
        }
    }

    public void removed() {
        WaterStyle.writeOptions();
        super.removed();
    }

    private ButtonWidget centeredButton(ButtonWidget button) {
        button.x = (this.width - button.width) / 2;
        button.y = (int)((this.height - button.height) / (1.25D));
        return button;
    }

    private String getWaterStyleDisplayText() {
        return "Water Style: " + WaterBiomeColor.STYLE.displayName;
    }

    public void tick() {
        super.tick();
        this.minecraft.textureManager.tick();
    }

}
