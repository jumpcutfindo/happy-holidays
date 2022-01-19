package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.ArrayList;
import java.util.List;

import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PatrolRoute {
    public static final String ROUTE_ADD_POINT_SUCCESS = "item.happyholidays.patrol_orders.add_point_success";
    public static final String ROUTE_ADD_POINT_FAIL = "item.happyholidays.patrol_orders.add_point_fail";
    public static final String ROUTE_REMOVE_POINT_SUCCESS = "item.happyholidays.patrol_orders.remove_point_success";
    public static final String ROUTE_REMOVE_POINT_FAIL = "item.happyholidays.patrol_orders.remove_point_fail";
    public static final String ROUTE_COMPLETE = "item.happyholidays.patrol_orders.complete_route";
    public static final String ROUTE_LOCKED = "item.happyholidays.patrol_orders.locked_route";

    private List<BlockPos> routePoints;
    private boolean isComplete;

    public PatrolRoute() {
        this.routePoints = new ArrayList<>();
    }

    public boolean isEmpty() {
        return routePoints.isEmpty();
    }

    public BlockPos getStart() {
        return this.isEmpty() ? null : this.routePoints.get(0);
    }

    public BlockPos getEnd() {
        return this.routePoints.get(this.routePoints.size() - 1);
    }

    public boolean takeAction(Level level, Player player, BlockPos pos) {
        if (this.isComplete()) {
            if (level.isClientSide()) {
                GameplayMessage message = new GameplayMessage(MessageType.NEUTRAL, ROUTE_LOCKED);
                Messenger.sendChatMessage(message, player);
            }
            return this.isComplete();
        } else if (this.isStart(pos) && !this.isComplete() && this.getPoints().size() >= 1) {
            boolean isComplete = this.endRoute(pos);
            if (isComplete && level.isClientSide()) {
                GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_COMPLETE);
                Messenger.sendChatMessage(message, player);
            }
            return isComplete;
        } else if (this.hasPoint(pos)) {
            boolean isRemoved = this.removePoint(pos);

            if (level.isClientSide()) {
                if (isRemoved) {
                    GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_REMOVE_POINT_SUCCESS);
                    Messenger.sendChatMessage(message, player);
                } else {
                    GameplayMessage message = new GameplayMessage(MessageType.ERROR, ROUTE_REMOVE_POINT_FAIL);
                    Messenger.sendChatMessage(message, player);
                }
            }

            return isRemoved;
        } else {
            boolean isAdded = this.addPoint(pos);

            if (level.isClientSide()) {
                if (isAdded) {
                    GameplayMessage message = new GameplayMessage(MessageType.SUCCESS, ROUTE_ADD_POINT_SUCCESS);
                    Messenger.sendChatMessage(message, player);
                } else {
                    GameplayMessage message = new GameplayMessage(MessageType.ERROR, ROUTE_ADD_POINT_FAIL);
                    Messenger.sendChatMessage(message, player);
                }
            }

            return isAdded;
        }
    }

    public boolean addPoint(BlockPos pos) {
        if (!isValidPoint(pos)) {
            return false;
        }
        else this.routePoints.add(pos);

        return true;
    }

    public boolean removePoint(BlockPos pos) {
        return this.routePoints.remove(pos);
    }

    public boolean hasPoint(BlockPos pos) {
        return this.routePoints.contains(pos);
    }

    public boolean endRoute(BlockPos pos) {
        if (!this.isStart(pos)) return false;
        this.routePoints.add(pos);
        this.isComplete = true;

        return true;
    }

    public boolean isStart(BlockPos pos) {
        return pos.equals(this.getStart());
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public List<BlockPos> getPoints() {
        return this.routePoints;
    }

    public boolean isValidPoint(BlockPos pos) {
        if (this.isEmpty()) return true;

        BlockPos latestPos = this.getEnd();
        return !this.hasPoint(pos) && (latestPos.getX() == pos.getX() || latestPos.getZ() == pos.getZ());
    }

    public CompoundTag serializeTag() {
        CompoundTag mainTag = new CompoundTag();

        // Save array size
        mainTag.putInt("NumPoints", this.routePoints.size());

        // Save list of points
        ListTag pointList = new ListTag();
        for (BlockPos pos : this.routePoints) {
            CompoundTag posTag = NbtUtils.writeBlockPos(pos);
            pointList.add(posTag);
        }

        mainTag.put("Points", pointList);
        mainTag.putBoolean("IsComplete", this.isComplete);

        return mainTag;
    }

    public void deserializeTag(CompoundTag tag) {
        int numPoints = tag.getInt("NumPoints");
        this.routePoints = new ArrayList<>();

        ListTag pointList = (ListTag) tag.get("Points");
        for (int i = 0; i < numPoints; i++) {
            this.routePoints.add(NbtUtils.readBlockPos((CompoundTag) pointList.get(i)));
        }

        this.isComplete = tag.getBoolean("IsComplete");
    }
}
