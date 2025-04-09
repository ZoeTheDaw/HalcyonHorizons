package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ArchivesPlantBlock extends BushBlock {

    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE;

    public ArchivesPlantBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).strength(0.5F, .6F).sound(SoundType.STEM).noOcclusion().noCollission().offsetType(BlockBehaviour.OffsetType.XZ));
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        Vec3 vec3 = blockState.getOffset(blockGetter, blockPos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);

        boolean canPlace = belowState.is(HorizonsBlockRegistry.TRAVERTINE.get());

        return canPlace;
    }

    static {
        SHAPE = Block.box((double) 2.0F, (double) 0.0F, (double) 2.0F, (double) 14.0F, (double) 13.0F, (double) 14.0F);
    }
}
