package com.gwi.hns.demo.pay.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.gwi.hns.demo.pay.common.vo.BaseParamsVo;

public class ParamsUtil {
    public static<T> void appendToMap(Map<String, Object> map, T t) {
        @SuppressWarnings("unchecked")
        Class<? extends BaseParamsVo> clas = (Class<? extends BaseParamsVo>) t.getClass();
        try {
            Method[] methods = clas.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String name = method.getName().replaceFirst("get", "");
                    Object invoke = method.invoke(t);
                    if (invoke != null && !"Class".equals(name)) {
                        name = (name.charAt(0) + "").toLowerCase() + name.substring(1, name.length());
                        map.put(name, invoke);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
