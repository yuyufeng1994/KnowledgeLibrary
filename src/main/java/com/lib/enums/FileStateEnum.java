package com.lib.enums;

/**
 * 使用枚举 表示 常量的数据字段
 */
public enum FileStateEnum {
    FILE_UNUPLOAD(0, "未上传"),
    FILE_UPLOAD_FAIL(1, "上传失败"),
    FILE_UPLOAD_SUCCESS(2,"待处理"),
    FILE_HANDLERING(3,"处理中"),
    FILE_CHECKING(4,"审核中"),
    FILE_PUBLIC(5,"共享"),
    FILE_PRIVATE(6,"私有"),
    FILE_LOCK(7, "冻结");
	
    private int state;

    private String stateInfo;

    FileStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static FileStateEnum stateOf(int index) {
        for (FileStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
