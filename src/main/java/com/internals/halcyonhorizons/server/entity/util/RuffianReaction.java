//package com.internals.halcyonhorizons.server.entity.util;
//
//import com.internals.halcyonhorizons.server.entity.living.RuffianBaseEntity;
//import net.minecraft.world.entity.player.Player;
//
//public enum RuffianReaction {
//    WARY(0, 80),
//    AGGRESSIVE(0, 40),
//    NEUTRAL(10, 25),
//    HELPFUL(8, 30);
//
//    private double minDistance;
//    private double maxDistance;
//
//    RuffianReaction(double minDistance, double maxDistance) {
//        this.minDistance = minDistance;
//        this.maxDistance = maxDistance;
//    }
//
//    public double getMinDistance() {
//        return this.minDistance;
//    }
//
//    public double getMaxDistance() {
//        return this.maxDistance;
//    }
//
//    public static RuffianReaction fromReputation(int rep) {
//        if (rep <= -10) {
//            return AGGRESSIVE;
//        }
//        if (rep <= 10) {
//            return WARY;
//        }
//        if (rep <= 30) {
//            return NEUTRAL;
//        }
//        return HELPFUL;
//    }
//
//    public boolean validPlayer(RuffianBaseEntity ruffian, Player player) {
//        if (this == WARY && player.getY() > ruffian.getY() + 15) {
//            return false;
//        }
//        if (this != AGGRESSIVE && this != HELPFUL) {
//            return player.isInWaterOrBubble() || !ruffian.isInWaterOrBubble();
//        }
//        return true;
//    }
//}
