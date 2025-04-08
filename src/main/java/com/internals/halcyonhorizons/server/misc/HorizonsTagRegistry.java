package com.internals.halcyonhorizons.server.misc;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class HorizonsTagRegistry {
    public static final TagKey<Block> UNMOVABLE = registerBlockTag("unmovable");
    public static final TagKey<Block> MARBLE_BRICKS = registerBlockTag("marble_bricks");
    public static final TagKey<Block> TRAVERTINE_BRICKS = registerBlockTag("travertine_bricks");
    public static final TagKey<Block> ALPHABET_BLOCKS = registerBlockTag("alphabet_blocks");
    public static final TagKey<Block> NUMBER_BLOCKS = registerBlockTag("number_blocks");


    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(HalcyonHorizons.MODID, name));
    }
}
