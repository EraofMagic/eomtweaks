package tocraft.eomtw.ftb.teams;

import dev.ftb.mods.ftbteams.event.TeamCollectPropertiesEvent;

import java.util.function.Consumer;

public class TeamCollectPropertiesEventHandler implements Consumer<TeamCollectPropertiesEvent> {
    @Override
    public void accept(TeamCollectPropertiesEvent event) {
        event.add(EoMTeamProperties.DESTROY_BLOCKS_BLACKLIST);
        event.add(EoMTeamProperties.DESTROY_BLOCKS_WHITELIST);
        event.add(EoMTeamProperties.INTERACT_BLOCKS_BLACKLIST);
        event.add(EoMTeamProperties.INTERACT_BLOCKS_WHITELIST);
        event.add(EoMTeamProperties.INTERACT_ENTITIES_BLACKLIST);
        event.add(EoMTeamProperties.INTERACT_ENTITIES_WHITELIST);
    }
}
