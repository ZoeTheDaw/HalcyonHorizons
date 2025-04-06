package com.internals.halcyonhorizons.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NightTorchBlock extends TorchBlock {
    protected final ParticleOptions flameParticle;

    public NightTorchBlock(Properties properties, ParticleOptions particleOptions) {
        super(properties, particleOptions);
        this.flameParticle = particleOptions;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double $$4 = (double)blockPos.getX() + (double)0.5F;
        double $$5 = (double)blockPos.getY() + 0.7;
        double $$6 = (double)blockPos.getZ() + (double)0.5F;
        level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, $$4, $$5, $$6, (double)0.0F, (double)0.0F, (double)0.0F);
        level.addParticle(this.flameParticle, $$4, $$5, $$6, (double)0.0F, (double)0.0F, (double)0.0F);
    }
}
