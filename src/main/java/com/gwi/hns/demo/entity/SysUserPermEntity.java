package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserPermEntity extends CommonEntity {

	private static final long serialVersionUID = 1678614858678809045L;
	//用户ID
	private String userId;
	//权限ID
	private String permId;
}
