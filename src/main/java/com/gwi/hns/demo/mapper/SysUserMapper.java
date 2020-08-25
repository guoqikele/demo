package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysUserEntity;
import com.gwi.hns.demo.vo.UserVo;

@Repository
public interface SysUserMapper {

	SysUserEntity getUser(String userId);

	void createUser(SysUserEntity user);

	List<SysUserEntity> getUserList(UserVo vo);

	void updateUser(SysUserEntity user);
	
	SysUserEntity getUserByName(String name);
	
	List<SysUserEntity> getUserByIds(List<String> userIds);
}
