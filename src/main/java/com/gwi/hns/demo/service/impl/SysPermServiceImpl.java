package com.gwi.hns.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwi.hns.demo.entity.SysPermEntity;
import com.gwi.hns.demo.mapper.SysPermMapper;
import com.gwi.hns.demo.service.SysPermService;
import com.gwi.hns.demo.vo.PermVo;
import com.gwi.hns.demo.vo.ResponseEntity;

@Service
@Transactional
public class SysPermServiceImpl implements SysPermService {
	@Autowired
	private SysPermMapper permMapper;

	@Override
	public ResponseEntity<SysPermEntity> getPerm(String permId) {
		SysPermEntity perm = permMapper.getPerm(permId);
		ResponseEntity<SysPermEntity> resp = new ResponseEntity<SysPermEntity>();
		resp.setData(perm);
		return resp;
	}

	@Override
	public ResponseEntity<String> createPerm(SysPermEntity perm) {
		//判断权限是否存在
		SysPermEntity permByName = permMapper.getPermByCode(perm.getCode());
		ResponseEntity<String> resp = new ResponseEntity<String>();
		if (permByName == null) {
			Date now = new Date();
			perm.setAvailable(0);
			perm.setCreateTime(now);
			perm.setUpdateTime(now);
			permMapper.createPerm(perm);
			resp.setData(perm.getId());
		}else {
			resp.setCode(510);
			resp.setErrorMsg("权限已经存在");
		}
		return resp;
	}

	@Override
	public ResponseEntity<List<SysPermEntity>> getPermList(PermVo vo) {
		List<SysPermEntity> permList = permMapper.getPermList(vo);
		ResponseEntity<List<SysPermEntity>> resp = new ResponseEntity<List<SysPermEntity>>();
		resp.setData(permList);
		return resp;
	}

	@Override
	public ResponseEntity<Object> updatePerm(SysPermEntity perm) {
		perm.setUpdateTime(new Date());
		permMapper.updatePerm(perm);
		ResponseEntity<Object> resp = new ResponseEntity<Object>();
		return resp;
	}

	@Override
	public ResponseEntity<Object> updatePermStatus(String permId) {
		SysPermEntity perm = permMapper.getPerm(permId);
		perm.setAvailable((perm.getAvailable() + 1) % 2);
		ResponseEntity<Object> resp = new ResponseEntity<Object>();
		return resp;
	}
}
