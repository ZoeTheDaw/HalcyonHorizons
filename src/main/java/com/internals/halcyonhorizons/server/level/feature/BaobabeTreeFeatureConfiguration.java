package com.internals.halcyonhorizons.server.level.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BaobabeTreeFeatureConfiguration implements FeatureConfiguration {
    public static final Codec<BaobabeTreeFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((config) -> config.trunkProvider),
                    BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((config) -> config.foliageProvider),
                    Codec.INT.fieldOf("foliage_radius").orElse(3).forGetter((config) -> config.foliageRadius),
                    Codec.INT.fieldOf("height").orElse(10).forGetter((config) -> config.height)
            ).apply(instance, BaobabeTreeFeatureConfiguration::new)
    );

    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider foliageProvider;
    public final int foliageRadius;
    public final int height;

    public BaobabeTreeFeatureConfiguration(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, int foliageRadius, int height) {
        this.trunkProvider = trunkProvider;
        this.foliageProvider = foliageProvider;
        this.foliageRadius = foliageRadius;
        this.height = height;
    }
}
