package com.gwi.hns.demo.controller;

import java.util.List;

import com.gwi.hns.demo.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwi.hns.demo.entity.SysRoleEntity;
import com.gwi.hns.demo.service.SysRoleService;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.RoleVo;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;
    /**
     * 获取角色详情
     *
     * @return
     */
    @GetMapping("/{roleId}")
    public ResponseEntity<SysRoleEntity> getrole(@PathVariable String roleId) {
        return roleService.getRole(roleId);
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createrole(@RequestBody SysRoleEntity role) throws CommonException {
        return roleService.createRole(role);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<List<SysRoleEntity>> getroleList(RoleVo vo) {
        return roleService.getRoleList(vo);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updaterole(@RequestBody SysRoleEntity role) {
        return roleService.updateRole(role);
    }

    /**
     * 更新角色状态
     *
     * @param roleId
     * @return
     */
    @PutMapping("/change/status/{roleId}")
    public ResponseEntity<Object> updateRoleStatus(@PathVariable String roleId) {
        return roleService.updateRoleStatus(roleId);
    }
}
