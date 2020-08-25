package com.gwi.hns.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gwi.hns.demo.entity.SysPermEntity;
import com.gwi.hns.demo.vo.PermVo;

@Repository
public interface SysPermMapper {

	SysPermEntity getPerm(String permId);

	SysPermEntity getPermByCode(String code);

	void createPerm(SysPermEntity perm);

	List<SysPermEntity> getPermList(PermVo vo);

	void updatePerm(SysPermEntity perm);
	
	List<SysPermEntity> getPermByIds(List<String> permIds);
}
