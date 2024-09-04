package dev.tocraft.eomtw.data;

import dev.tocraft.eomtw.EoMTweaks;
import dev.tocraft.eomtw.worldgen.EoMBiomeModifiers;
import dev.tocraft.eomtw.worldgen.EoMConfiguredFeatures;
import dev.tocraft.eomtw.worldgen.EoMPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EoMRegistriesProvider extends DatapackBuiltinEntriesProvider {
    public EoMRegistriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EoMTweaks.MODID));
    }

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, EoMConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, EoMPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, EoMBiomeModifiers::bootstrap);
}
