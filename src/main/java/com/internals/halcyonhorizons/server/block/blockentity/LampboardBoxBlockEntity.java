package com.internals.halcyonhorizons.server.block.blockentity;

import com.internals.halcyonhorizons.server.block.LampboardBoxBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LampboardBoxBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;

    public LampboardBoxBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(HorizonsBlockEntityRegistry.LAMPBOARD_BOX.get(), blockPos, blockState);
        this.items = NonNullList.withSize(9, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            @Override
            protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
                LampboardBoxBlockEntity.this.playSound(blockState, SoundEvents.BARREL_OPEN);
                LampboardBoxBlockEntity.this.updateBlockState(blockState, true);
            }

            @Override
            protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
                LampboardBoxBlockEntity.this.playSound(blockState, SoundEvents.BARREL_CLOSE);
                LampboardBoxBlockEntity.this.updateBlockState(blockState, false);
            }

            @Override
            protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int i1) {

            }

            @Override
            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof ChestMenu) {
                    Container container = ((ChestMenu) player.containerMenu).getContainer();
                    return container == LampboardBoxBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.loadFromTag(compoundTag);
    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items, false);
        }
    }

    public void loadFromTag(CompoundTag compoundTag) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag) && compoundTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    public int getContainerSize() {
        return 9;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    protected Component getDefaultName() {
        return
                Component.translatable("block.halcyonhorizons.lampboard_box");
    }

    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return ChestMenu.oneRow(i, inventory);
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    void updateBlockState(BlockState blockState, boolean v) {
        this.level.setBlock(this.getBlockPos(), (BlockState) blockState.setValue(LampboardBoxBlock.OPEN, v), 3);
    }

    void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vec3i vec3i = ((Direction) blockState.getValue(LampboardBoxBlock.FACING)).getNormal();
        double vec3i2 = (double) this.worldPosition.getX() + (double) 0.5F + (double) vec3i.getX() / (double) 2.0F;
        double vec3i3 = (double) this.worldPosition.getY() + (double) 0.5F + (double) vec3i.getY() / (double) 2.0F;
        double vec3i4 = (double) this.worldPosition.getZ() + (double) 0.5F + (double) vec3i.getZ() / (double) 2.0F;
        this.level.playSound((Player) null, vec3i2, vec3i3, vec3i4, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }


}
