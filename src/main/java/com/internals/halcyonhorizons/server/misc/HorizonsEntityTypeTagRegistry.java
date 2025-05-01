package com.internals.halcyonhorizons.server.misc;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class HorizonsEntityTypeTagRegistry {
    public static final TagKey<EntityType<?>> PERMAFROST_WALKABLE_MOBS = registerEntityTypeTag("permafrost_walkable_mobs");

    private HorizonsEntityTypeTagRegistry() {
    }

    private static TagKey<EntityType<?>> registerEntityTypeTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(HalcyonHorizons.MODID, name));
    }
}
