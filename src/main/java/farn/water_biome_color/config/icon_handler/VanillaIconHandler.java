package farn.water_biome_color.config.icon_handler;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.WaterStyle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import org.lwjgl.opengl.GL11;

public class VanillaIconHandler extends DrawContext implements IconHandler{

    @Override
    public void drawIcon(Minecraft mc, Screen screen) {
        float scale = 5F;
        float x = ((float) screen.width / (2 * scale)) - 8;
        int y = 10;
        float r = 1.0F;
        float g = 1.0F;
        float b = 1.0F;
        int sprite = Block.WATER.textureId;
        int startU = sprite % 16 * 16;
        int startV = sprite / 16 * 16;
        int endU = startU + 16;
        int endV = startV + 16;
        Tessellator tess = Tessellator.INSTANCE;
        if(WaterBiomeColor.STYLE != WaterStyle.OFF) {
            int color = WaterBiomeColor.getWaterColor(0.5F, 1F);
            r = (float)(color >> 16 & 255) / 255.0F;
            g = (float)(color >> 8 & 255) / 255.0F;
            b = (float)(color & 255) / 255.0F;
        }
        int bgX = (screen.width / 2);
        int bgY = (int)(y * scale);
        int bgMinX = bgX - 50;
        int bgMaxX = bgX + 50;
        int bgMinY = bgY - 10;
        int bgMaxY = bgY + 90;

        this.fill(bgMinX - 1, bgMinY - 1, bgMaxX + 1, bgMaxY + 1, -6250336);
        this.fill(bgMinX, bgMinY, bgMaxX, bgMaxY, -16777216);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        mc.textureManager.bindTexture(mc.textureManager.getTextureId("/terrain.png"));
        tess.startQuads();
        tess.color(r, g, b);
        tess.vertex(x, y + 16, 0, startU, endV);
        tess.vertex(x + 16, y + 16, 0, endU, endV);
        tess.vertex(x + 16, y, 0, endU, startV);
        tess.vertex(x, y, 0, startU, startV);
        tess.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
