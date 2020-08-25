package com.gwi.hns.demo.pay.wechat.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateWechatPayCodeBody {
    private String orderSeq;
    private String qrCode;
    private String qrValidTime;
    private String tranStatus;
    private String tradeType;
    private String tranCode;
    private String tranMsg;
    private String prepayId;
    private String codeUrl;
    private String mwebUrl;
    private String wcPayDataAppId;
    private String wcPayDataPartnerId;
    private String wcPayDataPrepayId;
    private String wcPayDataTimeStamp;
    private String wcPayDataNonceStr;
    private String wcPayDataPackage;
    private String wcPayDataSignType;
    private String wcPayDataPaySign;
    private String orderUtr;
}
