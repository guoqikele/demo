package com.gwi.hns.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.service.SysMenuService;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.MenuVo;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单详情
     * 
     * @return
     */
    @GetMapping("/{menuId}")
    public ResponseEntity<SysMenuEntity> getMenu(@PathVariable String menuId) {
        return menuService.getMenu(menuId);
    }

    /**
     * 创建菜单
     * 
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody SysMenuEntity menu) {
        return menuService.createMenu(menu);
    }

    /**
     * 分页查询
     * 
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<List<SysMenuEntity>> getMenuList(MenuVo vo) {
        return menuService.getMenuList(vo);
    }

    /**
     * 更新菜单
     * 
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updateMenu(@RequestBody SysMenuEntity menu) {
        return menuService.updateMenu(menu);
    }

    /**
     * 更新菜单状态
     * 
     * @param menu
     * @return
     */
    @PutMapping("/change/status/{menuId}")
    public ResponseEntity<Object> updateMenuStatus(@PathVariable String menuId) {
        return menuService.updateMenuStatus(menuId);
    }
}
