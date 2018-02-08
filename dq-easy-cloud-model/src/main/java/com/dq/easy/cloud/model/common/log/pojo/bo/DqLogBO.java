package com.dq.easy.cloud.model.common.log.pojo.bo;

import java.util.HashMap;
import java.util.Map;
import com.dq.easy.cloud.model.common.array.DqArrayUtils;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;

/**
 * 
 * <p>
 * 日志业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：该类对日志数据传输对象进行逻辑处理
 *  约定：使用该类必须使用newInstance方法进行实例初始化。默认构造只是为了提供给json序列号
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月8日 上午10:22:06
 */
public class DqLogBO {
	private DqLogDTO dqLogDTO;
	
	private static DqLogBO newInstance(){
		return new DqLogBO();
	}
	public static DqLogBO newInstantce(DqLogDTO dqLogDTO){
		return newInstance().buildDqLogDTO(dqLogDTO);
	}
	public DqLogBO buildDqLogDTO(DqLogDTO dqLogDTO){
		this.dqLogDTO = dqLogDTO;
		return this;
	}
	
	public DqLogBO buildInputDatas(Class<?> [] parameterTypes, Object [] targetArgs){
		if (DqArrayUtils.isEmpty(parameterTypes)){
			return this;
		}
		int parameterTypesLength = parameterTypes.length;
//		获取目标参数
		Object [] targetArgsByTypes = new Object[parameterTypesLength];
		DqArrayUtils.putToTargetArray(targetArgs, targetArgsByTypes);
		Map<String, Object> inputDatas = new HashMap<>();
		for (int i = 0 ; i < parameterTypesLength ; ++ i){
			Class<?> typeClazz = parameterTypes[i];
			Object targetArg = targetArgsByTypes[i];
			inputDatas.put(typeClazz.getName(), targetArg);
		}
		
		buildInputDatas(inputDatas);
		return this;
	}
	public DqLogBO buildInputDatas(Map<String, Object> inputDatas){
		this.dqLogDTO.setInputDatas(inputDatas);
		return this;
	}
	
	public DqLogBO buildOutData(Map<String, Object> outData){
		this.dqLogDTO.setOutData(outData);
		return this;
	}
	
	public DqLogBO buildTargetMethodName(String targetMethodName){
		this.dqLogDTO.setTargetMethodName(targetMethodName);
		return this;
	}
	
	public DqLogBO buildTargetClassName(String targetClassName){
		this.dqLogDTO.setTargetClassName(targetClassName);
		return this;
	}
	
	public DqLogBO buildRequestPath(String requestPath){
		this.dqLogDTO.setRequestPath(requestPath);
		return this;
	}
	public DqLogDTO getDqLogDTO() {
		return dqLogDTO;
	}
	
}
