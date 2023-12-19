package tocraft.eomtw.registry;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
	
		public static void register(IEventBus eventBus) {
			BLOCKS.register(eventBus);
		}
	}
	
	public class Items {
		private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EoMTweaks.MODID);
		
		public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", () -> new BlockItem(Blocks.TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
		public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new BlockItem(Blocks.DEEPSLATE_TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
		public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", () -> new BlockItem(Blocks.TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
		public static final RegistryObject<Item> RAW_TIN_BLOCK = ITEMS.register("raw_tin_block", () -> new BlockItem(Blocks.RAW_TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
		public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword", () -> new SwordItem(EoMTiers.TIN, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(EoMTiers.TIN, 1.5F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(EoMTiers.TIN, 1, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(EoMTiers.TIN, 6.0F, -3.1F, (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		public static final RegistryObject<Item> TIN_HOE =  ITEMS.register("tin_hoe", () -> new HoeItem(EoMTiers.TIN, -2, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
		
		public static void register(IEventBus eventBus) {
			ITEMS.register(eventBus);
		}
	}
	
	public static void register(IEventBus eventBus) {
		Items.register(eventBus);
		Blocks.register(eventBus);
	}
}