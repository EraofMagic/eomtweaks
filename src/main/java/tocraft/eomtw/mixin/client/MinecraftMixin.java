package tocraft.eomtw.mixin.client;

import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tocraft.eomtw.util.TraceUtils;

// based on code by comp500
@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "crash", at = @At("HEAD"))
    private static void onCrash(CrashReport report, CallbackInfo ci) {
        StringBuilder crashReportBuilder = new StringBuilder();
        TraceUtils.printTrace(report.getException().getStackTrace(), crashReportBuilder);
        System.out.println(crashReportBuilder);
    }
}
