package com.gwi.hns.demo.pay.constant;

public enum UrlConstant {
    APP_PAY("https://ebspay.boc.cn/PGWPortal/B2CRecvOrder.do", "微信、支付宝或者银联支付"),
    QUERY_ORDER("https://ebspay.boc.cn/PGWPortal/QueryOrder.do", "查询订单"),
    QUERY_TRANSACTION("https://ebspay.boc.cn/PGWPortal/QueryOrderTrans.do", "查询交易"),
    REQUEST_REFUND("https://ebspay.boc.cn/PGWPortal/RefundOrder.do", "请求退款");

    private String url;
    private String description;
    public String getUrl() {
        return url;
    }
    public String getDescription() {
        return description;
    }
    private UrlConstant(String url, String description) {
        this.url = url;
        this.description = description;
    }

}
