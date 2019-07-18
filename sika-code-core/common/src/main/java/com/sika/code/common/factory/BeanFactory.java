package com.sika.code.common.factory;

import com.sika.code.basic.pojo.request.BaseRequestBO;
import com.sika.code.basic.pojo.response.BaseResponseBO;
import com.sika.code.basic.util.Assert;

/**
 * <p>
 * 对象创建工厂
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月14日 上午11:53:23
 */
public class BeanFactory {
    /**
     * <p>
     * 根据beanClazz 创建对象
     * </p>
     *
     * @param beanClazz : Class : 泛型类的class
     * @return
     * @author daiqi
     * @创建时间 2018年4月14日 上午11:54:45
     */
    public static <T> T newInstance(Class<T> beanClazz) {
        try {
            return beanClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * 创建一个新的response对象并且进行构建，该方法不使用json进行数据转换
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     request : TestRequest : 请求对象 : 是
     *     PojoForResponse : PojoForResponse : 转换为response的pojo : 否
     * </pre>
     *
     * @param requestBO
     * @param pojoForResponse
     * @return Response
     * @author daiqi
     * @date 2018/10/10 15:49
     */
    public static <POJO, Response extends BaseResponseBO<POJO>> Response newResponseBO(BaseRequestBO<Response> requestBO, POJO pojoForResponse) {
        Assert.verifyObjNull(requestBO, "request");
        Class<Response> responseClass = requestBO.responseClass();
        Assert.verifyObjNull(responseClass, "responseClass");
        Response response = newInstance(responseClass);
        response.build(pojoForResponse);
        return response;
    }

}
