package farn.water_biome_color.config;

import farn.water_biome_color.WaterBiomeColor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.TranslationStorage;
import org.lwjgl.opengl.GL11;

public class ConfigScreen extends Screen {
    Screen parent;

    public ConfigScreen(Screen parent) {
        this.parent = parent;
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        super.render(mouseX, mouseY, delta);
        this.drawCenteredTextWithShadow(minecraft.textRenderer, "Water Biome's Color Configuration", this.width / 2, 12, 16777215);
        drawWaterIcon();
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

    private void drawWaterIcon() {
        float scale = 0.5F;
        int textureScale = (int)(256 * scale);
        GL11.glPushMatrix();
        int textureId = minecraft.textureManager.getTextureId(WaterBiomeColor.STYLE.iconPath);
        minecraft.textureManager.bindTexture(textureId);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glScalef(scale, scale, 0.0F);
        drawTexture((this.width - textureScale), (this.height - textureScale) / 2, 0,0, 256, 256);
        GL11.glPopMatrix();
    }

}
