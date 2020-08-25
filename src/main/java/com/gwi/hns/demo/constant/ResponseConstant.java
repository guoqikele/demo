package com.gwi.hns.demo.constant;

public enum ResponseConstant {
    SUCCESS(200, "成功"), SYSTEM_ERROR(5000, "系统错误"), LOGING_FAILD(5001, "用户名或密码错误"),
    FORBIDDEN(4003, "用户没有访问权限"), USER_EXIST(510, "用户已经存在");

    private int code;
    private String msg;

    ResponseConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
