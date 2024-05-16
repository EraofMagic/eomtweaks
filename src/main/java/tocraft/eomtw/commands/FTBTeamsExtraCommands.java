package tocraft.eomtw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.ftb.mods.ftbteams.data.Team;
import dev.ftb.mods.ftbteams.data.TeamArgument;
import dev.ftb.mods.ftbteams.data.TeamArgumentProvider;
import dev.ftb.mods.ftbteams.property.TeamPropertyArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import tocraft.craftedcore.event.common.CommandEvents;

public class FTBTeamsExtraCommands implements CommandEvents.CommandRegistration {
    private RequiredArgumentBuilder<CommandSourceStack, TeamArgumentProvider> teamArg() {
        return Commands.argument("team", TeamArgument.create());
    }

    private Team teamArg(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        return TeamArgument.get(context, "team");
    }

    private String string(CommandContext<?> context, String name) {
        return StringArgumentType.getString(context, name);
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection selection) {
        dispatcher.register(Commands.literal("ftbteamsextra")
                .then(teamArg())
                .then(Commands.argument("key", TeamPropertyArgument.create())
                        .then(Commands.argument("value", StringArgumentType.greedyString()))
                        .executes(ctx -> {
                            teamArg(ctx).settings(ctx.getSource(), TeamPropertyArgument.get(ctx, "key"), string(ctx, "value"));
                            return 1;
                        })));
    }
}
