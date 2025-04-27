package com.internals.halcyonhorizons.server.block;

import com.internals.halcyonhorizons.server.misc.HorizonsTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BarGateBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty OPEN;
    public static final BooleanProperty POWERED;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape Z_SHAPE;
    protected static final VoxelShape X_SHAPE;
    protected static final VoxelShape Z_SHAPE_LOW;
    protected static final VoxelShape X_SHAPE_LOW;
    protected static final VoxelShape Z_COLLISION_SHAPE;
    protected static final VoxelShape X_COLLISION_SHAPE;
    protected static final VoxelShape Z_SUPPORT_SHAPE;
    protected static final VoxelShape X_SUPPORT_SHAPE;
    protected static final VoxelShape Z_OCCLUSION_SHAPE;
    protected static final VoxelShape X_OCCLUSION_SHAPE;
    protected static final VoxelShape Z_OCCLUSION_SHAPE_LOW;
    protected static final VoxelShape X_OCCLUSION_SHAPE_LOW;
    private final SoundEvent openSound;
    private final SoundEvent closeSound;

    public BarGateBlock(BlockBehaviour.Properties properties, SoundEvent openSound, SoundEvent closeSound) {
        super(properties);
        this.openSound = openSound;
        this.closeSound = closeSound;
        this.registerDefaultState(
                this.stateDefinition.any().setValue(OPEN, false).setValue(POWERED, false).setValue(WATERLOGGED, false)
        );
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return ((Direction) blockState.getValue(FACING)).getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        Direction.Axis direction$axis = direction.getAxis();
        if (((Direction) blockState.getValue(FACING)).getClockWise().getAxis() != direction$axis) {
        }
        return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);

    }

    public VoxelShape getBlockSupportShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        if ((Boolean) blockState.getValue(OPEN)) {
            return Shapes.empty();
        } else {
            return ((Direction) blockState.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_SUPPORT_SHAPE : X_SUPPORT_SHAPE;
        }
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if ((Boolean) blockState.getValue(OPEN)) {
            return Shapes.empty();
        } else {
            return ((Direction) blockState.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_COLLISION_SHAPE : X_COLLISION_SHAPE;
        }
    }

    public VoxelShape getOcclusionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {

        return ((Direction) blockState.getValue(FACING)).getAxis() == Direction.Axis.X ? X_OCCLUSION_SHAPE : Z_OCCLUSION_SHAPE;
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        switch (pathComputationType) {
            case LAND -> {
                return (Boolean) blockState.getValue(OPEN);
            }
            case WATER -> {
                return false;
            }
            case AIR -> {
                return (Boolean) blockState.getValue(OPEN);
            }
            default -> {
                return false;
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Level level = blockPlaceContext.getLevel();
        BlockPos blockpos = blockPlaceContext.getClickedPos();
        boolean flag = level.hasNeighborSignal(blockpos);
        Direction direction = blockPlaceContext.getHorizontalDirection();
        Direction.Axis direction$axis = direction.getAxis();
        return (BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(FACING, direction)).setValue(OPEN, flag)).setValue(POWERED, flag));
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if ((Boolean)blockState.getValue(OPEN)) {
            blockState = (BlockState)blockState.setValue(OPEN, false);
            level.setBlock(blockPos, blockState, 10);
        } else {
            Direction direction = player.getDirection();
            if (blockState.getValue(FACING) == direction.getOpposite()) {
                blockState = (BlockState)blockState.setValue(FACING, direction);
            }

            blockState = (BlockState)blockState.setValue(OPEN, true);
            level.setBlock(blockPos, blockState, 10);
        }
        boolean flag = (Boolean)blockState.getValue(OPEN);
        level.playSound(player, blockPos, flag ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(player, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if ((Boolean)blockState.getValue(POWERED) != flag) {
                level.setBlock(blockPos, (BlockState)((BlockState)blockState.setValue(POWERED, flag)).setValue(OPEN, flag), 2);
                if ((Boolean)blockState.getValue(OPEN) != flag) {
                    level.playSound((Player)null, blockPos, flag ? this.openSound : this.closeSound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
                    level.gameEvent((Entity)null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
                }
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(new Property[]{FACING, OPEN, POWERED, WATERLOGGED});
    }

    public static boolean connectsToDirection(BlockState blockState, Direction direction) {
        return ((Direction)blockState.getValue(FACING)).getAxis() == direction.getClockWise().getAxis();
    }

    public final boolean attachsTo(BlockState blockState, boolean b) {
        return !isExceptionForConnection(blockState) && b || blockState.getBlock() instanceof IronBarsBlock || blockState.is(BlockTags.WALLS);
    }

    static {
        OPEN = BlockStateProperties.OPEN;
        POWERED = BlockStateProperties.POWERED;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        FACING = HorizontalDirectionalBlock.FACING;
        Z_SHAPE = Block.box((double)0.0F, (double)0.0F, (double)6.0F, (double)16.0F, (double)16.0F, (double)10.0F);
        X_SHAPE = Block.box((double)6.0F, (double)0.0F, (double)0.0F, (double)10.0F, (double)16.0F, (double)16.0F);
        Z_SHAPE_LOW = Block.box((double)0.0F, (double)0.0F, (double)6.0F, (double)16.0F, (double)13.0F, (double)10.0F);
        X_SHAPE_LOW = Block.box((double)6.0F, (double)0.0F, (double)0.0F, (double)10.0F, (double)13.0F, (double)16.0F);
        Z_COLLISION_SHAPE = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
        X_COLLISION_SHAPE = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
        Z_SUPPORT_SHAPE = Block.box((double)0.0F, (double)5.0F, (double)6.0F, (double)16.0F, (double)24.0F, (double)10.0F);
        X_SUPPORT_SHAPE = Block.box((double)6.0F, (double)5.0F, (double)0.0F, (double)10.0F, (double)24.0F, (double)16.0F);
        Z_OCCLUSION_SHAPE = Shapes.or(Block.box((double)0.0F, (double)5.0F, (double)7.0F, (double)2.0F, (double)16.0F, (double)9.0F), Block.box((double)14.0F, (double)5.0F, (double)7.0F, (double)16.0F, (double)16.0F, (double)9.0F));
        X_OCCLUSION_SHAPE = Shapes.or(Block.box((double)7.0F, (double)5.0F, (double)0.0F, (double)9.0F, (double)16.0F, (double)2.0F), Block.box((double)7.0F, (double)5.0F, (double)14.0F, (double)9.0F, (double)16.0F, (double)16.0F));
        Z_OCCLUSION_SHAPE_LOW = Shapes.or(Block.box((double)0.0F, (double)2.0F, (double)7.0F, (double)2.0F, (double)13.0F, (double)9.0F), Block.box((double)14.0F, (double)2.0F, (double)7.0F, (double)16.0F, (double)13.0F, (double)9.0F));
        X_OCCLUSION_SHAPE_LOW = Shapes.or(Block.box((double)7.0F, (double)2.0F, (double)0.0F, (double)9.0F, (double)13.0F, (double)2.0F), Block.box((double)7.0F, (double)2.0F, (double)14.0F, (double)9.0F, (double)13.0F, (double)16.0F));
    }

}
