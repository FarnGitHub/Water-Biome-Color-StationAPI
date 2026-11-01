package farn.water_biome_color.config.icon_handler;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.WaterStyle;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;

public class StapiIconHandler extends DrawContext implements IconHandler{
    final float scale = 5F;

    @Override
    public void drawIcon(Minecraft mc, Screen screen) {
        final float x = ((float) screen.width / (2 * scale)) - 8;
        final float y = 10;
        float r = 1.0F;
        float g = 1.0F;
        float b = 1.0F;
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
        Sprite sprite = Atlases.getTerrain().getTexture(Block.WATER.textureId).getSprite();
        float startU = sprite.getMinU();
        float startV = sprite.getMinV();
        float endU = sprite.getMaxU();
        float endV = sprite.getMaxV();
        Tessellator tess = Tessellator.INSTANCE;
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, 0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();
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
