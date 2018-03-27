package com.dq.easy.cloud.module.basic.constant;

/**
 * 
 * <p>
 * 基础服务名称类
 * </p>
 *
 * <pre>
 *  说明：所有服务名称最终都必须继承此类
 *  约定：各个子服务消费者通过继承此类来提供自己的服务请求映射，并通过子服务的ServiceName类来进行引用
 *  命名规范：通过服务名称+ServiceName后缀,如用户服务请求映射类（UserServiceName）
 *  使用示例：UserServiceName.SERVICE_NAME_ATOM_USER
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月6日 上午9:45:23
 */
public class DqBaseServiceName {
	/** 服务名称---用户原子服务---EASY-CLOUD-SERVICE-ATOM-USER */
	public static final String SERVICE_NAME_ATOM_USER = "EASY-CLOUD-SERVICE-ATOM-USER";
	/** 服务名称---电影原子服务---EASY-CLOUD-SERVICE-ATOM-MOVIE */
	public static final String SERVICE_NAME_ATOM_MOVIE = "EASY-CLOUD-SERVICE-ATOM-MOVIE";
}
