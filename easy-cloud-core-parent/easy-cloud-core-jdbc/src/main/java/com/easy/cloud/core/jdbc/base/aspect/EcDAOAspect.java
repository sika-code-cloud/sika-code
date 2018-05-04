package com.easy.cloud.core.jdbc.base.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

@Aspect
@Component
@EnableAsync(proxyTargetClass = true)
public class EcDAOAspect {
	@Pointcut("execution(* com.easy..dao.*.save(..))")
	public void saveAspect() {
		
	}
	@Pointcut("execution(* com.easy..dao.*.update(..))")
	public void udpateAspect() {
		
	}

	@Around(value = "saveAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		EcBaseAspectBO baseAspectBO = new EcBaseAspectBO();
		baseAspectBO.buildBaseAspectData(joinPoint);
		Object entity = baseAspectBO.getTParam(1);
		Object args [] = {entity};
		Field [] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		List<Field> fieldList = getAllFields(entity.getClass());
		for (Field f : fieldList) {
		    System.out.println("getFields---" + f.getName());
		}
		return joinPoint.proceed(args);
	}
	
	List<Field> getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>() ;
		Class<?> tempClass = clazz;
		while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
		      fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
		      tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
		}
		return fieldList;
	}

	@AfterThrowing(value = "saveAspect()", throwing = "ex")
	public void afterThrowing(Throwable ex) {
		throw new RuntimeException(ex);
	}
}
