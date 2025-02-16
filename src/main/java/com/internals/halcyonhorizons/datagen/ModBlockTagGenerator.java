package com.internals.halcyonhorizons.datagen;

import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HalcyonHorizons.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.LOGS)
                .add(HorizonsBlockRegistry.BAOBABE_LOG.get(),
                        HorizonsBlockRegistry.BAOBABE_WOOD.get(),
                        HorizonsBlockRegistry.STRIPPED_BAOBABE_LOG.get(),
                        HorizonsBlockRegistry.STRIPPED_BAOBABE_WOOD.get()
                );
    }
}
