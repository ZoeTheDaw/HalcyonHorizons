package com.internals.halcyonhorizons.server.item;

import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HorizonsCreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HalcyonHorizons.MODID);

    public static final RegistryObject<CreativeModeTab> AVIAN_INFIRMARY = CREATIVE_MODE_TABS.register("avian_infirmary",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HorizonsBlockRegistry.BAOBABE_PLANKS.get()))
                    .title(Component.translatable("creativetab.avian_infirmary"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HorizonsBlockRegistry.BAOBABE_LOG.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_BAOBABE_LOG.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_BAOBABE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_PLANKS.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_PLANKS_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_PLANKS_SLAB.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_PLANKS_FENCE.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_FENCE_GATE.get());
                        output.accept(HorizonsItemRegistry.BAOBABE_DOOR.get());
                        output.accept(HorizonsItemRegistry.BAOBABE_TRAPDOOR.get());
                        output.accept(HorizonsItemRegistry.BAOBABE_PRESSURE_PLATE.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_BUTTON.get());
                        output.accept(HorizonsItemRegistry.BAOBABE_SIGN.get());
                        output.accept(HorizonsItemRegistry.BAOBABE_HANGING_SIGN.get());
                        output.accept(HorizonsBlockRegistry.BAOBABE_SAPLING.get());
                        output.accept(HorizonsBlockRegistry.FLUFFPULP_BLOCK.get());
                        output.accept(HorizonsBlockRegistry.FLUFFPULP_STRANDS.get());
                        output.accept(HorizonsBlockRegistry.FLUFFPULP_TUFT.get());
                        output.accept(HorizonsBlockRegistry.FLUFFPILLOW.get());
                        output.accept(HorizonsBlockRegistry.LAMPPAPER_BLOCK.get());
                        output.accept(HorizonsBlockRegistry.LAMPBOARD_BLOCK.get());
                        output.accept(HorizonsBlockRegistry.LAMPBOARD_BOX.get());
                        output.accept(HorizonsItemRegistry.LAMPBOARD_DOOR.get());
                        output.accept(HorizonsItemRegistry.LAMPBOARD_TRAPDOOR.get());
                        output.accept(HorizonsBlockRegistry.NIGHTLIGHT.get());
                        output.accept(HorizonsItemRegistry.NIGHT_TORCH.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_A.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_B.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_C.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_D.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_E.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_F.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_G.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_H.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_I.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_J.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_K.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_L.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_M.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_N.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_O.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_P.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_Q.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_R.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_S.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_T.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_U.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_V.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_W.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_X.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_Y.get());
                        output.accept(HorizonsBlockRegistry.ALPHABET_BLOCK_Z.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_0.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_1.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_2.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_3.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_4.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_5.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_6.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_7.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_8.get());
                        output.accept(HorizonsBlockRegistry.NUMBER_BLOCK_9.get());
                        output.accept(HorizonsItemRegistry.FLUFFPULP_STRAND.get());
                        output.accept(HorizonsItemRegistry.FLUFFPULP_CHUNK.get());
                        output.accept(HorizonsItemRegistry.FLUFFPULP_DIAPER.get());
                        output.accept(HorizonsItemRegistry.FLUFFPULP_FEATHER.get());
                        output.accept(HorizonsItemRegistry.LAMPPAPER_SHEET.get());
                        output.accept(HorizonsItemRegistry.NIGHTLIGHT_GEL.get());
                        output.accept(HorizonsItemRegistry.BABY_FORMULA.get());
                        output.accept(HorizonsItemRegistry.BEAD_BERRIES.get());


                    }).build());

    public static final RegistryObject<CreativeModeTab> ANTIQUE_ARCHIVES = CREATIVE_MODE_TABS.register("antique_archives",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HorizonsBlockRegistry.CROLIVE_PLANKS.get()))
                    .title(Component.translatable("creativetab.antique_archives"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HorizonsBlockRegistry.CROLIVE_LOG.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_CROLIVE_LOG.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_CROLIVE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_PLANKS.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_PLANKS_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_PLANKS_SLAB.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_PLANKS_FENCE.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_FENCE_GATE.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_WOOD_WALL.get());
                        output.accept(HorizonsItemRegistry.CROLIVE_DOOR.get());
                        output.accept(HorizonsItemRegistry.CROLIVE_TRAPDOOR.get());
                        output.accept(HorizonsItemRegistry.CROLIVE_PRESSURE_PLATE.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_BUTTON.get());
                        output.accept(HorizonsItemRegistry.CROLIVE_SIGN.get());
                        output.accept(HorizonsItemRegistry.CROLIVE_HANGING_SIGN.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_SAPLING.get());
                        output.accept(HorizonsBlockRegistry.CROLIVE_BRANCH.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_SLAB.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_WALL.get());
                        output.accept(HorizonsBlockRegistry.COBBLED_TRAVERTINE.get());
                        output.accept(HorizonsBlockRegistry.COBBLED_TRAVERTINE_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.COBBLED_TRAVERTINE_SLAB.get());
                        output.accept(HorizonsBlockRegistry.COBBLED_TRAVERTINE_WALL.get());
                        output.accept(HorizonsBlockRegistry.IVY_COBBLED_TRAVERTINE.get());
                        output.accept(HorizonsBlockRegistry.IVY_COBBLED_TRAVERTINE_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.IVY_COBBLED_TRAVERTINE_SLAB.get());
                        output.accept(HorizonsBlockRegistry.IVY_COBBLED_TRAVERTINE_WALL.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.CRACKED_TRAVERTINE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_BRICK_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_BRICK_SLAB.get());
                        output.accept(HorizonsBlockRegistry.TRAVERTINE_BRICK_WALL.get());
                        output.accept(HorizonsBlockRegistry.CHISELED_TRAVERTINE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.IVY_TRAVERTINE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.IVY_TRAVERTINE_BRICK_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.IVY_TRAVERTINE_BRICK_SLAB.get());
                        output.accept(HorizonsBlockRegistry.IVY_TRAVERTINE_BRICK_WALL.get());
                        output.accept(HorizonsBlockRegistry.MARBLE.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_SLAB.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_WALL.get());
                        output.accept(HorizonsBlockRegistry.SMOOTH_MARBLE.get());
                        output.accept(HorizonsBlockRegistry.SMOOTH_MARBLE_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.SMOOTH_MARBLE_SLAB.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.CRACKED_MARBLE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_BRICK_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_BRICK_SLAB.get());
                        output.accept(HorizonsBlockRegistry.MARBLE_BRICK_WALL.get());
                        output.accept(HorizonsBlockRegistry.CHISELED_MARBLE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.IVY_MARBLE_BRICKS.get());
                        output.accept(HorizonsBlockRegistry.IVY_MARBLE_BRICK_STAIRS.get());
                        output.accept(HorizonsBlockRegistry.IVY_MARBLE_BRICK_SLAB.get());
                        output.accept(HorizonsBlockRegistry.IVY_MARBLE_BRICK_WALL.get());
                        output.accept(HorizonsBlockRegistry.IVY.get());
                        output.accept(HorizonsBlockRegistry.GOLD_BARS.get());
                        output.accept(HorizonsBlockRegistry.GOLD_BAR_GATE.get());
                        output.accept(HorizonsBlockRegistry.GLOWPINE_LOG.get());
                        output.accept(HorizonsBlockRegistry.GLOWPINE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_GLOWPINE_LOG.get());
                        output.accept(HorizonsBlockRegistry.STRIPPED_GLOWPINE_WOOD.get());
                        output.accept(HorizonsBlockRegistry.GLOWPINE_LEAVES.get());
                        output.accept(HorizonsBlockRegistry.GLOWPINE_SAPLING.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
