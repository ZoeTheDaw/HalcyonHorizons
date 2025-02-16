package com.internals.halcyonhorizons.datagen;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.item.HorizonsItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HorizonsItemRegistry.BAOBABE_DOOR.get())
                .pattern(".AA")
                .pattern(".AA")
                .pattern(".AA")
                .define('A', HorizonsBlockRegistry.BAOBABE_PLANKS.get())
                .unlockedBy(getHasName(HorizonsBlockRegistry.BAOBABE_PLANKS.get()), has(HorizonsBlockRegistry.BAOBABE_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HorizonsBlockRegistry.BAOBABE_TRAPDOOR.get())
                .pattern("...")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', HorizonsBlockRegistry.BAOBABE_PLANKS.get())
                .unlockedBy(getHasName(HorizonsBlockRegistry.BAOBABE_PLANKS.get()), has(HorizonsBlockRegistry.BAOBABE_PLANKS.get()))
                .save(pWriter);
    }

}