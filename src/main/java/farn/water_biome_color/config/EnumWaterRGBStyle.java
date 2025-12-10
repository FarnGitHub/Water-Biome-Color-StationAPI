package farn.water_biome_color.config;


public enum EnumWaterRGBStyle {
    TMC("TheMasterCaver"),
    VIVIAN("VivianFox"),
    FARN("Farn"),
    OFF("OFF");

    private final String stringer;

    EnumWaterRGBStyle(String string) {
        stringer = string;
    }

    @Override
    public String toString() {
        return stringer;
    }
}
