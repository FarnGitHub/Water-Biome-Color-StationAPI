package farn.water_biome_color.config;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;
import net.minecraft.client.resource.language.TranslationStorage;

import java.lang.reflect.*;
import java.util.function.*;

public class WaterRGBStyleConfigFactory implements ConfigFactoryProvider {

    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> immutableBuilder) {
        immutableBuilder.put(EnumWaterRGBStyle.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) ->
        {
            int enumOrdinal;
            if (enumOrOrdinal instanceof Integer ordinal) {
                enumOrdinal = ordinal;
            } else {
                enumOrdinal = ((EnumWaterRGBStyle) enumOrOrdinal).ordinal();
            }
            return new WaterRGBStyleEntryHandler(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrdinal, ((EnumWaterRGBStyle) defaultEnum).ordinal(), EnumWaterRGBStyle.class);
        }));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> immutableBuilder) {
        immutableBuilder.put(EnumWaterRGBStyle.class, enumEntry -> enumEntry);
    }

    static class WaterRGBStyleEntryHandler extends EnumConfigEntryHandler<EnumWaterRGBStyle> {

        public WaterRGBStyleEntryHandler(String id, ConfigEntry configEntry, Field parentField, Object parentObject, boolean multiplayerSynced, Integer value, Integer defaultValue, Class parentEnum) {
            super(id, configEntry, parentField, parentObject, multiplayerSynced, value, defaultValue, parentEnum);
        }

        public String getButtonText() {
            return TranslationStorage.getInstance().get(EnumWaterRGBStyle.values()[value].getDisplayName()) + " (" + (value + 1) + "/" + parentEnumArray.length + ")";
        }
    }
}
