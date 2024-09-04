package dev.tocraft.eomtw.client;

import dev.tocraft.eomtw.EoMTweaks;
import dev.tocraft.eomtw.registry.EoMRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = EoMTweaks.MODID)
public class EoMClientEventHandler {

    @SubscribeEvent
    public static void event(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(event.getTab()).orElseThrow();
        if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(EoMRegistry.Items.TIN_SWORD.get());
            event.accept(EoMRegistry.Items.TIN_SHOVEL.get());
            event.accept(EoMRegistry.Items.TIN_PICKAXE.get());
            event.accept(EoMRegistry.Items.TIN_AXE.get());
            event.accept(EoMRegistry.Items.TIN_HOE.get());
            event.accept(EoMRegistry.Items.BRICK_FORM.get());
        } else if (tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(EoMRegistry.Items.TIN_ORE);
            event.accept(EoMRegistry.Items.DEEPSLATE_TIN_ORE);
            event.accept(EoMRegistry.Items.TIN_BLOCK);
            event.accept(EoMRegistry.Items.RAW_TIN_BLOCK);
        } else if (tabKey == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(EoMRegistry.Items.TIN_DOOR);
            event.accept(EoMRegistry.Items.TIN_TRAPDOOR);
        }
    }
}
