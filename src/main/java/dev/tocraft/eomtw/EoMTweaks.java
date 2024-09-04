package dev.tocraft.eomtw;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tocraft.craftedcore.event.common.CommandEvents;
import tocraft.craftedcore.platform.VersionChecker;
import dev.tocraft.eomtw.commands.FTBTeamsExtraCommands;
import dev.tocraft.eomtw.ftb.teams.EoMTeamsEventHandler;
import dev.tocraft.eomtw.registry.EoMRegistry;

import java.net.MalformedURLException;
import java.net.URL;

@Mod(EoMTweaks.MODID)
public class EoMTweaks {
    public static final String MODID = "eomtw";
    private static final String MAVEN_URL = "https://maven.tocraft.dev/public/dev/tocraft/eomtw/maven-metadata.xml";

    public EoMTweaks() {
        try {
            VersionChecker.registerMavenChecker(MODID, new URL(MAVEN_URL), Component.literal("EoMTweaks"));
        } catch (MalformedURLException ignored) {
        }
        EoMRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());

        if (ModList.get().isLoaded("ftbteams")) {
            CommandEvents.REGISTRATION.register(new FTBTeamsExtraCommands());
            EoMTeamsEventHandler.initialize();
        }
    }

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }
}
