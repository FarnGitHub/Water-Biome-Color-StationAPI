package farn.water_biome_color.config.icon_handler;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.config.WaterStyle;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;

public class StapiIconHandler implements IconHandler{
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
        StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();
        Sprite sprite = Atlases.getTerrain().getTexture(Block.WATER.textureId).getSprite();
        float startU = sprite.getMinU();
        float startV = sprite.getMinV();
        float endU = sprite.getMaxU();
        float endV = sprite.getMaxV();
        Tessellator tess = Tessellator.INSTANCE;
        tess.startQuads();
        tess.vertex(x, y + 16, 0, startU, endV);
        tess.vertex(x + 16, y + 16, 0, endU, endV);
        tess.vertex(x + 16, y, 0, endU, startV);
        tess.vertex(x, y, 0, startU, startV);
        tess.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
