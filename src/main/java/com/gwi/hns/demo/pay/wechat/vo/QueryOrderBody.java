package com.gwi.hns.demo.pay.wechat.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryOrderBody {
    List<OrderTrans> orderTrans;
    String qrOvertimeFlag;
}
