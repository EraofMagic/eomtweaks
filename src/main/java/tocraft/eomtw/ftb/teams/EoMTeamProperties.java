package tocraft.eomtw.ftb.teams;

import tocraft.eomtw.EoMTweaks;

import java.util.HashMap;

public class EoMTeamProperties {
    public static final PrivacyToIdMapProperty BREAK_BLOCKS_BLACKLIST = new PrivacyToIdMapProperty(EoMTweaks.id("break_blocks_blacklist"), new HashMap<>());
    public static final PrivacyToIdMapProperty BREAK_BLOCKS_WHITELIST = new PrivacyToIdMapProperty(EoMTweaks.id("break_blocks_whitelist"), new HashMap<>());
    public static final PrivacyToIdMapProperty INTERACT_BLOCKS_BLACKLIST = new PrivacyToIdMapProperty(EoMTweaks.id("interact_blocks_blacklist"), new HashMap<>());
    public static final PrivacyToIdMapProperty INTERACT_BLOCKS_WHITELIST = new PrivacyToIdMapProperty(EoMTweaks.id("interact_blocks_whitelist"), new HashMap<>());
    public static final PrivacyToIdMapProperty INTERACT_ENTITIES_BLACKLIST = new PrivacyToIdMapProperty(EoMTweaks.id("interact_entities_blacklist"), new HashMap<>());
    public static final PrivacyToIdMapProperty INTERACT_ENTITIES_WHITELIST = new PrivacyToIdMapProperty(EoMTweaks.id("interact_entities_whitelist"), new HashMap<>());
}
