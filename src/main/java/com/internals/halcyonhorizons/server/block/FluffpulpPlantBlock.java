package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FluffpulpPlantBlock extends BushBlock {

    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE;

    public FluffpulpPlantBlock(Properties p_51021_) {
        super(p_51021_);
    }

    public VoxelShape getShape(BlockState p_53517_, BlockGetter p_53518_, BlockPos p_53519_, CollisionContext p_53520_) {
        Vec3 vec3 = p_53517_.getOffset(p_53518_, p_53519_);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(HorizonsBlockRegistry.FLUFFPULP_BLOCK.get()) &&
                !p_51042_.is(BlockTags.DIRT) &&
                !p_51042_.is(Blocks.FARMLAND);
    }
    
    static {
        SHAPE = Block.box((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)13.0F, (double)14.0F);
    }
}
