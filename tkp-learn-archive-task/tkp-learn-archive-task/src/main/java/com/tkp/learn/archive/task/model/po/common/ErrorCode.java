package com.tkp.learn.archive.task.model.po.common;

public enum ErrorCode {

    NO_LOGIN("用户信息失效，请重新登录！",-1),
    FAIL("系统繁忙，请稍后重试～!", 2),
    NO_SUBSCRIBE("当前客户尚未订阅养老官微公众号！",3),
    NO_BIND("当前客户尚未绑定养老官微公众号！",4),
    BAD_REQUEST("Bad Request！",400),
    METHOD_NOT_ALLOWED("Method Not Allowed！",405),
    SERVICE_ERROR("业务错误！", 8001),
    ILLEGAL_COUPON("不合法的卡券编号！", 8003),
    NOT_ENOUGH_COUPON("卡券剩余数量不足，请重新分配！", 8004),
    ILLEGAL_ROLE("不合法的角色权限！", 8005),
    NO_SHARE_ID("分享ID不存在！", 8006),
    PARAM_ERROR("参数错误！", 9000),
    WARN("WARN！", 505),
    REMOTE_EXCEPTION("接口调用异常！",10000),
    REMOTE_DATA_ERROR("接口数据错误！", 10001),
    NO_STRATEGY("不合法的策略码!",10002),
    OAUTH_ERROR("微信网页授权异常",3);

    private String name;
    private int code;

    ErrorCode(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

}