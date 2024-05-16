package tocraft.eomtw.ftb.teams;

import com.mojang.logging.LogUtils;
import dev.ftb.mods.ftbchunks.data.FTBChunksAPI;
import dev.ftb.mods.ftbchunks.data.FTBChunksTeamData;
import dev.ftb.mods.ftbchunks.data.Protection;
import dev.ftb.mods.ftbchunks.data.ProtectionOverride;
import dev.ftb.mods.ftbteams.data.PrivacyMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class EoMProtections {
    public static final Protection ADVANCED_BREAK_BLOCK = (player, pos, hand, chunk, entity) -> {
        BlockState blockState = player.level.getBlockState(pos);

        if (blockState.is(FTBChunksAPI.EDIT_WHITELIST_TAG)) {
            return ProtectionOverride.ALLOW;
        }

        if (chunk != null) {
            List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.BREAK_BLOCKS_WHITELIST);
            List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.BREAK_BLOCKS_BLACKLIST);
            return canUse(whiteList, blackList, ForgeRegistries.BLOCKS.getKey(blockState.getBlock()));
        }

        return ProtectionOverride.CHECK;
    };

    public static final Protection ADVANCED_INTERACT_BLOCK = (player, pos, hand, chunk, entity) -> {
        BlockState blockState = player.level.getBlockState(pos);

        if (blockState.is(FTBChunksAPI.INTERACT_WHITELIST_TAG)) {
            return ProtectionOverride.ALLOW;
        }

        if (chunk != null) {
            List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_BLOCKS_WHITELIST);
            List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_BLOCKS_BLACKLIST);
            return canUse(whiteList, blackList, Objects.requireNonNull(blockState.getBlock().getRegistryName()));
        }

        return ProtectionOverride.CHECK;
    };

    public static final Protection ADVANCED_INTERACT_ENTITY = (player, pos, hand, chunk, entity) -> {
        if (entity != null) {
            if (entity.getType().is(FTBChunksAPI.ENTITY_INTERACT_WHITELIST_TAG)) {
                return ProtectionOverride.ALLOW;
            }

            if (chunk != null) {
                List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_ENTITIES_WHITELIST);
                List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_ENTITIES_BLACKLIST);
                return canUse(whiteList, blackList, EntityType.getKey(entity.getType()));
            }
        }

        return ProtectionOverride.CHECK;
    };

    public static ProtectionOverride canUse(List<ResourceLocation> whiteList, List<ResourceLocation> blackList, ResourceLocation idToBeChecked) {
        LogUtils.getLogger().warn(idToBeChecked.toString());
        if (blackList.contains(idToBeChecked)) {
            return ProtectionOverride.DENY;
        } else if (whiteList.contains(idToBeChecked) && !blackList.contains(idToBeChecked)) {
            return ProtectionOverride.ALLOW;
        } else if (whiteList.isEmpty()) {
            return ProtectionOverride.ALLOW;
        } else {
            return ProtectionOverride.CHECK;
        }
    }

    public static List<ResourceLocation> getIdList(ServerPlayer p, FTBChunksTeamData teamData, PrivacyToIdMapProperty property) {
        List<ResourceLocation> list;
        if (teamData.isTeamMember(p.getUUID())) {
            list = teamData.getTeam().getProperty(property).get(PrivacyMode.PRIVATE);
        } else if (teamData.isAlly(p.getUUID())) {
            list = teamData.getTeam().getProperty(property).get(PrivacyMode.ALLIES);
        } else {
            list = teamData.getTeam().getProperty(property).get(PrivacyMode.PUBLIC);
        }

        return list != null ? list : new ArrayList<>();
    }
}
