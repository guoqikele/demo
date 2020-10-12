package com.gwi.hns.demo.service.impl;

import java.util.Date;
import java.util.List;

import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.mapper.SysMenuMapper;
import com.gwi.hns.demo.service.SysMenuService;
import com.gwi.hns.demo.vo.MenuVo;
import com.gwi.hns.demo.vo.ResponseEntity;

@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public ResponseEntity<String> createMenu(SysMenuEntity menu) throws CommonException {
        //判断菜单是否存在
        SysMenuEntity menuByName = menuMapper.getMenuByName(menu.getName());
        ResponseEntity<String> resp = new ResponseEntity<String>();
        if (menuByName == null) {
            Date now = new Date();
            menu.setAvailable(0);
            menu.setCreateTime(now);
            menu.setUpdateTime(now);
            menuMapper.createMenu(menu);
            resp.setData(menu.getId());
        } else {
            throw new CommonException(ResponseConstant.MENU_EXIST);
        }
        return resp;
    }

    @Override
    public ResponseEntity<List<SysMenuEntity>> getMenuList(MenuVo vo) {
        List<SysMenuEntity> userList = menuMapper.getMenuList(vo);
        ResponseEntity<List<SysMenuEntity>> resp = new ResponseEntity<List<SysMenuEntity>>();
        resp.setData(userList);
        return resp;
    }

    @Override
    public ResponseEntity<Object> updateMenu(SysMenuEntity menu) {
        menu.setUpdateTime(new Date());
        menuMapper.updateMenu(menu);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

    @Override
    public ResponseEntity<Object> updateMenuStatus(String menuId) {
        SysMenuEntity menu = menuMapper.getMenu(menuId);
        menu.setAvailable((menu.getAvailable() + 1) % 2);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

    @Override
    public ResponseEntity<SysMenuEntity> getMenu(String menuId) {
        SysMenuEntity menu = menuMapper.getMenu(menuId);
        ResponseEntity<SysMenuEntity> resp = new ResponseEntity<SysMenuEntity>();
        resp.setData(menu);
        return resp;
    }
}
