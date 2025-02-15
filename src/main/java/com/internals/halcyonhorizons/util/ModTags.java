package com.internals.halcyonhorizons.util;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Blocks {
    }

    public static class Items {
        public static final TagKey<Item> BAOBABE_LOGS = createTag("baobabe_logs");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(new ResourceLocation(HalcyonHorizons.MODID, name));
    }
}}
