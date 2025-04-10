package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.misc.HorizonsMath;
import com.internals.halcyonhorizons.server.misc.HorizonsTagRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class GlowpineTreeFeature extends Feature<NoneFeatureConfiguration> {

    public GlowpineTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Random trunk height between 6 and 10
        int height = 6 + random.nextInt(5);

        // Check space
        for (int y = 0; y <= height + 2; y++) {
            if (!level.getBlockState(origin.above(y)).isAir()) {
                return false; // not enough space
            }
        }

        // Place trunk
        for (int y = 0; y < height; y++) {
            level.setBlock(origin.above(y), HorizonsBlockRegistry.GLOWPINE_LOG.get().defaultBlockState(), 2);
        }

        // Foliage layers - bushier at the bottom
        BlockPos top = origin.above(height);
        int foliageLayers = 4; // Number of leafy layers
        for (int i = 0; i < foliageLayers; i++) {
            int radius = i / 2 + 1; // Increase radius every 2 layers
            BlockPos layerPos = top.below(i);
            placeLeafCircle(level, layerPos, radius);
        }

        // Tiny leaf tip at the top
        level.setBlock(top.above(), HorizonsBlockRegistry.GLOWPINE_LEAVES.get().defaultBlockState(), 2);

        return true;
    }

    private void placeLeafCircle(WorldGenLevel level, BlockPos center, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= radius + 1) { // Diamond/circle shape
                    BlockPos pos = center.offset(dx, 0, dz);
                    if (level.getBlockState(pos).isAir()) {
                        level.setBlock(pos, HorizonsBlockRegistry.GLOWPINE_LEAVES.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }


}

