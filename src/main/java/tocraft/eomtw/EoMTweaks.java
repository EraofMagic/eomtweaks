package tocraft.eomtw;

import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tocraft.craftedcore.platform.VersionChecker;
import tocraft.eomtw.registry.EoMRegistry;
import tocraft.eomtw.registry.OreRegistry;

@Mod(EoMTweaks.MODID)
public class EoMTweaks {

    public static final String MODID = "eomtw";
    private static final String MAVEN_URL = "https://maven.tocraft.dev/public/dev/tocraft/eomtw/maven-metadata.xml";

    public EoMTweaks() {
        try {
            VersionChecker.registerMavenChecker(MODID, new URL(MAVEN_URL), new TextComponent("EoMTweaks"));
        } catch (MalformedURLException ignored) {
        }
        EoMRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, OreRegistry::biomeLoadingEvent);
    }

}
