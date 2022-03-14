package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

public enum NaughtyNiceAction {
    // Nice events
    KILL_HOSTILE_MOB_EVENT(10, Type.NICE),
    HELP_SANTA_ELF_EVENT(50, Type.NICE),
    APPEASE_GRINCH_EVENT(20, Type.NICE),
    TAME_NUTCRACKER_EVENT(10, Type.NICE),

    // Naughty events
    KILL_PASSIVE_MOB_EVENT(5, Type.NAUGHTY),
    KILL_SANTA_ELF_EVENT(200, Type.NAUGHTY);

    private int cost;
    private NaughtyNiceAction.Type actionType;

    NaughtyNiceAction(int cost, NaughtyNiceAction.Type actionType) {
        this.cost = cost;
        this.actionType = actionType;
    }

    public int getCost() {
        return cost;
    }

    public Type getActionType() {
        return actionType;
    }

    public enum Type {
        NICE, NAUGHTY;
    }
}
