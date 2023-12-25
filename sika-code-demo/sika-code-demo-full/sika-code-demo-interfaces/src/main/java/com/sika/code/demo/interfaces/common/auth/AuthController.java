package com.sika.code.demo.interfaces.common.auth;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/12 22:19
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseBizController {


    @RequestMapping(value = "list1")
    public Result list1(@RequestBody Map<String, Object> map) {
        log.info("请求参数():{}", JSONUtil.toJSONString(map));
        log.info("StpUtil.getSession():{}", JSONUtil.toJSONString(StpUtil.getSession()));
        log.info("StpUtil.getTokenSession():{}", JSONUtil.toJSONString(StpUtil.getTokenSession()));
        log.info("user-delete:{}", StpUtil.hasPermission("user-delete"));
        log.info("user-list:{}", StpUtil.hasPermission("user-list"));
        log.info("per-delete:{}", StpUtil.hasPermission("per-delete"));
        log.info("admin:{}", StpUtil.hasRole("admin"));
        log.info("super-admin:{}", StpUtil.hasRole("super-admin"));
        log.info("super-admin11:{}", StpUtil.hasRole("super-admin11"));
        log.info("test-item:{}", StpUtil.hasRole("test-item"));
        log.info("test-role:{}", StpUtil.hasRole("test-role"));
        log.info("role:{}", StpUtil.getExtra("role"));
        log.info("role:{}", StpUtil.getExtra("role"));
        log.info("age:{}", StpUtil.getExtra("age"));
        log.info("name:{}", StpUtil.getExtra("name"));
        log.info("loginId:{}", StpUtil.getExtra("loginId"));
        return success(1);
    }

    @RequestMapping(value = "loginTemp")
    public Result login(String name, String pwd, String device) {
        // 第一步：比对前端提交的账号名称、密码
        Integer id = 10001;
        if ("zhang".equals(name) && "1234563".equals(pwd)) {
            // 第二步：根据账号id，进行登录
        } else if ("lisi".equals(name) && "123456".equals(pwd)) {
            id = 10002;
        } else {
            return fail("登录失败");
        }
        StpUtil.login(id, new SaLoginModel()
                .setDevice(device)
                .setExtra("role", "admin")
                .setExtra("age", 12)
                .setExtra("name", "zhangsan"));
        UserPO userPO = new UserPO();
        userPO.setAddress("tetetet");
        StpUtil.getSession().set("user", userPO);
        StpUtil.getSession().set("name", "zhangsan");
        StpUtil.getSession().set("name", "lisi");
        UserPO userPOF = StpUtil.getSession().getModel("user", UserPO.class);
        return success(userPOF);
    }

    @RequestMapping(value = "getSessionKey")
    public Result getSession(String key) {
        return success(StpUtil.getSession().get(key));
    }

    @RequestMapping(value = "getSession")
    public Result getSession() {
        return success(StpUtil.getSession());
    }

    @RequestMapping(value = "getTokenInfo")
    public Result getToken() {
        return success(StpUtil.getTokenInfo());
    }

    @RequestMapping(value = "logout")
    public Result logout() {
        StpUtil.logoutByTokenValue(StpUtil.getTokenValue());
        StpUtil.logout();
        return success("登出成功");
    }


    @RequestMapping(value = "logoutAb")
    public Result logoutAb(Integer id) {
        StpUtil.logout(id);
        return success("登出成功");
    }

    @RequestMapping(value = "kickout")
    public Result kickout(Integer id) {
        StpUtil.kickout(id);
        return success("踢出成功");
    }

    @RequestMapping(value = "disable")
    public Result disable(Integer id) {
        StpUtil.disable(id, 86400);
        log.info("disable后是否被封禁【{}】", StpUtil.isDisable(id));
        log.info("封禁的时间【{}】", StpUtil.getDisableTime(id));
        StpUtil.untieDisable(id);
        log.info("untieDisable后是否被封禁【{}】", StpUtil.isDisable(id));
        return success("封禁成功");
    }

    @RequestMapping(value = "disableAndKickOut")
    public Result disableAndKickOut(Integer id) {
        // 先踢下线
        StpUtil.kickout(id);
        // 再封禁账号
        StpUtil.disable(id, 86400);
        log.info("disable后是否被封禁【{}】", StpUtil.isDisable(id));
        return success("封禁成功");
    }

    @RequestMapping(value = "getPermission")
    public Result getPermission() {
        return success(StpUtil.getPermissionList());
    }

    @RequestMapping(value = "getRoleList")
    public Result getRoleList() {
        return success(StpUtil.getRoleList());
    }
}
