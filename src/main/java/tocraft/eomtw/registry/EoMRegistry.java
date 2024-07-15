package tocraft.eomtw.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tocraft.eomtw.EoMTweaks;
import tocraft.eomtw.item.EoMTiers;

public class EoMRegistry {

    public class Blocks {
        private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EoMTweaks.MODID);

        public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F)));
        public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(TIN_ORE.get()).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
        public static final RegistryObject<Block> RAW_TIN_BLOCK = BLOCKS.register("raw_tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
        public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
        public static final RegistryObject<Block> TIN_DOOR = BLOCKS.register("tin_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
        public static final RegistryObject<Block> TIN_TRAPDOOR = BLOCKS.register("tin_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(Blocks::never)));

        private static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
            return false;
        }

        public static void register(IEventBus eventBus) {
            BLOCKS.register(eventBus);
        }
    }

    public class Items {
        private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EoMTweaks.MODID);

        public static final CreativeModeTab RESOURCES = new CreativeModeTab("Resources") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(TIN_INGOT.get());
            }
        };

        public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", () -> new BlockItem(Blocks.TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new BlockItem(Blocks.DEEPSLATE_TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new BlockItem(Blocks.TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        public static final RegistryObject<Item> RAW_TIN_BLOCK = ITEMS.register("raw_tin_block", () -> new BlockItem(Blocks.RAW_TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item((new Item.Properties()).tab(RESOURCES)));
        public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust", () -> new Item((new Item.Properties()).tab(RESOURCES)));
        public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item((new Item.Properties()).tab(RESOURCES)));
        public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item((new Item.Properties()).tab(RESOURCES)));
        public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword", () -> new SwordItem(EoMTiers.TIN, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(EoMTiers.TIN, 1.5F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(EoMTiers.TIN, 1, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(EoMTiers.TIN, 6.0F, -3.1F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> TIN_HOE = ITEMS.register("tin_hoe", () -> new HoeItem(EoMTiers.TIN, -2, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> TIN_DOOR = ITEMS.register("tin_door", () -> new DoubleHighBlockItem(Blocks.TIN_DOOR.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_REDSTONE)));
        public static final RegistryObject<Item> TIN_TRAPDOOR = ITEMS.register("tin_trapdoor", () -> new BlockItem(Blocks.TIN_TRAPDOOR.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_REDSTONE)));
        public static final RegistryObject<Item> FIRE_BRICK = ITEMS.register("fire_brick", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> UNFIRED_FIRE_BRICK = ITEMS.register("unfired_fire_brick", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> UNFIRED_COKE_BRICK = ITEMS.register("unfired_coke_brick", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> FIRED_COKE_BRICK = ITEMS.register("fired_coke_brick", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> UNFIRED_SEARED_BRICK = ITEMS.register("unfired_seared_brick", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> BRICK_FORM = ITEMS.register("brick_pattern", () -> new Item(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_TOOLS)));
        public static final RegistryObject<Item> GYPSUM_DUST = ITEMS.register("gypsum_dust", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> CALCITE_DUST = ITEMS.register("calcite_dust", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> STONE_DUST = ITEMS.register("stone_dust", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> BRICK_DUST = ITEMS.register("brick_dust", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> QUARTZ_DUST = ITEMS.register("quartz_dust", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> WASH_PAN = ITEMS.register("wash_pan", () -> new Item(new Item.Properties().tab(RESOURCES).durability(256)));
        public static final RegistryObject<Item> TANNED_LEATHER = ITEMS.register("tanned_leather", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> SOAKED_TANNED_LEATHER = ITEMS.register("soaked_tanned_leather", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> LEATHER_STRIPE = ITEMS.register("leather_stripe", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> LEATHER_SHEET = ITEMS.register("leather_sheet", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> BARK_OIL = ITEMS.register("bark_oil", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> BLIGHTED_OIL = ITEMS.register("blighted_oil", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> TAINTED_LEATHER = ITEMS.register("tainted_leather", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> BLESSED_OIL = ITEMS.register("blessed_oil", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> RUNIC_LEATHER = ITEMS.register("runic_leather", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static final RegistryObject<Item> MUNDANCE_BELT = ITEMS.register("Mundane_belt", () -> new Item(new Item.Properties().tab(RESOURCES)));
        public static void register(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        Items.register(eventBus);
        Blocks.register(eventBus);
    }
}
