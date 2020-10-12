package com.gwi.hns.demo.controller;

import java.util.List;

import com.gwi.hns.demo.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwi.hns.demo.entity.SysPermEntity;
import com.gwi.hns.demo.service.SysPermService;
import com.gwi.hns.demo.vo.ResponseEntity;
import com.gwi.hns.demo.vo.PermVo;

@RestController
@RequestMapping("sys/perm")
public class SysPermController {
    @Autowired
    private SysPermService permService;

    /**
     * 获取用户详情
     *
     * @return
     */
    @GetMapping("/{permId}")
    public ResponseEntity<SysPermEntity> getPerm(@PathVariable String permId) {
        return permService.getPerm(permId);
    }

    /**
     * 创建用户
     *
     * @param perm
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createPerm(@RequestBody SysPermEntity perm) throws CommonException {
        return permService.createPerm(perm);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<List<SysPermEntity>> getPermList(PermVo vo) {
        return permService.getPermList(vo);
    }

    /**
     * 更新用户
     *
     * @param perm
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> updatePerm(@RequestBody SysPermEntity perm) {
        return permService.updatePerm(perm);
    }

    /**
     * 更新用户状态
     *
     * @param perm
     * @return
     */
    @PutMapping("/change/status/{permId}")
    public ResponseEntity<Object> updatePermStatus(@PathVariable String permId) {
        return permService.updatePermStatus(permId);
    }
}
