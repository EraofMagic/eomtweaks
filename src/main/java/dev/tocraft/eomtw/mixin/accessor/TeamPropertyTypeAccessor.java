package dev.tocraft.eomtw.mixin.accessor;

import dev.ftb.mods.ftbteams.api.property.TeamPropertyType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Invoker;

@Pseudo
@Mixin(value = TeamPropertyType.class, remap = false)
public interface TeamPropertyTypeAccessor {
    @Invoker
    static <Y> TeamPropertyType<Y> callRegister(String id, TeamPropertyType.FromNet<Y> p) {
        throw new AssertionError();
    }
}
