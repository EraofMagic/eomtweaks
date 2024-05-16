package tocraft.eomtw.ftb.teams;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.ftb.mods.ftbteams.data.PrivacyMode;
import dev.ftb.mods.ftbteams.property.TeamProperty;
import dev.ftb.mods.ftbteams.property.TeamPropertyType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

@SuppressWarnings("unused")
public class PrivacyToIdMapProperty extends TeamProperty<Map<PrivacyMode, List<ResourceLocation>>> {
    public static final TeamPropertyType<Map<PrivacyMode, List<ResourceLocation>>> PRIVACY_TO_ID_MAP = TeamPropertyType.register("privacy_to_id_list_map", PrivacyToIdMapProperty::new);

    public PrivacyToIdMapProperty(ResourceLocation id, Map<PrivacyMode, List<ResourceLocation>> def) {
        super(id, def);
    }

    public PrivacyToIdMapProperty(ResourceLocation id, FriendlyByteBuf buf) {
        this(id, fromJson(JsonParser.parseString(buf.readUtf())).orElseThrow());
    }

    @Override
    public TeamPropertyType<Map<PrivacyMode, List<ResourceLocation>>> getType() {
        return PRIVACY_TO_ID_MAP;
    }

    @Override
    public Optional<Map<PrivacyMode, List<ResourceLocation>>> fromString(String string) {
        return fromJson(JsonParser.parseString(string));
    }

    private static Optional<Map<PrivacyMode, List<ResourceLocation>>> fromJson(JsonElement json) {
        Map<PrivacyMode, List<ResourceLocation>> map = new HashMap<>();
        if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getValue().isJsonArray()) {
                    List<ResourceLocation> list = new ArrayList<>();
                    for (JsonElement jsonElement : entry.getValue().getAsJsonArray()) {
                        list.add(new ResourceLocation(jsonElement.getAsString()));
                    }
                    map.put(PrivacyMode.valueOf(entry.getKey()), list);
                }
            }
        }

        return map.isEmpty() ? Optional.empty() : Optional.of(map);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        JsonObject map = new JsonObject();
        for (Map.Entry<PrivacyMode, List<ResourceLocation>> entry : defaultValue.entrySet()) {
            JsonArray list = new JsonArray();
            for (ResourceLocation resourceLocation : entry.getValue()) {
                list.add(resourceLocation.toString());
            }
            map.add(entry.getKey().name(), list);
        }
        buf.writeUtf(map.toString());
    }
}
