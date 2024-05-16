package tocraft.eomtw.ftb.teams;

import dev.ftb.mods.ftbteams.event.ClientTeamPropertiesChangedEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EoMTeamsClientEventHandler {
    public static void initialize() {

    }


    private void teamPropertiesChanged(ClientTeamPropertiesChangedEvent event) {
        event.getTeam().properties.map.forEach((key, value) -> {
            key.id.getNamespace();

        });
    }
}
