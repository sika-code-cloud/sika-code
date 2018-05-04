package com.easy.cloud.core.jdbc.base.primarykey;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 
 * @ClassName : KeyGeneratSnowflakeldWorlker 
 * @Description : 支持数据库分片的主键生成策略---SnowflakeldWorlker
 * @author daiqi
 * @date 2017年12月4日 下午12:57:51 
 *
 */
public class EcKeyGeneratSnowflakeldWorlker extends AbstractUUIDGenerator implements Configurable {
	@Autowired
	private EcSnowflakeIdWorkerBO snowflakeIdWorker;
	
	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		if(arg1 != null && arg1 instanceof EcBaseEntity){
			EcBaseEntity baseEntity = (EcBaseEntity) arg1;
			if(baseEntity.getId() != null){
				return baseEntity.getId();
			}
		}
		return snowflakeIdWorker.nextId();
	}

	@Override
	public void configure(Type arg0, Properties properties, ServiceRegistry arg2) throws MappingException {
		
	}

}
