package com.gwi.hns.demo.service;

import java.util.List;

import com.gwi.hns.demo.entity.SysPermEntity;
import com.gwi.hns.demo.vo.PermVo;
import com.gwi.hns.demo.vo.ResponseEntity;

public interface SysPermService {

	ResponseEntity<SysPermEntity> getPerm(String permId);

	ResponseEntity<String> createPerm(SysPermEntity perm);

	ResponseEntity<List<SysPermEntity>> getPermList(PermVo vo);

	ResponseEntity<Object> updatePerm(SysPermEntity perm);

	ResponseEntity<Object> updatePermStatus(String permId);

}
