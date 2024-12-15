package com.internals.halcyonhorizons.server.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HorizonsServerConfig {

    public final ForgeConfigSpec.DoubleValue skyBiomeMeanWidth;
    public final ForgeConfigSpec.IntValue skyBiomeMeanSeparation;
    public final ForgeConfigSpec.DoubleValue skyBiomeWidthRandomness;
    public final ForgeConfigSpec.DoubleValue skyBiomeSpacingRandomness;
    public final ForgeConfigSpec.BooleanValue warnGenerationIncompatibility;


    public HorizonsServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("generation");
        skyBiomeMeanWidth = builder.comment("Average radius (in blocks) of an Halcyon Horizons biome.").translation("sky_biome_mean_width").defineInRange("cave_biome_mean_width", 300D, 10.0D, Double.MAX_VALUE);
        skyBiomeMeanSeparation = builder.comment("Average separation (in blocks) between each Halcyon Horizons biome.").translation("sky_biome_mean_separation").defineInRange("sky_biome_mean_separation", 900, 50, Integer.MAX_VALUE);
        skyBiomeWidthRandomness = builder.comment("How irregularly shaped Halcyon Horizons biomes can generate. 0 = all biomes nearly circular. 1 = biomes completely squiggly in shape.").translation("sky_biome_width_randomness").defineInRange("sky_biome_width_randomness", 0.15D, 0, 1D);
        skyBiomeSpacingRandomness = builder.comment("Average spacing in between Halcyon Horizons biomes. 0 = all biomes nearly perfectly equidistant. 1 = biomes completely randomly spread out, sometimes next to eachother.").translation("sky_biome_spacing_randomness").defineInRange("sky_biome_spacing_randomness", 0.45D, 0, 1D);
        warnGenerationIncompatibility = builder.comment("Whether to warn users when a server starts if an incompatible generation mod is detected.").translation("warn_generation_incompatibility").define("warn_generation_incompatibility", true);
        builder.pop();
    }
}
