package com.sika.code.demo.interfaces.common.migrate.executor;

import cn.hutool.core.util.BooleanUtil;
import com.dtflys.forest.Forest;
import com.google.common.collect.Maps;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.migrate.constant.MigrateTypeEnum;
import com.sika.code.migrate.executor.MigrateResultExecutor;
import com.sika.code.migrate.pojo.MigrateResultDTO;
import com.sika.code.migrate.pojo.MigrateRuleResponse;
import com.sika.code.migrate.pojo.MigrateRuleResult;
import com.sika.code.migrate.util.SpringMVCUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <pre>
 *  迁移结果规则实现类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/21 13:09
 */
@Slf4j
@Component
public class MigrateResultExecutorImpl implements MigrateResultExecutor {
    @Override
    public void execute(MigrateRuleResult ruleResult) {
        MigrateRuleResponse ruleResponse = (MigrateRuleResponse) SpringMVCUtil.getRequest().getAttribute("migrateRuleResponse");
        if (needWriteResult(ruleResponse)) {
            log.info("获取{}", JSONUtil.toJSONString(ruleResult.getResult()));
            // 异步发送结果给指定服务
            MigrateResultDTO resultDTO = new MigrateResultDTO(ruleResult.getResult(), getRspHeads(), ruleResponse.getRequestId());
            Forest.post("http://localhost:8082/auth/response").async().contentTypeJson().addBody(resultDTO).execute();
        }
    }

    protected boolean needWriteResult(MigrateRuleResponse ruleResponse) {
        if (ruleResponse == null) {
            return false;
        }
        if (BooleanUtil.isFalse(ruleResponse.getMatch())) {
            return false;
        }
        if (!MigrateTypeEnum.isContrast(ruleResponse.getMigrateType())) {
            return false;
        }
        return true;
    }

    protected Map<String, String> getRspHeads() {
        HttpServletResponse response = SpringMVCUtil.getResponse();
        Map<String, String> heads = Maps.newHashMap();
        for (String headerName : response.getHeaderNames()) {
            heads.put(headerName, response.getHeader(headerName));
        }
        return heads;
    }


}
