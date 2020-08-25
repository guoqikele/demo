package com.gwi.hns.demo.pay.wechat.resp;

import com.gwi.hns.demo.pay.common.vo.Header;
import com.gwi.hns.demo.pay.wechat.vo.GenerateWechatPayCodeBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateWechatPayCodeResp {
    private Header header;
    private GenerateWechatPayCodeBody body;
}
