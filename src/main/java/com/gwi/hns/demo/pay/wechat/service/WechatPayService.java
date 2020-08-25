package com.gwi.hns.demo.pay.wechat.service;

import com.gwi.hns.demo.pay.wechat.resp.GenerateWechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.resp.QueryOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.RefundOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.WechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.vo.QueryOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.RefundOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.WechatPayParamsVo;

public interface WechatPayService {

    GenerateWechatPayCodeResp generateWechatPayCode(WechatPayParamsVo params);

    WechatPayCodeResp WechatPayCode(WechatPayParamsVo params);

    QueryOrderResp queryOrder(QueryOrderParamsVo params);

    RefundOrderResp refundOrder(RefundOrderParamsVo params);

}
