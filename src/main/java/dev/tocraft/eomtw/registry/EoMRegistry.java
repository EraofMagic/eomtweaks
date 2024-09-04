package dev.tocraft.eomtw.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import dev.tocraft.eomtw.EoMTweaks;
import dev.tocraft.eomtw.item.EoMTiers;

public class EoMRegistry {

    public static class Blocks {
        private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EoMTweaks.MODID);

        public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
        public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(TIN_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
        public static final RegistryObject<Block> RAW_TIN_BLOCK = BLOCKS.register("raw_tin_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
        public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
        public static final RegistryObject<Block> TIN_DOOR = BLOCKS.register("tin_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion(), BlockSetType.IRON));
        public static final RegistryObject<Block> TIN_TRAPDOOR = BLOCKS.register("tin_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(Blocks::never), BlockSetType.IRON));

        private static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
            return false;
        }

        public static void register(IEventBus eventBus) {
            BLOCKS.register(eventBus);
        }
    }

    public static class InventoryTabs {
        private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EoMTweaks.MODID);
        public static final RegistryObject<CreativeModeTab> RESOURCES = TABS.register("resources", () -> CreativeModeTab.builder().title(Component.translatable("eomtw.tab.resources")).icon(() -> new ItemStack(EoMRegistry.Items.TIN_INGOT.get())).displayItems((arg, arg2) -> {
            arg2.accept(EoMRegistry.Items.RAW_TIN.get());
            arg2.accept(EoMRegistry.Items.TIN_DUST.get());
            arg2.accept(EoMRegistry.Items.TIN_NUGGET.get());
            arg2.accept(EoMRegistry.Items.TIN_INGOT.get());
            arg2.accept(EoMRegistry.Items.FIRE_BRICK.get());
            arg2.accept(EoMRegistry.Items.UNFIRED_FIRE_BRICK.get());
            arg2.accept(EoMRegistry.Items.UNFIRED_COKE_BRICK.get());
            arg2.accept(EoMRegistry.Items.FIRED_COKE_BRICK.get());
            arg2.accept(EoMRegistry.Items.UNFIRED_SEARED_BRICK.get());

            arg2.accept(EoMRegistry.Items.GYPSUM_DUST.get());
            arg2.accept(EoMRegistry.Items.CALCITE_DUST.get());
            arg2.accept(EoMRegistry.Items.STONE_DUST.get());
            arg2.accept(EoMRegistry.Items.BRICK_DUST.get());
            arg2.accept(EoMRegistry.Items.QUARTZ_DUST.get());
            arg2.accept(EoMRegistry.Items.WASH_PAN.get());
            arg2.accept(EoMRegistry.Items.TANNED_LEATHER.get());
            arg2.accept(EoMRegistry.Items.SOAKED_TANNED_LEATHER.get());
            arg2.accept(EoMRegistry.Items.LEATHER_STRIPE.get());
            arg2.accept(EoMRegistry.Items.LEATHER_SHEET.get());
            arg2.accept(EoMRegistry.Items.BARK_OIL.get());
            arg2.accept(EoMRegistry.Items.BLIGHTED_OIL.get());
            arg2.accept(EoMRegistry.Items.TAINTED_LEATHER.get());
            arg2.accept(EoMRegistry.Items.BLESSED_OIL.get());
            arg2.accept(EoMRegistry.Items.RUNIC_LEATHER.get());
            arg2.accept(EoMRegistry.Items.MUNDANCE_BELT.get());
        }).build());

        public static void register(IEventBus eventBus) {
            TABS.register(eventBus);
        }
    }

    @SuppressWarnings("unused")
    public static class Items {
        private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EoMTweaks.MODID);

        public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", () -> new BlockItem(Blocks.TIN_ORE.get(), (new Item.Properties())));
        public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new BlockItem(Blocks.DEEPSLATE_TIN_ORE.get(), (new Item.Properties())));
        public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new BlockItem(Blocks.TIN_BLOCK.get(), (new Item.Properties())));
        public static final RegistryObject<Item> RAW_TIN_BLOCK = ITEMS.register("raw_tin_block", () -> new BlockItem(Blocks.RAW_TIN_BLOCK.get(), (new Item.Properties())));
        public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item((new Item.Properties())));
        public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust", () -> new Item((new Item.Properties())));
        public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item((new Item.Properties())));
        public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item((new Item.Properties())));
        public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword", () -> new SwordItem(EoMTiers.TIN, 3, -2.4F, (new Item.Properties())));
        public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(EoMTiers.TIN, 1.5F, -3.0F, (new Item.Properties())));
        public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(EoMTiers.TIN, 1, -2.8F, (new Item.Properties())));
        public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(EoMTiers.TIN, 6.0F, -3.1F, (new Item.Properties())));
        public static final RegistryObject<Item> TIN_HOE = ITEMS.register("tin_hoe", () -> new HoeItem(EoMTiers.TIN, -2, -1.0F, (new Item.Properties())));
        public static final RegistryObject<Item> TIN_DOOR = ITEMS.register("tin_door", () -> new DoubleHighBlockItem(Blocks.TIN_DOOR.get(), (new Item.Properties())));
        public static final RegistryObject<Item> TIN_TRAPDOOR = ITEMS.register("tin_trapdoor", () -> new BlockItem(Blocks.TIN_TRAPDOOR.get(), (new Item.Properties())));
        public static final RegistryObject<Item> FIRE_BRICK = ITEMS.register("fire_brick", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> UNFIRED_FIRE_BRICK = ITEMS.register("unfired_fire_brick", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> UNFIRED_COKE_BRICK = ITEMS.register("unfired_coke_brick", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> FIRED_COKE_BRICK = ITEMS.register("fired_coke_brick", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> UNFIRED_SEARED_BRICK = ITEMS.register("unfired_seared_brick", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BRICK_FORM = ITEMS.register("brick_pattern", () -> new Item(new Item.Properties().durability(128)));
        public static final RegistryObject<Item> GYPSUM_DUST = ITEMS.register("gypsum_dust", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> CALCITE_DUST = ITEMS.register("calcite_dust", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> STONE_DUST = ITEMS.register("stone_dust", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BRICK_DUST = ITEMS.register("brick_dust", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> QUARTZ_DUST = ITEMS.register("quartz_dust", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> WASH_PAN = ITEMS.register("wash_pan", () -> new Item(new Item.Properties().durability(256)));
        public static final RegistryObject<Item> TANNED_LEATHER = ITEMS.register("tanned_leather", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> SOAKED_TANNED_LEATHER = ITEMS.register("soaked_tanned_leather", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> LEATHER_STRIPE = ITEMS.register("leather_stripe", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> LEATHER_SHEET = ITEMS.register("leather_sheet", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BARK_OIL = ITEMS.register("bark_oil", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BLIGHTED_OIL = ITEMS.register("blighted_oil", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> TAINTED_LEATHER = ITEMS.register("tainted_leather", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> BLESSED_OIL = ITEMS.register("blessed_oil", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> RUNIC_LEATHER = ITEMS.register("runic_leather", () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> MUNDANCE_BELT = ITEMS.register("mundane_belt", () -> new Item(new Item.Properties()));

        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        Blocks.register(eventBus);
        InventoryTabs.register(eventBus);
        Items.register(eventBus);
    }
}
