package com.gwi.hns.demo.controller;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.service.SysMenuService;
import com.gwi.hns.demo.vo.MenuVo;
import com.gwi.hns.demo.vo.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单详情
     * @param menuId 菜单id
     * @return 菜单信息
     */
    @GetMapping("/{menuId}")
    public ResponseEntity<SysMenuEntity> getMenu(@PathVariable String menuId) {
        return menuService.getMenu(menuId);
    }

    /**
     * 创建菜单
     * @param menu 菜单对象
     * @return 菜单id
     */
    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody SysMenuEntity menu) {
        return menuService.createMenu(menu);
    }

    /**
     * 分页查询
     * @param vo
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
     * @param menuId
     * @return
     */
    @PutMapping("/change/status/{menuId}")
    public ResponseEntity<Object> updateMenuStatus(@PathVariable String menuId) {
        return menuService.updateMenuStatus(menuId);
    }
}
