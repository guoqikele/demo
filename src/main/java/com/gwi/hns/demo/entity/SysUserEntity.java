package com.gwi.hns.demo.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserEntity extends CommonEntity {

	private static final long serialVersionUID = 3938088620748456208L;
	//组件id
	private String id;
	//用户名称
	private String name;
	//密码
	private String password;
	//全名
	private String fullName;
	//邮箱
	private String mail;
	//电话
	private String  phone;
	//生日
	private Date birthday;
	//性别（0 男  1女）
	private int gender;
}
