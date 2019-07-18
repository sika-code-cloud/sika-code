package com.sika.code.basic.constant;

/**
 * 
 * <p>
 * 	服务请求映射路径
 * </p>
 *
 * <pre>
 *  说明：所有服务请求映射的基础类，允许少量接口写在此中
 *  约定：各个子服务消费者通过继承此类来提供自己的服务请求映射，并通过子服务的ServiceReqMapping类来进行引用
 *  命名规范：通过服务名称+ServiceReqMapping后缀,如用户服务请求映射类（UserServiceReqMapping）
 *  使用示例：UserServiceReqMapping.USER_LOGIN_BY_USERNAME_PASSWORD
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午9:19:29
 */
public class BaseServiceReqMapping {

}
