package com.internals.halcyonhorizons.server.misc;

import com.internals.halcyonhorizons.HalcyonHorizons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HorizonsSoundRegistry {
    public static final DeferredRegister<SoundEvent> DEF_REG = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HalcyonHorizons.MODID);

    public static final RegistryObject<SoundEvent> LAMPPAPER_STEP = createSoundEvent("lamppaper_step");
    public static final RegistryObject<SoundEvent> LAMPPAPER_PLACE = createSoundEvent("lamppaper_place");
    public static final RegistryObject<SoundEvent> LAMPPAPER_BREAK = createSoundEvent("lamppaper_break");
    public static final RegistryObject<SoundEvent> LAMPPAPER_BREAKING = createSoundEvent("lamppaper_breaking");

    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        return DEF_REG.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HalcyonHorizons.MODID, soundName)));
    }
}
