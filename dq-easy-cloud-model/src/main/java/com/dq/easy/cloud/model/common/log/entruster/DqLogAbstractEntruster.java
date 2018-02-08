package com.dq.easy.cloud.model.common.log.entruster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;

/**
 * 
 * <p>
 * 日志委托处理类
 * </p>
 *
 * <pre>
 *  说明：该类是一个抽象类
 *  约定：所有委托类都应该直接或者间接继承此类
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月8日 上午10:01:43
 */
public abstract class DqLogAbstractEntruster implements DqLogEntruster {
	/** 目标方法 */
	protected Method targetMethod;
	/** 目标类 */
	protected Class<?> targetClass;
	/** 目标的参数值列表 */
	protected Object[] targetArgs;
	/** 目标的参数类型列表 */
	protected Class<?> [] parameterTypes; 
	/** 日志传输对象 */
	protected DqLogDTO dqlogDTO;

	@Override
	public void handle(ProceedingJoinPoint proceedingJoinPoint) {
		// 1：数据初始化
		init(proceedingJoinPoint);
		// 2：执行日志处理
		doHandle(proceedingJoinPoint);
	}

	/** 初始化数据 */
	private void init(ProceedingJoinPoint proceedingJoinPoint) {
		// 1：基础数据初始化
		initBaseData(proceedingJoinPoint);
		// 2：日志对象初始化
		initDqLogDTOData();
		// 3：其他数据初始化
		initOtherData();
	}
	
	/** 初始化基础数据 */
	private void initBaseData(ProceedingJoinPoint proceedingJoinPoint) {
		Signature signature = proceedingJoinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		
		this.targetMethod = methodSignature.getMethod();
		this.targetArgs = proceedingJoinPoint.getArgs();
		this.parameterTypes = targetMethod.getParameterTypes();
		this.targetClass = proceedingJoinPoint.getTarget().getClass();
		
	}
	
	private void initDqLogDTOData(){
		dqlogDTO = DqLogDTO.newInstance();
		Map<String, Object> inputDatas = new HashMap<>();
		dqlogDTO.buildInputDatas(inputDatas);
	}
	/** 初始化其他数据 */
	private void initOtherData(){
		
	}

	/** 执行业务逻辑 */
	private void doHandle(ProceedingJoinPoint proceedingJoinPoint) {

	}

}
