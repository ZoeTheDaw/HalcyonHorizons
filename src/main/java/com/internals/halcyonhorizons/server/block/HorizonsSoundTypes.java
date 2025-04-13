package com.internals.halcyonhorizons.server.block;

import com.internals.halcyonhorizons.server.misc.HorizonsSoundRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public class HorizonsSoundTypes {

    public static final SoundType LAMPPAPER = new ForgeSoundType(1.0F, 1.0F, () -> HorizonsSoundRegistry.LAMPPAPER_BREAK.get(), () -> HorizonsSoundRegistry.LAMPPAPER_STEP.get(), () -> HorizonsSoundRegistry.LAMPPAPER_PLACE.get(), () -> HorizonsSoundRegistry.LAMPPAPER_BREAKING.get(), () -> HorizonsSoundRegistry.LAMPPAPER_STEP.get());


}
