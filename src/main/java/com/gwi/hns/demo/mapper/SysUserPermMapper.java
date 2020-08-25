package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysUserPermEntity;

@Repository
public interface SysUserPermMapper {
	//通过用户id查询
	List<SysUserPermEntity> getUserPermByUserId(String userId);
	//批量新增
	int batchInsertUserPerm(List<SysUserPermEntity> list);
	//通过用户id删除关系
	void deleteByUserId(String userId);
}
