package com.gwi.hns.demo.service;

import java.util.List;
import java.util.Set;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.entity.SysUserEntity;
import com.gwi.hns.demo.exception.CommonException;
import com.gwi.hns.demo.vo.LoginVo;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.UserVo;



public interface SysUserService {

    ResponseEntity<SysUserEntity> getUser(String userId);

    ResponseEntity<String> createUser(SysUserEntity user) throws CommonException;

    ResponseEntity<List<SysUserEntity>> getUserList(UserVo vo);

    ResponseEntity<Object> updateUser(SysUserEntity user);

    ResponseEntity<Object> updateUserStatus(String userId);

    ResponseEntity<LoginVo> login(SysUserEntity user) throws CommonException;

    void logout(String sessionId);

    ResponseEntity<Integer> addPerm(String userId, String[] permIds);

    ResponseEntity<Set<SysMenuEntity>> getMenus(String sessionId);

}
