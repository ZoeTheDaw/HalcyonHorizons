package com.internals.halcyonhorizons.datagen.loot;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(HorizonsBlockRegistry.BAOBABE_PLANKS.get());
        this.dropSelf(HorizonsBlockRegistry.BAOBABE_DOOR.get());
        this.dropSelf(HorizonsBlockRegistry.BAOBABE_PLANKS.get());
        this.dropSelf(HorizonsBlockRegistry.FLUFFPULP_BLOCK.get());
        this.dropSelf(HorizonsBlockRegistry.BAOBABE_LOG.get());
        this.dropSelf(HorizonsBlockRegistry.BAOBABE_WOOD.get());
        this.dropSelf(HorizonsBlockRegistry.STRIPPED_BAOBABE_LOG.get());
        this.dropSelf(HorizonsBlockRegistry.STRIPPED_BAOBABE_WOOD.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_A.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_B.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_C.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_D.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_E.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_F.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_G.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_H.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_I.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_J.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_K.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_L.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_M.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_N.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_O.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_P.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_Q.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_R.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_S.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_T.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_U.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_V.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_W.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_X.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_Y.get());
        this.dropSelf(HorizonsBlockRegistry.ALPHABET_BLOCK_Z.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_0.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_1.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_2.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_3.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_4.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_5.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_6.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_7.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_8.get());
        this.dropSelf(HorizonsBlockRegistry.NUMBER_BLOCK_9.get());
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return HorizonsBlockRegistry.DEF_REG.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
