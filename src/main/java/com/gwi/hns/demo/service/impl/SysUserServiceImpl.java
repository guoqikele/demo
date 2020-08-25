package com.gwi.hns.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwi.hns.demo.constant.ResponseConstant;
import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.entity.SysPermEntity;
import com.gwi.hns.demo.entity.SysRolePermEntity;
import com.gwi.hns.demo.entity.SysUserEntity;
import com.gwi.hns.demo.entity.SysUserPermEntity;
import com.gwi.hns.demo.entity.SysUserRoleEntity;
import com.gwi.hns.demo.mapper.SysMenuMapper;
import com.gwi.hns.demo.mapper.SysPermMapper;
import com.gwi.hns.demo.mapper.SysRolePermMapper;
import com.gwi.hns.demo.mapper.SysUserMapper;
import com.gwi.hns.demo.mapper.SysUserPermMapper;
import com.gwi.hns.demo.mapper.SysUserRoleMapper;
import com.gwi.hns.demo.service.SysUserService;
import com.gwi.hns.demo.utils.TokenUtil;
import com.gwi.hns.demo.vo.LoginVo;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.Session;
import com.gwi.hns.demo.vo.UserBaseInfo;
import com.gwi.hns.demo.vo.UserVo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysUserPermMapper userPermMapper;
    @Autowired
    private SysRolePermMapper rolePermMapper;
    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysPermMapper permMapper;
    @Autowired
    private RedisTemplate redisCacheTemplate;

    /**
     * 查询用户详情
     */
    @Override
    public ResponseEntity<SysUserEntity> getUser(String userId) {
        SysUserEntity user = userMapper.getUser(userId);
        ResponseEntity<SysUserEntity> resp = new ResponseEntity<SysUserEntity>();
        resp.setData(user);
        resp.successResp();
        return resp;
    }

    /**
     * 新增用户
     */
    @Override
    public ResponseEntity<String> createUser(SysUserEntity user) {
        // 判断用户是否存在
        SysUserEntity userByName = userMapper.getUserByName(user.getName());
        ResponseEntity<String> resp = new ResponseEntity<String>();
        if (userByName == null) {
            Date now = new Date();
            user.setAvailable(0);
            user.setCreateTime(now);
            user.setUpdateTime(now);
            userMapper.createUser(user);
            resp.setData(user.getId());
            resp.successResp();
        } else {
            resp.failResp(ResponseConstant.USER_EXIST);
        }
        return resp;
    }

    /**
     * 获取用户列表
     */
    @Override
    public ResponseEntity<List<SysUserEntity>> getUserList(UserVo vo) {
        List<SysUserEntity> userList = userMapper.getUserList(vo);
        ResponseEntity<List<SysUserEntity>> resp = new ResponseEntity<List<SysUserEntity>>();
        resp.setData(userList);
        resp.successResp();
        return resp;
    }

    /**
     * 更新用户
     */
    @Override
    public ResponseEntity<Object> updateUser(SysUserEntity user) {
        user.setUpdateTime(new Date());
        userMapper.updateUser(user);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

    /**
     * 更新用户状态
     */
    @Override
    public ResponseEntity<Object> updateUserStatus(String userId) {
        SysUserEntity user = userMapper.getUser(userId);
        user.setAvailable((user.getAvailable() + 1) % 2);
        ResponseEntity<Object> resp = new ResponseEntity<Object>();
        return resp;
    }

    @Override
    public ResponseEntity<LoginVo> login(SysUserEntity user) {
        // 验证用户名密码是否正确
        SysUserEntity loginUser = authLogin(user);
        ResponseEntity<LoginVo> resp = new ResponseEntity<LoginVo>();
        if (loginUser == null) {
            resp.failResp(ResponseConstant.LOGING_FAILD);
            return resp;
        }

        String userId = loginUser.getId();
        // 查询用户角色
        List<SysUserRoleEntity> userRoles = userRoleMapper.getUserRoleByUserId(userId);
        Set<String> roleSet = new HashSet<String>();

        if (userRoles != null && userRoles.size() > 0) {
            roleSet.addAll(userRoles.stream().map(SysUserRoleEntity::getRoleId).collect(Collectors.toSet()));
        }
        // 查询用户权限
        List<SysUserPermEntity> userPerms = userPermMapper.getUserPermByUserId(userId);
        Set<String> permSet = new HashSet<String>();
        if (userPerms != null && userPerms.size() > 0) {
            permSet.addAll(userPerms.stream().map(SysUserPermEntity::getPermId).collect(Collectors.toSet()));
        }
        if (roleSet != null && roleSet.size() > 0) {
            for (String roleId : roleSet) {
                List<SysRolePermEntity> rolePerms = rolePermMapper.getRolePermByRoleId(roleId);
                if (rolePerms != null && rolePerms.size() > 0) {
                    permSet.addAll(
                            rolePerms.stream().map(SysRolePermEntity::getPermId).collect(Collectors.toSet()));
                }
            }
        }
        List<SysPermEntity> permEntityList = permMapper.getPermByIds(new ArrayList<String>(permSet));
        Set<String> permIds = permEntityList.stream().map(SysPermEntity::getCode).collect(Collectors.toSet());
        UserBaseInfo userInfo = new UserBaseInfo();
        // 复制属性
        BeanUtil.copyProperties(loginUser, userInfo);
        userInfo.setRoleIds(roleSet);
        userInfo.setPermIds(permSet);
        // 获取用户菜单
        List<SysMenuEntity> menus = menuMapper.getMenuByPermCodes(new ArrayList<String>(permIds));
        if (menus != null && menus.size() > 0) {
            userInfo.setMenuSet(new HashSet<SysMenuEntity>(menus));
        }
        Session session = new Session();
        session.setUserInfo(userInfo);
        String token = TokenUtil.sign(loginUser);
        session.setToken(token);
        String sessionId = IdUtil.randomUUID();
        // 将数据保存到Redis上面
        redisCacheTemplate.opsForValue().set(sessionId, session, 30, TimeUnit.MINUTES);
        LoginVo vo = new LoginVo();
        BeanUtil.copyProperties(loginUser, vo);
        vo.setToken(token);
        vo.setSessionId(sessionId);
        resp.setData(vo);
        resp.successResp();
        return resp;
    }

    /**
     * 密码验证
     * 
     * @param user
     * @return
     */
    private SysUserEntity authLogin(SysUserEntity user) {
        SysUserEntity userEntity = userMapper.getUserByName(user.getName());
        if (userEntity != null && user.getPassword().equals(userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

    @Override
    public void logout(String sessionId) {
        // 删除缓存
        redisCacheTemplate.opsForValue().set(sessionId, null, 0, TimeUnit.MINUTES);
    }

    @Override
    public ResponseEntity<Integer> addPerm(String userId, String[] permIds) {
        userPermMapper.deleteByUserId(userId);
        List<SysUserPermEntity> userPermEntityList = new ArrayList<SysUserPermEntity>();
        Date createTime = new Date();
        Date updateTime = createTime;
        if (permIds != null && permIds.length > 0) {
            for (String permId : permIds) {
                SysUserPermEntity sysUserPermEntity = new SysUserPermEntity();
                sysUserPermEntity.setUserId(userId);
                sysUserPermEntity.setPermId(permId);
                sysUserPermEntity.setCreateTime(createTime);
                sysUserPermEntity.setUpdateTime(updateTime);
                userPermEntityList.add(sysUserPermEntity);
            }
        }
        Integer count = userPermMapper.batchInsertUserPerm(userPermEntityList);
        // 更新session
        // redisCacheTemplate.opsForValue().get(key)
        ResponseEntity<Integer> rep = new ResponseEntity<Integer>();
        rep.successResp();
        rep.setData(count);
        return rep;
    }

    @Override
    public ResponseEntity<Set<SysMenuEntity>> getMenus(String sessionId) {
        Session session = (Session) redisCacheTemplate.opsForValue().get(sessionId);
        ResponseEntity<Set<SysMenuEntity>> rep = new ResponseEntity<Set<SysMenuEntity>>();
        if (session != null && session.getUserInfo() != null) {
            Set<SysMenuEntity> menuSet = session.getUserInfo().getMenuSet();
            rep.setData(menuSet);
        }
        rep.successResp();
        return rep;
    }
}
