package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysRoleEntity;
import com.gwi.hns.demo.vo.RoleVo;

@Repository
public interface SysRoleMapper {

	SysRoleEntity getRole(String roleId);

	SysRoleEntity getRoleByName(String name);

	void createRole(SysRoleEntity role);

	List<SysRoleEntity> getRoleList(RoleVo vo);

	void updateRole(SysRoleEntity role);
	
	List<SysRoleEntity> getRoleByIds(List<String> roleIds);
}
