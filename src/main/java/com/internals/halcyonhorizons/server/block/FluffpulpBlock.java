package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

public class FluffpulpBlock extends Block {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;
    private static final Direction[] ALL_DIRECTIONS = Direction.values();


    public FluffpulpBlock() {
        super(Properties.of().mapColor(MapColor.WOOL).strength(0.5F, .6F).sound(SoundType.WOOL));
    }

    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float fallAmount) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, blockState, blockPos, entity, fallAmount);
        } else {
            entity.causeFallDamage(fallAmount, 0.0F, level.damageSources().fall());
        }
    }

    public void updateEntityAfterFallOn(BlockGetter getter, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(getter, entity);
        } else {
            this.bounceUp(entity);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }
    }

    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {

        super.stepOn(level, blockPos, blockState, entity);
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState1.is(blockState.getBlock())) {
            this.tryAbsorbWater(level, blockPos);
        }

    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        this.tryAbsorbWater(level, blockPos);
        super.neighborChanged(blockState, level, blockPos, block, blockPos1, b);
    }

    protected void tryAbsorbWater(Level level, BlockPos blockPos) {
        if (this.removeWaterBreadthFirstSearch(level, blockPos)) {
            level.setBlock(blockPos, HorizonsBlockRegistry.WET_FLUFFPULP_BLOCK.get().defaultBlockState(), 2);
            level.levelEvent(2001, blockPos, Block.getId(Blocks.WATER.defaultBlockState()));
        }

    }

    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos blockPos) {
        BlockState fluffpulpState = level.getBlockState(blockPos);
        return BlockPos.breadthFirstTraversal(blockPos, 3, 65, (blockPos1, blockPosConsumer) -> {
            for (Direction direction : ALL_DIRECTIONS) {
                blockPosConsumer.accept(blockPos1.relative(direction));
            }

        }, (blockPos1) -> {
            if (blockPos1.equals(blockPos)) {
                return true;
            } else {
                BlockState blockstate = level.getBlockState(blockPos1);
                FluidState fluidstate = level.getFluidState(blockPos1);
                if (!fluffpulpState.canBeHydrated(level, blockPos, fluidstate, blockPos1)) {
                    return false;
                } else {
                    Block block = blockstate.getBlock();
                    if (block instanceof BucketPickup) {
                        BucketPickup bucketpickup = (BucketPickup) block;
                        if (!bucketpickup.pickupBlock(level, blockPos1, blockstate).isEmpty()) {
                            return true;
                        }
                    }

                    if (blockstate.getBlock() instanceof LiquidBlock) {
                        level.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 3);
                    } else {
                        if (!blockstate.is(Blocks.KELP) && !blockstate.is(Blocks.KELP_PLANT) && !blockstate.is(Blocks.SEAGRASS) && !blockstate.is(Blocks.TALL_SEAGRASS)) {
                            return false;
                        }

                        BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(blockPos1) : null;
                        dropResources(blockstate, level, blockPos1, blockentity);
                        level.setBlock(blockPos1, Blocks.AIR.defaultBlockState(), 3);
                    }

                    return true;
                }
            }
        }) > 1;
    }
}
