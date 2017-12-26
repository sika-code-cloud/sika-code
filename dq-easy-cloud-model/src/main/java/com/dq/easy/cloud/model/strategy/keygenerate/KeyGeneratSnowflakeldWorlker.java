package com.dq.easy.cloud.model.strategy.keygenerate;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.dq.easy.cloud.model.basic.entity.DqBaseEntity;
import com.dq.easy.cloud.model.strategy.keygenerate.vo.SnowflakeIdWorker;

/**
 * 
 * @ClassName : KeyGeneratSnowflakeldWorlker 
 * @Description : 主键生成策略---SnowflakeldWorlker
 * @author daiqi
 * @date 2017年12月4日 下午12:57:51 
 *
 */
public class KeyGeneratSnowflakeldWorlker extends AbstractUUIDGenerator implements Configurable {
	private static SnowflakeIdWorker snowflakeIdWorker;
	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		if(arg1 != null && arg1 instanceof DqBaseEntity){
			DqBaseEntity baseEntity = (DqBaseEntity) arg1;
			if(baseEntity.getId() != null){
				return baseEntity.getId();
			}
		}
		return snowflakeIdWorker.nextId();
	}

	@Override
	public void configure(Type arg0, Properties properties, ServiceRegistry arg2) throws MappingException {
		snowflakeIdWorker = SnowflakeIdWorker.singleInstance(0, 0);
	}

}
