package com.gwi.hns.demo.vo;


import java.util.Set;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.entity.SysUserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBaseInfo extends SysUserEntity{

	private static final long serialVersionUID = 840617554847445878L;
	
	private String ip;
	private Set<String> roleIds;
	private Set<String> permIds;
	private Set<SysMenuEntity> menuSet;
}
