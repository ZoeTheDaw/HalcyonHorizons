package com.internals.halcyonhorizons.server.block;

import com.github.alexthe666.citadel.item.BlockItemWithSupplier;
import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.item.HorizonsItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class HorizonsBlockRegistry {

    public static final BlockBehaviour.Properties FLUFFPULP_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.5F, .6F).sound(SoundType.WOOL);
    public static final BlockBehaviour.Properties BAOBABE_PLANKS_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F, 2.0F).sound(SoundType.CHERRY_WOOD).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties BAOBABE_LOG_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.CHERRY_WOOD).instrument(NoteBlockInstrument.BASS);

    public static final DeferredRegister<Block> DEF_REG = DeferredRegister.create(ForgeRegistries.BLOCKS, HalcyonHorizons.MODID);
    public static final RegistryObject<Block> FLUFFPULP = registerBlockAndItem("fluffpulp", () -> new Block(FLUFFPULP_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_PLANKS = registerBlockAndItem("baobabe_planks", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_LOG = registerBlockAndItem("baobabe_log", () -> new StrippableLogBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_WOOD = registerBlockAndItem("baobabe_wood", () -> new StrippableLogBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_BAOBABE_LOG = registerBlockAndItem("stripped_baobabe_log", () -> new RotatedPillarBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_BAOBABE_WOOD = registerBlockAndItem("stripped_baobabe_wood", () -> new RotatedPillarBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_DOOR = DEF_REG.register("baobabe_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(BAOBABE_PLANKS.get()).strength(1.5F).sound(SoundType.CHERRY_WOOD).noOcclusion(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> ALPHABET_BLOCK_A = registerBlockAndItem("alphabet_block_a", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_B = registerBlockAndItem("alphabet_block_b", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_C = registerBlockAndItem("alphabet_block_c", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_D = registerBlockAndItem("alphabet_block_d", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_E = registerBlockAndItem("alphabet_block_e", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_F = registerBlockAndItem("alphabet_block_f", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_G = registerBlockAndItem("alphabet_block_g", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_H = registerBlockAndItem("alphabet_block_h", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_I = registerBlockAndItem("alphabet_block_i", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_J = registerBlockAndItem("alphabet_block_j", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_K = registerBlockAndItem("alphabet_block_k", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_L = registerBlockAndItem("alphabet_block_l", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_M = registerBlockAndItem("alphabet_block_m", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_N = registerBlockAndItem("alphabet_block_n", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_O = registerBlockAndItem("alphabet_block_o", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_P = registerBlockAndItem("alphabet_block_p", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_Q = registerBlockAndItem("alphabet_block_q", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_R = registerBlockAndItem("alphabet_block_r", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_S = registerBlockAndItem("alphabet_block_s", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_T = registerBlockAndItem("alphabet_block_t", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_U = registerBlockAndItem("alphabet_block_u", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_V = registerBlockAndItem("alphabet_block_v", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_W = registerBlockAndItem("alphabet_block_w", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_X = registerBlockAndItem("alphabet_block_x", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_Y = registerBlockAndItem("alphabet_block_y", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> ALPHABET_BLOCK_Z = registerBlockAndItem("alphabet_block_z", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_0 = registerBlockAndItem("number_block_0", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_1 = registerBlockAndItem("number_block_1", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_2 = registerBlockAndItem("number_block_2", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_3 = registerBlockAndItem("number_block_3", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_4 = registerBlockAndItem("number_block_4", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_5 = registerBlockAndItem("number_block_5", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_6 = registerBlockAndItem("number_block_6", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_7 = registerBlockAndItem("number_block_7", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_8 = registerBlockAndItem("number_block_8", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> NUMBER_BLOCK_9 = registerBlockAndItem("number_block_9", () -> new Block(BAOBABE_PLANKS_PROPERTIES));


    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, int itemType) {
        RegistryObject<Block> blockObj = DEF_REG.register(name, block);
        HorizonsItemRegistry.DEF_REG.register(name, getBlockSupplier(itemType, blockObj));
        return blockObj;
    }

    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block) {
        return registerBlockAndItem(name, block, 0);
    }

    private static Supplier<? extends BlockItemWithSupplier> getBlockSupplier(int itemType, RegistryObject<Block> blockObj) {
        switch (itemType) {
            default:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties());

        }
    }


}
