package com.internals.halcyonhorizons.server.config;


import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRarity;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRegistry;
import com.internals.halcyonhorizons.server.misc.VoronoiGenerator;
import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BiomeGenerationConfig {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    private static final String OVERWORLD = "minecraft:overworld";

    private static final BiomeGenerationNoiseCondition AVIAN_INFIRMARY_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(OVERWORLD).distanceFromSpawn(400).horizonsRarityOffset(0).continentalness(0.6F, 1F).depth(1.2F, 1F).build();
    public static final LinkedHashMap<ResourceKey<Biome>, BiomeGenerationNoiseCondition> BIOMES = new LinkedHashMap<>();

    public static void reloadConfig() {
        BIOMES.put(HorizonsBiomeRegistry.AVIAN_INFIRMARY, getConfigData("avian_infirmary", AVIAN_INFIRMARY_CONDITION));
    }

    @Nullable
    @Deprecated(forRemoval = true, since="1.21")
    public static ResourceKey<Biome> getBiomeForEvent(EventReplaceBiome event) {
        VoronoiGenerator.VoronoiInfo voronoiInfo = HorizonsBiomeRarity.getRareBiomeInfoForQuad(event.getWorldSeed(), event.getX(), event.getZ());
        if(voronoiInfo != null){
            int foundRarityOffset = HorizonsBiomeRarity.getRareBiomeOffsetId(voronoiInfo);
            for (Map.Entry<ResourceKey<Biome>, BiomeGenerationNoiseCondition> condition : BIOMES.entrySet()) {
                if (foundRarityOffset == condition.getValue().getRarityOffset() && condition.getValue().test(event, voronoiInfo)) {
                    return condition.getKey();
                }
            }
        }
        return null;
    }

    public static int getBiomeCount() {
        return BIOMES.size();
    }

    public static boolean isBiomeDisabledCompletely(ResourceKey<Biome> biome){
        BiomeGenerationNoiseCondition noiseCondition = BIOMES.get(biome);
        return noiseCondition != null && noiseCondition.isDisabledCompletely();
    }

    private static <T> T getOrCreateConfigFile(File configDir, String configName, T defaults, Type type, Predicate<T> isInvalid) {
        File configFile = new File(configDir, configName + ".json");
        if (!configFile.exists()) {
            try {
                FileUtils.write(configFile, GSON.toJson(defaults));
            } catch (IOException e) {
                Citadel.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
            }
        }
        try {
            T found = GSON.fromJson(FileUtils.readFileToString(configFile), type);
            if (isInvalid.test(found)) {
                Citadel.LOGGER.warn("Old Biome Generation Config format found for " + configName + ", replacing with new one.");
                try {
                    FileUtils.write(configFile, GSON.toJson(defaults));
                } catch (IOException e) {
                    Citadel.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
                }
            } else {
                return found;
            }
        } catch (Exception e) {
            Citadel.LOGGER.error("Biome Generation Config: Could not load " + configFile, e);
        }

        return defaults;
    }

    private static File getConfigDirectory() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(), "alexscaves_biome_generation");
        return jsonPath.toFile();
    }

    private static BiomeGenerationNoiseCondition getConfigData(String fileName, BiomeGenerationNoiseCondition defaultConfigData) {
        BiomeGenerationNoiseCondition configData = getOrCreateConfigFile(getConfigDirectory(), fileName, defaultConfigData, new TypeToken<BiomeGenerationNoiseCondition>() {
        }.getType(), BiomeGenerationNoiseCondition::isInvalid);
        return configData;
    }
}
