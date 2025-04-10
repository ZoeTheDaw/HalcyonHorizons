package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class GlowpineSaplingBlock extends SaplingBlock {

    private final boolean growsNaturally;
    private final AbstractTreeGrower treeGrower;

    public GlowpineSaplingBlock(AbstractTreeGrower grower, Properties properties, boolean growsNaturally) {
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

    public void advanceTree(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, RandomSource randomSource) {
        if (blockState.getValue(STAGE) == 0) {
            serverLevel.setBlock(blockPos, blockState.cycle(STAGE), 4);
        } else {
            this.treeGrower.growTree(serverLevel, serverLevel.getChunkSource().getGenerator(), blockPos, blockState, randomSource);
        }

    }
}
