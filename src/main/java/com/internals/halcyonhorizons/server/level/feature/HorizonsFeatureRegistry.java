package com.internals.halcyonhorizons.server.level.feature;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HorizonsFeatureRegistry {
    public static final DeferredRegister<Feature<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.FEATURES, HalcyonHorizons.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BAOBABE_TREE = DEF_REG.register("baobabe_tree", () -> new BaobabeTreeFeature(NoneFeatureConfiguration.CODEC));
}