//package com.internals.halcyonhorizons.server.level.surface;
//
//import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
//import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
//import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRegistry;
//import net.minecraft.world.level.levelgen.SurfaceRules;
//
//public class HorizonsSurfaceRules {
//    public static void setup() {
//        SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.isBiome(HorizonsBiomeRegistry.AVIAN_INFIRMARY), createAvianInfirmaryRules());
//    }
//
//    public static SurfaceRules.RuleSource createAvianInfirmaryRules() {
//        SurfaceRules.RuleSource limestone = SurfaceRules.state(HorizonsBlockRegistry.FLUFFPULP.get().defaultBlockState());
//        return limestone;
//    }
//}
