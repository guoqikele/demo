package com.gwi.hns.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonEntity implements Serializable {
	private static final long serialVersionUID = -5210603898977271801L;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//是否可用（0：不可用  1：可用）
	private Integer available;
	//简介
	private String description;
}
