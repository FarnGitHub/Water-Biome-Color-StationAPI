package farn.water_biome_color.config.icon_handler;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.WaterStyle;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.opengl.GL11;

public class VanillaIconHandler implements IconHandler{
    @Override
    public void drawIcon(Screen screen) {
        float scale = 5F;
        float x = ((float) screen.width / (2 * scale)) - 8;
        int y = 10;
        GL11.glPushMatrix();
        float r = 1.0F;
        float g = 1.0F;
        float b = 1.0F;
        if(WaterBiomeColor.STYLE != WaterStyle.OFF) {
            int color = WaterBiomeColor.getWaterColor(0.5F, 1F);
            r = (float)(color >> 16 & 255) / 255.0F;
            g = (float)(color >> 8 & 255) / 255.0F;
            b = (float)(color & 255) / 255.0F;
        }
        GL11.glColor3f(r, g, b);
        GL11.glScalef(scale, scale, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
}
