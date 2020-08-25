package com.gwi.hns.demo.vo;

import com.gwi.hns.demo.constant.ResponseConstant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity<T> {
    // 响应码
    private Integer code;
    // 响应数据
    private T data;
    // 错误提示
    private String errorMsg;

    public void successResp() {
        this.code = 200;
    }

    public void failResp(ResponseConstant Constant) {
        this.code = Constant.getCode();
        this.errorMsg = Constant.getMsg();
    }
}
