package com.gwi.hns.demo.pay.wechat.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.gwi.hns.demo.pay.wechat.resp.GenerateWechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.resp.QueryOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.RefundOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.WechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.service.WechatPayService;
import com.gwi.hns.demo.pay.wechat.vo.QueryOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.RefundOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.WechatPayParamsVo;
import com.gwi.hns.demo.vo.ResponseEntity;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

@RestController
@RequestMapping("/test/wechatpay")
public class WechatPayController {
    @Autowired
    WechatPayService service;
    /**
     * 被动扫码
     */
    @PostMapping
    public void generateWechatPayCode(@RequestBody WechatPayParamsVo params, HttpServletResponse response) {
        GenerateWechatPayCodeResp resp = service.generateWechatPayCode(params);
        QrConfig config = new QrConfig();
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        if (resp.getBody() != null && StringUtils.isNotBlank(resp.getBody().getQrCode())) {
            BufferedImage bufferedImage = QrCodeUtil.generate(resp.getBody().getQrCode(), 300, 300);
            response.setHeader("Cache-Control", "private,no-cache,no-store");
            response.setContentType("image/png"); 
            //3.2获取输出流
            ServletOutputStream outputStream;
            try {
                outputStream = response.getOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    @PostMapping("/app")
    public ResponseEntity<WechatPayCodeResp> WechatPayCode(@RequestBody WechatPayParamsVo params) {
        WechatPayCodeResp resp = service.WechatPayCode(params);
        ResponseEntity<WechatPayCodeResp> responseEntity = 
                new ResponseEntity<WechatPayCodeResp>();
        responseEntity.setData(resp);
        return responseEntity;
    }
    
    /**
     * 查询交易与订单
     * @param params
     * @return
     */
    @PostMapping("/query_order")
    public ResponseEntity<QueryOrderResp> queryOrder(@RequestBody QueryOrderParamsVo params) {
        QueryOrderResp resp = service.queryOrder(params);
        ResponseEntity<QueryOrderResp> responseEntity = 
                new ResponseEntity<QueryOrderResp>();
        responseEntity.setData(resp);
        return responseEntity;
    }
    
    /**
     * 退款
     * @param params
     * @return
     */
    @PostMapping("/refund_order")
    public ResponseEntity<RefundOrderResp> refundOrder(@RequestBody RefundOrderParamsVo params) {
        RefundOrderResp resp = service.refundOrder(params);
        ResponseEntity<RefundOrderResp> responseEntity = 
                new ResponseEntity<RefundOrderResp>();
        responseEntity.setData(resp);
        return responseEntity;
    }
}
