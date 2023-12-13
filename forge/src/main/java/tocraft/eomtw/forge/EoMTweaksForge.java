package tocraft.eomtw.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tocraft.eomtw.EoMTweaks;
import tocraft.eomtw.registry.EoMRegistry;
import tocraft.eomtw.registry.OreRegistry;

@Mod(EoMTweaks.MODID)
public class EoMTweaksForge {
			
	public EoMTweaksForge() {
		new EoMTweaks().initialize();
		EoMRegistry.register(FMLJavaModLoadingContext.get().getModEventBus());
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, OreRegistry::biomeLoadingEvent);
	}
}
