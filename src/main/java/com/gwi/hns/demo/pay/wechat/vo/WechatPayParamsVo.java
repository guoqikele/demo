package com.gwi.hns.demo.pay.wechat.vo;

import com.gwi.hns.demo.pay.common.vo.BaseParamsVo;
import com.gwi.hns.demo.pay.constant.PaymentConstent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatPayParamsVo extends BaseParamsVo{
    //设备号
    private String deviceInfo;
    //附加数据
    private String attach;
    //终端ip
    private String spbillCreateIp;
    //证件类型
    private String identityType = PaymentConstent.IDENTITY_TYPE_IDCARD.getCode();
    //证件号
    private String identityNumber;
    //姓名
    private String identityName;
}
