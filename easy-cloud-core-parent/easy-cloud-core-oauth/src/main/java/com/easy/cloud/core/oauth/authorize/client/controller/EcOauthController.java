package com.easy.cloud.core.oauth.authorize.client.controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daiqi
 * @create 2018-07-18 9:11
 */
@RestController
@RequestMapping(value = "oauth/login")
public class EcOauthController extends EcBaseController{

    @RequestMapping(value = "wechat")
    public EcBaseServiceResult loginWechat() {
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @RequestMapping(value = "zfb")
    public EcBaseServiceResult loginZfb() {
        return EcBaseServiceResult.newInstanceOfSuccess();
    }
}
