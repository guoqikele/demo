package com.gwi.hns.demo.pay.common.vo;

import com.gwi.hns.demo.pay.constant.PaymentConstent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParamsVo {
    //商户号
    private String merchantNo;
    //支付类型
    private String payType = PaymentConstent.PAY_TYPE_SHOPPING.getCode();
    //商户订单号
    private String orderNo;
    //订单币种
    private String curCode = PaymentConstent.CUR_CODE_YUAN.getCode();
    //订单金额
    private Double orderAmount;
    //订单时间
    private String orderTime;
    //订单说明
    private String orderNote;
    //商户接收通知URL
    private String orderUrl;
    //订单超时时间
    private String orderTimeoutDate;
    //交易终端类型
    private String terminalChnl = PaymentConstent.TERMINAL_CHNL_PHONE.getCode();
    //商户签名数据
    private String signData;
    //交易类型
    private String tradeType;
    //商品描述
    private String body;
    //订单标题
    private String subject;
    //指定支付方式
    private String limitPay;
    //二维码有效时间
    private String qrValidTime;
}
