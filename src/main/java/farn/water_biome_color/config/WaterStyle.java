package farn.water_biome_color.config;

import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import farn.water_biome_color.WaterBiomeColor;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public enum WaterStyle {
    TMC("§bTheMasterCaver") {
        public void setRGB(
                float waterValue,
                LocalIntRef r,
                LocalIntRef g,
                LocalIntRef b,
                LocalIntRef a
        ) {
            r.set((int)(111.0F + waterValue * 76.0F));
            g.set((int)(139.0F + waterValue * 116.0F));
            b.set(255);
            a.set((int)(146.0F + waterValue * 50.0F));
        }
    },
    VIVIAN("§bVivian (Old)"){
        public void setRGB(
                float waterValue,
                LocalIntRef r,
                LocalIntRef g,
                LocalIntRef b,
                LocalIntRef a
        ) {
            r.set(128);
            g.set(128);
            b.set(128);
            a.set((int)(146F + waterValue * 50F));
        }
    },
    VIBRANT("§bVibrant Blue"){
        public void setRGB(
                float waterValue,
                LocalIntRef r,
                LocalIntRef g,
                LocalIntRef b,
                LocalIntRef a
        ) {
            r.set(192);
            g.set(192);
            b.set(255);
            a.set((int)(146F + waterValue * 50F));
        }
    },
    OFF("§cOFF");

    public static final File optionsFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "water_biome_color.cfg");

    public final String displayName;
    public final String iconPath;

    WaterStyle(String displayName) {
        this.displayName = displayName;
        this.iconPath = "/farn/water_biome_color/" + name() + ".png";
    }

    public static void readFromOption() {
        if(!optionsFile.exists()) writeOptions();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(optionsFile));
            WaterBiomeColor.STYLE = WaterStyle.values()[Integer.parseInt(reader.readLine())];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeOptions() {
        try(PrintWriter fw = new PrintWriter(optionsFile)) {
            fw.println(WaterBiomeColor.STYLE.ordinal());
        } catch (Exception ignored) {
        }
    }

    public void setRGB(float waterValue, LocalIntRef r, LocalIntRef g, LocalIntRef b, LocalIntRef a) {
    }

    static {
        readFromOption();
    }
}
