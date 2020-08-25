package com.gwi.hns.demo.pay.wechat.resp;

import com.gwi.hns.demo.pay.common.vo.Header;
import com.gwi.hns.demo.pay.wechat.vo.QueryOrderBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryOrderResp {
    private Header header;
    private QueryOrderBody body;
}
