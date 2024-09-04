package dev.tocraft.eomtw.events;

import cech12.bucketlib.api.item.UniversalBucketItem;
import cech12.bucketlib.util.BucketLibUtil;
import favouriteless.enchanted.common.blocks.entity.CauldronBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.tocraft.eomtw.EoMTweaks;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = EoMTweaks.MODID)
public class EoMEventHandler {

    @SubscribeEvent
    public static InteractionResult fillCauldron(PlayerInteractEvent.RightClickBlock event) {
        ItemStack item = event.getEntity().getItemInHand(event.getHand());
        if (!event.getLevel().isClientSide) {
            if (event.getLevel().getBlockEntity(event.getPos()) instanceof CauldronBlockEntity<?> cauldron) {
                Block block = event.getLevel().getBlockEntity(event.getPos()).getBlockState().getBlock();
                if (item.getItem() instanceof UniversalBucketItem) {
                    Fluid fluid = BucketLibUtil.getFluid(item);
                    if (fluid == Fluids.WATER) {
                        // TODO: Check the water amount!
                        if (cauldron.addWater(100)) {
                            event.getLevel().playSound(null, event.getPos(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
