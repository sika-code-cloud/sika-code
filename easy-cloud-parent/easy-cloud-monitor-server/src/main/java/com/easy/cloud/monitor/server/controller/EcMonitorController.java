package com.easy.cloud.monitor.server.controller;

import com.easy.cloud.core.authority.constant.EcAuthorityConstant;
import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import de.codecentric.boot.admin.config.AdminServerProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daiqi
 * @create 2018-07-24 19:47
 */
@Controller
@RequestMapping(value = "monitor")
public class EcMonitorController extends EcBaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AdminServerProperties adminServerProperties;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        request.setAttribute(EcAuthorityConstant.USERNAME, username);
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUsername(username);
        sysUserDTO.setPassword(password);
        sysUserService.login(request, sysUserDTO);
        return "redirect:" + adminServerProperties.getContextPath();
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
