package com.gwi.hns.demo.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuVo extends Page {
	private String name;
	private String id;
	private String parentId;
}
