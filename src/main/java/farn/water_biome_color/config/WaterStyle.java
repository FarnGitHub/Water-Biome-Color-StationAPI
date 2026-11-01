package farn.water_biome_color.config;

import farn.water_biome_color.WaterBiomeColor;
import farn.water_biome_color.util.RGBAModifier;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public enum WaterStyle {
    TMC("TheMasterCaver",(w,r,g,b,a)->{
        r.set((int)(111 + w * 76));
        g.set((int)(139 + w * 116));
        b.set(255);
        a.set((int)(146 + w * 50));
    }),
    VIVIAN("Vivian (Old)",(w,r,g,b,a)->{
        r.set(128);
        g.set(128);
        b.set(128);
        a.set((int)(146 + w * 50));
    }),
    GREY_SCALED("Grey Scaled",(w,r,g,b,a)->{
        int gray = (int)(
            0.598 * r.get() +
            1.174 * g.get() +
            0.228 * b.get()
        );
        r.set(gray);
        g.set(gray);
        b.set(gray);
    }),
    OFF("OFF", (w,r,g,b,a)->{});

    public static final File optionsFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "water_biome_color.cfg");

    public final String displayName;
    public final String iconPath;
    public final RGBAModifier modifier;

    WaterStyle(String displayName, RGBAModifier modifier) {
        this.displayName = displayName;
        this.iconPath = "/farn/water_biome_color/" + name() + ".png";
        this.modifier = modifier;
    }

    public static void readConfig() {
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

    static {
        readConfig();
    }
}
