package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class IvyFeature extends Feature<NoneFeatureConfiguration> {
    public IvyFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        context.config();
        if (!level.isEmptyBlock(origin)) {
            return false;
        } else {
            for(Direction direction : Direction.values()) {
                if (direction != Direction.DOWN && VineBlock.isAcceptableNeighbour(level, origin.relative(direction), direction)) {
                    level.setBlock(origin, (BlockState) HorizonsBlockRegistry.IVY.get().defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), true), 2);
                    return true;
                }
            }

            return false;
        }
    }
}
