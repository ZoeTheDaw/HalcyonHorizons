package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.misc.HorizonsMath;
import com.internals.halcyonhorizons.server.misc.HorizonsTagRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
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
        BlockPos treeBottom = context.origin();
        RandomSource randomsource = context.random();
        int height = 15 + randomsource.nextInt(6);

        if (!checkCanTreePlace(level, treeBottom, height)) {
            return false;
        }

        for (int y = 0; y < height; y++) {
            level.setBlock(treeBottom.above(y), HorizonsBlockRegistry.GLOWPINE_LOG.get().defaultBlockState(), 2);
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos basePos = treeBottom.offset(x, 0, z);
                level.setBlock(basePos, HorizonsBlockRegistry.GLOWPINE_WOOD.get().defaultBlockState(), 3);

                if ((x == 0 && Math.abs(z) == 1) || (z == 0 && Math.abs(x) == 1)) {
                    int height1 = 1 + level.getRandom().nextInt(2);
                    for (int y = 1; y <= height1; y++) {
                        BlockPos extendedPos = basePos.above(y);
                        level.setBlock(extendedPos, HorizonsBlockRegistry.GLOWPINE_WOOD.get().defaultBlockState(), 3);
                    }
                }
            }
        }

        placeCompassPatternedFoliage(level, treeBottom.above(height), randomsource, HorizonsBlockRegistry.GLOWPINE_LEAVES.get().defaultBlockState(), 2, 1, 2, 10);

        return true;
    }

    private boolean checkCanTreePlace(WorldGenLevel level, BlockPos treeBottom, int height) {
        BlockState below = level.getBlockState(treeBottom.below());
        if (!below.is(BlockTags.DIRT)) {
            return false;
        }
        for (int i = 0; i < height; i++) {
            if (!canReplace(level.getBlockState(treeBottom.above(i)))) {
                return false;
            }
        }
        BlockPos treeTop = treeBottom.above(height);
        for (BlockPos checkLeaf : BlockPos.betweenClosed(treeTop.offset(-3, -1, -3), treeTop.offset(3, 3, 3))) {
            if (!canReplace(level.getBlockState(checkLeaf))) {
                return false;
            }
        }
        return true;
    }

    private void placeCompassPatternedFoliage(WorldGenLevel level, BlockPos center, RandomSource randomSource, BlockState defaultLeaf, int radiusX, int radiusY, int radiusZ, int foliageHeight) {

        BlockPos tip = center;
        if (level.getBlockState(tip).isAir()) {
            level.setBlock(tip, maybeRandomLeaf(randomSource, defaultLeaf), 2);
        }

        BlockPos layer1 = center.below(1);
        placeCrossLeaves(level, layer1, randomSource, defaultLeaf);

        BlockPos layer2 = center.below(3);
        placeCrossLeaves(level, layer2, randomSource, defaultLeaf);

        BlockPos layer3 = center.below(4);
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (dx == 0 && dz == 0) continue;
                if (Math.abs(dx) == 2 && Math.abs(dz) == 2) continue;

                BlockPos pos = layer3.offset(dx, 0, dz);
                if (level.getBlockState(pos).isAir()) {
                    level.setBlock(pos, maybeRandomLeaf(randomSource, defaultLeaf), 2);
                }
            }
        }

        BlockPos layer5 = center.below(6);
        placeCrossLeaves(level, layer5, randomSource, defaultLeaf);

        BlockPos layer6 = center.below(7);
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (dx == 0 && dz == 0) continue;
                if (Math.abs(dx) == 2 && Math.abs(dz) == 2) continue;

                BlockPos pos = layer6.offset(dx, 0, dz);
                if (level.getBlockState(pos).isAir()) {
                    level.setBlock(pos, maybeRandomLeaf(randomSource, defaultLeaf), 2);
                }
            }
        }

        BlockPos layer7 = center.below(8);
        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                if (dx == 0 && dz == 0) continue;
                if (Math.abs(dx) == 3 && Math.abs(dz) == 3) continue;

                BlockPos pos = layer7.offset(dx, 0, dz);
                if (level.getBlockState(pos).isAir()) {
                    level.setBlock(pos, maybeRandomLeaf(randomSource, defaultLeaf), 3);
                }
            }
        }

        BlockPos layer8 = center.below(9);
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (dx == 0 && dz == 0) continue;
                if (Math.abs(dx) == 2 && Math.abs(dz) == 2) continue;

                BlockPos pos = layer8.offset(dx, 0, dz);
                if (level.getBlockState(pos).isAir()) {
                    level.setBlock(pos, maybeRandomLeaf(randomSource, defaultLeaf), 2);
                }
            }
        }

        BlockPos layer9 = center.below(10);
        placeCrossLeaves(level, layer9, randomSource, defaultLeaf);
    }

    private void placeCrossLeaves(WorldGenLevel level, BlockPos center, RandomSource random, BlockState defaultLeaf) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos pos = center.relative(dir);
            if (level.getBlockState(pos).isAir()) {
                level.setBlock(pos, maybeRandomLeaf(random, defaultLeaf), 2);
            }
        }
    }

    private BlockState maybeRandomLeaf(RandomSource random, BlockState defaultLeaf) {
        return random.nextFloat() < 0.1f
                ? HorizonsBlockRegistry.GLOWPINE_PINE_LEAVES.get().defaultBlockState()
                : defaultLeaf;
    }

    private static boolean canReplace(BlockState state) {
        return (state.isAir() || state.canBeReplaced() || state.is(HorizonsBlockRegistry.GLOWPINE_LEAVES.get()) || state.is(HorizonsBlockRegistry.GLOWPINE_PINE_LEAVES.get())) && !state.is(HorizonsTagRegistry.UNMOVABLE) && state.getFluidState().isEmpty();
    }
}

