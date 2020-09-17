package com.gwi.hns.demo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.vo.ResponseEntity;

//@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResponseEntity<Object> rep = new ResponseEntity<Object>();
        rep.setCode(ResponseConstant.SYSTEM_ERROR.getCode());
        rep.setErrorMsg(e.getCause().toString());
        return rep;
    }

}
