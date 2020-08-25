package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysUserRoleEntity;

@Repository
public interface SysUserRoleMapper {
	//通过用户id查询
	List<SysUserRoleEntity> getUserRoleByUserId(String userId);
	//批量新增
	int batchInsertUserRole(List<SysUserRoleEntity> list);
	//通过用户id删除关系
	void deleteByUserId(String userId);
}
