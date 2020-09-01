package com.sika.code.standard.fill.handler;

import cn.hutool.core.util.StrUtil;
import com.sika.code.database.common.handler.DefaultMetaObjectHandler;
import com.sika.code.standard.auth.util.AuthUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 标准填充Object助手类
 * 用来填充默认值
 *
 * @author daiqi
 * @create 2019-05-10 21:43
 */
@Slf4j
@Data
@Accessors(chain = true)
public class StandardMetaObjectHandler extends DefaultMetaObjectHandler {
    /**
     * 操作者的名称
     */
    private static final String OPERATOR_NAME = "operator";
    private String jwtSecret;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Serializable getCreateBy() {
        return getOperator();
    }

    @Override
    public Serializable getUpdateBy() {
        return getOperator();
    }

    /**
     * 获取操作者
     */
    protected String getOperator() {
        String operator = httpServletRequest.getHeader(OPERATOR_NAME);
        if (StrUtil.isNotBlank(operator)) {
            return operator;
        }
        String token = AuthUtil.getAuthToken(httpServletRequest);
        if (StrUtil.isNotBlank(token) && StrUtil.isNotBlank(jwtSecret)) {
            return AuthUtil.getUsernameFromRequest(jwtSecret);
        }
        return null;
    }
}
