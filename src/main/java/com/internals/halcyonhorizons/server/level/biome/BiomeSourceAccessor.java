package com.internals.halcyonhorizons.server.level.biome;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.Map;
import java.util.Set;

public interface BiomeSourceAccessor {
    void halcyonHorizons$setResourceKeyMap(Map<ResourceKey<Biome>, Holder<Biome>> var1);

    Map<ResourceKey<Biome>, Holder<Biome>> halcyonHorizons$getResourceKeyMap();

    void halcyonHorizons$expandBiomesWith(Set<Holder<Biome>> var1);
}