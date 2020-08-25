package com.gwi.hns.demo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

public class ConvertData {

    private final static String EQUAL_SIGN = "=";
    private final static String AND_SIGN = "&";

    /**
     * 组装验签的明文（按自然树进行组装）,用于签名
     * 
     * @param treeMap
     * @return
     */
    public static String convertToSign(TreeMap<String, Object> treeMap) {

        StringBuilder plainTextBuffer = new StringBuilder();
        for (String key : treeMap.keySet()) {
            if ("signature".equals(key)) {
                // 签名域本身不是明文内容之一，所以要过滤掉
                continue;
            }
            String value = treeMap.get(key).toString();
            if (!isEmpty(value)) {
                plainTextBuffer.append(key);
                plainTextBuffer.append(EQUAL_SIGN);
                try {
                    plainTextBuffer.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                plainTextBuffer.append(AND_SIGN);
            }
        }

        if (plainTextBuffer.length() < 1)
            return plainTextBuffer.toString();

        return plainTextBuffer.substring(0, plainTextBuffer.length() - 1);
    }

    /**
     * 组装验签的明文（按自然树进行组装）,用于验签
     * 
     * @param treeMap
     * @return
     */
    public static String convertToVerify(TreeMap<String, String> treeMap) {

        StringBuilder plainTextBuffer = new StringBuilder();
        for (String key : treeMap.keySet()) {
            if ("signature".equals(key)) {
                // 签名域本身不是明文内容之一，所以要过滤掉
                continue;
            }
            String value = treeMap.get(key);
            if (value != null) {
                plainTextBuffer.append(key);
                plainTextBuffer.append(EQUAL_SIGN);
                try {
                    plainTextBuffer.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                plainTextBuffer.append(AND_SIGN);
            }
        }

        if (plainTextBuffer.length() < 1)
            return plainTextBuffer.toString();

        return plainTextBuffer.substring(0, plainTextBuffer.length() - 1);
    }

    /**
     * 转换为接口对应的应答报文（按接口文档进行组装）
     * 
     * @param map
     * @return
     */
    public static String convertToMessage(Map<String, String> map) {

        StringBuilder tempMessage = new StringBuilder();
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (!isEmpty(value)) {
                tempMessage.append(key);
                tempMessage.append(EQUAL_SIGN);
                try {
                    tempMessage.append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                tempMessage.append(AND_SIGN);
            }
        }

        if (tempMessage.length() < 1)
            return tempMessage.toString();

        return tempMessage.substring(0, tempMessage.length() - 1);
    }

    /**
     * 将key=value&key=value的字符串转成map
     * 
     * @param message
     * @return
     */
    public static Map<String, String> convertStringToMap(String message) {
        Map<String, String> map = new TreeMap<String, String>();
        boolean hasError = false;
        String[] ss = message.split("&");
        for (String s : ss) {
            if (s == null || s.isEmpty()) {
                continue;
            }
            String[] kv = s.split("=");
            if (kv.length == 2) {
                try {
                    map.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            } else {
                hasError = true;
            }
        }
        if (hasError) {
            System.out.println("解析UPMP报文可能出现异常。报文是【{" + message + "}】");
        }
        return map;
    }

    /**
     * 判断字符串是否为空。会自动trim。
     * 
     * @param str
     * @return
     */
    private static boolean isEmpty(String str) {
        return (str == null || str.trim().isEmpty());
    }
}
