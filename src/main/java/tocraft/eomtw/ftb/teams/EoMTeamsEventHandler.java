package tocraft.eomtw.ftb.teams;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.utils.value.IntValue;
import dev.ftb.mods.ftbchunks.FTBCUtils;
import dev.ftb.mods.ftbchunks.FTBChunksExpected;
import dev.ftb.mods.ftbchunks.data.FTBChunksAPI;
import dev.ftb.mods.ftbteams.event.TeamCollectPropertiesEvent;
import dev.ftb.mods.ftbteams.event.TeamEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

public class EoMTeamsEventHandler {
    public static void initialize() {
        TeamEvent.COLLECT_PROPERTIES.register(EoMTeamsEventHandler::teamCollectPropertiesEvent);
        MinecraftForge.EVENT_BUS.addListener(EoMTeamsEventHandler::entityInteractSpecific);
        InteractionEvent.INTERACT_ENTITY.register(EoMTeamsEventHandler::interactEntity);
        BlockEvent.BREAK.register(EoMTeamsEventHandler::blockBreak);
        InteractionEvent.LEFT_CLICK_BLOCK.register(EoMTeamsEventHandler::blockLeftClick);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(EoMTeamsEventHandler::blockRightClick);
    }

    private static void teamCollectPropertiesEvent(TeamCollectPropertiesEvent event) {
        event.add(EoMTeamProperties.DESTROY_BLOCKS_BLACKLIST);
        event.add(EoMTeamProperties.DESTROY_BLOCKS_WHITELIST);
        event.add(EoMTeamProperties.INTERACT_BLOCKS_BLACKLIST);
        event.add(EoMTeamProperties.INTERACT_BLOCKS_WHITELIST);
        event.add(EoMTeamProperties.INTERACT_ENTITIES_BLACKLIST);
        event.add(EoMTeamProperties.INTERACT_ENTITIES_WHITELIST);
    }

    private static void entityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        if (!event.getPlayer().level.isClientSide && FTBChunksAPI.getManager().protect(event.getPlayer(), event.getHand(), event.getEntity().blockPosition(), EoMProtections.ADVANCED_INTERACT_BLOCK, event.getTarget())) {
            event.setCancellationResult(InteractionResult.FAIL);
            event.setCanceled(true);
        }
    }

    private static EventResult interactEntity(Player player, Entity entity, InteractionHand hand) {
        if (player instanceof ServerPlayer && FTBChunksAPI.getManager().protect(player, hand, entity.blockPosition(), EoMProtections.ADVANCED_INTERACT_ENTITY, entity)) {
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }

    public static EventResult blockLeftClick(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        if (player instanceof ServerPlayer && FTBChunksAPI.getManager().protect(player, hand, pos, EoMProtections.ADVANCED_DESTROY_BLOCK, null)) {
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }

    public static EventResult blockRightClick(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        if (player instanceof ServerPlayer sp && FTBChunksAPI.getManager().protect(player, hand, pos, EoMProtections.ADVANCED_INTERACT_BLOCK, null)) {
            FTBCUtils.forceHeldItemSync(sp, hand);
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }

    private static EventResult blockBreak(Level level, BlockPos pos, BlockState blockState, ServerPlayer player, @Nullable IntValue intValue) {
        if (FTBChunksAPI.getManager().protect(player, InteractionHand.MAIN_HAND, pos, FTBChunksExpected.getBlockBreakProtection(), null)) {
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }
}
