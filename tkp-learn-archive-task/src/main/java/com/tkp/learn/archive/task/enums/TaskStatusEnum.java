package com.tkp.learn.archive.task.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
public enum TaskStatusEnum {
    FINISH(0, "执行完成"),
    WORKING(1, "执行中"),
    EXCEPTION(99,"执行异常");

    private int code;

    private String name;

    TaskStatusEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(int code) {
        for (TaskStatusEnum ele : values()) {
            if(ele.getCode()==code) {
                return ele.getName();
            }
        }
        return null;
    }

}
