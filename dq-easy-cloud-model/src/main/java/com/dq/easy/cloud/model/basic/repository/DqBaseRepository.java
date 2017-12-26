package com.dq.easy.cloud.model.basic.repository;


import org.springframework.stereotype.Repository;
import com.dq.easy.cloud.model.jdbc.handler.DqJdbcTemplateHandler;

/**
 * 
 * @ClassName : DqBaseRepository 
 * @Description : 所有dao的基础层
 * @author daiqi
 * @date 2017年12月6日 下午4:41:16 
 *
 */
@Repository
public class DqBaseRepository {
	
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
	 *		return dqBaseRepository.save(userEntity);
	 *	}
     * </pre>
     *
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:41:26
     */
    public <T> T save(final T obj) { 
    	return DqJdbcTemplateHandler.save(obj);
    } 
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
	public <T> T saveOrUpdate(final T obj){
    	return DqJdbcTemplateHandler.saveOrUpdate(obj);
    }
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
    public static <T> T findOne(Class<T> clazz, Long id){
    	return DqJdbcTemplateHandler.findOne(clazz, id);
    }
	
}
