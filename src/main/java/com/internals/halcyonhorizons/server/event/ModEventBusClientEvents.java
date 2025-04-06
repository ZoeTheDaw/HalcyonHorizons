//package com.internals.halcyonhorizons.server.event;
//
//import com.internals.halcyonhorizons.HalcyonHorizons;
//import com.internals.halcyonhorizons.server.block.blockentity.HorizonsBlockEntityRegistry;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
//import net.minecraft.client.renderer.blockentity.SignRenderer;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = HalcyonHorizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class ModEventBusClientEvents {
//    @SubscribeEvent
//    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerBlockEntityRenderer(HorizonsBlockEntityRegistry.MOD_SIGN.get(), SignRenderer::new);
//        event.registerBlockEntityRenderer(HorizonsBlockEntityRegistry.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
//    }
//}
