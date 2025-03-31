package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class CroliveSaplingBlock extends SaplingBlock {
    private final boolean growsNaturally;
    private final AbstractTreeGrower treeGrower;

    public CroliveSaplingBlock(AbstractTreeGrower grower, Properties properties, boolean growsNaturally) {
        super(grower, properties);
        this.growsNaturally = growsNaturally;
        this.treeGrower = grower;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (growsNaturally) {
            super.randomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(HorizonsBlockRegistry.TRAVERTINE.get());
    }

    public void advanceTree(ServerLevel p_222001_, BlockPos p_222002_, BlockState p_222003_, RandomSource p_222004_) {
        System.out.println("Attempting to advance tree at " + p_222002_); // Log position

        if (p_222003_.getValue(STAGE) == 0) {
            System.out.println("Sapling is at stage 0, advancing to next stage.");
            p_222001_.setBlock(p_222002_, p_222003_.cycle(STAGE), 4);
        } else {
            System.out.println("Sapling is at stage 1, checking block below...");
            if (p_222001_.getBlockState(p_222002_.below()).is(HorizonsBlockRegistry.TRAVERTINE.get())) {
                System.out.println("Correct block detected! Growing tree...");
                this.treeGrower.growTree(p_222001_, p_222001_.getChunkSource().getGenerator(), p_222002_, p_222003_, p_222004_);
            } else {
                System.out.println("Tree growth blocked. Block below: " + p_222001_.getBlockState(p_222002_.below()).getBlock());
            }
        }
    }
}
