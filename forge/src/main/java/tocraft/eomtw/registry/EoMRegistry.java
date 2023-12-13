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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tocraft.eomtw.EoMTweaks;
import tocraft.eomtw.item.EoMTiers;

public class EoMRegistry {
	// Registries
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EoMTweaks.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EoMTweaks.MODID);

	// Blocks
	public static final RegistryObject<Block> B_TIN_ORE = BLOCKS.register("tin_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F)));
	public static final RegistryObject<Block> B_DEEPSLATE_TIN_ORE = BLOCKS.register("deepslate_tin_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(B_TIN_ORE.get()).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
	public static final RegistryObject<Block> B_RAW_TIN_BLOCK = BLOCKS.register("raw_tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.RAW_IRON).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> B_TIN_BLOCK = BLOCKS.register("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	
	// Items
	public static final RegistryObject<Item> I_TIN_ORE = ITEMS.register("tin_ore", () -> new BlockItem(B_TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	public static final RegistryObject<Item> I_DEEPSLATE_TIN_ORE = ITEMS.register("deepslate_tin_ore", () -> new BlockItem(B_DEEPSLATE_TIN_ORE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	public static final RegistryObject<Item> I_TIN_BLOCK = ITEMS.register("tin_block", () -> new BlockItem(B_TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	public static final RegistryObject<Item> I_RAW_TIN_BLOCK = ITEMS.register("raw_tin_block", () -> new BlockItem(B_RAW_TIN_BLOCK.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	public static final RegistryObject<Item> I_RAW_TIN = ITEMS.register("raw_tin", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> I_TIN_DUST = ITEMS.register("tin_dust", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> I_TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> I_TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item((new Item.Properties()).tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> I_TIN_SWORD = ITEMS.register("tin_sword", () -> new SwordItem(EoMTiers.TIN, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> I_TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(EoMTiers.TIN, 1.5F, -3.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
	public static final RegistryObject<Item> I_TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(EoMTiers.TIN, 1, -2.8F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
	public static final RegistryObject<Item> I_TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(EoMTiers.TIN, 6.0F, -3.1F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
	public static final RegistryObject<Item> I_TIN_HOE =  ITEMS.register("tin_hoe", () -> new HoeItem(EoMTiers.TIN, -2, -1.0F, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
	
	public static void register() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
