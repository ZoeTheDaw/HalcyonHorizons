package com.internals.halcyonhorizons.server.block;

import com.internals.halcyonhorizons.server.misc.HorizonsEntityTypeTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class PermafrostBlock extends Block {
    public PermafrostBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(1.2F, 4.5F).sound(SoundType.DEEPSLATE));
    }

    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            boolean hasFrostWalker = EnchantmentHelper.hasFrostWalker(livingEntity);
            boolean wearingLeatherBoots = livingEntity.getItemBySlot(EquipmentSlot.FEET).canWalkOnPowderedSnow(livingEntity);
            boolean entityWalkOnPowderSnow = !(entity instanceof Player) && entity.getType().is(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS);
            boolean entityWalkOnPermafrost = !(entity instanceof Player) && entity.getType().is(HorizonsEntityTypeTagRegistry.PERMAFROST_WALKABLE_MOBS);

            if (!entity.isSteppingCarefully() && !hasFrostWalker && !wearingLeatherBoots && !entityWalkOnPowderSnow && !entityWalkOnPermafrost) {
                entity.hurt(level.damageSources().freeze(), 1.0F);
            }
        }
        super.stepOn(level, blockPos, blockState, entity);
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return true;
    }
}
