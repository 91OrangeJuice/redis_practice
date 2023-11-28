package com.tkp.learn.archive.task.enums;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
    public enum TaskNameEnum {
    INITDAYTASK("initDayTask","初始化历史行为数据任务"),
    HANDLEDAYTASK("TopListTask.handleDayTask", "统计月表年表跑批任务"),
    HANDLETOPLISTTASK("TopListTask.handleTopListTask", "统计榜单跑批任务");

    private String code;

    private String name;

    TaskNameEnum(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
