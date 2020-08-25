package com.gwi.hns.demo.pay.wechat.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTrans {
    private String orderNo;
    private String orderSeq;
    private String orderStatus;
    private String cardTyp;
    private String payTime;
    private String payAmount;
    private String unionPaySeq;
    
    private String tranSeq;
    private String merchantNo;
    private String custTranId;
    private String tranCode;
    private String tranTime;
    private String tranAmount;
    private String tranStatus;
    
    private String mRefundSeq;
    private String curCode;
    private String refundAmount;
    private String orderAmount;
    private String bankTranSeq;
    private String signData;
}
