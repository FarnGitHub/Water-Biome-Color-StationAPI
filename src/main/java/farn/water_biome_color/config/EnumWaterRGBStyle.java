package farn.water_biome_color.config;


public enum EnumWaterRGBStyle {
    TMC("TheMasterCaver"),
    VIVIAN("Vivian"),
    VIBRANT("Vibrant Blue"),
    OFF("OFF");

    private final String stringer;

    EnumWaterRGBStyle(String string) {
        stringer = string;
    }

    public String getDisplayName() {
        return stringer;
    }
}
