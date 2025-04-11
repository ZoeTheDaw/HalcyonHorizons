package com.internals.halcyonhorizons.server.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IvyBlock extends Block implements IForgeShearable {
    public static final BooleanProperty UP;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION;
    protected static final float AABB_OFFSET = 1.0F;
    private static final VoxelShape UP_AABB;
    private static final VoxelShape WEST_AABB;
    private static final VoxelShape EAST_AABB;
    private static final VoxelShape NORTH_AABB;
    private static final VoxelShape SOUTH_AABB;
    private final Map<BlockState, VoxelShape> shapesCache;

    public IvyBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, .6F).randomTicks().noOcclusion().sound(SoundType.VINE));
        this.registerDefaultState((BlockState) ((BlockState) ((BlockState) ((BlockState) ((BlockState) ((BlockState) this.stateDefinition.any()).setValue(UP, false)).setValue(NORTH, false)).setValue(EAST, false)).setValue(SOUTH, false)).setValue(WEST, false));
        this.shapesCache = ImmutableMap.copyOf((Map) this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), IvyBlock::calculateShape)));
    }

    private static VoxelShape calculateShape(BlockState blockState) {
        VoxelShape voxelshape = Shapes.empty();
        if ((Boolean) blockState.getValue(UP)) {
            voxelshape = UP_AABB;
        }

        if ((Boolean) blockState.getValue(NORTH)) {
            voxelshape = Shapes.or(voxelshape, NORTH_AABB);
        }

        if ((Boolean) blockState.getValue(SOUTH)) {
            voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
        }

        if ((Boolean) blockState.getValue(EAST)) {
            voxelshape = Shapes.or(voxelshape, EAST_AABB);
        }

        if ((Boolean) blockState.getValue(WEST)) {
            voxelshape = Shapes.or(voxelshape, WEST_AABB);
        }

        return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return (VoxelShape) this.shapesCache.get(blockState);
    }

    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return this.hasFaces(this.getUpdatedState(blockState, levelReader, blockPos));
    }

    private boolean hasFaces(BlockState blockState) {
        return this.countFaces(blockState) > 0;
    }

    private int countFaces(BlockState blockState) {
        int i = 0;

        for (BooleanProperty booleanproperty : PROPERTY_BY_DIRECTION.values()) {
            if ((Boolean) blockState.getValue(booleanproperty)) {
                ++i;
            }
        }

        return i;
    }

    private boolean canSupportAtFace(BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        if (direction == Direction.DOWN) {
            return false;
        } else {
            BlockPos blockpos = blockPos.relative(direction);
            if (isAcceptableNeighbour(blockGetter, blockpos, direction)) {
                return true;
            } else if (direction.getAxis() == Direction.Axis.Y) {
                return false;
            } else {
                BooleanProperty booleanproperty = (BooleanProperty) PROPERTY_BY_DIRECTION.get(direction);
                BlockState blockstate = blockGetter.getBlockState(blockPos.above());
                return blockstate.is(this) && (Boolean) blockstate.getValue(booleanproperty);
            }
        }
    }

    public static boolean isAcceptableNeighbour(BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return MultifaceBlock.canAttachTo(blockGetter, direction, blockPos, blockGetter.getBlockState(blockPos));
    }

    private BlockState getUpdatedState(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        BlockPos blockpos = blockPos.above();
        if ((Boolean) blockState.getValue(UP)) {
            blockState = (BlockState) blockState.setValue(UP, isAcceptableNeighbour(blockGetter, blockpos, Direction.DOWN));
        }

        BlockState blockstate = null;

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BooleanProperty booleanproperty = getPropertyForFace(direction);
            if ((Boolean) blockState.getValue(booleanproperty)) {
                boolean flag = this.canSupportAtFace(blockGetter, blockPos, direction);
                if (!flag) {
                    if (blockstate == null) {
                        blockstate = blockGetter.getBlockState(blockpos);
                    }

                    flag = blockstate.is(this) && (Boolean) blockstate.getValue(booleanproperty);
                }

                blockState = (BlockState) blockState.setValue(booleanproperty, flag);
            }
        }

        return blockState;
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (direction == Direction.DOWN) {
            return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
        } else {
            BlockState blockstate = this.getUpdatedState(blockState, levelAccessor, blockPos);
            return !this.hasFaces(blockstate) ? Blocks.AIR.defaultBlockState() : blockstate;
        }
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DO_VINES_SPREAD) && serverLevel.random.nextInt(4) == 0 && serverLevel.isAreaLoaded(blockPos, 4)) {
            Direction direction = Direction.getRandom(randomSource);
            BlockPos blockpos = blockPos.above();
            if (direction.getAxis().isHorizontal() && !(Boolean) blockState.getValue(getPropertyForFace(direction))) {
                if (this.canSpread(serverLevel, blockPos)) {
                    BlockPos blockpos4 = blockPos.relative(direction);
                    BlockState blockstate4 = serverLevel.getBlockState(blockpos4);
                    if (blockstate4.isAir()) {
                        Direction direction3 = direction.getClockWise();
                        Direction direction4 = direction.getCounterClockWise();
                        boolean flag = (Boolean) blockState.getValue(getPropertyForFace(direction3));
                        boolean flag1 = (Boolean) blockState.getValue(getPropertyForFace(direction4));
                        BlockPos blockpos2 = blockpos4.relative(direction3);
                        BlockPos blockpos3 = blockpos4.relative(direction4);
                        if (flag && isAcceptableNeighbour(serverLevel, blockpos2, direction3)) {
                            serverLevel.setBlock(blockpos4, (BlockState) this.defaultBlockState().setValue(getPropertyForFace(direction3), true), 2);
                        } else if (flag1 && isAcceptableNeighbour(serverLevel, blockpos3, direction4)) {
                            serverLevel.setBlock(blockpos4, (BlockState) this.defaultBlockState().setValue(getPropertyForFace(direction4), true), 2);
                        } else {
                            Direction direction1 = direction.getOpposite();
                            if (flag && serverLevel.isEmptyBlock(blockpos2) && isAcceptableNeighbour(serverLevel, blockPos.relative(direction3), direction1)) {
                                serverLevel.setBlock(blockpos2, (BlockState) this.defaultBlockState().setValue(getPropertyForFace(direction1), true), 2);
                            } else if (flag1 && serverLevel.isEmptyBlock(blockpos3) && isAcceptableNeighbour(serverLevel, blockPos.relative(direction4), direction1)) {
                                serverLevel.setBlock(blockpos3, (BlockState) this.defaultBlockState().setValue(getPropertyForFace(direction1), true), 2);
                            } else if ((double) randomSource.nextFloat() < 0.05 && isAcceptableNeighbour(serverLevel, blockpos4.above(), Direction.UP)) {
                                serverLevel.setBlock(blockpos4, (BlockState) this.defaultBlockState().setValue(UP, true), 2);
                            }
                        }
                    } else if (isAcceptableNeighbour(serverLevel, blockpos4, direction)) {
                        serverLevel.setBlock(blockPos, (BlockState) blockState.setValue(getPropertyForFace(direction), true), 2);
                    }
                }
            } else {
                if (direction == Direction.UP && blockPos.getY() < serverLevel.getMaxBuildHeight() - 1) {
                    if (this.canSupportAtFace(serverLevel, blockPos, direction)) {
                        serverLevel.setBlock(blockPos, (BlockState) blockState.setValue(UP, true), 2);
                        return;
                    }

                    if (serverLevel.isEmptyBlock(blockpos)) {
                        if (!this.canSpread(serverLevel, blockPos)) {
                            return;
                        }

                        BlockState blockstate3 = blockState;

                        for (Direction direction2 : Direction.Plane.HORIZONTAL) {
                            if (randomSource.nextBoolean() || !isAcceptableNeighbour(serverLevel, blockpos.relative(direction2), direction2)) {
                                blockstate3 = (BlockState) blockstate3.setValue(getPropertyForFace(direction2), false);
                            }
                        }

                        if (this.hasHorizontalConnection(blockstate3)) {
                            serverLevel.setBlock(blockpos, blockstate3, 2);
                        }

                        return;
                    }
                }

                if (blockPos.getY() > serverLevel.getMinBuildHeight()) {
                    BlockPos blockpos1 = blockPos.below();
                    BlockState blockstate = serverLevel.getBlockState(blockpos1);
                    if (blockstate.isAir() || blockstate.is(this)) {
                        BlockState blockstate1 = blockstate.isAir() ? this.defaultBlockState() : blockstate;
                        BlockState blockstate2 = this.copyRandomFaces(blockState, blockstate1, randomSource);
                        if (blockstate1 != blockstate2 && this.hasHorizontalConnection(blockstate2)) {
                            serverLevel.setBlock(blockpos1, blockstate2, 2);
                        }
                    }
                }
            }
        }

    }

    private BlockState copyRandomFaces(BlockState blockState, BlockState blockState1, RandomSource randomSource) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (randomSource.nextBoolean()) {
                BooleanProperty booleanproperty = getPropertyForFace(direction);
                if ((Boolean) blockState.getValue(booleanproperty)) {
                    blockState1 = (BlockState) blockState1.setValue(booleanproperty, true);
                }
            }
        }

        return blockState1;
    }

    private boolean hasHorizontalConnection(BlockState blockState) {
        return (Boolean) blockState.getValue(NORTH) || (Boolean) blockState.getValue(EAST) || (Boolean) blockState.getValue(SOUTH) || (Boolean) blockState.getValue(WEST);
    }

    private boolean canSpread(BlockGetter blockGetter, BlockPos blockPos) {
        int i = 4;
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(blockPos.getX() - 4, blockPos.getY() - 1, blockPos.getZ() - 4, blockPos.getX() + 4, blockPos.getY() + 1, blockPos.getZ() + 4);
        int j = 5;

        for (BlockPos blockpos : iterable) {
            if (blockGetter.getBlockState(blockpos).is(this)) {
                --j;
                if (j <= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        BlockState blockstate = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        if (blockstate.is(this)) {
            return this.countFaces(blockstate) < PROPERTY_BY_DIRECTION.size();
        } else {
            return super.canBeReplaced(blockState, blockPlaceContext);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockstate = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        boolean flag = blockstate.is(this);
        BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();

        for (Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction != Direction.DOWN) {
                BooleanProperty booleanproperty = getPropertyForFace(direction);
                boolean flag1 = flag && (Boolean) blockstate.getValue(booleanproperty);
                if (!flag1 && this.canSupportAtFace(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), direction)) {
                    return (BlockState) blockstate1.setValue(booleanproperty, true);
                }
            }
        }

        return flag ? blockstate1 : null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(new Property[]{UP, NORTH, EAST, SOUTH, WEST});
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180 -> {
                return (BlockState) ((BlockState) ((BlockState) ((BlockState) blockState.setValue(NORTH, (Boolean) blockState.getValue(SOUTH))).setValue(EAST, (Boolean) blockState.getValue(WEST))).setValue(SOUTH, (Boolean) blockState.getValue(NORTH))).setValue(WEST, (Boolean) blockState.getValue(EAST));
            }
            case COUNTERCLOCKWISE_90 -> {
                return (BlockState) ((BlockState) ((BlockState) ((BlockState) blockState.setValue(NORTH, (Boolean) blockState.getValue(EAST))).setValue(EAST, (Boolean) blockState.getValue(SOUTH))).setValue(SOUTH, (Boolean) blockState.getValue(WEST))).setValue(WEST, (Boolean) blockState.getValue(NORTH));
            }
            case CLOCKWISE_90 -> {
                return (BlockState) ((BlockState) ((BlockState) ((BlockState) blockState.setValue(NORTH, (Boolean) blockState.getValue(WEST))).setValue(EAST, (Boolean) blockState.getValue(NORTH))).setValue(SOUTH, (Boolean) blockState.getValue(EAST))).setValue(WEST, (Boolean) blockState.getValue(SOUTH));
            }
            default -> {
                return blockState;
            }
        }
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT -> {
                return (BlockState) ((BlockState) blockState.setValue(NORTH, (Boolean) blockState.getValue(SOUTH))).setValue(SOUTH, (Boolean) blockState.getValue(NORTH));
            }
            case FRONT_BACK -> {
                return (BlockState) ((BlockState) blockState.setValue(EAST, (Boolean) blockState.getValue(WEST))).setValue(WEST, (Boolean) blockState.getValue(EAST));
            }
            default -> {
                return super.mirror(blockState, mirror);
            }
        }
    }

    public static BooleanProperty getPropertyForFace(Direction direction) {
        return (BooleanProperty) PROPERTY_BY_DIRECTION.get(direction);
    }

    static {
        UP = PipeBlock.UP;
        NORTH = PipeBlock.NORTH;
        EAST = PipeBlock.EAST;
        SOUTH = PipeBlock.SOUTH;
        WEST = PipeBlock.WEST;
        PROPERTY_BY_DIRECTION = (Map) PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((directionBooleanPropertyEntry) -> directionBooleanPropertyEntry.getKey() != Direction.DOWN).collect(Util.toMap());
        UP_AABB = Block.box((double) 0.0F, (double) 15.0F, (double) 0.0F, (double) 16.0F, (double) 16.0F, (double) 16.0F);
        WEST_AABB = Block.box((double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 1.0F, (double) 16.0F, (double) 16.0F);
        EAST_AABB = Block.box((double) 15.0F, (double) 0.0F, (double) 0.0F, (double) 16.0F, (double) 16.0F, (double) 16.0F);
        NORTH_AABB = Block.box((double) 0.0F, (double) 0.0F, (double) 0.0F, (double) 16.0F, (double) 16.0F, (double) 1.0F);
        SOUTH_AABB = Block.box((double) 0.0F, (double) 0.0F, (double) 15.0F, (double) 16.0F, (double) 16.0F, (double) 16.0F);
    }
}


