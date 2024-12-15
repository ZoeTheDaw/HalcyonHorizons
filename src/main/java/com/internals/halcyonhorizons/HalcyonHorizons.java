package com.internals.halcyonhorizons;

import com.internals.halcyonhorizons.server.block.HorizonsBlockRegistry;
import com.internals.halcyonhorizons.server.config.BiomeGenerationConfig;
import com.internals.halcyonhorizons.server.config.HorizonsServerConfig;
import com.internals.halcyonhorizons.server.event.CommonEvents;
import com.internals.halcyonhorizons.server.item.HorizonsItemRegistry;
import com.internals.halcyonhorizons.server.level.biome.HorizonsBiomeRegistry;
import com.internals.halcyonhorizons.server.level.structure.HorizonsStructureRegistry;
import com.internals.halcyonhorizons.server.level.structure.piece.HorizonsStructurePieceRegistry;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.ForgeChunkManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod(HalcyonHorizons.MODID)
public class HalcyonHorizons {
    public static final String MODID = "halcyonhorizons";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation PACKET_NETWORK_NAME = new ResourceLocation("halcyonhorizons:main_channel");
    public static final HorizonsServerConfig COMMON_CONFIG;
    private static final ForgeConfigSpec COMMON_CONFIG_SPEC;

    static {
        final Pair<HorizonsServerConfig, ForgeConfigSpec> serverPair = new ForgeConfigSpec.Builder().configure(HorizonsServerConfig::new);
        COMMON_CONFIG = serverPair.getLeft();
        COMMON_CONFIG_SPEC = serverPair.getRight();
    }

    public HalcyonHorizons() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG_SPEC, "halcyonhorizons-general.toml");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadConfig);
        modEventBus.addListener(this::reloadConfig);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        HorizonsBlockRegistry.DEF_REG.register(modEventBus);
        HorizonsItemRegistry.DEF_REG.register(modEventBus);
        HorizonsStructureRegistry.DEF_REG.register(modEventBus);
        HorizonsStructurePieceRegistry.DEF_REG.register(modEventBus);
        HorizonsBiomeRegistry.init();
    }

    private void loadConfig(final ModConfigEvent.Loading event) {
        BiomeGenerationConfig.reloadConfig();
    }

    private void reloadConfig(final ModConfigEvent.Reloading event) {
        BiomeGenerationConfig.reloadConfig();
    }



}