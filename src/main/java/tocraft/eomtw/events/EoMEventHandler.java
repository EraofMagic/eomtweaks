package tocraft.eomtw.events;

import cech12.bucketlib.api.item.UniversalBucketItem;
import cech12.bucketlib.util.BucketLibUtil;
import cech12.ceramicbucket.CeramicBucketMod;
import com.favouriteless.enchanted.common.blockentities.CauldronBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fml.common.Mod;
import tocraft.eomtw.EoMTweaks;

@Mod.EventBusSubscriber(modid = EoMTweaks.MODID)
public class EoMEventHandler {

    @SubscribeEvent
    public static InteractionResult FillCauldron(PlayerInteractEvent.RightClickBlock event) {
        ItemStack item = event.getPlayer().getItemInHand(event.getHand());
        if (!event.getWorld().isClientSide) {
            if (event.getWorld().getBlockEntity(event.getPos()) instanceof CauldronBlockEntity<?> cauldron) {
                Block block = event.getWorld().getBlockEntity(event.getPos()).getBlockState().getBlock();
                if (item.getItem() instanceof UniversalBucketItem) {
                    Fluid fluid = BucketLibUtil.getFluid(item);
                    if (fluid == Fluids.WATER) {
                            if (cauldron.addWater(FluidAttributes.BUCKET_VOLUME)) {
                                event.getWorld().playSound(null, event.getPos(), SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                                return InteractionResult.SUCCESS;
                            }
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
