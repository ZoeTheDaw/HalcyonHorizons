package com.internals.halcyonhorizons.server.level.biome;

import com.internals.halcyonhorizons.HalcyonHorizons;
import com.github.alexthe666.citadel.server.world.ExpandedBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class HorizonsBiomeRegistry {

    public static final DeferredRegister<Biome> DEF_REG = DeferredRegister.create(Registries.BIOME, HalcyonHorizons.MODID);
    public static final ResourceKey<Biome> AVIAN_INFIRMARY = ResourceKey.create(Registries.BIOME, new ResourceLocation(HalcyonHorizons.MODID, "avian_infirmary"));

    public static final List<ResourceKey<Biome>> HALCYON_HORIZONS_BIOMES = List.of(AVIAN_INFIRMARY);
    private static final Vec3 DEFAULT_LIGHT_COLOR = new Vec3(1, 1, 1);

    public static void init() {
        ExpandedBiomes.addExpandedBiome(AVIAN_INFIRMARY, LevelStem.OVERWORLD);
    }

    public static float getBiomeFogNearness(Holder<Biome> value) {
        if (value.is(AVIAN_INFIRMARY)) {
            return 0.5F;
        }
        return 1.0F;
    }

    public static float getBiomeSkyOverride(Holder<Biome> value) {
        if (value.is((ResourceLocation) HALCYON_HORIZONS_BIOMES)) {
            return 1.0F;
        }
        return 0.0F;
    }

    public static float calculateBiomeSkyOverride(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return HorizonsBiomeRegistry.getBiomeSkyOverride(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.level(), player.position(), HorizonsBiomeRegistry::getBiomeSkyOverride);
        }
    }

}