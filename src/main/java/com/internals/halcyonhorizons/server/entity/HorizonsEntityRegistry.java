//package com.internals.halcyonhorizons.server.entity;
//
//import com.internals.halcyonhorizons.HalcyonHorizons;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class HorizonsEntityRegistry {
//    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HalcyonHorizons.MODID);
//    public static final MobCategory SKY_CREATURE = MobCategory.create("sky_creature", "halcyonhorizons:sky_creature", 10, true, true, 128);
//    public static final RegistryObject<EntityType<RuffianEntity>> RUFFIAN = DEF_REG.register("ruffian", () -> (EntityType) EntityType.Builder.of(RuffianEntity::new, SKY_CREATURE).sized(2.3F, 2.5F).setTrackingRange(8).build("ruffian"));
//}
