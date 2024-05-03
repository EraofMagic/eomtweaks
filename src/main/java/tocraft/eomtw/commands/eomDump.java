package tocraft.eomtw.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import tocraft.eomtw.tools.sawDustTagGenerator;

public class eomDump {
    public eomDump(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(("EomDump")).requires(req -> req.hasPermission(3)).executes((command) -> {
            sawDustTagGenerator.generate();
            return 0;
        }));
    }
}
