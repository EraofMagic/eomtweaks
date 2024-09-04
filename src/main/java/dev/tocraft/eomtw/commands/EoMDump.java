package dev.tocraft.eomtw.commands;

import com.mojang.brigadier.CommandDispatcher;
import dev.tocraft.eomtw.tools.SawDustTagGenerator;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class EoMDump {
    public EoMDump(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(("EomDump")).requires(req -> req.hasPermission(3)).executes((command) -> {
            SawDustTagGenerator.generate();
            return 0;
        }));
    }
}
