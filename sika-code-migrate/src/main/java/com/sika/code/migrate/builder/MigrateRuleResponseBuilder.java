package com.sika.code.migrate.builder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.migrate.constant.MigrateConstant;
import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.sika.code.migrate.util.SpringMVCUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 *  规则响应构建者
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 23:16
 */
@AllArgsConstructor
@Data
public abstract class MigrateRuleResponseBuilder {

    protected MigrateRuleRequest ruleRequest;

    public MigrateRuleResponse build() {
        return new MigrateRuleResponse()
                .setMatch(false)
                .setRequestId(IdUtil.objectId())
                .setExtraParam(Maps.newLinkedHashMap());
    }

    public void reBuild(MigrateRuleResponse ruleResponse) {
        buildMigrateType(ruleResponse);
        buildUrl(ruleResponse);
        buildExtraParam(ruleResponse);

        SpringMVCUtil.getRequest().setAttribute(MigrateConstant.MIGRATE_RULE_REQUEST_KEY, ruleRequest);
        SpringMVCUtil.getRequest().setAttribute(MigrateConstant.MIGRATE_RULE_RESPONSE_KEY, ruleResponse);
    }



    protected void buildUrl(MigrateRuleResponse ruleResponse) {
        HttpServletRequest servletRequest = ruleRequest.getRequest();
        ruleResponse.setRedirectUrl(getRootUrl() + servletRequest.getRequestURI());
    }

    protected void buildExtraParam(MigrateRuleResponse ruleResponse) {
        ruleResponse.putExtraParam(MigrateConstant.FLOW_RESOURCE_KEY, getFlowResource());
        ruleResponse.putExtraParam(MigrateConstant.REQUEST_ID_KEY, ruleResponse.getRequestId());
        if (ruleResponse.getMigrateType() != null) {
            ruleResponse.putExtraParam(MigrateConstant.MIGRATE_TYPE_KEY, ruleResponse.getMigrateType());
        }
    }

    protected void buildMigrateType(MigrateRuleResponse ruleResponse) {
        String migrateTypeStr = getMigrateType();
        if (StrUtil.isNotBlank(migrateTypeStr)) {
            ruleResponse.setMigrateType(migrateTypeStr);
        }
    }

    protected abstract String getMigrateType() ;
    protected abstract String getRootUrl() ;
    protected abstract String getFlowResource() ;

}
