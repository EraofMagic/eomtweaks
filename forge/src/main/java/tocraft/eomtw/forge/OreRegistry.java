package tocraft.eomtw.forge;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import tocraft.eomtw.registry.EoMRegistry;

import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.data.worldgen.features.OreFeatures.STONE_ORE_REPLACEABLES;

public class OreRegistry {
	private final static int TIN_ORE_SIZE = 10;
	private final static int TIN_ORE_AMOUNT = 10;
	private final static int TIN_ORE_MIN_Y = -10;
	private final static int TIN_ORE_MAX_Y = 40;
	
	public static void setup() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, OreRegistry::biomeLoadingEvent);
	}
	
	public static final void biomeLoadingEvent(BiomeLoadingEvent event) {
		event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeatures.TIN_ORE_FEATURE);
	}
	
	public static final class ConfiguredFeatures {
		public static final List<OreConfiguration.TargetBlockState> TIN_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, EoMRegistry.B_TIN_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, EoMRegistry.B_DEEPSLATE_TIN_ORE.get().defaultBlockState()));
	    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TIN_ORE_FEATURE = FeatureUtils.register("tin_ore", Feature.ORE,
	            new OreConfiguration(TIN_ORE_TARGET_LIST, TIN_ORE_SIZE));
	}
    
    public static final class PlacedFeatures {
        public static final Holder<PlacedFeature> TIN_ORE_FEATURE = PlacementUtils.register("tin", ConfiguredFeatures.TIN_ORE_FEATURE,
                CountPlacement.of(TIN_ORE_AMOUNT), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(TIN_ORE_MIN_Y), VerticalAnchor.absolute(TIN_ORE_MAX_Y)));
    }
}
