package com.gwi.hns.demo.pay.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header {
    private String msgId;
    private String hdlSts;
    private String bdFlg;
    private String rtnCd;
    private String merchantNo;
    private String exception;
    private String returnActFlag;
    private String dealStatus;
    private String bodyFlag;
}
