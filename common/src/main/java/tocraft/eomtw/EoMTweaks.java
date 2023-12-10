package tocraft.eomtw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import tocraft.craftedcore.events.common.PlayerEvents;
import tocraft.craftedcore.platform.Platform;
import tocraft.craftedcore.platform.VersionChecker;

public class EoMTweaks {

	public static final Logger LOGGER = LoggerFactory.getLogger(EoMTweaks.class);
	public static final String MODID = "eomtw";
	public static String versionURL = "https://raw.githubusercontent.com/ToCraft/eomtweaks/1.18.2/gradle.properties";
	
	public void initialize() {		
		PlayerEvents.PLAYER_JOIN.register(player -> {
			String newestVersion = VersionChecker.checkForNewVersion(versionURL);
			if (newestVersion != null && !Platform.getMod(MODID).getVersion().equals(newestVersion))
				player.displayClientMessage(new TranslatableComponent("eomtw.update", newestVersion), false);
		});
	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MODID, name);
	}
}
