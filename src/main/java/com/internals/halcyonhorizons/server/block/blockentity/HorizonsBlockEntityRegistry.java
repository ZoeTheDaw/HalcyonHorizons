package com.internals.halcyonhorizons.server.block.blockentity;

import com.google.common.collect.ImmutableSet;
import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HorizonsBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HalcyonHorizons.MODID);

    public static final RegistryObject<BlockEntityType<LampboardBoxBlockEntity>> LAMPBOARD_BOX = DEF_REG.register("lampboard_box", () -> BlockEntityType.Builder.of(LampboardBoxBlockEntity::new, HorizonsBlockRegistry.LAMPBOARD_BOX.get()).build(null));

    public static void expandVanillaDefinitions() {
        ImmutableSet.Builder<Block> validSignBlocks = new ImmutableSet.Builder<>();
        validSignBlocks.addAll(BlockEntityType.SIGN.validBlocks);
        validSignBlocks.add(HorizonsBlockRegistry.BAOBABE_SIGN.get());
        validSignBlocks.add(HorizonsBlockRegistry.BAOBABE_WALL_SIGN.get());
        BlockEntityType.SIGN.validBlocks = validSignBlocks.build();
        ImmutableSet.Builder<Block> validHangingSignBlocks = new ImmutableSet.Builder<>();
        validHangingSignBlocks.addAll(BlockEntityType.HANGING_SIGN.validBlocks);
        validHangingSignBlocks.add(HorizonsBlockRegistry.BAOBABE_HANGING_SIGN.get());
        validHangingSignBlocks.add(HorizonsBlockRegistry.BAOBABE_WALL_HANGING_SIGN.get());
        BlockEntityType.HANGING_SIGN.validBlocks = validHangingSignBlocks.build();
    }
}
