package com.gwi.hns.demo.service;

import java.util.List;

import com.gwi.hns.demo.entity.SysRoleEntity;
import com.gwi.hns.demo.exception.CommonException;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.RoleVo;

public interface SysRoleService {

	ResponseEntity<SysRoleEntity> getRole(String roleId);

	ResponseEntity<String> createRole(SysRoleEntity role) throws CommonException;

	ResponseEntity<List<SysRoleEntity>> getRoleList(RoleVo vo);

	ResponseEntity<Object> updateRole(SysRoleEntity role);

	ResponseEntity<Object> updateRoleStatus(String roleId);

}
