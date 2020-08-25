package com.gwi.hns.demo.pay.wechat.vo;

import com.gwi.hns.demo.pay.common.vo.BaseParamsVo;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RefundOrderParamsVo extends BaseParamsVo{
    String mRefundSeq;
    String refundAmount;
}
