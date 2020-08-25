package com.gwi.hns.demo.pay.wechat.resp;

import com.gwi.hns.demo.pay.common.vo.Header;
import com.gwi.hns.demo.pay.wechat.vo.WechatPayCodeBody;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WechatPayCodeResp {
    private Header header;
    private WechatPayCodeBody body;
}
