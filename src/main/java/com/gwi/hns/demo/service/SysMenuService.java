package com.gwi.hns.demo.service;

import java.util.List;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.exception.CommonException;
import com.gwi.hns.demo.vo.MenuVo;
import com.gwi.hns.demo.vo.ResponseEntity;

public interface SysMenuService {

    ResponseEntity<String> createMenu(SysMenuEntity menu) throws CommonException;

    ResponseEntity<List<SysMenuEntity>> getMenuList(MenuVo vo);

    ResponseEntity<Object> updateMenu(SysMenuEntity menu);

    ResponseEntity<Object> updateMenuStatus(String menuId);

    ResponseEntity<SysMenuEntity> getMenu(String menuId);

}
