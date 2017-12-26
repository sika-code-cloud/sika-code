package com.dq.easy.cloud.model.jdbc.handler;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.entity.DqBaseEntity;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

@Component
public class DqJdbcTemplateHandler {
	  
    private static JdbcTemplate jdbcTemplate;  
    
    private static EntityManager entityManager;
    
    @Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		DqJdbcTemplateHandler.jdbcTemplate = jdbcTemplate;
	}
    
    @PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		DqJdbcTemplateHandler.entityManager = entityManager;
	}
    /**
     * 
     * <p>
     * 保存对象
     * </p>
     * <p>
     * 调用该方法必须包含在事务里面，通过在方法上使用org.springframework.transaction.annotation.Transactional来添加事务
     * </p>
     * 
     * <pre>
	 *	<code>@Transactional</code>
	 *	public UserEntity saveUserEntity(UserEntity userEntity){
	 *		return DqJdbcTemplateHandler.saveOrUpdate(userEntity);
	 *	}
     * </pre>
     *
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:41:26
     */
    public static <T> T save(final T obj) { 
    	return entityManager.merge(obj);
    } 
    /**
     * 
     * <p>
     * 保存或者更新对象
     * </p>
     * 
     * <p>
     * 调用该方法必须包含在事务里面，通过在方法上使用org.springframework.transaction.annotation.Transactional来添加事务
     * </p>
     *
     * <pre>
	 *	<code>@Transactional</code>
	 *	public UserEntity saveUserEntity(UserEntity userEntity){
	 *		return DqJdbcTemplateHandler.saveOrUpdate(userEntity);
	 *	}
     * </pre>
     * 
     * @param obj
     * @return
     * @author daiqi
     * @date 2017年12月6日 下午1:41:43
     */
    @SuppressWarnings("unchecked")
	public static <T> T saveOrUpdate(final T obj){
    	if(obj == null ){
    		return null;
    	}
    	
//    	传入对象的id
    	Long id = null;
    	if(obj instanceof DqBaseEntity){
    		id = ((DqBaseEntity) obj).getId();
    	}
    	
//    	保存数据库中获取到的对象
    	T tFromDataBase = null;
//    	id不为空，执行查询
    	if(DqBaseUtils.isNotNull(id)) {
    		tFromDataBase = (T) entityManager.find(obj.getClass(), id);
    	}
//    	数据库中存在对应id的数据，执行更新 否则执行保存操作
    	if(DqBaseUtils.isNotNull(tFromDataBase)){
    		return entityManager.merge(DqBaseUtils.copeFromObjToTargetObj(obj, tFromDataBase));
    	}else{
    		return entityManager.merge(obj);
    	}
    }
    /**
     * 
     * <p>
     * 根据主键获取持久化对象,使用该方法只能获取到持久化类的对象
     * </p>
     *
     * <pre>
     * DqJdbcTemplateHandler.findOne(UserEntity.class,id)
     * </pre>
     *
     * @param clazz : 持久化对象的class
     * @param id : 持久化对象的主键
     * @return clazz对应的持久化对象
     * @author daiqi
     * @date 2017年12月6日 下午5:31:34
     */
    public static <T> T findOne(Class<T> clazz, Long id){
    	return entityManager.find(clazz, id);
    }
    /**
     * 
     * <p>获取泛型列表数据，若获取数据条数为0返回size为0的空列表</p>
     *
     * <pre>
     * 该接口获取到的数据能转换成任意clazz对应类的对象列表
     * </pre>
     *
     * @param sql : String : 需要执行的sql
     * @param list : List<Object> : 参数列表
     * @param clazz : Class<T> : 泛型的class
     * @return 泛型列表
     * @author daiqi
     * @date 2017年12月6日 下午2:42:58
     */
    public static <T> List<T> findTList(String sql, List<Object> list, Class<T> clazz){
		if(DqCollectionsUtils.isEmpty(list)){
			list = new ArrayList<>();
		}
		List<T> retList = new ArrayList<>();
		
		if(DqStringUtils.isEmpty(sql)){
			return retList; 
		}
		try {
	        retList = jdbcTemplate.query(sql, list.toArray(), new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return retList;
		}
		return retList;
	}
	
    /**
     * 
     * <p>获取泛型列表数据，若获取数据条数为0返回size为0的空列表</p>
     *
     * <pre>
     * 该接口获取到的数据能转换成任意clazz对应类的对象列表
     * </pre>
     *
     * @param sql : StringBuilder : 需要执行的sql
     * @param list : List<Object> : 参数列表
     * @param clazz : Class<T> : 泛型的class
     * @return 泛型列表
     * @author daiqi
     * @date 2017年12月6日 下午2:42:58
     */
    public static <T> List<T> findTList(StringBuilder sqlBuilder, List<Object> list, Class<T> clazz){
    	if(DqBaseUtils.isNull(sqlBuilder)){
    		sqlBuilder = DqStringUtils.getDefaultStringBuilder();
    	}
    	return findTList(sqlBuilder.toString(), list, clazz);
    }
    /**
     * 
     * <p>获取泛型对象，若对象数据库不存在，返回null</p>
     *
     * <pre>
     * 该接口获取到的数据能转换成任意clazz对应类的对象
     * </pre>
     *
     * @param sql : String : 需要执行的sql
     * @param list : List<Object> : 参数列表
     * @param clazz : Class<T> : 泛型的class
     * @return 泛型对象
     * @author daiqi
     * @date 2017年12月6日 下午2:54:45
     */
    public static <T> T findTObj(String sql, List<Object> list, Class<T> clazz){
    	List<T> fromDataBaseList = findTList(sql, list, clazz);
    	if(DqCollectionsUtils.isNotEmpty(fromDataBaseList)){
    		return fromDataBaseList.get(0);
    	}
    	return null;
    }
    
    /**
     * 
     * <p>获取泛型对象，若对象数据库不存在，返回null</p>
     *
     * <pre>
     * 该接口获取到的数据能转换成任意clazz对应类的对象
     * </pre>
     *
     * @param sqlBuilder : StringBuilder : 需要执行的sql
     * @param list : List<Object> : 参数列表
     * @param clazz : Class<T> : 泛型的class
     * @return 泛型对象
     * @author daiqi
     * @date 2017年12月6日 下午2:54:45
     */
    public static <T> T findTObj(StringBuilder sqlBuilder, List<Object> list, Class<T> clazz){
    	if(DqBaseUtils.isNull(sqlBuilder)){
    		return null;
    	}
    	return findTObj(sqlBuilder.toString(), list, clazz);
    }
    /**
     * 
     * <p>根据传入的持久化类class获取持久化实体@javax.persistence.Table注解上的表名</p>
     *
     * <pre>
     * DqJdbcTemplateHandler.getEntityTableName(UserEntity.class) = test_user
     * </pre>
     *
     * @param clazz : 持久化类的Class
     * @return 持久化类 所对应的表名
     * @author daiqi
     * @date 2017年12月6日 下午5:43:34
     */
    public static <T> String getEntityTableName(Class<T> entityClazz){
    	if(DqBaseUtils.isNull(entityClazz)){
    		return DqStringUtils.EMPTY;
    	}
//    	获取Table注解
    	Table tableAnno = entityClazz.getAnnotation(Table.class);
    	if(DqBaseUtils.isNull(tableAnno) || DqStringUtils.isEmpty(tableAnno.name())){
    		return DqStringUtils.EMPTY;
    	}
    	return tableAnno.name();
    }
}
