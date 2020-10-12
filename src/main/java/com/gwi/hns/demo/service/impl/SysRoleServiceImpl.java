package com.gwi.hns.demo.service.impl;

import java.util.Date;
import java.util.List;

import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwi.hns.demo.entity.SysRoleEntity;
import com.gwi.hns.demo.mapper.SysRoleMapper;
import com.gwi.hns.demo.service.SysRoleService;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.RoleVo;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public ResponseEntity<SysRoleEntity> getRole(String roleId) {
        SysRoleEntity role = roleMapper.getRole(roleId);
        ResponseEntity<SysRoleEntity> resp = new ResponseEntity<SysRoleEntity>();
        resp.setData(role);
        return resp;
    }

    @Override
    public ResponseEntity<String> createRole(SysRoleEntity role) throws CommonException {
        //判断用户是否存在
        SysRoleEntity menuByName = roleMapper.getRoleByName(role.getName());
        ResponseEntity<String> resp = new ResponseEntity<String>();
        if (menuByName == null) {
            Date now = new Date();
            role.setAvailable(0);
            role.setCreateTime(now);
            role.setUpdateTime(now);
            roleMapper.createRole(role);
            resp.setData(role.getId());
        } else {
            throw new CommonException(ResponseConstant.ROLE_EXIST);
        }
        return resp;
    }

    @Override
    public ResponseEntity<List<SysRoleEntity>> getRoleList(RoleVo vo) {
        List<SysRoleEntity> menuList = roleMapper.getRoleList(vo);
        ResponseEntity<List<SysRoleEntity>> resp = new ResponseEntity<List<SysRoleEntity>>();
        resp.setData(menuList);
        return resp;
    }

    @Override
    public ResponseEntity<Object> updateRole(SysRoleEntity role) {
        role.setUpdateTime(new Date());
        roleMapper.updateRole(role);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

    @Override
    public ResponseEntity<Object> updateRoleStatus(String roleId) {
        SysRoleEntity role = roleMapper.getRole(roleId);
        role.setAvailable((role.getAvailable() + 1) % 2);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

}
