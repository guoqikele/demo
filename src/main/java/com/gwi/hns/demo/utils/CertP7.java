package com.gwi.hns.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.gwi.hns.demo.pay.util.PKCS7Tool;

public class CertP7 {

    public static String sign(String data) throws Exception {
        // 获取签名证书路径
        Resource resource = new ClassPathResource("pay/95566SW060000009.pfx");
        File file = resource.getFile();
        if (file.exists()) {
            System.out.println("文件存在");
        }
        //String pathPfx = CertP7.class.getClassLoader().getResource("/pay/95566SZ800000007.pfx").getPath();
        // 创建PKCS7签名工具,签名需要证书库口令、证书口令
        PKCS7Tool pkcs = PKCS7Tool.getSigner(file.getAbsolutePath(), "1", "1");

        return pkcs.sign(data.getBytes());
    }

    public static void verify(String signature, String data) throws GeneralSecurityException, IOException {
        // 获取验签证书路径
        String pathCer = CertP7.class.getClassLoader().getResource("verify.cer").getPath();
        // 创建PKCS7验签工具
        PKCS7Tool pkcs = PKCS7Tool.getVerifier(pathCer);

        pkcs.verify(signature, data.getBytes(), null);
    }
}
