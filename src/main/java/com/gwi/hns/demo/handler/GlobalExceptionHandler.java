package com.gwi.hns.demo.handler;

import javax.servlet.http.HttpServletRequest;

import com.gwi.hns.demo.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.vo.ResponseEntity;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, CommonException e) throws Exception {
        ResponseEntity<Object> rep = new ResponseEntity<Object>();
        rep.setCode(e.getErrorCode());
        rep.setErrorMsg(e.getCause().toString());
        return rep;
    }

}
