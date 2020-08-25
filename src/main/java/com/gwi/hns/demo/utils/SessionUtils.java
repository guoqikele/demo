package com.gwi.hns.demo.utils;

import javax.servlet.http.HttpServletRequest;

import com.gwi.hns.demo.constant.SystemConstant;

public class SessionUtils {
	
	public static String getSessionId (HttpServletRequest request) {
		return request.getHeader(SystemConstant.SESSION_ID.getAttribute()).toString();
	}
}
