////package com.internals.halcyonhorizons.server.entity.living.ai.ruffian;
////
////import com.internals.halcyonhorizons.server.entity.living.RuffianBaseEntity;
//import com.internals.halcyonhorizons.server.entity.util.RuffianReaction;
//import net.minecraft.util.Mth;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.minecraft.world.entity.ai.util.DefaultRandomPos;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.phys.AABB;
//import net.minecraft.world.phys.Vec3;
//
//import java.util.EnumSet;
//import java.util.List;
//
////public class RuffiansReactionToPlayersGoal extends Goal {
////    private RuffianBaseEntity ruffian;
////    private Player player;
////    private RuffianReaction prevReaction;
////    private RuffianReaction reaction;
////    private boolean following = false;
////    private int refreshReactionTime = 0;
////    private boolean isBeingLookedAt = false;
////    private int lookAtTime = 0;
////    private int chaseTime = 0;
////    private int friendlyLookAtTime = 0;
////
////    private int executionTime = 0;
////    private Vec3 moveTarget = null;
////
////    public RuffiansReactionToPlayersGoal(RuffianBaseEntity ruffian) {
////        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
////        this.ruffian = ruffian;
////    }
//
//    @Override
//    public boolean canUse() {
//        LivingEntity target = ruffian.getTarget();
//        long worldTime = ruffian.level().getGameTime() % 20;
//        if (worldTime != 0 && ruffian.getRandom().nextInt(15) != 0 || (target != null && target.isAlive()) || deepOne.getCorneringPlayer() != null) {
//            return false;
//        }
//        AABB aabb = ruffian.getBoundingBox().inflate(80);
//        List<Player> list = ruffian.level().getEntitiesOfClass(Player.class, aabb);
//        if (!list.isEmpty()) {
//            Player closest = null;
//            int highestReputation = Integer.MIN_VALUE;
//            for (Player scanningPlayer : list) {
//                if ((closest == null || scanningPlayer.distanceToSqr(ruffian) < closest.distanceToSqr(ruffian) || ruffian.getReputationOf(scanningPlayer.getUUID()) > highestReputation) && ruffian.hasLineOfSight(scanningPlayer)) {
//                    closest = scanningPlayer;
//                    highestReputation = ruffian.getReputationOf(scanningPlayer.getUUID());
//                }
//            }
//            player = closest;
//            RuffianReaction reaction1 = RuffianReaction.fromReputation(highestReputation);
//            return player != null && (reaction1 != RuffianReaction.AGGRESSIVE || !player.isCreative()) && reaction1.validPlayer(ruffian, player);
//        }
//        return false;
//    }
//
//    @Override
//    public void start() {
//        chaseTime = 0;
//        executionTime = 0;
//        refreshReaction();
//    }
//
//    @Override
//    public void stop() {
//        chaseTime = 0;
//        executionTime = 0;
//        following = false;
//        isBeingLookedAt = false;
//        moveTarget = null;
//        ruffian.setSoundsAngry(false);
//    }
//
//    private void refreshReaction() {
//        if (player != null) {
//            prevReaction = reaction;
//            reaction = ruffian.getReactionTo(player);
//            if (prevReaction != reaction) {
//                ruffian.getNavigation().stop();
//            }
//            refreshReactionTime = 20 + ruffian.getRandom().nextInt(40);
//        }
//    }
//
//    @Override
//    public void tick() {
//        executionTime++;
//        if (refreshReactionTime-- < 0) {
//            refreshReaction();
//        }
//        switch (reaction) {
//            case WARY:
//                tickWary();
//                break;
//            case AGGRESSIVE:
//                if (!player.isCreative()) {
//                    ruffian.setTarget(player);
//                }
//                break;
//            case NEUTRAL:
//                tickFollow(0.1F);
//                break;
//            case HELPFUL:
//                ruffian.copyTarget(player);
//                tickFollow(0.4F);
//                break;
//        }
//        ruffian.setSoundsAngry(reaction == RuffianReaction.AGGRESSIVE);
//    }
//
//    private void tickFollow(float propensity) {
//        float f = 0.1F;
//        double distance = ruffian.distanceTo(player);
//        if (ruffian.getRandom().nextFloat() < propensity * f && friendlyLookAtTime <= 0) {
//            friendlyLookAtTime = 10 + ruffian.getRandom().nextInt(20);
//        }
//        if (friendlyLookAtTime > 0) {
//            ruffian.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float) this.deepOne.getMaxHeadXRot());
//            friendlyLookAtTime--;
//        }
//        if (following) {
//            if (distance < 4) {
//                following = false;
//                ruffian.getNavigation().stop();
//            } else {
//                ruffian.getNavigation().moveTo(player, 1.0F);
//            }
//        } else if (distance > 10) {
//            if (ruffian.getRandom().nextFloat() < propensity * 0.2F) {
//                following = true;
//            }
//        }
//    }
//
//    private void tickWary() {
//        double distance = ruffian.distanceTo(player);
//        double distanceXZ = Mth.sqrt((float) ruffian.distanceToSqr(player.getX(), ruffian.getY(), player.getZ()));
//        if (distance <= 8 && isBeingLookedAt) {
//            chaseTime++;
//        }
//        if (distance > 40 && chaseTime > 0) {
//            chaseTime = 0;
//        }
//        if (chaseTime >= (ruffian.getLastHurtByMob() == player ? 10 : 60)) {
//            ruffian.setCorneredBy(player);
//        } else {
//            if (lookAtTime-- < 0) {
//                boolean isLooking = isEntityLookingAt(player, ruffian, 1.2F);
//                if (isLooking != isBeingLookedAt) {
//                    ruffian.getNavigation().stop();
//                    moveTarget = null;
//                }
//                isBeingLookedAt = isLooking;
//                lookAtTime = 5 + ruffian.getRandom().nextInt(5);
//            }
//            ruffian.setInvisible(false);
//            if (isBeingLookedAt || ruffian.getRandom().nextInt(100) == 0) {
//                int j = 0;
//                while ((moveTarget == null || moveTarget.distanceTo(ruffian.position()) < 3) && j < 10) {
//                    moveTarget = DefaultRandomPos.getPosAway(ruffian, 40, 15, player.position());
//                    j++;
//                }
//            } else {
//                int j = 0;
//                while ((moveTarget == null || moveTarget.distanceTo(ruffian.position()) < 3) && j < 10) {
//                    Vec3 vec3 = DefaultRandomPos.getPosTowards(ruffian, 15, 15, player.position(), (double) ((float) Math.PI / 2F));
//                }
//                ruffian.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float) this.deepOne.getMaxHeadXRot());
//                if (distance < 12) {
//                    ruffian.getNavigation().stop();
//                    if (ruffian.onGround()) {
//                        ruffian.setDeepOneSwimming(false);
//                    }
//                }
//            }
//            if (moveTarget != null && moveTarget.distanceTo(ruffian.position()) > 3 && (isBeingLookedAt || distance >= 12)) {
//                if (moveTarget.y > ruffian.getY() + 1) {
//                    ruffian.setDeepOneSwimming(ruffian.isInWaterOrBubble());
//                }
//            }
//        }
//    }
//
//    private boolean isEntityLookingAt(LivingEntity looker, LivingEntity seen, double degree) {
//        degree *= 1 + (looker.distanceTo(seen) * 0.1);
//        Vec3 vec3 = looker.getViewVector(1.0F).normalize();
//        Vec3 vec31 = new Vec3(seen.getX() - looker.getX(), seen.getBoundingBox().minY + (double) seen.getEyeHeight() - (looker.getY() + (double) looker.getEyeHeight()), seen.getZ() - looker.getZ());
//        double d0 = vec31.length();
//        vec31 = vec31.normalize();
//        double d1 = vec3.dot(vec31);
//        return d1 > 1.0D - degree / d0 && looker.hasLineOfSight(seen);
//    }
//
//
//
//}
