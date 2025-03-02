package com.internals.halcyonhorizons.datagen;

import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HalcyonHorizons.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(HorizonsBlockRegistry.BAOBABE_PLANKS);
        blockWithItem(HorizonsBlockRegistry.BAOBABE_DOOR);
        blockWithItem(HorizonsBlockRegistry.BAOBABE_TRAPDOOR);
        blockWithItem(HorizonsBlockRegistry.BAOBABE_PLANKS);
        blockWithItem(HorizonsBlockRegistry.FLUFFPULP_BLOCK);
        blockWithItem(HorizonsBlockRegistry.BAOBABE_LOG);
        blockWithItem(HorizonsBlockRegistry.BAOBABE_WOOD);
        blockWithItem(HorizonsBlockRegistry.STRIPPED_BAOBABE_LOG);
        blockWithItem(HorizonsBlockRegistry.STRIPPED_BAOBABE_WOOD);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_A);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_B);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_C);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_D);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_E);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_F);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_G);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_H);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_I);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_J);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_K);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_L);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_M);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_N);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_O);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_P);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_Q);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_R);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_S);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_T);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_U);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_V);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_W);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_X);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_Y);
        blockWithItem(HorizonsBlockRegistry.ALPHABET_BLOCK_Z);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_0);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_1);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_2);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_3);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_4);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_5);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_6);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_7);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_8);
        blockWithItem(HorizonsBlockRegistry.NUMBER_BLOCK_9);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
