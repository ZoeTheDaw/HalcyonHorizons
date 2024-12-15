package com.internals.halcyonhorizons.server.level.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public interface MultiNoiseBiomeSourceAccessor {
    void halcyonHorizons$setLastSampledSeed(long var1);

    void halcyonHorizons$setLastSampledDimension(ResourceKey<Level> var1);
}