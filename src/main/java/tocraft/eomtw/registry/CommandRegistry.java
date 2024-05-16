package tocraft.eomtw.registry;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import tocraft.eomtw.commands.eomDump;

@Mod.EventBusSubscriber(modid = "eomtw")
public class CommandRegistry {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new eomDump(event.getDispatcher());

        // TODO: replace with CommandEvents.REGISTRATION

        ConfigCommand.register(event.getDispatcher());
    }
}
