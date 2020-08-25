package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserRoleEntity extends CommonEntity {

	private static final long serialVersionUID = -2276599312894313157L;
	//用户ID
	private String userId;
	//角色ID
	private String roleId;
}
