package com.gwi.hns.demo.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session implements Serializable{
	private static final long serialVersionUID = -7259451745435771142L;
	private UserBaseInfo userInfo;
	private String token;
	private String freshToken;
}
