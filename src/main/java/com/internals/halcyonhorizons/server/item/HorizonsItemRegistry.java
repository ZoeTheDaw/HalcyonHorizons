package com.internals.halcyonhorizons.server.item;

import com.internals.halcyonhorizons.HalcyonHorizons;
import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRegistry;
import com.github.alexthe666.citadel.server.block.LecternBooks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HorizonsItemRegistry{
    public static final DeferredRegister<Item> DEF_REG = DeferredRegister.create(ForgeRegistries.ITEMS, HalcyonHorizons.MODID);
    public static final Rarity RARITY_DEMONIC = Rarity.create("halcyonhorizons:youthful",style -> style.withColor(16751342));
    public static final RegistryObject<Item> BAOBABE_DOOR = DEF_REG.register("baobabe_door", () -> new DoubleHighBlockItem(HorizonsBlockRegistry.BAOBABE_DOOR.get(), (new Item.Properties())));
    public static final RegistryObject<Item> BAOBABE_TRAPDOOR = DEF_REG.register("baobabe_trapdoor", () -> new BlockItem(HorizonsBlockRegistry.BAOBABE_TRAPDOOR.get(), (new Item.Properties())));
    public static final RegistryObject<Item> BAOBABE_PRESSURE_PLATE = DEF_REG.register("baobabe_pressure_plate", () -> new BlockItem(HorizonsBlockRegistry.BAOBABE_PRESSURE_PLATE.get(), (new Item.Properties())));
    public static final RegistryObject<Item> BAOBABE_PLANKS_FENCE = DEF_REG.register("baobabe_fence", () -> new BlockItem(HorizonsBlockRegistry.BAOBABE_PLANKS_FENCE.get(), (new Item.Properties())));
//    public static final RegistryObject<Item> BAOBABE_SIGN = DEF_REG.register("baobabe_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), HorizonsBlockRegistry.BAOBABE_SIGN.get(), HorizonsBlockRegistry.BAOBABE_WALL_SIGN.get()));
//    public static final RegistryObject<Item> BAOBABE_HANGING_SIGN = DEF_REG.register("baobabe_hanging_sign", () -> new HangingSignItem(HorizonsBlockRegistry.BAOBABE_HANGING_SIGN.get(), HorizonsBlockRegistry.BAOBABE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
}