package com.dq.easy.cloud.model.common.log.aspect;

import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLogOpen;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 日志记录切面类
 * </p>
 *
 * <pre>
 *  说明：对添加日志记录注解的类做日志记录
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月7日 下午7:06:15
 */
@Aspect
@Component
@Order(99)
public class DqLogAspect {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass()) ;
	
	@Pointcut("@within(com.dq.easy.cloud.model.common.log.annotation.DqLogOpen)")
	public void dqLogOpenPointcut() {
	}

	/**
	 * dqLog日志记录
	 * 
	 * @param joinPoint
	 */
	@Around("dqLogOpenPointcut()")
    public Object dqLogOpenAround(ProceedingJoinPoint pjp) throws Throwable {
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Object[] targetArgs = pjp.getArgs();
		Class targetClass = pjp.getTarget().getClass();
		Class[] parameterTypes = targetMethod.getParameterTypes();
		DqLogUtils.info("参数值啦啦啦", targetArgs, LOG);
		DqLogUtils.info("参数类型啦啦啦", parameterTypes, LOG);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = (HttpServletRequest) sra.getRequest();

        String url = request.getServletPath().toString();
        if(DqStringUtils.isEmpty(url)){
            url = request.getPathInfo();
        }
        if(DqStringUtils.isEmpty(url)){
            url = request.getRequestURI();
        }
        LOG.info("**********************************************  Request To Controller ("+url+") **********************************************");
        LOG.info("------------------------  Request Parameters  ------------------------");
        Object[] os = pjp.getArgs();
        Object result = null;
        try{
            for(Object o : os ){
                if(o instanceof ServletResponse){
                    continue;
                }else if(o instanceof Model){
                    LOG.info("org.springframework.ui.Model  ---------   "+DqJSONUtils.parseObject(o, String.class));
                }else if(o instanceof ServletRequest){
                	LOG.info("ServletRequest ParameterMap  ---------   "+DqJSONUtils.parseObject(((ServletRequest) o).getParameterMap(), String.class));
                }else if(!(o instanceof BindingResult)){
                	LOG.info(DqJSONUtils.parseObject(o, String.class));
                }
            }
        }catch (Error e){
        	LOG.info("Controller Log Error ---------   "+e.getMessage());
        }finally {
            result = pjp.proceed();
        }
        return result;
    }
    @AfterReturning(value="dqLogOpenPointcut()",returning="obj")
    public Object controllerLogResponse(Object obj) throws Throwable {
    	LOG.info("------------------------  Response Result  ------------------------");
    	LOG.info(DqJSONUtils.parseObject(obj, String.class));
    	LOG.info("**********************************************  Request End **********************************************");
        return obj;
    }

	/**
	 * <p>
	 * 执行日志记录的业务处理
	 * </p>
	 *
	 * @param joinPoint
	 * @return
	 * @author daiqi 创建时间 2018年2月7日 下午7:21:56
	 */
	protected Object doLogLogic(ProceedingJoinPoint joinPoint) {
		try {

			Object obj = joinPoint.proceed();
			MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
			Method method = joinPointObject.getMethod();
			Class<?> clazz = method.getClass();
			DqLogOpen dqLogOpen = clazz.getAnnotation(DqLogOpen.class);
			DqLogUtils.info("测试", dqLogOpen, LOG);
			boolean flag = method.isAnnotationPresent(DqLogOpen.class);
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

		}
	}
}
