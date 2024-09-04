package dev.tocraft.eomtw.ftb.teams;

import com.mojang.logging.LogUtils;
import dev.ftb.mods.ftbchunks.api.FTBChunksTags;
import dev.ftb.mods.ftbchunks.api.ProtectionPolicy;
import dev.ftb.mods.ftbchunks.api.ChunkTeamData;
import dev.ftb.mods.ftbchunks.api.Protection;
import dev.ftb.mods.ftbteams.api.property.PrivacyMode;
import dev.ftb.mods.ftbteams.api.property.TeamProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("resource")
public final class EoMProtections {
    public static final Protection ADVANCED_BREAK_BLOCK = (player, pos, hand, chunk, entity) -> {
        BlockState blockState = player.level().getBlockState(pos);

        if (blockState.is(FTBChunksTags.Blocks.EDIT_WHITELIST_TAG)) {
            return ProtectionPolicy.ALLOW;
        }

        if (chunk != null) {
            List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.BREAK_BLOCKS_WHITELIST);
            List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.BREAK_BLOCKS_BLACKLIST);
            return canUse(whiteList, blackList, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockState.getBlock())));
        }

        return ProtectionPolicy.CHECK;
    };

    public static final Protection ADVANCED_INTERACT_BLOCK = (player, pos, hand, chunk, entity) -> {
        BlockState blockState = player.level().getBlockState(pos);

        if (blockState.is(FTBChunksTags.Blocks.INTERACT_WHITELIST_TAG)) {
            return ProtectionPolicy.ALLOW;
        }

        if (chunk != null) {
            List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_BLOCKS_WHITELIST);
            List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_BLOCKS_BLACKLIST);
            return canUse(whiteList, blackList, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockState.getBlock())));
        }

        return ProtectionPolicy.CHECK;
    };

    public static final Protection ADVANCED_INTERACT_ENTITY = (player, pos, hand, chunk, entity) -> {
        if (entity != null) {
            if (entity.getType().is(FTBChunksTags.Entities.ENTITY_INTERACT_WHITELIST_TAG)) {
                return ProtectionPolicy.ALLOW;
            }

            if (chunk != null) {
                List<ResourceLocation> whiteList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_ENTITIES_WHITELIST);
                List<ResourceLocation> blackList = getIdList(player, chunk.getTeamData(), EoMTeamProperties.INTERACT_ENTITIES_BLACKLIST);
                return canUse(whiteList, blackList, EntityType.getKey(entity.getType()));
            }
        }

        return ProtectionPolicy.CHECK;
    };

    public static ProtectionPolicy canUse(List<ResourceLocation> whiteList, List<ResourceLocation> blackList, ResourceLocation idToBeChecked) {
        LogUtils.getLogger().warn(idToBeChecked.toString());
        if (blackList.contains(idToBeChecked)) {
            return ProtectionPolicy.DENY;
        } else if (whiteList.contains(idToBeChecked) && !blackList.contains(idToBeChecked)) {
            return ProtectionPolicy.ALLOW;
        } else if (whiteList.isEmpty()) {
            return ProtectionPolicy.ALLOW;
        } else {
            return ProtectionPolicy.CHECK;
        }
    }

    public static List<ResourceLocation> getIdList(ServerPlayer p, ChunkTeamData teamData, PrivacyToIdMapProperty property) {
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
