package com.internals.halcyonhorizons.server.block.grower;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CroliveGrower extends AbstractTreeGrower {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CROLIVE_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(HalcyonHorizons.MODID, "crolive_tree"));

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        System.out.println("CroliveGrower: Selecting tree feature (Bees: " + b + ")");
        return CROLIVE_TREE;
    }
}
