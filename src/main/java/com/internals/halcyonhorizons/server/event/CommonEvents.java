package com.internals.halcyonhorizons.server.event;

import com.google.common.collect.ImmutableSet;
import com.internals.halcyonhorizons.server.level.biome.BiomeSourceAccessor;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRarity;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonEvents {

    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        HorizonsBiomeRarity.init();
        //moved from citadel
        RegistryAccess registryAccess = event.getServer().registryAccess();
        Registry<Biome> allBiomes = registryAccess.registryOrThrow(Registries.BIOME);
        Registry<LevelStem> levelStems = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
        Map<ResourceKey<Biome>, Holder<Biome>> biomeMap = new HashMap<>();
        for (ResourceKey<Biome> biomeResourceKey : allBiomes.registryKeySet()) {
            Optional<Holder.Reference<Biome>> holderOptional = allBiomes.getHolder(biomeResourceKey);
            holderOptional.ifPresent(biomeHolder -> biomeMap.put(biomeResourceKey, biomeHolder));
        }
        for (ResourceKey<LevelStem> levelStemResourceKey : levelStems.registryKeySet()) {
            Optional<Holder.Reference<LevelStem>> holderOptional = levelStems.getHolder(levelStemResourceKey);
            if (holderOptional.isPresent() && holderOptional.get().value().generator().getBiomeSource() instanceof BiomeSourceAccessor expandedBiomeSource) {
                expandedBiomeSource.halyconHorizons$setResourceKeyMap(biomeMap);
                if (levelStemResourceKey.equals(LevelStem.OVERWORLD)) {
                    ImmutableSet.Builder<Holder<Biome>> biomeHolders = ImmutableSet.builder();
                    for (ResourceKey<Biome> biomeResourceKey : HorizonsBiomeRegistry.HALCYON_HORIZONS_BIOMES) {
                        allBiomes.getHolder(biomeResourceKey).ifPresent(biomeHolders::add);
                    }
                    expandedBiomeSource.halyconHorizons$expandBiomesWith(biomeHolders.build());
                }
            }
        }
    }
}
