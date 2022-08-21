package com.sika.code.migrate.executor;

import com.sika.code.migrate.pojo.MigrateRuleResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 *  迁移请求执行器
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/21 12:54
 */
public interface MigrateRequestExecutor {
    /**
     * <p>
     * 根据规则响应-执行
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     * @author sikadai
     * @since 2022/8/21 12:57
     * @param request
     * @param response
     * @return void
     */
     boolean execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
