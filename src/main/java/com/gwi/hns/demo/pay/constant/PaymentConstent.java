package com.gwi.hns.demo.pay.constant;

public enum PaymentConstent {
    //网上购物
    PAY_TYPE_SHOPPING("1", "网上购物"),
    CUR_CODE_YUAN("001", "人民币"),
    TERMINAL_CHNL_PHONE("008", "手机"),
    TRADE_TYPE_WX("APP_WX", "APP支付（微信）"),
    TRADE_TYPE_AL("NATIVE_ZFB", "支付宝扫码支付"),
    IDENTITY_TYPE_IDCARD("IDCARD", "身份证");
    private String code;
    private String description;
    private PaymentConstent(String code, String description) {
        this.code = code;
        this.description = description;
    }
    private PaymentConstent() {
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
