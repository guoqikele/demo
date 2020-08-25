package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.vo.MenuVo;

@Repository
public interface SysMenuMapper {

	SysMenuEntity getMenu(String menuId);

	SysMenuEntity getMenuByName(String name);

	List<SysMenuEntity> getMenuList(MenuVo vo);

	void updateMenu(SysMenuEntity menu);

	void createMenu(SysMenuEntity menu);
	
	List<SysMenuEntity> getMenuByIds(List<String> menuIds);
	
	List<SysMenuEntity> getMenuByPermCodes(List<String> permCodes);
}
