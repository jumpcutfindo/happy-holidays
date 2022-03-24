package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PatrolRoute {
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

    public ActionResult takeAction(Level level, Player player, BlockPos pos) {
        if (this.isComplete()) {
            return ActionResult.LOCKED;
        } else if (this.isStart(pos) && !this.isComplete() && this.getPoints().size() >= 1) {
            this.endRoute(pos);
            return ActionResult.COMPLETE;
        } else if (this.hasPoint(pos)) {
            boolean isRemoved = this.removePoint(pos);
            return isRemoved ? ActionResult.REMOVE_POINT_SUCCESS : ActionResult.REMOVE_POINT_FAIL;
        } else {
            boolean isAdded = this.addPoint(pos);
            return isAdded ? ActionResult.ADD_POINT_SUCCESS : ActionResult.ADD_POINT_FAIL;
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

    public void endRoute(BlockPos pos) {
        this.routePoints.add(pos);
        this.isComplete = true;
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

    public int getLength() {
        return this.getPoints().size();
    }

    public boolean isValidPoint(BlockPos pos) {
        if (this.isEmpty()) return true;
        return !this.hasPoint(pos);
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

    public enum ActionResult {
        ADD_POINT_SUCCESS,
        ADD_POINT_FAIL,
        REMOVE_POINT_SUCCESS,
        REMOVE_POINT_FAIL,
        COMPLETE,
        LOCKED,
        ERASE,
        NONE,
    }
}
