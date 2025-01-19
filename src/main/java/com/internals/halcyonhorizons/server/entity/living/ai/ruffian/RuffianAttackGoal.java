package com.internals.halcyonhorizons.server.entity.living.ai.ruffian;

import com.internals.halcyonhorizons.server.entity.living.AbstractRuffian;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class RuffianAttackGoal extends Goal {
    private AbstractRuffian ruffian;

    public RuffianAttackGoal(AbstractRuffian ruffian) {
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        this.ruffian = ruffian;
    }

    @Override
    public boolean canUse() {
        return ruffian.getTarget() != null && ruffian.getTarget().isAlive()
    }


    public void tick() {
        LivingEntity target = ruffian.getTarget();
        if (target != null) {
            ruffian.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ(), 20.0F, (float) deepOne.getMaxHeadXRot());
            ruffian.startAttackBehavior(target);
        }
    }
}
