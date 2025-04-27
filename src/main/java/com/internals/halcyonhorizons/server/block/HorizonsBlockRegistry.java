package com.internals.halcyonhorizons.server.block;

import com.github.alexthe666.citadel.item.BlockItemWithSupplier;
import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.grower.BaobabeGrower;
import com.internals.halcyonhorizons.server.block.grower.CroliveGrower;
import com.internals.halcyonhorizons.server.block.grower.GlowpineGrower;
import com.internals.halcyonhorizons.server.item.HorizonsItemRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class HorizonsBlockRegistry {

    public static final BlockBehaviour.Properties FLUFFPULP_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(0.5F, .6F).sound(SoundType.WOOL);
    public static final BlockBehaviour.Properties BAOBABE_PLANKS_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(1.5F, 2.0F).sound(SoundType.CHERRY_WOOD).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties BAOBABE_LOG_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(1.5F).sound(SoundType.CHERRY_WOOD).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties LAMPPAPER_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F, .6F).sound(HorizonsSoundTypes.LAMPPAPER);
    public static final BlockBehaviour.Properties NIGHTLIGHT_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().lightLevel(state -> 15).strength(2F, 11.0F).sound(SoundType.AMETHYST);

    public static final BlockBehaviour.Properties CROLIVE_PLANKS_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F, 2.0F).sound(SoundType.STONE).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties CROLIVE_LOG_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.STONE).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties TRAVERTINE_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(1.2F, 4.5F).sound(SoundType.DRIPSTONE_BLOCK);
    public static final BlockBehaviour.Properties MARBLE_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(1.2F, 4.5F).sound(SoundType.DEEPSLATE_BRICKS);

    public static final BlockBehaviour.Properties GLOWPINE_PLANKS_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F, 2.0F).sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties GLOWPINE_LOG_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.WOOD).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties STRATOSCHIST_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 4.5F).sound(SoundType.DEEPSLATE).instrument(NoteBlockInstrument.BASS);
    public static final BlockBehaviour.Properties PERMAFROST_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(2.0F, 4.5F).friction(0.98F).sound(SoundType.CORAL_BLOCK).instrument(NoteBlockInstrument.BASS);


    public static final WoodType BAOBABE_WOOD_TYPE = WoodType.register(new WoodType("halcyonhorizons:baobabe", BlockSetType.CHERRY));
    public static final WoodType CROLIVE_WOOD_TYPE = WoodType.register(new WoodType("halcyonhorizons:crolive", BlockSetType.DARK_OAK));
    public static final WoodType GLOWPINE_WOOD_TYPE = WoodType.register(new WoodType("halcyonhorizons:glowpine", BlockSetType.SPRUCE));


    public static final DeferredRegister<Block> DEF_REG = DeferredRegister.create(ForgeRegistries.BLOCKS, HalcyonHorizons.MODID);

    public static final RegistryObject<Block> FLUFFPULP_BLOCK = registerBlockAndItem("fluffpulp_block", () -> new FluffpulpBlock());
    public static final RegistryObject<Block> WET_FLUFFPULP_BLOCK = registerBlockAndItem("wet_fluffpulp_block", () -> new WetFluffpulpBlock());
    public static final RegistryObject<Block> FLUFFPULP_STRANDS = registerBlockAndItem("fluffpulp_strands", () -> new FluffpulpPlantBlock());
    public static final RegistryObject<Block> FLUFFPULP_TUFT = registerBlockAndItem("fluffpulp_tuft", () -> new FluffpulpPlantBlock());
    public static final RegistryObject<Block> FLUFFPILLOW = registerBlockAndItem("fluffpillow", () -> new FluffPillowBlock(FLUFFPULP_PROPERTIES));

    public static final RegistryObject<Block> LAMPPAPER_BLOCK = registerBlockAndItem("lamppaper_block", () -> new Block(LAMPPAPER_PROPERTIES));
    public static final RegistryObject<Block> LAMPBOARD_BLOCK = registerBlockAndItem("lampboard_block", () -> new HorizontalRotationBlock(LAMPPAPER_PROPERTIES));
    public static final RegistryObject<Block> LAMPBOARD_BOX = registerBlockAndItem("lampboard_box", () -> new LampboardBoxBlock());
    public static final RegistryObject<Block> LAMPBOARD_DOOR = DEF_REG.register("lampboard_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(LAMPBOARD_BLOCK.get()).strength(1.5F).sound(SoundType.GRASS).noOcclusion(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> LAMPBOARD_TRAPDOOR = DEF_REG.register("lampboard_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.GRASS).noOcclusion(), BlockSetType.CHERRY));

    public static final RegistryObject<Block> NIGHTLIGHT = registerBlockAndItem("nightlight", () -> new Block(NIGHTLIGHT_PROPERTIES));
    public static final RegistryObject<Block> NIGHT_TORCH = DEF_REG.register("night_torch", () -> new TorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).noCollission().lightLevel(state -> 15).instabreak().noOcclusion(), ParticleTypes.SMOKE));
    public static final RegistryObject<Block> NIGHT_WALL_TORCH = DEF_REG.register("night_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).noCollission().lightLevel(state -> 15).instabreak().noOcclusion(), ParticleTypes.SMOKE));

    public static final RegistryObject<Block> BEAD_BERRY_BUSH = DEF_REG.register("bead_berry_bush", () -> new BeadBerryBushBlock());

    public static final RegistryObject<Block> BAOBABE_PLANKS = registerBlockAndItem("baobabe_planks", () -> new Block(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_PLANKS_STAIRS = registerBlockAndItem("baobabe_stairs", () -> new StairBlock(BAOBABE_PLANKS.get().defaultBlockState(), BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_PLANKS_SLAB = registerBlockAndItem("baobabe_slab", () -> new SlabBlock(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_PLANKS_FENCE = registerBlockAndItem("baobabe_fence", () -> new FenceBlock(BAOBABE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_FENCE_GATE = registerBlockAndItem("baobabe_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(BAOBABE_PLANKS.get()).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD).forceSolidOn(), SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> BAOBABE_SIGN = DEF_REG.register("baobabe_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.CHERRY_WOOD), BAOBABE_WOOD_TYPE));
    public static final RegistryObject<Block> BAOBABE_WALL_SIGN = DEF_REG.register("baobabe_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.CHERRY_WOOD), BAOBABE_WOOD_TYPE));
    public static final RegistryObject<Block> BAOBABE_HANGING_SIGN = DEF_REG.register("baobabe_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F), BAOBABE_WOOD_TYPE));
    public static final RegistryObject<Block> BAOBABE_WALL_HANGING_SIGN = DEF_REG.register("baobabe_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(BAOBABE_HANGING_SIGN.get()), BAOBABE_WOOD_TYPE));
    public static final RegistryObject<Block> BAOBABE_LOG = registerBlockAndItem("baobabe_log", () -> new StrippableLogBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_WOOD = registerBlockAndItem("baobabe_wood", () -> new StrippableLogBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_BAOBABE_LOG = registerBlockAndItem("stripped_baobabe_log", () -> new RotatedPillarBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_BAOBABE_WOOD = registerBlockAndItem("stripped_baobabe_wood", () -> new RotatedPillarBlock(BAOBABE_LOG_PROPERTIES));
    public static final RegistryObject<Block> BAOBABE_SAPLING = registerBlockAndItem("baobabe_sapling", () -> new BaobabeSaplingBlock(new BaobabeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).lightLevel(state -> 15).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.CHERRY_WOOD), true));
    public static final RegistryObject<Block> BAOBABE_PRESSURE_PLATE = DEF_REG.register("baobabe_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(BAOBABE_PLANKS.get()).noCollission().strength(0.5F).sound(SoundType.CHERRY_WOOD), BlockSetType.CHERRY));
    public static final RegistryObject<Block> BAOBABE_DOOR = DEF_REG.register("baobabe_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(BAOBABE_PLANKS.get()).strength(1.5F).sound(SoundType.CHERRY_WOOD).noOcclusion(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> BAOBABE_TRAPDOOR = DEF_REG.register("baobabe_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.CHERRY_WOOD).noOcclusion(), BlockSetType.CHERRY));
    public static final RegistryObject<Block> BAOBABE_BUTTON = registerBlockAndItem("baobabe_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(0.5F).sound(SoundType.CHERRY_WOOD), BlockSetType.CHERRY, 30, true));

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

    public static final RegistryObject<Block> CROLIVE_PLANKS = registerBlockAndItem("crolive_planks", () -> new Block(CROLIVE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_PLANKS_STAIRS = registerBlockAndItem("crolive_stairs", () -> new StairBlock(CROLIVE_PLANKS.get().defaultBlockState(), CROLIVE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_PLANKS_SLAB = registerBlockAndItem("crolive_slab", () -> new SlabBlock(CROLIVE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_PLANKS_FENCE = registerBlockAndItem("crolive_fence", () -> new FenceBlock(CROLIVE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_FENCE_GATE = registerBlockAndItem("crolive_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(CROLIVE_PLANKS.get()).strength(2.0F, 3.0F).sound(SoundType.STONE).forceSolidOn(), SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> CROLIVE_WOOD_WALL = registerBlockAndItem("crolive_wall", () -> new WallBlock(CROLIVE_PLANKS_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_SIGN = DEF_REG.register("crolive_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.STONE), CROLIVE_WOOD_TYPE));
    public static final RegistryObject<Block> CROLIVE_WALL_SIGN = DEF_REG.register("crolive_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.STONE), CROLIVE_WOOD_TYPE));
    public static final RegistryObject<Block> CROLIVE_HANGING_SIGN = DEF_REG.register("crolive_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F), CROLIVE_WOOD_TYPE));
    public static final RegistryObject<Block> CROLIVE_WALL_HANGING_SIGN = DEF_REG.register("crolive_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(CROLIVE_HANGING_SIGN.get()), CROLIVE_WOOD_TYPE));
    public static final RegistryObject<Block> CROLIVE_LOG = registerBlockAndItem("crolive_log", () -> new StrippableLogBlock(CROLIVE_LOG_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_WOOD = registerBlockAndItem("crolive_wood", () -> new StrippableLogBlock(CROLIVE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_CROLIVE_LOG = registerBlockAndItem("stripped_crolive_log", () -> new RotatedPillarBlock(CROLIVE_LOG_PROPERTIES));
    public static final RegistryObject<Block> STRIPPED_CROLIVE_WOOD = registerBlockAndItem("stripped_crolive_wood", () -> new RotatedPillarBlock(CROLIVE_LOG_PROPERTIES));
    public static final RegistryObject<Block> CROLIVE_SAPLING = registerBlockAndItem("crolive_sapling", () -> new CroliveSaplingBlock(new CroliveGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.WOOD), true));
    public static final RegistryObject<Block> POTTED_CROLIVE_SAPLING = DEF_REG.register("potted_crolive_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CROLIVE_SAPLING, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CROLIVE_BRANCH = registerBlockAndItem("crolive_branch", () -> new CroliveBranchBlock());
    public static final RegistryObject<Block> POTTED_CROLIVE_BRANCH = DEF_REG.register("potted_crolive_branch", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CROLIVE_BRANCH, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CROLIVE_PRESSURE_PLATE = DEF_REG.register("crolive_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(CROLIVE_PLANKS.get()).noCollission().strength(0.5F).sound(SoundType.STONE), BlockSetType.DARK_OAK));
    public static final RegistryObject<Block> CROLIVE_DOOR = DEF_REG.register("crolive_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(CROLIVE_PLANKS.get()).strength(1.5F).sound(SoundType.STONE).noOcclusion(), BlockSetType.DARK_OAK));
    public static final RegistryObject<Block> CROLIVE_TRAPDOOR = DEF_REG.register("crolive_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.STONE).noOcclusion(), BlockSetType.DARK_OAK));
    public static final RegistryObject<Block> CROLIVE_BUTTON = registerBlockAndItem("crolive_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(0.5F).sound(SoundType.STONE), BlockSetType.DARK_OAK, 30, true));

    public static final RegistryObject<Block> TRAVERTINE = registerBlockAndItem("travertine", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_STAIRS = registerBlockAndItem("travertine_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_SLAB = registerBlockAndItem("travertine_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_WALL = registerBlockAndItem("travertine_wall", () -> new WallBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_TRAVERTINE = registerBlockAndItem("cobbled_travertine", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_TRAVERTINE_STAIRS = registerBlockAndItem("cobbled_travertine_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_TRAVERTINE_SLAB = registerBlockAndItem("cobbled_travertine_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_TRAVERTINE_WALL = registerBlockAndItem("cobbled_travertine_wall", () -> new WallBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_COBBLED_TRAVERTINE = registerBlockAndItem("ivy_cobbled_travertine", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_COBBLED_TRAVERTINE_STAIRS = registerBlockAndItem("ivy_cobbled_travertine_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_COBBLED_TRAVERTINE_SLAB = registerBlockAndItem("ivy_cobbled_travertine_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_COBBLED_TRAVERTINE_WALL = registerBlockAndItem("ivy_cobbled_travertine_wall", () -> new WallBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_TRAVERTINE = registerBlockAndItem("polished_travertine", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_TRAVERTINE_STAIRS = registerBlockAndItem("polished_travertine_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_TRAVERTINE_SLAB = registerBlockAndItem("polished_travertine_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_BRICKS = registerBlockAndItem("travertine_bricks", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_BRICK_STAIRS = registerBlockAndItem("travertine_brick_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_BRICK_SLAB = registerBlockAndItem("travertine_brick_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_BRICK_WALL = registerBlockAndItem("travertine_brick_wall", () -> new WallBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> CRACKED_TRAVERTINE_BRICKS = registerBlockAndItem("cracked_travertine_bricks", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> CHISELED_TRAVERTINE_BRICKS = registerBlockAndItem("chiseled_travertine_bricks", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_TRAVERTINE_BRICKS = registerBlockAndItem("ivy_travertine_bricks", () -> new Block(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_TRAVERTINE_BRICK_STAIRS = registerBlockAndItem("ivy_travertine_brick_stairs", () -> new StairBlock(TRAVERTINE.get().defaultBlockState(), TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_TRAVERTINE_BRICK_SLAB = registerBlockAndItem("ivy_travertine_brick_slab", () -> new SlabBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> IVY_TRAVERTINE_BRICK_WALL = registerBlockAndItem("ivy_travertine_brick_wall", () -> new WallBlock(TRAVERTINE_PROPERTIES));
    public static final RegistryObject<Block> TRAVERTINE_PILLAR = registerBlockAndItem("travertine_pillar", () -> new RotatedPillarBlock(TRAVERTINE_PROPERTIES));

    public static final RegistryObject<Block> MARBLE = registerBlockAndItem("marble", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_STAIRS = registerBlockAndItem("marble_stairs", () -> new StairBlock(MARBLE.get().defaultBlockState(), MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_SLAB = registerBlockAndItem("marble_slab", () -> new SlabBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_WALL = registerBlockAndItem("marble_wall", () -> new WallBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_BRICKS = registerBlockAndItem("marble_bricks", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_BRICK_STAIRS = registerBlockAndItem("marble_brick_stairs", () -> new StairBlock(MARBLE.get().defaultBlockState(), MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_BRICK_SLAB = registerBlockAndItem("marble_brick_slab", () -> new SlabBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_BRICK_WALL = registerBlockAndItem("marble_brick_wall", () -> new WallBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> CRACKED_MARBLE_BRICKS = registerBlockAndItem("cracked_marble_bricks", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> CHISELED_MARBLE_BRICKS = registerBlockAndItem("chiseled_marble_bricks", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> IVY_MARBLE_BRICKS = registerBlockAndItem("ivy_marble_bricks", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> IVY_MARBLE_BRICK_STAIRS = registerBlockAndItem("ivy_marble_brick_stairs", () -> new StairBlock(MARBLE.get().defaultBlockState(), MARBLE_PROPERTIES));
    public static final RegistryObject<Block> IVY_MARBLE_BRICK_SLAB = registerBlockAndItem("ivy_marble_brick_slab", () -> new SlabBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> IVY_MARBLE_BRICK_WALL = registerBlockAndItem("ivy_marble_brick_wall", () -> new WallBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_MARBLE = registerBlockAndItem("polished_marble", () -> new Block(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_MARBLE_STAIRS = registerBlockAndItem("polished_marble_stairs", () -> new StairBlock(MARBLE.get().defaultBlockState(), MARBLE_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_MARBLE_SLAB = registerBlockAndItem("polished_marble_slab", () -> new SlabBlock(MARBLE_PROPERTIES));
    public static final RegistryObject<Block> MARBLE_PILLAR = registerBlockAndItem("marble_pillar", () -> new RotatedPillarBlock(MARBLE_PROPERTIES));

    public static final RegistryObject<Block> GOLD_BARS = registerBlockAndItem("gold_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(0.3F, 0.6F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> GOLD_BAR_GATE = registerBlockAndItem("gold_bar_gate", () -> new BarGateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).noOcclusion().strength(0.3F, 0.6F).sound(SoundType.METAL).noOcclusion().forceSolidOn(), SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE));

    public static final RegistryObject<Block> IVY = registerBlockAndItem("ivy", () -> new IvyBlock());

    public static final RegistryObject<Block> GLOWPINE_LOG = registerBlockAndItem("glowpine_log", () -> new StrippableLogBlock(GLOWPINE_LOG_PROPERTIES.lightLevel(state -> 5)));
    public static final RegistryObject<Block> GLOWPINE_WOOD = registerBlockAndItem("glowpine_wood", () -> new StrippableLogBlock(GLOWPINE_LOG_PROPERTIES.lightLevel(state -> 5)));
    public static final RegistryObject<Block> STRIPPED_GLOWPINE_LOG = registerBlockAndItem("stripped_glowpine_log", () -> new RotatedPillarBlock(GLOWPINE_LOG_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> STRIPPED_GLOWPINE_WOOD = registerBlockAndItem("stripped_glowpine_wood", () -> new RotatedPillarBlock(GLOWPINE_LOG_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> GLOWPINE_PLANKS = registerBlockAndItem("glowpine_planks", () -> new Block(GLOWPINE_PLANKS_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> GLOWPINE_PLANKS_STAIRS = registerBlockAndItem("glowpine_stairs", () -> new StairBlock(GLOWPINE_PLANKS.get().defaultBlockState(), GLOWPINE_PLANKS_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> GLOWPINE_PLANKS_SLAB = registerBlockAndItem("glowpine_slab", () -> new SlabBlock(GLOWPINE_PLANKS_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> GLOWPINE_PLANKS_FENCE = registerBlockAndItem("glowpine_fence", () -> new FenceBlock(GLOWPINE_PLANKS_PROPERTIES.lightLevel(state -> 10)));
    public static final RegistryObject<Block> GLOWPINE_FENCE_GATE = registerBlockAndItem("glowpine_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(GLOWPINE_PLANKS.get()).lightLevel(state -> 10).strength(2.0F, 3.0F).sound(SoundType.WOOD).forceSolidOn(), SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> GLOWPINE_SIGN = DEF_REG.register("glowpine_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of().lightLevel(state -> 10).mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), GLOWPINE_WOOD_TYPE));
    public static final RegistryObject<Block> GLOWPINE_WALL_SIGN = DEF_REG.register("glowpine_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of().lightLevel(state -> 10).mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), GLOWPINE_WOOD_TYPE));
    public static final RegistryObject<Block> GLOWPINE_HANGING_SIGN = DEF_REG.register("glowpine_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().lightLevel(state -> 10).mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F), GLOWPINE_WOOD_TYPE));
    public static final RegistryObject<Block> GLOWPINE_WALL_HANGING_SIGN = DEF_REG.register("glowpine_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().lightLevel(state -> 10).mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(GLOWPINE_HANGING_SIGN.get()), GLOWPINE_WOOD_TYPE));
    public static final RegistryObject<Block> GLOWPINE_LEAVES = registerBlockAndItem("glowpine_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isSuffocating((blockState, getter, pos) -> false)));
    public static final RegistryObject<Block> GLOWPINE_PINE_LEAVES = registerBlockAndItem("glowpine_pine_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.2F).randomTicks().lightLevel(state -> 10).sound(SoundType.CHERRY_LEAVES).noOcclusion().isSuffocating((blockState, getter, pos) -> false)));
    public static final RegistryObject<Block> GLOWPINE_SAPLING = registerBlockAndItem("glowpine_sapling", () -> new GlowpineSaplingBlock(new GlowpineGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).lightLevel(state -> 5).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.GRASS), true));
    public static final RegistryObject<Block> POTTED_GLOWPINE_SAPLING = DEF_REG.register("potted_glowpine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GLOWPINE_SAPLING, BlockBehaviour.Properties.of().instabreak().lightLevel(state -> 5).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> GLOWPINE_BUTTON = registerBlockAndItem("glowpine_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noCollission().lightLevel(state -> 5).strength(0.5F).sound(SoundType.WOOD), BlockSetType.DARK_OAK, 30, true));
    public static final RegistryObject<Block> GLOWPINE_PRESSURE_PLATE = DEF_REG.register("glowpine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(GLOWPINE_PLANKS.get()).lightLevel(state -> 5).noCollission().strength(0.5F).sound(SoundType.CHERRY_WOOD), BlockSetType.CHERRY));
    public static final RegistryObject<Block> GLOWPINE_DOOR = DEF_REG.register("glowpine_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(GLOWPINE_PLANKS.get()).lightLevel(state -> 10).strength(1.5F).sound(SoundType.WOOD).noOcclusion(), BlockSetType.DARK_OAK));
    public static final RegistryObject<Block> GLOWPINE_TRAPDOOR = DEF_REG.register("glowpine_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().lightLevel(state -> 10).mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), BlockSetType.DARK_OAK));

    public static final RegistryObject<Block> STRATOSCHIST = registerBlockAndItem("stratoschist", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_STAIRS = registerBlockAndItem("stratoschist_stairs", () -> new StairBlock(STRATOSCHIST.get().defaultBlockState(), STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_SLAB = registerBlockAndItem("stratoschist_slab", () -> new SlabBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_WALL = registerBlockAndItem("stratoschist_wall", () -> new WallBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_STRATOSCHIST = registerBlockAndItem("cobbled_stratoschist", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_STRATOSCHIST_STAIRS = registerBlockAndItem("cobbled_stratoschist_stairs", () -> new StairBlock(STRATOSCHIST.get().defaultBlockState(), STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_STRATOSCHIST_SLAB = registerBlockAndItem("cobbled_stratoschist_slab", () -> new SlabBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_STRATOSCHIST_WALL = registerBlockAndItem("cobbled_stratoschist_wall", () -> new WallBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_BRICKS = registerBlockAndItem("stratoschist_bricks", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_BRICK_STAIRS = registerBlockAndItem("stratoschist_brick_stairs", () -> new StairBlock(STRATOSCHIST.get().defaultBlockState(), STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_BRICK_SLAB = registerBlockAndItem("stratoschist_brick_slab", () -> new SlabBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_BRICK_WALL = registerBlockAndItem("stratoschist_brick_wall", () -> new WallBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> CRACKED_STRATOSCHIST_BRICKS = registerBlockAndItem("cracked_stratoschist_bricks", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> CHISELED_STRATOSCHIST_BRICKS = registerBlockAndItem("chiseled_stratoschist_bricks", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_STRATOSCHIST = registerBlockAndItem("polished_stratoschist", () -> new Block(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_STRATOSCHIST_STAIRS = registerBlockAndItem("polished_stratoschist_stairs", () -> new StairBlock(STRATOSCHIST.get().defaultBlockState(), STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_STRATOSCHIST_SLAB = registerBlockAndItem("polished_stratoschist_slab", () -> new SlabBlock(STRATOSCHIST_PROPERTIES));
    public static final RegistryObject<Block> STRATOSCHIST_PILLAR = registerBlockAndItem("stratoschist_pillar", () -> new RotatedPillarBlock(STRATOSCHIST_PROPERTIES));

    public static final RegistryObject<Block> PERMAFROST = registerBlockAndItem("permafrost", () -> new PermafrostBlock());
    public static final RegistryObject<Block> PERMAFROST_STAIRS = registerBlockAndItem("permafrost_stairs", () -> new StairBlock(STRATOSCHIST.get().defaultBlockState(), PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_SLAB = registerBlockAndItem("permafrost_slab", () -> new SlabBlock(PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_WALL = registerBlockAndItem("permafrost_wall", () -> new WallBlock(PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_BRICKS = registerBlockAndItem("permafrost_bricks", () -> new Block(PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_BRICK_STAIRS = registerBlockAndItem("permafrost_brick_stairs", () -> new StairBlock(PERMAFROST_BRICKS.get().defaultBlockState(), PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_BRICK_SLAB = registerBlockAndItem("permafrost_brick_slab", () -> new SlabBlock(PERMAFROST_PROPERTIES));
    public static final RegistryObject<Block> PERMAFROST_BRICK_WALL = registerBlockAndItem("permafrost_brick_wall", () -> new WallBlock(PERMAFROST_PROPERTIES));


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

    public static void setup() {
        FlowerPotBlock flowerPotBlock = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPotBlock.addPlant(CROLIVE_SAPLING.getId(), POTTED_CROLIVE_SAPLING);
        flowerPotBlock.addPlant(CROLIVE_BRANCH.getId(), POTTED_CROLIVE_BRANCH);
        flowerPotBlock.addPlant(GLOWPINE_SAPLING.getId(), POTTED_GLOWPINE_SAPLING);

    }


}
