package com.zyd.blog.controller;

import com.zyd.blog.business.dto.SysUserDTO;
import com.zyd.blog.business.service.SysUserService;
import com.zyd.blog.framework.object.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块
 *
 * @author daiqi
 * @create 2020-08-25 1:06
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 注册
     */
    @PostMapping(value = "register")
    public ResponseVO register(@RequestBody SysUserDTO user) {
        return ResponseVO.newSuccess();
    }

}
