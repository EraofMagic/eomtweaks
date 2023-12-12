package tocraft.eomtw.forge;

import net.minecraftforge.fml.common.Mod;
import tocraft.eomtw.EoMTweaks;

@Mod(EoMTweaks.MODID)
public class EoMTweaksForge {
			
	public EoMTweaksForge() {
		new EoMTweaks().initialize();
		EoMRegistry.register();
		OreRegistry.setup();
	}
}
