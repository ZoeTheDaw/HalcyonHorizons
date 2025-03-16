package com.internals.halcyonhorizons.server.misc;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class HorizonsTagRegistry {
    public static final TagKey<Block> UNMOVEABLE = registerBlockTag("unmovable");


    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(HalcyonHorizons.MODID, name));
    }
}
