package com.internals.halcyonhorizons.server.level.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BaobabeTreeFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState BAOBABE_WOOD = Blocks.OAK_WOOD.defaultBlockState(); // Change to your custom block
    private static final BlockState BAOBABE_LOG = Blocks.OAK_LOG.defaultBlockState(); // Change to your custom block
    private static final BlockState LAMPPAPER_BLOCK = Blocks.RED_MUSHROOM_BLOCK.defaultBlockState(); // Change to your custom block
    private static final BlockState NIGHTLIGHT_BLOCK = Blocks.SHROOMLIGHT.defaultBlockState(); // Change to your custom block

    public BaobabeTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        System.out.println("BaobabeTreeFeature: Attempting to place tree at " + context.origin());



        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Ensure the tree is placed on valid ground
        if (!level.getBlockState(origin.below()).isSolid()) {
            return false;
        }

        // Generate 3x3 base of Baobabe Wood
        placeBase(level, origin);

        // Generate trunk height (4-9 blocks tall)
        int trunkHeight = 4 + random.nextInt(6);
        BlockPos topPos = placeTrunk(level, origin.above(), trunkHeight);

        // Place Nightlight Block at the top of the trunk
        level.setBlock(topPos, NIGHTLIGHT_BLOCK, 3);

        // Generate the mushroom-like foliage cap
        placeFoliage(level, topPos.above(), random);

        return true;
    }

    // Places a 3x3 base of Baobabe Wood
    private void placeBase(LevelAccessor level, BlockPos pos) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos basePos = pos.offset(dx, 0, dz);
                level.setBlock(basePos, BAOBABE_WOOD, 3);
            }
        }
    }

    // Generates the trunk upwards from the center of the base
    private BlockPos placeTrunk(LevelAccessor level, BlockPos startPos, int height) {
        BlockPos.MutableBlockPos pos = startPos.mutable();


        for (int i = 0; i < height; i++) {
            level.setBlock(pos, BAOBABE_LOG, 3);
            pos.move(Direction.UP);
        }

        return pos.immutable(); // Return final top position
    }

    // Generates the mushroom-like foliage cap
    private void placeFoliage(LevelAccessor level, BlockPos topPos, RandomSource random) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int radius = 2 + random.nextInt(2); // Randomize radius (2-3)

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                int distance = Math.abs(dx) + Math.abs(dz);
                if (distance <= radius + random.nextInt(2)) { // Rounded shape like a mushroom cap
                    pos.set(topPos.getX() + dx, topPos.getY(), topPos.getZ() + dz);
                    level.setBlock(pos, LAMPPAPER_BLOCK, 3);
                }
            }
        }
    }
}