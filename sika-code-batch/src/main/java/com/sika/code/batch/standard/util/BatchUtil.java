package com.sika.code.batch.standard.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.sika.code.core.base.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *  批处理工具类
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/8/3 22:31
 */
@Slf4j
public class BatchUtil {

    /**
     * <p>
     * 校验结果
     * </p>
     *
     * @param objectMap    : 对象Map
     * @param codeName     : 编码名称
     * @param msgName      : 消息名称
     * @param successCodes : 成功的响应码
     * @return void
     * @author sikadai
     * @since 2022/8/3 22:39
     */
    public static void verifyResult(Map<String, Object> objectMap, String codeName, String msgName, Set<String> successCodes) {
        Assert.notNull(objectMap, "响应对象不能为空");
        if (CharSequenceUtil.isBlank(codeName)) {
            return;
        }
        if (CollUtil.isEmpty(successCodes)) {
            log.info("没有配置成功响应编码集合");
            return;
        }
        String code = MapUtil.getStr(objectMap, codeName);
        String msg = MapUtil.getStr(objectMap, msgName);
        if (CharSequenceUtil.isBlank(code)) {
            throw new RuntimeException(CharSequenceUtil.format("codeName【{}】对应的响应码为空", codeName));
        }
        if (!successCodes.contains(code)) {
            throw new RuntimeException(CharSequenceUtil.format("codeName【{}】对应的响应码【{}】与配置的成功编码列表【{}】不匹配，异常消息为【{}】"
                    , codeName, code, JSONUtil.toJSONString(successCodes), msg));
        }
    }
}
