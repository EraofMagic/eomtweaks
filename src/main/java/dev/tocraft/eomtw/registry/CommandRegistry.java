package dev.tocraft.eomtw.registry;

import dev.tocraft.eomtw.EoMTweaks;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import dev.tocraft.eomtw.commands.EoMDump;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = EoMTweaks.MODID)
public class CommandRegistry {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new EoMDump(event.getDispatcher());

        // TODO: replace with CommandEvents.REGISTRATION
        ConfigCommand.register(event.getDispatcher());
    }
}
