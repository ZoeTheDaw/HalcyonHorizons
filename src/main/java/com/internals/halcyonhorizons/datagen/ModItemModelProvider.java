package com.internals.halcyonhorizons.datagen;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HalcyonHorizons.MODID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
    }
}
