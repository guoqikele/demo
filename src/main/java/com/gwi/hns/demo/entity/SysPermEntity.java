package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermEntity extends CommonEntity {

	private static final long serialVersionUID = -5783939584037764195L;
	//主键id
	private String id;
	//权限名称
	private String name;
	//权限父ID
	private String parentId;
	//权限编码
	private String code;
}
