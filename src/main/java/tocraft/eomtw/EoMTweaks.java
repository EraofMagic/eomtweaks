package tocraft.eomtw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tocraft.craftedcore.events.common.PlayerEvents;
import tocraft.craftedcore.platform.Platform;
import tocraft.craftedcore.platform.VersionChecker;
import tocraft.eomtw.registry.EoMRegistry;
import tocraft.eomtw.registry.OreRegistry;

@Mod(EoMTweaks.MODID)
public class EoMTweaks {

	public static final Logger LOGGER = LoggerFactory.getLogger(EoMTweaks.class);
	public static final String MODID = "eomtw";
	public static String versionURL = "https://raw.githubusercontent.com/ToCraft/eomtweaks/1.18.2/gradle.properties";
	
	public EoMTweaks() {		
		PlayerEvents.PLAYER_JOIN.register(player -> {
			String newestVersion = VersionChecker.checkForNewVersion(versionURL);
			if (newestVersion != null && !Platform.getMod(MODID).getVersion().equals(newestVersion))
				player.displayClientMessage(new TranslatableComponent("eomtw.update", newestVersion), false);
		});
		
		EoMRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, OreRegistry::biomeLoadingEvent);
	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MODID, name);
	}
}
