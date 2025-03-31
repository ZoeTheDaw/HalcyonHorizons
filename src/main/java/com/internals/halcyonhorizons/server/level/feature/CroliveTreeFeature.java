package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.misc.HorizonsTagRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CroliveTreeFeature extends Feature<NoneFeatureConfiguration> {

    public CroliveTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos treeBottom = context.origin();
        int height = 7 + randomsource.nextInt(4);

        if (!checkCanTreePlace(level, treeBottom, height)) {
            return false;
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos basePos = treeBottom.offset(x, 0, z);
                level.setBlock(basePos, HorizonsBlockRegistry.CROLIVE_WOOD.get().defaultBlockState(), 3);

                // Check if the position is north, south, east, or west (not diagonal or center)
                if ((x == 0 && Math.abs(z) == 1) || (z == 0 && Math.abs(x) == 1)) {
                    int height1 = 1 + level.getRandom().nextInt(2);  // Random height of 1 or 2 blocks
                    for (int y = 1; y <= height1; y++) {
                        BlockPos extendedPos = basePos.above(y);
                        level.setBlock(extendedPos, HorizonsBlockRegistry.CROLIVE_WOOD.get().defaultBlockState(), 3);
                    }
                }
            }
        }

        // Generate the trunk as a single column above the 3x3 base
        for (int i = 1; i < height; i++) {  // Use '< height' to stop before the top block
            // Trunk generation
            BlockPos trunkPos = treeBottom.above(i);
            level.setBlock(trunkPos, HorizonsBlockRegistry.CROLIVE_LOG.get().defaultBlockState(), 3);

            // Branch generation from the 4th block upwards
            if (i >= 4 && level.getRandom().nextFloat() < 0.5f) {  // 50% chance to generate a branch at this height
                int branchLength = 2 + level.getRandom().nextInt(1);  // Branch length of 2-3
                Direction branchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom());  // Random horizontal direction

                // Generate the branch
                BlockPos branchStart = trunkPos.relative(branchDirection);
                for (int j = 0; j < branchLength; j++) {
                    BlockPos branchPos = branchStart.relative(branchDirection, j);

                    // Check if the block directly below is not air, and change direction if needed
                    if (!level.getBlockState(branchPos.below()).isAir()) {
                        // Try a new direction to avoid overlapping with existing branches
                        branchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(level.getRandom());
                        branchPos = branchStart.relative(branchDirection, j);
                    }

                    level.setBlock(branchPos, HorizonsBlockRegistry.CROLIVE_WOOD.get().defaultBlockState(), 3);

                    // Optional upward curve at the end of the branch
                    if (j == branchLength - 1 && level.getRandom().nextFloat() < 0.4f) {  // 20% chance to curve up
                        BlockPos curvedBranchPos = branchPos.above();
                        level.setBlock(curvedBranchPos, HorizonsBlockRegistry.CROLIVE_WOOD.get().defaultBlockState(), 3);
                    }
                }
            }
        }


        BlockPos topOfTrunk = treeBottom.above(height);
        level.setBlock(topOfTrunk, HorizonsBlockRegistry.CROLIVE_WOOD.get().defaultBlockState(), 3);

        return true;
    }

    private boolean checkCanTreePlace(WorldGenLevel level, BlockPos treeBottom, int height) {
        BlockState below = level.getBlockState(treeBottom.below());
        if (!below.is(HorizonsBlockRegistry.TRAVERTINE.get())) {
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

    public static boolean canReplace(BlockState state) {
        return (state.isAir() || state.canBeReplaced() || state.is(HorizonsBlockRegistry.LAMPPAPER_BLOCK.get())) && !state.is(HorizonsTagRegistry.UNMOVEABLE) && state.getFluidState().isEmpty();
    }
}
