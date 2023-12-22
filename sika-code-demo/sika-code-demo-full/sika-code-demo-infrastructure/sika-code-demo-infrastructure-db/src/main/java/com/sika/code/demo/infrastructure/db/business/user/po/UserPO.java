package com.sika.code.demo.infrastructure.db.business.user.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表 持久化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:39
 */
@Getter
@Setter
@TableName("lf_user")
public class UserPO extends BaseBizPO {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人标识
     */
    private String createBy;
    /**
     * 最后更新人标识
     */
    private String updateBy;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 授权密码
     */
    private String oauthPwd;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 性别【1：男，2：女，0：未知】
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 省份编码
     */
    private Long provinceCode;
    /**
     * token
     */
    private String token;
    /**
     * 用户类型：1：游客，2：系统用户
     */
    private Integer type;
    /**
     * 市编码
     */
    private Long cityCode;
    /**
     * 县编码
     */
    private Long countyCode;
    private String address;
    /**
     * 逻辑删除标志[1:删除,0:未删]
     */
    private Integer deleted;
}
