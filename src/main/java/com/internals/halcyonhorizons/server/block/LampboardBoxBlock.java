package com.internals.halcyonhorizons.server.block;

import com.github.alexthe666.citadel.repack.jaad.Play;
import com.github.alexthe666.citadel.repack.jcodec.common.DictionaryCompressor;
import com.internals.halcyonhorizons.server.block.blockentity.HorizonsBlockEntityRegistry;
import com.internals.halcyonhorizons.server.block.blockentity.LampboardBoxBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LampboardBoxBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty OPEN;
    public static final ResourceLocation CONTENTS;

    public LampboardBoxBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F, .6F).sound(SoundType.GRASS));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof LampboardBoxBlockEntity) {
                player.openMenu((LampboardBoxBlockEntity) blockEntity);
            }
            return InteractionResult.CONSUME;
        }
    }

    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        if (!blockState.is(blockState1.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof LampboardBoxBlockEntity) {
                level.updateNeighbourForOutputSignal(blockPos, blockState.getBlock());
            }
            super.onRemove(blockState, level, blockPos, blockState1, b);
        }
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof LampboardBoxBlockEntity) {
            ((LampboardBoxBlockEntity) blockEntity).recheckOpen();
        }
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LampboardBoxBlockEntity(blockPos, blockState);
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof LampboardBoxBlockEntity) {
                ((LampboardBoxBlockEntity) blockEntity).setCustomName(itemStack.getHoverName());
            }
        }
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{FACING, OPEN});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof LampboardBoxBlockEntity lampboardBoxBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !lampboardBoxBlockEntity.isEmpty()) {
                // Create an item stack from the block itself
                ItemStack stack = new ItemStack(this);
                blockEntity.saveToItem(stack);  // Save the block's contents to the item stack
                if (lampboardBoxBlockEntity.hasCustomName()) {
                    stack.setHoverName(lampboardBoxBlockEntity.getCustomName());  // Preserve the name if it has one
                }
                ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);  // Drop the block with its contents
            } else {
                lampboardBoxBlockEntity.unpackLootTable(player);  // Drop individual contents if not in Creative mode
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }


    // Modify the Rotation and Mirroring Behavior
    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState) blockState.setValue(FACING, rotation.rotate((Direction) blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction) blockState.getValue(FACING)));
    }

    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity parameter = (BlockEntity) builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (parameter instanceof LampboardBoxBlockEntity lampboardBoxBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, (stackConsumer) -> {
                for (int i = 0; i < lampboardBoxBlockEntity.getContainerSize(); ++i) {
                    stackConsumer.accept(lampboardBoxBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(state, builder);
    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        ItemStack itemStack = super.getCloneItemStack(blockGetter, blockPos, blockState);
        blockGetter.getBlockEntity(blockPos, HorizonsBlockEntityRegistry.LAMPBOARD_BOX.get()).ifPresent((lampboardBoxBlockEntity) -> lampboardBoxBlockEntity.saveToItem(itemStack));
        return itemStack;
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        OPEN = BooleanProperty.create("open");
        CONTENTS = new ResourceLocation("contents");
    }
}
