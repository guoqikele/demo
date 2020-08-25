package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRolePermEntity extends CommonEntity {

	private static final long serialVersionUID = 2741436093641191740L;
	//角色ID
	private String roleId;
	//权限ID
	private String permId;
}
