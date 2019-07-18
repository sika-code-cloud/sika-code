package com.sika.code.basic.pojo.response;

import com.sika.code.basic.pojo.bo.BaseBO;

/**
 * <p>基础响应逻辑处理接口</p>
 * <pre>
 *     POJO : 需要转化为Response对象的POJO对象 可以为任意Object对象
 *     所有的response最终都需要实现该接口
 * </pre>
 * @author daiqi
 * @create 2018-09-25 12:01
 */
public interface BaseResponseBO<POJO> extends BaseBO {
    /**
     * <p>
     * 根据pojo构建response对象
     * </p>
     *
     * @param pojo
     * @return void
     * @author daiqi
     * @date 2018/10/10 17:48
     */
    void build(POJO pojo);
}
