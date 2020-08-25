package com.gwi.hns.demo.pay.wechat.vo;

import com.gwi.hns.demo.pay.common.vo.BaseParamsVo;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class QueryOrderParamsVo extends BaseParamsVo {
    //订单号，多个
    private String orderNos;
    //订单号，单个
    private String orderNo;
}
