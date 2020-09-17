package com.gwi.hns.demo.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.vo.ResponseEntity;

public class SessionHandlerInterceptor implements HandlerInterceptor {
    //在目标方法执行之前运行此方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        if (StringUtils.isEmpty(loginUser)) {
            //说明用户未登陆
            ResponseEntity<String> resp = new ResponseEntity<String>();
            resp.setCode(ResponseConstant.FORBIDDEN.getCode());
            resp.setErrorMsg(ResponseConstant.FORBIDDEN.getMsg());
            JsonMapper mapper = new JsonMapper();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(mapper.writeValueAsString(resp));
            return false;
        }
        return true;
    }
}
