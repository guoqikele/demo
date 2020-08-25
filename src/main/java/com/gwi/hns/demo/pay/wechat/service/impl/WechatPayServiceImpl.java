package com.gwi.hns.demo.pay.wechat.service.impl;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gwi.hns.demo.pay.common.vo.Header;
import com.gwi.hns.demo.pay.constant.UrlConstant;
import com.gwi.hns.demo.pay.util.ParamsUtil;
import com.gwi.hns.demo.pay.wechat.resp.GenerateWechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.resp.QueryOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.RefundOrderResp;
import com.gwi.hns.demo.pay.wechat.resp.WechatPayCodeResp;
import com.gwi.hns.demo.pay.wechat.service.WechatPayService;
import com.gwi.hns.demo.pay.wechat.vo.GenerateWechatPayCodeBody;
import com.gwi.hns.demo.pay.wechat.vo.OrderTrans;
import com.gwi.hns.demo.pay.wechat.vo.QueryOrderBody;
import com.gwi.hns.demo.pay.wechat.vo.QueryOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.RefundOrderParamsVo;
import com.gwi.hns.demo.pay.wechat.vo.WechatPayCodeBody;
import com.gwi.hns.demo.pay.wechat.vo.WechatPayParamsVo;
import com.gwi.hns.demo.utils.CertP7;
import com.gwi.hns.demo.utils.ConvertData;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class WechatPayServiceImpl implements WechatPayService{
    @Value("${payparams.merchantNo}")
    private String merchantNo;
    @Value("${payparams.limitPay}")
    private String limitPay;
    @Value("${payparams.qrValidTime}")
    private String qrValidTime;
    @Value("${payparams.OrderTimeoutDate}")
    private Long orderTimeoutDate;
    //@Value("${payparams.orderUrl}")
    //private String orderUrl;
    //@Value("${payparams.signData}")
    //private String signData;
    
    JsonMapper mapper = new JsonMapper();
    /**
     * 被动扫码
     */
    @Override
    public GenerateWechatPayCodeResp generateWechatPayCode(WechatPayParamsVo params) {
        UnitParam(params);
        try {
            System.out.println(mapper.writeValueAsString(params));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        TreeMap<String, Object> paramMap = new TreeMap<>();
        ParamsUtil.appendToMap(paramMap, params);
        // 返回签名结果
        String sign;
        try {
            sign = CertP7.sign(ConvertData.convertToSign(paramMap));
            System.out.println(sign);
            paramMap.put("signData", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String result= HttpUtil.post(UrlConstant.APP_PAY.getUrl(), paramMap);
        Map<String, String> map = parseXml(result);
        Header header = BeanUtil.fillBeanWithMap(map, new Header(), false);
        GenerateWechatPayCodeBody body = 
                BeanUtil.fillBeanWithMap(map, new GenerateWechatPayCodeBody(), false);
        //验证签名
        //CertP7.verify(signature, data);
        GenerateWechatPayCodeResp resp = new GenerateWechatPayCodeResp();
        resp.setHeader(header);
        resp.setBody(body);
        return resp;
    }

    /**
     * @param params
     */
    private void UnitParam(WechatPayParamsVo params) {
        if (StringUtils.isNotBlank(limitPay)) {
            params.setLimitPay(limitPay);
        }
        params.setMerchantNo(merchantNo);
        Date date = new Date();
        Long nowTime = date.getTime();
        Long OrderTimeoutDate = nowTime + orderTimeoutDate * 1000;
        SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
        params.setOrderTime(formate.format(date));
        //设置超时时间为五分钟
        params.setOrderTimeoutDate(formate.format(new Date(OrderTimeoutDate)));
        params.setOrderNo(merchantNo.substring(merchantNo.length() - 6, merchantNo.length()) + nowTime);
        //params.setOrderUrl(orderUrl);
        //params.setSignData(signData);
        params.setQrValidTime(qrValidTime);
    }

    private static Map<String, String> parseXml(String result) {
        //创建dom4j解析器对象
        SAXReader saxReader = new SAXReader();
        //通过字节流加载硬盘中的xml文件到内存
        try {
            Document xmlDocument = saxReader.read(new ByteArrayInputStream(result.getBytes()));
            //获取根节点
            Element rootElement = xmlDocument.getRootElement();
            List<Element> elements = new ArrayList<Element>();
            //获取根节点下的直接子节点的个数和名字
            Element bodyElement = rootElement.element("body");
            elements.addAll(bodyElement.elements());
            Element headElement = rootElement.element("header");
            elements.addAll(headElement.elements());
            if (elements != null && elements.size() > 0) {
                Map<String, String> map = elements.stream().filter(e->StringUtils.isNotBlank(e.getStringValue())).collect(
                        Collectors.toMap(Element::getName, Element::getStringValue)
                    );
                return map;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }

    @Override
    public WechatPayCodeResp WechatPayCode(WechatPayParamsVo params) {
        UnitParam(params);
        HashMap<String, Object> paramMap = new HashMap<>();
        ParamsUtil.appendToMap(paramMap, params);
        String result= HttpUtil.post(UrlConstant.APP_PAY.getUrl(), paramMap);
        log.info(result);
        Map<String, String> dataMap = parseXml(result);
        Header header = BeanUtil.fillBeanWithMap(dataMap, new Header(), false);
        WechatPayCodeBody body = 
                BeanUtil.fillBeanWithMap(dataMap, new WechatPayCodeBody(), false);
        WechatPayCodeResp resp = new WechatPayCodeResp();
        resp.setHeader(header);
        resp.setBody(body);
        return resp;
    }

    @Override
    public QueryOrderResp queryOrder(QueryOrderParamsVo params) {
        //params.setSignData(signData);
        params.setMerchantNo(merchantNo);
        HashMap<String, Object> paramMap = new HashMap<>();
        ParamsUtil.appendToMap(paramMap, params);
        String result= HttpUtil.post(UrlConstant.QUERY_ORDER.getUrl(), paramMap);
        log.info(result);
        QueryOrderResp resp = new QueryOrderResp();
        parseListXml(result, resp);
        return resp;
    }

    private void parseListXml(String result, QueryOrderResp resp) {
      //创建dom4j解析器对象
        SAXReader saxReader = new SAXReader();
        //通过字节流加载硬盘中的xml文件到内存
        try {
            Document xmlDocument = saxReader.read(new ByteArrayInputStream(result.getBytes()));
            //获取根节点
            Element rootElement = xmlDocument.getRootElement();
            
            //获取根节点下的直接子节点的个数和名字
            Element bodyElement = rootElement.element("body");
            List<Element> orderTransElements = bodyElement.elements("orderTrans");
            List<OrderTrans> orderTrans = new ArrayList<OrderTrans>();
            if (orderTransElements != null && orderTransElements.size() > 0) {
                for (Element element : orderTransElements) {
                    List<Element> subElement = element.elements();
                    if (subElement != null && subElement.size() > 0) {
                        Map<String, String> map = subElement.stream().filter(e->StringUtils.isNotBlank(e.getStringValue())).collect(
                                Collectors.toMap(Element::getName, Element::getStringValue)
                            );
                        OrderTrans body = BeanUtil.fillBeanWithMap(map, new OrderTrans(), false);
                        orderTrans.add(body);
                    }
                }
            }
            QueryOrderBody body = new QueryOrderBody();
            body.setOrderTrans(orderTrans);
            resp.setBody(body);
            Element headElement = rootElement.element("header");
            List<Element> elements = new ArrayList<Element>();
            elements.addAll(headElement.elements());
            if (elements != null && elements.size() > 0) {
                Map<String, String> map = elements.stream().filter(e->StringUtils.isNotBlank(e.getStringValue())).collect(
                        Collectors.toMap(Element::getName, Element::getStringValue)
                    );
                Header header = BeanUtil.fillBeanWithMap(map, new Header(), false);
                resp.setHeader(header);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RefundOrderResp refundOrder(RefundOrderParamsVo params) {
        //params.setSignData(signData);
        params.setMerchantNo(merchantNo);
        HashMap<String, Object> paramMap = new HashMap<>();
        ParamsUtil.appendToMap(paramMap, params);
        String result= HttpUtil.post(UrlConstant.QUERY_ORDER.getUrl(), paramMap);
        log.info(result);
        QueryOrderResp resp = new QueryOrderResp();
        parseListXml(result, resp);
        RefundOrderResp respp = new RefundOrderResp();
        respp.setBody(resp.getBody());
        respp.setHeader(resp.getHeader());
        return respp;
    }
}
