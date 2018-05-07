package com.easy.cloud.core.jdbc.base.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.impl.EcLogRepositoryProxy;
import com.easy.cloud.core.jdbc.audit.annotation.EcAuditAnnotation;
import com.easy.cloud.core.jdbc.audit.constant.EcAuditConstant.EcActionType;
import com.easy.cloud.core.jdbc.audit.constant.EcAuditConstant.EcType;

/**
 * 
 * @ClassName : DqBaseRepository 
 * @Description : 所有dao的基础层
 * @author daiqi
 * @date 2017年12月6日 下午4:41:16 
 *
 */
@EcLogAnnotation(level = EcLogLevelEnum.INFO, proxyClass = EcLogRepositoryProxy.class, type = EcLogTypeEnum.REPOSITORY)
public interface EcBaseDAO<T> {
	
	/**
     * 
     * <p>
     * 持久化对象
     * </p>
     * <p>
     * 调用该方法必须包含在事务里面，通过在方法上使用org.springframework.transaction.annotation.Transactional来添加事务
     * </p>
     * 
     * <pre>
	 *	<code>@Transactional</code>
	 *	public UserEntity saveUserEntity(UserEntity userEntity){
	 *		return baseDAO.save(userEntity);
	 *	}
     * </pre>
     *
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:41:26
     */
	@EcAuditAnnotation(actionType = EcActionType.SAVE, type = EcType.SAVE)
    public int save(final T obj);
    /**
     * 
     * <p>
     * 保存或者更新对象
     * </p>
     * <p>
     * 调用该方法必须包含在事务里面，通过在方法上使用org.springframework.transaction.annotation.Transactional来添加事务
     * </p>
     * 
     * <pre>
	 *	<code>@Transactional</code>
	 *	public UserEntity saveUserEntity(UserEntity userEntity){
	 *		return dqBaseRepository.saveOrUpdate(userEntity);
	 *	}
     * </pre>
     *
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:41:26
     */
	@EcAuditAnnotation(actionType = EcActionType.UPDATE, type = EcType.UPDATE)
	public int update(final T obj);
	 /**
     * 
     * <p>
     * 根据主键获取持久化对象,使用该方法只能获取到持久化类的对象
     * </p>
     *
     * <pre>
     * dqBaseRepository.findOne(UserEntity.class,id)
     * </pre>
     *
     * @param clazz : 持久化对象的class
     * @param id : 持久化对象的主键
     * @return clazz对应的持久化对象
     * @author daiqi
     * @date 2017年12月6日 下午5:31:34
     */
    public T findById(@Param(value = "id") Object id);
    
    /** 分页计数 */
    int listCount(@Param("maps") Map<String, Object> paramMap);
    
    /** 列表分页 */
	List<T> listPage(@Param("maps") Map<String, Object> paramMap);
	
}
