package com.internals.halcyonhorizons.server.entity.living;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractRuffian extends PathfinderMob implements InventoryCarrier, Npc, Merchant {
    public static final int RUFFIAN_SLOT_OFFSET = 500;
    private static final int RUFFIAN_INVENTORY_SIZE = 10;
    @Nullable
    private Player tradingPlayer;
    @Nullable
    protected MerchantOffers offers;
    private final SimpleContainer inventory = new SimpleContainer(8);

    /// Ruffian pathfinding to indicate things they  wouldn't approach.
    public AbstractRuffian(EntityType<? extends AbstractRuffian> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public int getRuffianXP() {
        return 0;
    }

    /*
    Below is all the abstract ruffians behaviors in relation to trades.
     */


    @Nullable
    public Player getTradingPlayer() {
        /// Gets the player that is currently trading with the ruffian.
        return this.tradingPlayer;
    }

    public boolean isTrading() {
        ///  A boolean state to indicate if the player is trading or not. Returns as true if the a player is currently trading.
        return this.tradingPlayer != null;
    }

    @Nullable
    public Entity changeDimension(@NotNull ServerLevel p_35295_, @NotNull ITeleporter teleporter) {
        /// Cancel trading if the ruffian is sent to another dimension.
        this.stopTrading();
        return super.changeDimension(p_35295_, teleporter);
    }

    public boolean showProgressBar() {
        return false;
    }

    protected void stopTrading() {
        /// Stops trading if the trading player becomes null
        this.setTradingPlayer((Player)null);
    }

    public void die(@NotNull DamageSource p_35270_) {
        ///  Dies and stops trading
        super.die(p_35270_);
        this.stopTrading();
    }

    public boolean canBeLeashed(@NotNull Player p_35272_) {
        /// Self explanatory
        return false;
    }

}
