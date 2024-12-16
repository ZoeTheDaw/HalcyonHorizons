package com.internals.halcyonhorizons.server.level.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public interface MultiNoiseBiomeSourceAccessor {

    void halyconHorizons$setLastSampledSeed(long seed);

    void halyconHorizons$setLastSampledDimension(ResourceKey<Level> dimension);
}