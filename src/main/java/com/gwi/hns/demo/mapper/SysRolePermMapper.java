package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysRolePermEntity;

@Repository
public interface SysRolePermMapper {
	//通过用户id查询
	List<SysRolePermEntity> getRolePermByRoleId(String userId);
	//批量新增
	int batchInsertRolePerm(List<SysRolePermEntity> list);
	//通过角色id删除关系
	void deleteByRoleId(String userId);
}
