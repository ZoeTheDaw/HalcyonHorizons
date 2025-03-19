package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.misc.HorizonsTagRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class BaobabeTreeFeature extends Feature<NoneFeatureConfiguration> {

    public BaobabeTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos treeBottom = context.origin();
        int height = 5 + randomsource.nextInt(4);

        if (!checkCanTreePlace(level, treeBottom, height)) {
            return false;
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos basePos = treeBottom.offset(x, 0, z);
                level.setBlock(basePos, HorizonsBlockRegistry.BAOBABE_WOOD.get().defaultBlockState(), 3);
            }
        }

        // Generate the trunk as a single column above the 3x3 base
        for (int i = 1; i <= height; i++) { // Start at 1 to avoid overwriting the base
            BlockPos trunkPos = treeBottom.above(i);
            level.setBlock(trunkPos, HorizonsBlockRegistry.BAOBABE_LOG.get().defaultBlockState(), 3);
        }

        BlockPos topOfTrunk = treeBottom.above(height);
        level.setBlock(topOfTrunk, HorizonsBlockRegistry.NIGHTLIGHT.get().defaultBlockState(), 3);

        makeCap(level, treeBottom.above(height - (height - 1)), height, new BlockPos.MutableBlockPos());

        return true;
    }

    private boolean checkCanTreePlace(WorldGenLevel level, BlockPos treeBottom, int height) {
        BlockState below = level.getBlockState(treeBottom.below());
        if (!below.is(HorizonsBlockRegistry.FLUFFPULP_BLOCK.get())) {
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

    protected void makeCap(WorldGenLevel level, BlockPos center, int height, BlockPos.MutableBlockPos mutablePos) {
        int foliageRadius = 2;

        for (int i = height - 3; i <= height; ++i) {
            int j = i < height ? foliageRadius : foliageRadius - 1;
            int k = foliageRadius - 2;

            for (int l = -j; l <= j; ++l) {
                for (int i1 = -j; i1 <= j; ++i1) {
                    boolean flag = l == -j;
                    boolean flag1 = l == j;
                    boolean flag2 = i1 == -j;
                    boolean flag3 = i1 == j;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;

                    if (i >= height || flag4 != flag5) {
                        mutablePos.setWithOffset(center, l, i, i1);
                        if (!level.getBlockState(mutablePos).isSolidRender(level, mutablePos)) {
                            BlockState blockState = HorizonsBlockRegistry.LAMPPAPER_BLOCK.get().defaultBlockState();
                            this.setBlock(level, mutablePos, blockState);
                        }
                    }
                }
            }
        }
    }

    public static boolean canReplace(BlockState state) {
        return (state.isAir() || state.canBeReplaced() || state.is(HorizonsBlockRegistry.LAMPPAPER_BLOCK.get())) && !state.is(HorizonsTagRegistry.UNMOVEABLE) && state.getFluidState().isEmpty();
    }

}