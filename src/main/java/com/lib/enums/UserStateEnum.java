package com.lib.enums;

/**
 * 使用枚举 表示 常量的数据字段
 */
public enum UserStateEnum {
    ADMIN(0, "管理员"),
    COMMON(1, "普通用户"),
    LOCK(2, "锁定");

    private int state;

    private String stateInfo;

    UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserStateEnum stateOf(int index) {
        for (UserStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
