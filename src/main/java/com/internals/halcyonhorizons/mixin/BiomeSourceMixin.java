package com.internals.halcyonhorizons.mixin;

import com.internals.halcyonhorizons.server.level.biome.BiomeSourceAccessor;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import org.spongepowered.asm.mixin.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(BiomeSource.class)

public class BiomeSourceMixin  implements BiomeSourceAccessor {
    @Mutable
    @Final
    @Shadow
    private Supplier<Set<Holder<Biome>>> possibleBiomes;
    @Unique
    private boolean halcyonHorizons$expanded;
    @Unique
    private Map<ResourceKey<Biome>, Holder<Biome>> halcyonHorizons$map = new HashMap<>();

    @Override
    public void halcyonHorizons$setResourceKeyMap(Map<ResourceKey<Biome>, Holder<Biome>> map) {
        this.halcyonHorizons$map = map;
    }

    @Override
    public Map<ResourceKey<Biome>, Holder<Biome>> halcyonHorizons$getResourceKeyMap() {
        return halcyonHorizons$map;
    }

    @Override
    public void halcyonHorizons$expandBiomesWith(Set<Holder<Biome>> newGenBiomes) {
        if(!halcyonHorizons$expanded){
            ImmutableSet.Builder<Holder<Biome>> builder = ImmutableSet.builder();
            builder.addAll(this.possibleBiomes.get());
            builder.addAll(newGenBiomes);
            possibleBiomes = Suppliers.memoize(builder::build);
            halcyonHorizons$expanded = true;
        }
    }
}
