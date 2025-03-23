package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class FluffPillowBlock extends SlabBlock {
    public FluffPillowBlock(Properties properties) {
        super(properties);
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
}
