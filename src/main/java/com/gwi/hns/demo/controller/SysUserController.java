package com.gwi.hns.demo.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwi.hns.demo.entity.SysMenuEntity;
import com.gwi.hns.demo.entity.SysUserEntity;
import com.gwi.hns.demo.service.SysUserService;
import com.gwi.hns.demo.utils.SessionUtils;
import com.gwi.hns.demo.vo.LoginVo;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.UserVo;


@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    /**
     * 获取用户详情
     * 
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<SysUserEntity> getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    /**
     * 创建用户
     * 
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody SysUserEntity user) {
        return userService.createUser(user);
    }

    /**
     * 分页查询
     * 
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<List<SysUserEntity>> getUserList(UserVo vo) {
        return userService.getUserList(vo);
    }

    /**
     * 更新用户
     * 
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody SysUserEntity user) {
        return userService.updateUser(user);
    }

    /**
     * 更新用户状态
     * 
     * @param user
     * @return
     */
    @PutMapping("/change/status/{userId}")
    public ResponseEntity<Object> updateUserStatus(@PathVariable String userId) {
        return userService.updateUserStatus(userId);
    }

    /**
     * 登录
     * 
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginVo> login(@RequestBody SysUserEntity user) {
        return userService.login(user);
    }

    /**
     * 登出
     * 
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sessionId = "";
        for (Cookie cookie : cookies) {
            boolean isExsit = cookie.getName().equals("sessionId");
            if (isExsit) {
                sessionId = cookie.getValue();
            }
        }
        userService.logout(sessionId);

        ResponseEntity<Object> respo = new ResponseEntity<Object>();
        return respo;
    }

    /**
     * 新增用户权限
     * 
     * @param userId
     * @param permIds
     * @return
     */
    @PostMapping("/add_perms/{userId}")
    public ResponseEntity<Integer> addPerms(@PathVariable String userId, @RequestBody String[] permIds) {
        return userService.addPerm(userId, permIds);
    }

    /**
     * 查询当前登录用户菜单
     * 
     * @return
     */
    @GetMapping("/menus")
    public ResponseEntity<Set<SysMenuEntity>> getMenus(HttpServletRequest request) {
        String sessionId = SessionUtils.getSessionId(request);
        return userService.getMenus(sessionId);
    }
}
