package com.internals.halcyonhorizons.mixin;

import com.internals.halcyonhorizons.server.config.BiomeGenerationConfig;
import com.internals.halcyonhorizons.server.config.BiomeGenerationNoiseCondition;
import com.internals.halcyonhorizons.server.level.biome.BiomeSourceAccessor;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRarity;
import com.internals.halcyonhorizons.server.level.biome.MultiNoiseBiomeSourceAccessor;
import com.internals.halcyonhorizons.server.misc.VoronoiGenerator;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(value = MultiNoiseBiomeSource.class, priority = -69420)
public class MultiNoiseBiomeSourceMixin implements MultiNoiseBiomeSourceAccessor {
    @Unique
    private long halcyonHorizons$lastSampledWorldSeed;

    @Unique
    private ResourceKey<Level> halcyonHorizons$lastSampledDimension;

    @Inject(at = @At("HEAD"),
            method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;",
            cancellable = true
    )
    private void citadel_getNoiseBiomeCoords(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
        VoronoiGenerator.VoronoiInfo voronoiInfo = HorizonsBiomeRarity.getRareBiomeInfoForQuad(halcyonHorizons$lastSampledWorldSeed, x, z);
        if(voronoiInfo != null){
            float unquantizedDepth = Climate.unquantizeCoord(sampler.sample(x, y, z).depth());
            int foundRarityOffset = HorizonsBiomeRarity.getRareBiomeOffsetId(voronoiInfo);
            for (Map.Entry<ResourceKey<Biome>, BiomeGenerationNoiseCondition> condition : BiomeGenerationConfig.BIOMES.entrySet()) {
                if (foundRarityOffset == condition.getValue().getRarityOffset() && condition.getValue().test(x, y, z, unquantizedDepth, sampler, halcyonHorizons$lastSampledDimension, voronoiInfo)) {
                    cir.setReturnValue(((BiomeSourceAccessor)this).halcyonHorizons$getResourceKeyMap().get(condition.getKey()));
                }
            }
        }
    }

    @Override
    public void halcyonHorizons$setLastSampledSeed(long seed) {
        halcyonHorizons$lastSampledWorldSeed = seed;
    }

    @Override
    public void halcyonHorizons$setLastSampledDimension(ResourceKey<Level> dimension) {
        halcyonHorizons$lastSampledDimension = dimension;
    }
}
