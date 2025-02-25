package com.internals.halcyonhorizons.server.block.blockentity;

import com.google.common.collect.ImmutableSet;
import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.google.common.collect.ImmutableSet;
//import com.internals.halcyonhorizons.server.block.entity.ModHangingSignBlockEntity;
//import com.internals.halcyonhorizons.server.block.entity.ModSignBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HorizonsBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HalcyonHorizons.MODID);

//    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
//            DEF_REG.register("mod_sign", () ->
//                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
//                            HorizonsBlockRegistry.BAOBABE_SIGN.get(), HorizonsBlockRegistry.BAOBABE_WALL_SIGN.get()).build(null));
//
//    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
//            DEF_REG.register("mod_hanging_sign", () ->
//                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
//                            HorizonsBlockRegistry.BAOBABE_HANGING_SIGN.get(), HorizonsBlockRegistry.BAOBABE_WALL_HANGING_SIGN.get()).build(null));
//
//
//    public static void register(IEventBus eventBus) {
//        DEF_REG.register(eventBus);
//    }
//
//
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
