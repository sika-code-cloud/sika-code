package me.zhyd.justauth.custom;

import me.zhyd.oauth.config.AuthSource;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/10/26 16:54
 * @since 1.8
 */
public enum AuthCustomSource implements AuthSource {

    /**
     * 自己搭建的gitlab私服
     */
    MYGITLAB {
        /**
         * 授权的api
         *
         * @return url
         */
        @Override
        public String authorize() {
            return "http://gitlab.innodev.cn:9001/oauth/authorize";
        }

        /**
         * 获取accessToken的api
         *
         * @return url
         */
        @Override
        public String accessToken() {
            return "http://gitlab.innodev.cn:9001/oauth/token";
        }

        /**
         * 获取用户信息的api
         *
         * @return url
         */
        @Override
        public String userInfo() {
            return "http://gitlab.innodev.cn:9001/api/v4/user";
        }
    }
}
