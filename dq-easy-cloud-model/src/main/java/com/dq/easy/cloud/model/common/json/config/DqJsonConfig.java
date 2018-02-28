package com.dq.easy.cloud.model.common.json.config;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 
 * <p>
 * json配置类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月28日 下午3:31:35
 */
@Component
public class DqJsonConfig {
	private static Map<Class<?>, String[]>  CANT_BE_SERIALIZED_CLASS = new HashMap<>();
		
	static {
		setCantBeSerializedClass(BufferedImage.class, new String[]{"targetReturnValue"});
	}
	
	private static void setCantBeSerializedClass(Class<?> clazz, String[] value){
		CANT_BE_SERIALIZED_CLASS.put(clazz, value);
	}
	
	public static boolean isCantBeSerializedClass(Class<?> clazz){
		return CANT_BE_SERIALIZED_CLASS.get(clazz) == null ? false : true;
	}
	
	public static Map<Class<?>, String[]> getCantBeSerializedClass() {
		return CANT_BE_SERIALIZED_CLASS;
	}
	
}
