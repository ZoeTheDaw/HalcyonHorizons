package com.internals.halcyonhorizons.datagen;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.item.HorizonsItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }
    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HorizonsBlockRegistry.BAOBABE_PLANKS.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', HorizonsItemRegistry.BAOBABE_DOOR.get())
                .unlockedBy("has_baobabe_planks", has(HorizonsItemRegistry.BAOBABE_DOOR.get())).save(recipeOutput);
    }

}