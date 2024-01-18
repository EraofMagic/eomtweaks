package tocraft.eomtw;

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
    public static String versionURL = "https://raw.githubusercontent.com/EraOfMagic/eomtweaks/1.18.2/gradle.properties";

    public EoMTweaks() {
        VersionChecker.registerChecker(MODID, versionURL, new TextComponent("EoMTweaks"));
        EoMRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, OreRegistry::biomeLoadingEvent);
    }

}
