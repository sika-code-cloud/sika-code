package com.sika.code.migrate.rule;


import com.sika.code.migrate.pojo.MigrateRuleRequest;
import com.sika.code.migrate.pojo.MigrateRuleResponse;

/**
 * <pre>
 *  迁移规则
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/20 8:41
 */
public interface MigrateRequestRule {
    /**
     * <p>
     * 规则匹配
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     request ： HttpServletRequest : 请求对象 ： 必须
     *     response ： HttpServletResponse : 响应对象 ： 必须
     *     以下参数都是从当前request提取出来的
     *     requestFullPath ： http://xxx.eee/test/find : 请求完整路径 ： 必须
     *     requestUri ： test/find : 请求的uri ： 必须
     *     requestBody ： {} : 请求体参数 ： 若没有则为集合
     *     requestQueryParam ： {} : 请求查询参数 ： 若没有则为空集合
     *     requestHeadParam ： {} : 请求头参数 ： 若没有则为空集合
     * </pre>
     *
     * @param ruleRequest
     * @return com.sika.code.demo.interfaces.common.migrate.pojo.MigrateRuleResponse
     * @author sikadai
     * @since 2022/8/20 9:01
     */
    MigrateRuleResponse match(MigrateRuleRequest ruleRequest);
}
