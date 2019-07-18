package com.sika.code.basic.pojo.request;

import com.sika.code.basic.pojo.bo.BaseBO;
import com.sika.code.basic.pojo.response.BaseResponseBO;
import com.sika.code.common.factory.BeanFactory;

/**
 * <p>
 * 基础请求逻辑处理接口
 * </p>
 *
 * <pre>
 *  POJO : 转化的POJO对象 可以为任意Object对象
 *  RESPONSE : 对应的响应对象
 *  所有的request类最终需要实现该接口
 *  </pre>
 *
 * @author daiqi
 * @create 2018-09-25 12:00
 */
public interface BaseRequestBO<RESPONSE extends BaseResponseBO> extends BaseBO {

    /**
     * <p>
     * 获取响应的class
     * </p>
     *
     * @return java.lang.Class<RESPONSE>
     * @author daiqi
     * @date 2019/6/18 22:40
     */
    Class<RESPONSE> responseClass();

    /**
     * <p>
     * 执行并得到响应对象
     * </p>
     *
     * @return RESPONSE
     * @author daiqi
     * @date 2019/6/10 23:31
     */
    RESPONSE execute();

    /**
     * <p>
     * 创建响应逻辑对象
     * </p>
     * @author daiqi
     * @date 2019/6/21 21:41
     * @param requestBO : 请求逻辑对象
     * @param pojoForResponse : pojo
     * @return ResponseBO
     */
    default <POJO, ResponseBO extends BaseResponseBO<POJO>> ResponseBO newResponseBO(BaseRequestBO<ResponseBO> requestBO, POJO pojoForResponse) {
        return BeanFactory.newResponseBO(requestBO, pojoForResponse);
    }


}
