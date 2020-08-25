package com.gwi.hns.demo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVo {
	private String uerId;
	private String name;
	private String token;
	private String sessionId;
}
