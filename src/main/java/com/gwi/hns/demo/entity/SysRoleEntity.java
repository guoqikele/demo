package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoleEntity extends CommonEntity {

	private static final long serialVersionUID = 9063492086854519288L;
	//组件id
	private String id;
	//角色名称
	private String name;
}
