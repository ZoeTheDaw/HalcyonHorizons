package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class WetFluffpulpBlock extends Block {

    public WetFluffpulpBlock() {
        super(Properties.of().mapColor(MapColor.WOOL).strength(0.5F, .6F).sound(SoundType.FROGLIGHT));
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (level.dimensionType().ultraWarm()) {
            level.setBlock(blockPos, HorizonsBlockRegistry.FLUFFPULP_BLOCK.get().defaultBlockState(), 3);
            level.levelEvent(2009, blockPos, 0);
            level.playSound((Player)null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }

    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        Direction direction = Direction.getRandom(randomSource);
        if (direction != Direction.UP) {
            BlockPos blockPos1 = blockPos.relative(direction);
            BlockState blockState1 = level.getBlockState(blockPos1);
            if (!blockState.canOcclude() || !blockState1.isFaceSturdy(level, blockPos1, direction.getOpposite())) {
                double x = (double)blockPos.getX();
                double y = (double)blockPos.getY();
                double z = (double)blockPos.getZ();
                if (direction == Direction.DOWN) {
                    y -= 0.05;
                    x += randomSource.nextDouble();
                    z += randomSource.nextDouble();
                } else {
                    y += randomSource.nextDouble() * 0.8;
                    if (direction.getAxis() == Direction.Axis.X) {
                        z += randomSource.nextDouble();
                        if (direction == Direction.EAST) {
                            ++x;
                        } else {
                            x += 0.05;
                        }
                    } else {
                        x += randomSource.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++z;
                        } else {
                            z += 0.05;
                        }
                    }
                }

                level.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }
    }

    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        double v = Math.abs(entity.getDeltaMovement().y);
        if (v < 0.1 && !entity.isSteppingCarefully()) {
            double v1 = 0.4 + v * 0.2;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(v1, (double)1.0F, v1));
        }

        super.stepOn(level, blockPos, blockState, entity);
    }
}
