package dev.tocraft.eomtw.ftb.teams;

import com.google.gson.*;
import dev.ftb.mods.ftblibrary.config.ConfigGroup;
import dev.ftb.mods.ftbteams.api.property.PrivacyMode;
import dev.ftb.mods.ftbteams.api.property.TeamProperty;
import dev.ftb.mods.ftbteams.api.property.TeamPropertyType;
import dev.ftb.mods.ftbteams.api.property.TeamPropertyValue;
import dev.tocraft.eomtw.mixin.accessor.TeamPropertyTypeAccessor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

@SuppressWarnings("unused")
public class PrivacyToIdMapProperty extends TeamProperty<Map<PrivacyMode, List<ResourceLocation>>> {
    public static final TeamPropertyType<Map<PrivacyMode, List<ResourceLocation>>> PRIVACY_TO_ID_MAP = TeamPropertyTypeAccessor.callRegister("privacy_to_id_list_map", PrivacyToIdMapProperty::fromNetwork);

    public PrivacyToIdMapProperty(ResourceLocation id, Map<PrivacyMode, List<ResourceLocation>> def) {
        super(id, () -> def);
    }

    public static PrivacyToIdMapProperty fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return new PrivacyToIdMapProperty(id, fromJson(JsonParser.parseString(buf.readUtf())));
    }

    @Override
    public TeamPropertyType<Map<PrivacyMode, List<ResourceLocation>>> getType() {
        return PRIVACY_TO_ID_MAP;
    }

    @Override
    public Optional<Map<PrivacyMode, List<ResourceLocation>>> fromString(String string) {
        try {
            Map<PrivacyMode, List<ResourceLocation>> map = new HashMap<>(fromJson(JsonParser.parseString(string)));
            return map.isEmpty() ? Optional.empty() : Optional.of(map);
        } catch (JsonParseException e) {
            return Optional.empty();
        }
    }

    private static Map<PrivacyMode, List<ResourceLocation>> fromJson(JsonElement json) {
        Map<PrivacyMode, List<ResourceLocation>> map = new HashMap<>();
        if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getValue().isJsonArray()) {
                    try {
                        List<ResourceLocation> list = new ArrayList<>();
                        for (JsonElement jsonElement : entry.getValue().getAsJsonArray()) {
                            list.add(new ResourceLocation(jsonElement.getAsString()));
                        }
                        map.put(PrivacyMode.NAME_MAP.get(entry.getKey().toLowerCase()), list);
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        return map;
    }

    private static JsonElement toJson(Map<PrivacyMode, List<ResourceLocation>> map) {
        JsonObject json = new JsonObject();
        map.forEach((k, v) -> {
            JsonArray array = new JsonArray();
            v.forEach(k2 -> array.add(k2.toString()));
            json.add(k.name(), array);
        });

        return json;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(toJson(getDefaultValue()).toString());
    }

    @Override
    public String toString(Map<PrivacyMode, List<ResourceLocation>> map) {
        return toJson(map).toString();
    }

    // TODO: Replace with cool custom menu
    @Override
    public void config(ConfigGroup config, TeamPropertyValue<Map<PrivacyMode, List<ResourceLocation>>> value) {
        config.addString(id.getPath(), toString(value.getValue()), s -> value.setValue(fromString(s).orElse(new HashMap<>())), toString(getDefaultValue()));
    }
}
