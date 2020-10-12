package com.gwi.hns.demo.exception;

import com.gwi.hns.demo.constant.ResponseConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonException extends Exception {
    private String massage;
    private int errorCode;
    private ResponseConstant constant;

    public CommonException(String massage, int errorCode) {
        this.massage = massage;
        this.errorCode = errorCode;
    }

    public CommonException(ResponseConstant constant) {
        this.massage = constant.getMsg();
        this.errorCode = constant.getCode();
    }
}
