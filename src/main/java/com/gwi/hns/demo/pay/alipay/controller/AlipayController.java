package com.gwi.hns.demo.pay.alipay.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.gwi.hns.demo.pay.alipay.entity.AliPayParamsVo;
import com.gwi.hns.demo.pay.alipay.service.AliPayService;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

@RestController
@RequestMapping("/test/alipay")
public class AlipayController {
    @Autowired
    AliPayService service;
    /**
     * 生成二维码扫码支付（支付宝）
     */
    @PostMapping
    public void generateAlipayCode(@RequestBody AliPayParamsVo params, HttpServletResponse response) {
        QrConfig config = new QrConfig();
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        config.setCharset(Charset.forName("uft-8"));
        BufferedImage bufferedImage = QrCodeUtil.generate("www.baidu.com", 300, 300);
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
