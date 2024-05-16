package tocraft.eomtw.ftb.teams;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.ftb.mods.ftbteams.property.TeamProperty;
import dev.ftb.mods.ftbteams.property.TeamPropertyType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

@SuppressWarnings("unused")
public class StringToIdMapProperty extends TeamProperty<Map<String, List<ResourceLocation>>> {
    public static final TeamPropertyType<Map<String, List<ResourceLocation>>> STRING_TO_ID_MAP = TeamPropertyType.register("string_to_id_list_map", StringToIdMapProperty::new);

    public StringToIdMapProperty(ResourceLocation id, Map<String, List<ResourceLocation>> def) {
        super(id, def);
    }

    public StringToIdMapProperty(ResourceLocation id, FriendlyByteBuf buf) {
        this(id, fromJson(JsonParser.parseString(buf.readUtf())).orElseThrow());
    }

    @Override
    public TeamPropertyType<Map<String, List<ResourceLocation>>> getType() {
        return STRING_TO_ID_MAP;
    }

    @Override
    public Optional<Map<String, List<ResourceLocation>>> fromString(String string) {
        return fromJson(JsonParser.parseString(string));
    }

    private static Optional<Map<String, List<ResourceLocation>>> fromJson(JsonElement json) {
        Map<String, List<ResourceLocation>> map = new HashMap<>();
        if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getValue().isJsonArray()) {
                    List<ResourceLocation> list = new ArrayList<>();
                    for (JsonElement jsonElement : entry.getValue().getAsJsonArray()) {
                        list.add(new ResourceLocation(jsonElement.getAsString()));
                    }
                    map.put(entry.getKey(), list);
                }
            }
        }

        return map.isEmpty() ? Optional.empty() : Optional.of(map);
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        JsonObject map = new JsonObject();
        for (Map.Entry<String, List<ResourceLocation>> entry : defaultValue.entrySet()) {
            JsonArray list = new JsonArray();
            for (ResourceLocation resourceLocation : entry.getValue()) {
                list.add(resourceLocation.toString());
            }
            map.add(entry.getKey(), list);
        }
        buf.writeUtf(map.toString());
    }
}
