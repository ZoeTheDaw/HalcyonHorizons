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
    @Shadow
    public Supplier<Set<Holder<Biome>>> possibleBiomes;
    @Unique
    private boolean expanded;
    private Map<ResourceKey<Biome>, Holder<Biome>> map = new HashMap<>();

    @Unique
    @Override
    public void halyconHorizons$setResourceKeyMap(Map<ResourceKey<Biome>, Holder<Biome>> map) {
        this.map = map;
    }

    @Unique
    @Override
    public Map<ResourceKey<Biome>, Holder<Biome>> halyconHorizons$getResourceKeyMap() {
        return map;
    }

    @Unique
    @Override
    public void expandBiomesWith(Set<Holder<Biome>> newGenBiomes) {
        if(!expanded){
            ImmutableSet.Builder<Holder<Biome>> builder = ImmutableSet.builder();
            builder.addAll(this.possibleBiomes.get());
            builder.addAll(newGenBiomes);
            possibleBiomes = Suppliers.memoize(builder::build);
            expanded = true;
        }
    }
}
