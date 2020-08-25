package com.gwi.hns.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysMenuEntity extends CommonEntity {

	private static final long serialVersionUID = 8300262967520201260L;
	//主键id
	private String id;
	//菜单名称
	private String name;
	//菜单图片地址
	private String imgUrl;
	//菜单地址
	private String url;
	//父菜单id
	private String parentId;
	//权限ID
	private String  permId;
}
